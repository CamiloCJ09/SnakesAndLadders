package model;

public class Table {

    private Cell cells;
    private int rows;
    private int columns;
    private int laders;
    private int snakes;
    //Revisar
    private Snake snake;
    private ExistantNum firstNum;

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
        generateSnakes();

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
        generateSnakes();
    }

    //TODO recursive method to create and fill cells
    public void createTable(){
        cells = new Cell(totalCells);

        //System.out.println("Cell number"+cells.getNumber());
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

    public void existingNum(){

    }
    public void generateSnakes(){
        int piso = columns+1;
        int techo = totalCells-1;

        int num = (int)(Math.random()*((techo+1)-(piso)))+piso;
        posSnakes += num+",";

        //System.out.println("First time: "+num);
        snake = new Snake(num, "Snake "+snakes);

        num = (int)(Math.random()*((techo+1)-(piso)))+piso;
        if(posSnakes.contains(String.valueOf(num))){
            num = (int)(Math.random()*((techo+1)-(piso)))+piso;
        }
        posSnakes += num+",";

        //System.out.println("Second time: "+num);

        generateSnakes(snakes-1, snake, num);
    }
    public void generateSnakes(int snakes, Snake currentSnake, int numValue){
        //Snakes
        if(snakes!=0){
            //int value = (int)((Math.random()*totalCells)-2);
            int piso = columns+1;
            int techo = totalCells-1;

            Snake tempSnake = new Snake(numValue, "Snake "+snakes);
            currentSnake.setNextSnake(tempSnake);

            System.out.println("current "+ currentSnake.getName()+" " +currentSnake.getNum());
            System.out.println("temp "+ tempSnake.getName()+" " +tempSnake.getNum());

            int num = (int)(Math.random()*((techo+1)-(piso)))+piso;
            if(posSnakes.contains(String.valueOf(num))){
                num = (int)(Math.random()*((techo+1)-(piso)))+piso;
                posSnakes += num+",";
            }


            generateSnakes(snakes-1, tempSnake, num);
        }
    }

    public void addSnakes(int snakes){

    }
}
