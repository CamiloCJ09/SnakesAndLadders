package model;

public class Cell {

    private int number;
    private Cell next;
    private Cell behind;
    private Cell lader;
    private int ladderNum;
    private Cell snake;
    private int snakeNum;

    public Cell(int number){
        this.number = number;
        this.next = null;
        this.behind = null;
        this.lader = null;
        this.snake = null;
    }

    public Cell(int number, Cell cell, boolean isFirst){
        if(isFirst){
            this.number = number;
            this.next = cell;
            this.behind = null;
            this.lader = null;
            this.snake = null;
        } else{
            this.number = number;
            this.next = null;
            this.behind = cell;
            this.lader = null;
            this.snake = null;
        }
    }

    public Cell(int number, Cell next, Cell behind){
        this.number = number;
        this.next = next;
        this.behind = behind;
        this.lader = null;
        this.snake = null;
    }

    public Cell(int number, Cell next, Cell behind, Cell optional, boolean isLader){
        if(isLader){
            this.number = number;
            this.next = next;
            this.behind = behind;
            this.lader = optional;
            this.snake = null;
        } else{
            this.number = number;
            this.next = next;
            this.behind = behind;
            this.lader = null;
            this.snake = optional;
        }
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
}
