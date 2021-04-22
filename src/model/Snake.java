package model;

public class Snake {
    private int num;
    private String name;
    private int references;

    private Snake nextSnake;

    public Snake(int num, String name){
        this.num = num;
        this.name = name;
        this.references = 1;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReferences() {
        return references;
    }

    public void setReferences(int references) {
        this.references = references;
    }

    public Snake getNextSnake() {
        return nextSnake;
    }

    public void setNextSnake(Snake nextSnake) {
        this.nextSnake = nextSnake;
    }
}
