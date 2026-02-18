# Arabic Crossword App - كلمات متقاطعة

A native Android application for playing Arabic crossword puzzles, built with Java.

## Features

✨ **Core Features:**
- Interactive crossword grid with touch input
- Full Arabic language support (RTL layout)
- Arabic keyboard for input
- Timer to track solving time
- Hint system with coin-based rewards (50 coins start)
- Progress tracking
- Check answers functionality
- Reset puzzle option
- Multiple puzzle support

🎮 **Gameplay:**
- Tap any white cell to select it
- Use the Arabic keyboard at the bottom to enter letters
- Use hints (costs 10 coins) to reveal correct letters
- Check your progress anytime
- Complete the puzzle to see your time

## Project Structure

```
ArabicCrossword/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/arabiccrossword/
│   │       │   ├── MainActivity.java          # Main activity with game logic
│   │       │   ├── CrosswordView.java         # Custom view for grid
│   │       │   ├── CrosswordPuzzle.java       # Puzzle data model
│   │       │   ├── CrosswordCell.java         # Cell data model
│   │       │   └── PuzzleFactory.java         # Creates sample puzzles
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml      # Main UI layout
│   │       │   └── values/
│   │       │       ├── strings.xml            # String resources
│   │       │       └── themes.xml             # App theme
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Setup Instructions

### Prerequisites
- Android Studio (Arctic Fox or later)
- JDK 8 or higher
- Android SDK 24 (Android 7.0) or higher

### Installation Steps

1. **Open in Android Studio:**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the `ArabicCrossword` folder and select it

2. **Sync Gradle:**
   - Android Studio will automatically sync Gradle files
   - Wait for the sync to complete
   - If prompted, update Gradle or Android Gradle Plugin

3. **Configure Emulator or Device:**
   - **For Emulator:** 
     - Tools → Device Manager → Create Device
     - Select a device (e.g., Pixel 5)
     - Choose API 24 or higher
   - **For Physical Device:**
     - Enable Developer Options
     - Enable USB Debugging
     - Connect via USB

4. **Run the App:**
   - Click the green "Run" button (▶️) or press Shift+F10
   - Select your device/emulator
   - Wait for build and installation

## How to Play

1. **Start:** The app launches with a puzzle and timer starts automatically
2. **Select Cell:** Tap any white cell in the grid
3. **Enter Letter:** Use the Arabic keyboard to input a letter
4. **Delete:** Press the "حذف" (Delete) button to clear a cell
5. **Hint:** Click "💡 تلميح" to reveal a correct letter (costs 10 coins)
6. **Check:** Click "✓ فحص" to see how many answers are correct
7. **Reset:** Click "🔄 إعادة" to clear all entries and restart
8. **Win:** Complete all cells correctly to win and see your time!

## Customization Guide

### Adding New Puzzles

Edit `PuzzleFactory.java` to create new puzzles:

```java
public static CrosswordPuzzle createCustomPuzzle() {
    CrosswordPuzzle puzzle = new CrosswordPuzzle(rows, cols);
    
    // Define pattern (0 = white, 1 = black)
    int[][] pattern = {
        {0, 0, 1, 0},
        {0, 0, 0, 0},
        // ... more rows
    };
    
    // Define answers
    String[][] answers = {
        {"ب", "ي", "#", "ت"},
        {"ح", "ل", "م", "ة"},
        // ... more rows
    };
    
    // Add clues
    puzzle.addAcrossClue(1, "Your clue text", row, col);
    puzzle.addDownClue(2, "Your clue text", row, col);
    
    return puzzle;
}
```

### Changing Colors

Edit `CrosswordView.java`:
- `selectedPaint.setColor(Color.parseColor("#FFE082"));` - Selected cell color
- `cellPaint.setColor(Color.WHITE);` - White cell color
- `blackPaint.setColor(Color.BLACK);` - Black cell color

Edit `themes.xml`:
- `colorPrimary` - Header background
- `colorAccent` - Accent color

### Adjusting Coin Values

Edit `MainActivity.java`:
- Initial coins: `private int coins = 50;`
- Hint cost: `coins -= 10;` (in showHint method)

### Changing Grid Size

In `PuzzleFactory.java`, modify:
```java
CrosswordPuzzle puzzle = new CrosswordPuzzle(10, 8); // rows, cols
```

## Building APK

1. **Debug APK:**
   - Build → Build Bundle(s) / APK(s) → Build APK(s)
   - APK location: `app/build/outputs/apk/debug/`

2. **Release APK:**
   - Build → Generate Signed Bundle / APK
   - Create or select keystore
   - Follow signing wizard
   - APK location: `app/build/outputs/apk/release/`

## Technical Details

- **Language:** Java
- **Min SDK:** 24 (Android 7.0)
- **Target SDK:** 34 (Android 14)
- **Dependencies:**
  - AndroidX AppCompat
  - Material Components
  - ConstraintLayout

## Features to Add (Future)

- [ ] Puzzle difficulty levels (Easy, Medium, Hard)
- [ ] Save/load game state
- [ ] Multiple puzzle categories
- [ ] Online leaderboards
- [ ] Daily challenges
- [ ] Social sharing
- [ ] Sound effects
- [ ] Dark mode
- [ ] Landscape orientation
- [ ] Puzzle editor
- [ ] Clue display panel

## Troubleshooting

**Problem:** App crashes on start
- **Solution:** Check logcat for errors, ensure all files are properly imported

**Problem:** Arabic text not displaying
- **Solution:** Ensure RTL support is enabled in AndroidManifest.xml and themes.xml

**Problem:** Keyboard not showing
- **Solution:** Check that keyboardLayout is properly initialized in activity_main.xml

**Problem:** Grid too small/large
- **Solution:** Adjust cellSize calculation in CrosswordView.java

## License

This project is provided as-is for educational purposes.

## Credits

Developed as a native Android application for Arabic crossword puzzles.

---

**Enjoy playing! استمتع باللعب!** 🎉
