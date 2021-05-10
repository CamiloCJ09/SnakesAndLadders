package model;

/**
 * The type Snake.
 */
public class Snake {
    private int num;
    private String name;
    private int references;

    private Snake nextSnake;

    /**
     * Instantiates a new Snake.
     *
     * @param num  the num
     * @param name the name
     */
    public Snake(int num, String name){
        this.num = num;
        this.name = name;
        this.references = 1;
    }

    /**
     * Gets num.
     *
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * Sets num.
     *
     * @param num the num
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets references.
     *
     * @return the references
     */
    public int getReferences() {
        return references;
    }

    /**
     * Sets references.
     *
     * @param references the references
     */
    public void setReferences(int references) {
        this.references = references;
    }

    /**
     * Gets next snake.
     *
     * @return the next snake
     */
    public Snake getNextSnake() {
        return nextSnake;
    }

    /**
     * Sets next snake.
     *
     * @param nextSnake the next snake
     */
    public void setNextSnake(Snake nextSnake) {
        this.nextSnake = nextSnake;
    }
}
