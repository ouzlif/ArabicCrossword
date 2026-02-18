package com.example.arabiccrossword;

public class CrosswordCell {
    private char answer;
    private char userInput;
    private boolean isBlack;
    private String clue;
    private int clueNumber;
    private boolean isClueStart;
    
    public CrosswordCell() {
        this.answer = ' ';
        this.userInput = ' ';
        this.isBlack = false;
        this.clue = "";
        this.clueNumber = 0;
        this.isClueStart = false;
    }
    
    public CrosswordCell(char answer, boolean isBlack) {
        this.answer = answer;
        this.userInput = ' ';
        this.isBlack = isBlack;
        this.clue = "";
        this.clueNumber = 0;
        this.isClueStart = false;
    }
    
    public char getAnswer() {
        return answer;
    }
    
    public void setAnswer(char answer) {
        this.answer = answer;
    }
    
    public char getUserInput() {
        return userInput;
    }
    
    public void setUserInput(char userInput) {
        this.userInput = userInput;
    }
    
    public boolean isBlack() {
        return isBlack;
    }
    
    public void setBlack(boolean black) {
        isBlack = black;
    }
    
    public String getClue() {
        return clue;
    }
    
    public void setClue(String clue) {
        this.clue = clue;
    }
    
    public int getClueNumber() {
        return clueNumber;
    }
    
    public void setClueNumber(int clueNumber) {
        this.clueNumber = clueNumber;
    }
    
    public boolean isClueStart() {
        return isClueStart;
    }
    
    public void setClueStart(boolean clueStart) {
        isClueStart = clueStart;
    }
    
    public boolean isCorrect() {
        return !isBlack && userInput == answer;
    }
    
    public boolean isEmpty() {
        return userInput == ' ';
    }
}
