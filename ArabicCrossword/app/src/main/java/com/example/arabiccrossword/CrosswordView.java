package com.example.arabiccrossword;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CrosswordView extends View {
    private CrosswordPuzzle puzzle;
    private Paint cellPaint;
    private Paint blackPaint;
    private Paint textPaint;
    private Paint selectedPaint;
    private Paint cluePaint;
    private Paint linePaint;
    private int cellSize;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private OnCellSelectedListener listener;
    
    public interface OnCellSelectedListener {
        void onCellSelected(int row, int col);
    }
    
    public CrosswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    private void init() {
        cellPaint = new Paint();
        cellPaint.setColor(Color.WHITE);
        cellPaint.setStyle(Paint.Style.FILL);
        
        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        blackPaint.setStyle(Paint.Style.FILL);
        
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(48);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        
        selectedPaint = new Paint();
        selectedPaint.setColor(Color.parseColor("#FFE082"));
        selectedPaint.setStyle(Paint.Style.FILL);
        
        cluePaint = new Paint();
        cluePaint.setColor(Color.BLUE);
        cluePaint.setTextSize(24);
        cluePaint.setAntiAlias(true);
        
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(2);
        linePaint.setStyle(Paint.Style.STROKE);
    }
    
    public void setPuzzle(CrosswordPuzzle puzzle) {
        this.puzzle = puzzle;
        calculateCellSize();
        invalidate();
    }
    
    private void calculateCellSize() {
        if (puzzle == null) return;
        
        int width = getWidth();
        int height = getHeight();
        
        int cellWidth = width / puzzle.getCols();
        int cellHeight = height / puzzle.getRows();
        
        cellSize = Math.min(cellWidth, cellHeight);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateCellSize();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (puzzle == null || cellSize == 0) return;
        
        for (int i = 0; i < puzzle.getRows(); i++) {
            for (int j = 0; j < puzzle.getCols(); j++) {
                drawCell(canvas, i, j);
            }
        }
    }
    
    private void drawCell(Canvas canvas, int row, int col) {
        CrosswordCell cell = puzzle.getCell(row, col);
        if (cell == null) return;
        
        float left = col * cellSize;
        float top = row * cellSize;
        float right = left + cellSize;
        float bottom = top + cellSize;
        
        // Draw cell background
        if (row == selectedRow && col == selectedCol) {
            canvas.drawRect(left, top, right, bottom, selectedPaint);
        } else if (cell.isBlack()) {
            canvas.drawRect(left, top, right, bottom, blackPaint);
        } else {
            canvas.drawRect(left, top, right, bottom, cellPaint);
        }
        
        // Draw cell border
        canvas.drawRect(left, top, right, bottom, linePaint);
        
        // Draw clue number
        if (cell.isClueStart() && cell.getClueNumber() > 0) {
            canvas.drawText(String.valueOf(cell.getClueNumber()), 
                          left + 10, top + 25, cluePaint);
        }
        
        // Draw user input
        if (!cell.isBlack() && cell.getUserInput() != ' ') {
            Paint paint = new Paint(textPaint);
            if (cell.isCorrect()) {
                paint.setColor(Color.parseColor("#4CAF50"));
            }
            
            // Calculate text position for vertical centering
            Rect bounds = new Rect();
            String text = String.valueOf(cell.getUserInput());
            paint.getTextBounds(text, 0, text.length(), bounds);
            float x = left + cellSize / 2;
            float y = top + cellSize / 2 + bounds.height() / 2;
            
            canvas.drawText(text, x, y, paint);
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (puzzle == null || cellSize == 0) return false;
            
            int col = (int) (event.getX() / cellSize);
            int row = (int) (event.getY() / cellSize);
            
            if (row >= 0 && row < puzzle.getRows() && 
                col >= 0 && col < puzzle.getCols()) {
                
                CrosswordCell cell = puzzle.getCell(row, col);
                if (cell != null && !cell.isBlack()) {
                    selectedRow = row;
                    selectedCol = col;
                    invalidate();
                    
                    if (listener != null) {
                        listener.onCellSelected(row, col);
                    }
                }
            }
        }
        return true;
    }
    
    public void setOnCellSelectedListener(OnCellSelectedListener listener) {
        this.listener = listener;
    }
    
    public int getSelectedRow() {
        return selectedRow;
    }
    
    public int getSelectedCol() {
        return selectedCol;
    }
    
    public void clearSelection() {
        selectedRow = -1;
        selectedCol = -1;
        invalidate();
    }
}
