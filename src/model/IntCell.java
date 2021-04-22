package model;

public class IntCell {

    private int value;
    private IntCell next;

    public IntCell(int value){
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public IntCell getNext() {
        return next;
    }

    public void setNext(IntCell next) {
        this.next = next;
    }
}
