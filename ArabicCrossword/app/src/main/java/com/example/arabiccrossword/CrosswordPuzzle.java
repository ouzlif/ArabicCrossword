package com.example.arabiccrossword;

import java.util.ArrayList;
import java.util.List;

public class CrosswordPuzzle {
    private CrosswordCell[][] grid;
    private int rows;
    private int cols;
    private List<Clue> acrossClues;
    private List<Clue> downClues;
    
    public static class Clue {
        public int number;
        public String text;
        public int row;
        public int col;
        public boolean isAcross;
        
        public Clue(int number, String text, int row, int col, boolean isAcross) {
            this.number = number;
            this.text = text;
            this.row = row;
            this.col = col;
            this.isAcross = isAcross;
        }
    }
    
    public CrosswordPuzzle(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new CrosswordCell[rows][cols];
        this.acrossClues = new ArrayList<>();
        this.downClues = new ArrayList<>();
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new CrosswordCell();
            }
        }
    }
    
    public CrosswordCell getCell(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            return grid[row][col];
        }
        return null;
    }
    
    public void setCell(int row, int col, CrosswordCell cell) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            grid[row][col] = cell;
        }
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public List<Clue> getAcrossClues() {
        return acrossClues;
    }
    
    public List<Clue> getDownClues() {
        return downClues;
    }
    
    public void addAcrossClue(int number, String text, int row, int col) {
        acrossClues.add(new Clue(number, text, row, col, true));
    }
    
    public void addDownClue(int number, String text, int row, int col) {
        downClues.add(new Clue(number, text, row, col, false));
    }
    
    public boolean isCompleted() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                CrosswordCell cell = grid[i][j];
                if (!cell.isBlack() && !cell.isCorrect()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int getCorrectCount() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].isCorrect()) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public int getTotalCells() {
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!grid[i][j].isBlack()) {
                    count++;
                }
            }
        }
        return count;
    }
}
