package model;

public class Table {

    private Cell cells;
    private int rows;
    private int columns;
    private int laders;
    private int snakes;
    private int participants;
    private String icons;
    private int totalCells;

    public Table(int rows, int columns, int laders, int snakes, int participants){
        this.rows = rows;
        this.columns = columns;
        this.laders = laders;
        this.snakes = snakes;
        this.participants = participants;
        this.totalCells = rows*columns;
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

    //TODO recursive method to create and fill cells
    public void createTable(){
        cells = new Cell(totalCells);
        createTable(totalCells, cells, totalCells);
    }

    public void createTable(int totalCells, Cell nextCell, int total){
        if(!(totalCells == 0)){
            Cell tempCell = new Cell(totalCells, null, nextCell);
            nextCell.setNext(tempCell);
            createTable(totalCells-1, tempCell, total);
        }if(nextCell == null){
            Cell tempCell = new Cell(totalCells);
            nextCell.setNext(tempCell);
        }
    }
    /*
    public void createTable(int totalCells, Cell nextCell, int total){
        if(totalCells == 0){
            Cell tempCell = new Cell(totalCells, nextCell, true);
        }else if(nextCell == null){
            Cell tempCell = new Cell(totalCells);
            nextCell.setNext(tempCell);
        }else{
        	Cell tempCell = new Cell(totalCells, nextCell, true);
        	tempCell.setBehind(createTable(totalCells-1, tempCell, total))
        }
    }
     */

}
