package com.example.arabiccrossword;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CrosswordView crosswordView;
    private TextView timerText;
    private TextView coinsText;
    private TextView progressText;
    private LinearLayout keyboardLayout;
    private Button hintButton;
    private Button checkButton;
    private Button resetButton;
    
    private CrosswordPuzzle currentPuzzle;
    private int coins = 50;
    private int seconds = 0;
    private Handler timerHandler;
    private Runnable timerRunnable;
    private boolean isTimerRunning = false;
    
    // Arabic alphabet for keyboard
    private final String[] arabicLetters = {
        "ا", "ب", "ت", "ث", "ج", "ح", "خ", "د",
        "ذ", "ر", "ز", "س", "ش", "ص", "ض", "ط",
        "ظ", "ع", "غ", "ف", "ق", "ك", "ل", "م",
        "ن", "ه", "و", "ي", "ة", "ى", "ء"
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeViews();
        setupKeyboard();
        setupPuzzle();
        setupTimer();
        setupButtons();
    }
    
    private void initializeViews() {
        crosswordView = findViewById(R.id.crosswordView);
        timerText = findViewById(R.id.timerText);
        coinsText = findViewById(R.id.coinsText);
        progressText = findViewById(R.id.progressText);
        keyboardLayout = findViewById(R.id.keyboardLayout);
        hintButton = findViewById(R.id.hintButton);
        checkButton = findViewById(R.id.checkButton);
        resetButton = findViewById(R.id.resetButton);
    }
    
    private void setupPuzzle() {
        currentPuzzle = PuzzleFactory.createSamplePuzzle();
        crosswordView.setPuzzle(currentPuzzle);
        
        crosswordView.setOnCellSelectedListener((row, col) -> {
            // Cell selected - keyboard is ready for input
        });
        
        updateProgress();
        updateCoins();
    }
    
    private void setupKeyboard() {
        keyboardLayout.removeAllViews();
        
        // Create rows of 8 buttons each
        int buttonsPerRow = 8;
        LinearLayout currentRow = null;
        
        for (int i = 0; i < arabicLetters.length; i++) {
            if (i % buttonsPerRow == 0) {
                currentRow = new LinearLayout(this);
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                currentRow.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                keyboardLayout.addView(currentRow);
            }
            
            final String letter = arabicLetters[i];
            Button button = new Button(this);
            button.setText(letter);
            button.setTextSize(18);
            
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            );
            params.setMargins(4, 4, 4, 4);
            button.setLayoutParams(params);
            
            button.setOnClickListener(v -> onLetterClick(letter));
            
            if (currentRow != null) {
                currentRow.addView(button);
            }
        }
        
        // Add delete button
        LinearLayout lastRow = new LinearLayout(this);
        lastRow.setOrientation(LinearLayout.HORIZONTAL);
        lastRow.setLayoutParams(new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        keyboardLayout.addView(lastRow);
        
        Button deleteButton = new Button(this);
        deleteButton.setText("⌫ حذف");
        deleteButton.setTextSize(18);
        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        );
        deleteParams.setMargins(4, 4, 4, 4);
        deleteButton.setLayoutParams(deleteParams);
        deleteButton.setOnClickListener(v -> onDeleteClick());
        lastRow.addView(deleteButton);
    }
    
    private void onLetterClick(String letter) {
        int row = crosswordView.getSelectedRow();
        int col = crosswordView.getSelectedCol();
        
        if (row >= 0 && col >= 0) {
            CrosswordCell cell = currentPuzzle.getCell(row, col);
            if (cell != null && !cell.isBlack()) {
                cell.setUserInput(letter.charAt(0));
                crosswordView.invalidate();
                updateProgress();
                
                if (currentPuzzle.isCompleted()) {
                    onPuzzleCompleted();
                }
            }
        } else {
            Toast.makeText(this, "اختر خانة أولاً", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void onDeleteClick() {
        int row = crosswordView.getSelectedRow();
        int col = crosswordView.getSelectedCol();
        
        if (row >= 0 && col >= 0) {
            CrosswordCell cell = currentPuzzle.getCell(row, col);
            if (cell != null && !cell.isBlack()) {
                cell.setUserInput(' ');
                crosswordView.invalidate();
                updateProgress();
            }
        }
    }
    
    private void setupButtons() {
        hintButton.setOnClickListener(v -> showHint());
        checkButton.setOnClickListener(v -> checkPuzzle());
        resetButton.setOnClickListener(v -> resetPuzzle());
    }
    
    private void showHint() {
        if (coins < 10) {
            Toast.makeText(this, "لا تملك عملات كافية!", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Find first empty or wrong cell
        for (int i = 0; i < currentPuzzle.getRows(); i++) {
            for (int j = 0; j < currentPuzzle.getCols(); j++) {
                CrosswordCell cell = currentPuzzle.getCell(i, j);
                if (cell != null && !cell.isBlack() && !cell.isCorrect()) {
                    cell.setUserInput(cell.getAnswer());
                    coins -= 10;
                    updateCoins();
                    crosswordView.invalidate();
                    updateProgress();
                    Toast.makeText(this, "تم استخدام تلميح", Toast.LENGTH_SHORT).show();
                    
                    if (currentPuzzle.isCompleted()) {
                        onPuzzleCompleted();
                    }
                    return;
                }
            }
        }
    }
    
    private void checkPuzzle() {
        int correct = currentPuzzle.getCorrectCount();
        int total = currentPuzzle.getTotalCells();
        
        if (correct == total) {
            onPuzzleCompleted();
        } else {
            Toast.makeText(this, "صحيح: " + correct + " من " + total, 
                         Toast.LENGTH_SHORT).show();
        }
    }
    
    private void resetPuzzle() {
        new AlertDialog.Builder(this)
            .setTitle("إعادة تعيين")
            .setMessage("هل تريد إعادة تعيين اللغز؟")
            .setPositiveButton("نعم", (dialog, which) -> {
                for (int i = 0; i < currentPuzzle.getRows(); i++) {
                    for (int j = 0; j < currentPuzzle.getCols(); j++) {
                        CrosswordCell cell = currentPuzzle.getCell(i, j);
                        if (cell != null && !cell.isBlack()) {
                            cell.setUserInput(' ');
                        }
                    }
                }
                crosswordView.invalidate();
                updateProgress();
                seconds = 0;
                updateTimer();
            })
            .setNegativeButton("لا", null)
            .show();
    }
    
    private void setupTimer() {
        timerHandler = new Handler();
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                if (isTimerRunning) {
                    seconds++;
                    updateTimer();
                    timerHandler.postDelayed(this, 1000);
                }
            }
        };
        startTimer();
    }
    
    private void startTimer() {
        isTimerRunning = true;
        timerHandler.post(timerRunnable);
    }
    
    private void stopTimer() {
        isTimerRunning = false;
    }
    
    private void updateTimer() {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        timerText.setText(String.format("%02d:%02d", minutes, secs));
    }
    
    private void updateCoins() {
        coinsText.setText("🪙 " + coins);
    }
    
    private void updateProgress() {
        int correct = currentPuzzle.getCorrectCount();
        int total = currentPuzzle.getTotalCells();
        progressText.setText(correct + " / " + total);
    }
    
    private void onPuzzleCompleted() {
        stopTimer();
        
        int minutes = seconds / 60;
        int secs = seconds % 60;
        
        new AlertDialog.Builder(this)
            .setTitle("مبروك! 🎉")
            .setMessage("أكملت اللغز في " + minutes + ":" + String.format("%02d", secs))
            .setPositiveButton("لغز جديد", (dialog, which) -> {
                // Load new puzzle
                setupPuzzle();
                seconds = 0;
                startTimer();
            })
            .setNegativeButton("خروج", (dialog, which) -> finish())
            .setCancelable(false)
            .show();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (!currentPuzzle.isCompleted()) {
            startTimer();
        }
    }
}
