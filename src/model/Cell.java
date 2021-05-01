package model;

public class Cell {

    private int number;
    private Cell next;
    private Cell behind;
    private Cell lader;
    private int ladderNum;
    private Cell snake;
    private int snakeNum;
    private String participants;
    private char snakeLetter;

    public Cell(int number){
        this.number = number;
        this.participants = "";
        this.snakeLetter = 0;
        this.ladderNum = 0;
        this.next = null;
        this.behind = null;
        this.lader = null;
        this.snake = null;
    }

    public Cell(int number, Cell cell, boolean isFirst){
        if(isFirst){
            this.number = number;
            this.next = cell;
            this.participants = "";
            this.snakeLetter = 0;
            this.ladderNum = 0;
            this.behind = null;
            this.lader = null;
            this.snake = null;
        } else{
            this.number = number;
            this.participants = "";
            this.snakeLetter = 0;
            this.ladderNum = 0;
            this.next = null;
            this.behind = cell;
            this.lader = null;
            this.snake = null;
        }
    }

    public Cell(int number, Cell next, Cell behind){
        this.number = number;
        this.participants = "";
        this.next = next;
        this.behind = behind;
        this.lader = null;
        this.snake = null;
    }

    public Cell(int number, Cell next, Cell behind, Cell optional, boolean isLader){
        if(isLader){
            this.number = number;
            this.participants = "";
            this.next = next;
            this.behind = behind;
            this.lader = optional;
            this.snake = null;
        } else{
            this.number = number;
            this.participants = "";
            this.next = next;
            this.behind = behind;
            this.lader = null;
            this.snake = optional;
        }
    }

    public String toString(){
        return "["+number+"]";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Cell getNext() {
        return next;
    }

    public void setNext(Cell next) {
        this.next = next;
    }

    public Cell getBehind() {
        return behind;
    }

    public void setBehind(Cell behind) {
        this.behind = behind;
    }

    public Cell getLader() {
        return lader;
    }

    public void setLader(Cell lader) {
        this.lader = lader;
    }

    public Cell getSnake() {
        return snake;
    }

    public void setSnake(Cell snake) {
        this.snake = snake;
    }

    public int getLadderNum() {
        return ladderNum;
    }

    public void setLadderNum(int ladderNum) {
        this.ladderNum = ladderNum;
    }

    public char getSnakeLetter() {
        return snakeLetter;
    }

    public void setSnakeLetter(char snakeLetter) {
        this.snakeLetter = snakeLetter;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public int getSnakeNum() {
        return snakeNum;
    }

    public void setSnakeNum(int snakeNum) {
        this.snakeNum = snakeNum;
    }
}
