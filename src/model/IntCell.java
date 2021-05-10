package model;

/**
 * The type Int cell.
 */
public class IntCell {

    private int value;
    private IntCell next;

    /**
     * Instantiates a new Int cell.
     *
     * @param value the value
     */
    public IntCell(int value){
        this.value = value;
        this.next = null;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets next.
     *
     * @return the next
     */
    public IntCell getNext() {
        return next;
    }

    /**
     * Sets next.
     *
     * @param next the next
     */
    public void setNext(IntCell next) {
        this.next = next;
    }
}
