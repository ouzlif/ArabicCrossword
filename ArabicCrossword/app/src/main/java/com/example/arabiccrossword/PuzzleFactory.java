package com.example.arabiccrossword;

public class PuzzleFactory {
    
    public static CrosswordPuzzle createSamplePuzzle() {
        // Create a 10x8 puzzle similar to the image
        CrosswordPuzzle puzzle = new CrosswordPuzzle(10, 8);
        
        // Define the puzzle pattern (0 = white cell, 1 = black cell)
        int[][] pattern = {
            {0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1}
        };
        
        // Define answers for the puzzle
        String[][] answers = {
            {"م", "د", "ر", "#", "ت", "ا", "ج", "#"},
            {"ح", "ا", "س", "#", "ح", "ي", "ا", "ة"},
            {"ل", "ي", "ل", "ة", "س", "ع", "ي", "د"},
            {"ف", "ر", "ح", "#", "د", "ر", "ب", "ة"},
            {"ظ", "ل", "م", "ة", "ق", "ا", "س", "ي"},
            {"#", "ب", "ح", "ر", "ي", "ة", "#", "ن"},
            {"س", "م", "ا", "ء", "#", "ط", "ي", "ر"},
            {"ف", "ي", "#", "م", "د", "ر", "س", "ة"},
            {"ر", "ح", "ل", "#", "ق", "ل", "م", "ي"},
            {"ج", "م", "ي", "#", "ز", "ه", "ر", "#"}
        };
        
        // Setup grid with pattern and answers
        int clueNumber = 1;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                CrosswordCell cell = puzzle.getCell(i, j);
                
                if (pattern[i][j] == 1) {
                    cell.setBlack(true);
                } else {
                    cell.setAnswer(answers[i][j].charAt(0));
                    
                    // Determine if this is a clue start
                    boolean isClueStart = false;
                    
                    // Check for across clue start
                    if (j == 0 || pattern[i][j-1] == 1) {
                        if (j < 7 && pattern[i][j+1] == 0) {
                            isClueStart = true;
                        }
                    }
                    
                    // Check for down clue start
                    if (i == 0 || pattern[i-1][j] == 1) {
                        if (i < 9 && pattern[i+1][j] == 0) {
                            isClueStart = true;
                        }
                    }
                    
                    if (isClueStart) {
                        cell.setClueStart(true);
                        cell.setClueNumber(clueNumber++);
                    }
                }
            }
        }
        
        // Add clues (examples in Arabic)
        puzzle.addAcrossClue(1, "مكان للتدريس", 0, 0);
        puzzle.addAcrossClue(2, "عكس الموت", 0, 4);
        puzzle.addDownClue(1, "عكس القبح", 0, 0);
        puzzle.addDownClue(3, "وقت الظلام", 0, 2);
        puzzle.addAcrossClue(4, "يوم خاص", 1, 4);
        puzzle.addAcrossClue(5, "ما نراه في الليل", 2, 0);
        puzzle.addDownClue(6, "مكان يسكن فيه الناس", 1, 1);
        puzzle.addAcrossClue(7, "الفرح", 3, 0);
        puzzle.addAcrossClue(8, "طريق", 3, 4);
        puzzle.addDownClue(9, "عدم النور", 4, 0);
        puzzle.addAcrossClue(10, "صعب أو شديد", 4, 0);
        puzzle.addDownClue(11, "شيء نكتب به", 3, 4);
        puzzle.addAcrossClue(12, "حرية", 5, 1);
        puzzle.addDownClue(13, "لون السماء", 5, 1);
        puzzle.addAcrossClue(14, "السماء العليا", 6, 0);
        puzzle.addAcrossClue(15, "حيوان يطير", 6, 5);
        puzzle.addDownClue(16, "مكان للدراسة", 7, 3);
        puzzle.addAcrossClue(17, "سفر", 8, 0);
        puzzle.addAcrossClue(18, "أداة كتابة", 8, 4);
        puzzle.addDownClue(19, "كل شيء", 9, 0);
        puzzle.addAcrossClue(20, "نبات جميل", 9, 4);
        
        return puzzle;
    }
    
    public static CrosswordPuzzle createEasyPuzzle() {
        CrosswordPuzzle puzzle = new CrosswordPuzzle(6, 6);
        
        int[][] pattern = {
            {0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 1},
            {0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0}
        };
        
        String[][] answers = {
            {"ب", "ي", "ت", "#", "ق", "ط"},
            {"ح", "ل", "م", "#", "ل", "م"},
            {"ر", "ج", "ل", "ش", "ج", "ر"},
            {"#", "#", "ب", "م", "س", "#"},
            {"و", "ر", "د", "#", "ا", "ب"},
            {"ل", "و", "ن", "#", "ح", "ة"}
        };
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                CrosswordCell cell = puzzle.getCell(i, j);
                if (pattern[i][j] == 1) {
                    cell.setBlack(true);
                } else {
                    cell.setAnswer(answers[i][j].charAt(0));
                }
            }
        }
        
        // Add simple clues
        puzzle.addAcrossClue(1, "مكان نسكن فيه", 0, 0);
        puzzle.addAcrossClue(2, "حيوان أليف", 0, 4);
        puzzle.addDownClue(1, "ما نراه عند النوم", 0, 0);
        puzzle.addAcrossClue(3, "رؤية في النوم", 1, 0);
        puzzle.addAcrossClue(4, "شخص ذكر", 2, 0);
        puzzle.addAcrossClue(5, "نبات كبير", 2, 3);
        puzzle.addDownClue(6, "أداة كتابة", 2, 4);
        puzzle.addAcrossClue(6, "زهرة جميلة", 4, 0);
        puzzle.addDownClue(7, "والد الأب", 4, 4);
        puzzle.addAcrossClue(7, "لون أو لونين", 5, 0);
        
        return puzzle;
    }
}
