# App Architecture - بنية التطبيق

## Component Overview

### 1. MainActivity (الواجهة الرئيسية)
**Purpose:** Main controller that manages the game flow
**Responsibilities:**
- Initialize UI components
- Handle user interactions
- Manage timer
- Update coin count
- Control game state

**Key Methods:**
- `onCreate()` - Initialize app
- `setupPuzzle()` - Load puzzle
- `setupKeyboard()` - Create Arabic keyboard
- `onLetterClick()` - Handle letter input
- `showHint()` - Reveal correct letter
- `checkPuzzle()` - Validate answers
- `resetPuzzle()` - Clear all entries

---

### 2. CrosswordView (عرض الشبكة)
**Purpose:** Custom view that renders the crossword grid
**Responsibilities:**
- Draw grid cells
- Highlight selected cell
- Show user input
- Handle touch events
- Display clue numbers

**Key Methods:**
- `onDraw()` - Render the grid
- `drawCell()` - Draw individual cell
- `onTouchEvent()` - Handle cell selection
- `calculateCellSize()` - Adjust to screen size

---

### 3. CrosswordPuzzle (نموذج اللغز)
**Purpose:** Data model for the puzzle
**Responsibilities:**
- Store grid structure
- Manage clues (across/down)
- Track completion status
- Calculate progress

**Key Methods:**
- `getCell()` - Get cell at position
- `isCompleted()` - Check if solved
- `getCorrectCount()` - Count correct answers
- `addAcrossClue()` - Add horizontal clue
- `addDownClue()` - Add vertical clue

---

### 4. CrosswordCell (خلية واحدة)
**Purpose:** Represents a single cell in the grid
**Properties:**
- `answer` - Correct letter
- `userInput` - User's letter
- `isBlack` - Black cell or white
- `clueNumber` - Clue number if start
- `clue` - Clue text

**Key Methods:**
- `isCorrect()` - Check if user input matches answer
- `isEmpty()` - Check if cell is empty

---

### 5. PuzzleFactory (مصنع الألغاز)
**Purpose:** Creates puzzle instances
**Responsibilities:**
- Define puzzle patterns
- Set answers
- Add clues
- Create different difficulty levels

**Methods:**
- `createSamplePuzzle()` - Large 10x8 puzzle
- `createEasyPuzzle()` - Small 6x6 puzzle

---

## Data Flow

```
User taps cell
    ↓
CrosswordView.onTouchEvent()
    ↓
Update selectedRow, selectedCol
    ↓
User presses keyboard letter
    ↓
MainActivity.onLetterClick()
    ↓
CrosswordCell.setUserInput()
    ↓
CrosswordView.invalidate()
    ↓
Grid redraws with new letter
```

## Game State Management

### Timer System
```
MainActivity.onCreate()
    ↓
setupTimer()
    ↓
Handler posts Runnable every 1 second
    ↓
updateTimer() updates TextView
```

### Hint System
```
User clicks hint button
    ↓
Check if coins >= 10
    ↓
Find first incorrect cell
    ↓
Set cell.userInput = cell.answer
    ↓
Deduct 10 coins
    ↓
Redraw grid
```

### Completion Check
```
After each letter input
    ↓
Call puzzle.isCompleted()
    ↓
Check all cells match answers
    ↓
If complete: show dialog
    ↓
Stop timer, show time
```

## UI Components

### Header (Header Bar)
- Timer (left) - Shows elapsed time
- Title (center) - "كلمات متقاطعة"
- Coins (right) - Shows remaining coins

### Progress Bar
- Shows: "X / Y" (correct / total)
- Updates after each input

### Grid Area
- Main CrosswordView
- Responsive sizing
- Touch-enabled cells

### Control Buttons
- Hint (💡) - Costs 10 coins
- Check (✓) - Validate progress
- Reset (🔄) - Clear all entries

### Keyboard
- 31 Arabic letters
- Delete button
- ScrollView for small screens

## File Structure Details

```
MainActivity.java (500+ lines)
├── UI Initialization
├── Puzzle Setup
├── Keyboard Creation
├── Event Handlers
├── Timer Management
└── Game Logic

CrosswordView.java (200+ lines)
├── Paint Objects
├── Drawing Methods
├── Touch Handling
└── Cell Rendering

CrosswordPuzzle.java (150+ lines)
├── Grid Management
├── Clue Storage
└── Completion Logic

CrosswordCell.java (80+ lines)
├── Cell Properties
└── Validation Methods

PuzzleFactory.java (150+ lines)
├── Pattern Definitions
├── Answer Arrays
└── Clue Setup
```

## Customization Points

### 1. Add New Puzzle
Edit: `PuzzleFactory.java`
- Create new method
- Define pattern and answers
- Add clues

### 2. Change Colors
Edit: `CrosswordView.java`
- Modify Paint colors
- Update selection highlight

### 3. Adjust Difficulty
Edit: `PuzzleFactory.java`
- Change grid size
- Modify patterns
- Add/remove black cells

### 4. Modify UI
Edit: `activity_main.xml`
- Rearrange components
- Change button text
- Adjust layouts

### 5. Change Coin System
Edit: `MainActivity.java`
- Initial coins value
- Hint cost
- Reward system

## Performance Considerations

1. **Grid Rendering:** Uses Canvas for efficient drawing
2. **Touch Events:** Only processes valid cell selections
3. **Memory:** Puzzle data stored in efficient arrays
4. **Updates:** Only redraws when necessary (invalidate())

## Testing Checklist

- [ ] App launches successfully
- [ ] Grid displays correctly
- [ ] Can select cells
- [ ] Arabic keyboard works
- [ ] Letters appear in cells
- [ ] Timer counts up
- [ ] Hint reveals correct letter
- [ ] Check shows progress
- [ ] Reset clears grid
- [ ] Completion detected
- [ ] RTL layout works
- [ ] Works on different screen sizes

---

## Future Enhancements

1. **Persistence:** Save game state to SharedPreferences
2. **Database:** SQLite for multiple puzzles
3. **Network:** Download puzzles from server
4. **Animations:** Smooth transitions
5. **Sound:** Audio feedback
6. **Themes:** Dark mode support
7. **Accessibility:** TalkBack support
8. **Analytics:** Track user progress

---

For implementation details, see the source code comments in each file.
