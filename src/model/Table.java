package model;

public class Table {

    private Cell cells;
    private int rows;
    private int columns;
    private int laders;
    private int snakes;
    private int participants;
    private String icons;

    public Table(int rows, int columns, int laders, int snakes, int participants){
        this.rows = rows;
        this.columns = columns;
        this.laders = laders;
        this.snakes = snakes;
        this.participants = participants;
        //TODO recursive method to fill icons
    }

    public Table(int rows, int columns, int laders, int snakes, String icons){
        this.rows = rows;
        this.columns = columns;
        this.laders = laders;
        this.snakes = snakes;
        this.icons = icons;
        this.participants = icons.length();
    }
}
