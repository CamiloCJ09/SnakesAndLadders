package model;

public class Table {

    private Cell cells;
    private int rows;
    private int columns;
    private int laders;
    private int snakes;
    private int participants;
    private String icons;
    private String posLadders;
    private String posSnakes;
    private int totalCells;

    public Table(int rows, int columns, int laders, int snakes, int participants){
        this.rows = rows;
        this.columns = columns;
        this.laders = laders;
        this.snakes = snakes;
        this.participants = participants;
        this.totalCells = rows*columns;
        this.posSnakes = "";
        this.posLadders = "";
        generateSnakes(snakes);

        //TODO recursive method to fill icons
    }

    public Table(int rows, int columns, int laders, int snakes, String icons){
        this.rows = rows;
        this.columns = columns;
        this.laders = laders;
        this.snakes = snakes;
        this.icons = icons;
        this.totalCells = rows*columns;
        this.participants = icons.length();
        generateSnakes(snakes);
    }

    //TODO recursive method to create and fill cells
    public void createTable(){
        cells = new Cell(totalCells);

        System.out.println("Cell number"+cells.getNumber());
        createTable(totalCells, null);

    }
    /*
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
    */
    public Cell createTable(int totalCells, Cell nextCell) {
        if (totalCells == 0) { //Base case
            Cell tempCell = new Cell(totalCells, nextCell, true);
            System.out.println("Case 0"+tempCell.getNumber());
            return tempCell;
        } else if (nextCell == null) { //Last case
            Cell tempCell = new Cell(totalCells);
            tempCell.setBehind(createTable(totalCells - 1, tempCell));
            System.out.println("Case null"+tempCell.getNumber());

            return tempCell;
        } else { //General case
            Cell tempCell = new Cell(totalCells, nextCell, true);
            tempCell.setBehind(createTable(totalCells - 1, tempCell));
            System.out.println("General case"+tempCell.getNumber());

            return tempCell;
        }
    }

    public void generateSnakes(int snakes){
        //Snakes
        if(snakes>0){
            int value = (int)((Math.random()*totalCells)-1);
            System.out.println(value);
            posSnakes += value+",";
            System.out.println("posSnakes = " + posSnakes);
            generateSnakes(snakes-1);
        }

    }
    public void addSnakes(int snakes){

    }
}
