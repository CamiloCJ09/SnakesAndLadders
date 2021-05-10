package model;

/**
 * The type Cell.
 */
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

    /**
     * Instantiates a new Cell.
     *
     * @param number the number
     */
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

    /**
     * Instantiates a new Cell.
     *
     * @param number  the number
     * @param cell    the cell
     * @param isFirst the is first
     */
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

    /**
     * Instantiates a new Cell.
     *
     * @param number the number
     * @param next   the next
     * @param behind the behind
     */
    public Cell(int number, Cell next, Cell behind){
        this.number = number;
        this.participants = "";
        this.next = next;
        this.behind = behind;
        this.lader = null;
        this.snake = null;
    }

    /**
     * Instantiates a new Cell.
     *
     * @param number   the number
     * @param next     the next
     * @param behind   the behind
     * @param optional the optional
     * @param isLader  the is lader
     */
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
        if(snakeLetter!=0){
            return "["+number+(char)snakeLetter+"]";
        }else if(ladderNum != 0){
            return "["+number+"("+ladderNum+")"+"]";
        }else{
            return "["+number+"]";
        }
    }

    /**
     * To string 2 string.
     *
     * @return the string
     */
    public String toString2(){
        if(snakeLetter!=0){
            return "["+(char)snakeLetter+participants+"]";
        }else if(ladderNum != 0){
            return "["+"("+ladderNum+")"+participants+"]";
        }else{
            return "["+participants+"]";
        }
    }


    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets number.
     *
     * @param number the number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Gets next.
     *
     * @return the next
     */
    public Cell getNext() {
        return next;
    }

    /**
     * Sets next.
     *
     * @param next the next
     */
    public void setNext(Cell next) {
        this.next = next;
    }

    /**
     * Gets behind.
     *
     * @return the behind
     */
    public Cell getBehind() {
        return behind;
    }

    /**
     * Sets behind.
     *
     * @param behind the behind
     */
    public void setBehind(Cell behind) {
        this.behind = behind;
    }

    /**
     * Gets lader.
     *
     * @return the lader
     */
    public Cell getLader() {
        return lader;
    }

    /**
     * Sets lader.
     *
     * @param lader the lader
     */
    public void setLader(Cell lader) {
        this.lader = lader;
    }

    /**
     * Gets snake.
     *
     * @return the snake
     */
    public Cell getSnake() {
        return snake;
    }

    /**
     * Sets snake.
     *
     * @param snake the snake
     */
    public void setSnake(Cell snake) {
        this.snake = snake;
    }

    /**
     * Gets ladder num.
     *
     * @return the ladder num
     */
    public int getLadderNum() {
        return ladderNum;
    }

    /**
     * Sets ladder num.
     *
     * @param ladderNum the ladder num
     */
    public void setLadderNum(int ladderNum) {
        this.ladderNum = ladderNum;
    }

    /**
     * Gets snake letter.
     *
     * @return the snake letter
     */
    public char getSnakeLetter() {
        return snakeLetter;
    }

    /**
     * Sets snake letter.
     *
     * @param snakeLetter the snake letter
     */
    public void setSnakeLetter(char snakeLetter) {
        this.snakeLetter = snakeLetter;
    }

    /**
     * Gets participants.
     *
     * @return the participants
     */
    public String getParticipants() {
        return participants;
    }

    /**
     * Sets participants.
     *
     * @param participants the participants
     */
    public void setParticipants(String participants) {
        this.participants = participants;
    }

    /**
     * Gets snake num.
     *
     * @return the snake num
     */
    public int getSnakeNum() {
        return snakeNum;
    }

    /**
     * Sets snake num.
     *
     * @param snakeNum the snake num
     */
    public void setSnakeNum(int snakeNum) {
        this.snakeNum = snakeNum;
    }
}
