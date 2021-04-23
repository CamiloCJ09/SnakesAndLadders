package model;

import exceptions.IntListIndexOutOfBounds;

public class Table {

    private Cell cells;
    private int rows;
    private int columns;
    private int ladders;
    private int snakes;
    //Revisar
    private Snake snake;
    private IntList snakeList;
    private IntList ladderList;

    private int participants;
    private String icons;
    private int totalCells;

    public Table(int rows, int columns, int ladders, int snakes, int participants) throws IntListIndexOutOfBounds {
        this.rows = rows;
        this.columns = columns;
        this.ladders = ladders;
        this.snakes = snakes;
        this.participants = participants;
        this.totalCells = rows*columns;
        this.snakeList = new IntList();
        this.ladderList = new IntList();
        setupSnakes(snakes);
        setupLadders(ladders);

        //TODO recursive method to fill icons
    }

    public Table(int rows, int columns, int ladders, int snakes, String icons) throws IntListIndexOutOfBounds {
        this.rows = rows;
        this.columns = columns;
        this.ladders = ladders;
        this.snakes = snakes;
        this.icons = icons;
        this.totalCells = rows*columns;
        this.participants = icons.length();
        setupSnakes(snakes);
        setupLadders(ladders);
    }

    //TODO recursive method to create and fill cells
    public void createTable(){
        cells = new Cell(totalCells);

        //System.out.println("Cell number"+cells.getNumber());
        createTable(totalCells, null);

    }

    private void setupSnakes(int snakes) throws IntListIndexOutOfBounds {
        if(snakes == 1){
            int snake = (int)(1+(Math.random()*(totalCells-2)));
            if(snakeList.contains(snake)){
                System.out.println("papi, si sirvo");
                setupSnakes(snakes);
                return;
            }
            snakeList.add(snake);
            System.out.println("Snake: "+ snake);
        } else{
            int snake = (int)(1+(Math.random()*(totalCells-2)));
            if(snakeList.contains(snake)){
                System.out.println("papi, si sirvo");
                setupSnakes(snakes);
                return;
            }
            snakeList.add(snake);
            System.out.println("Snake: "+ snake);
            setupSnakes(snakes-1);
        }
    }

    private void setupLadders(int ladders) throws IntListIndexOutOfBounds {
        if(ladders == 1){
            int ladder = (int)(1+(Math.random()*(totalCells-2)));
            if(ladderList.contains(ladder)){
                System.out.println("Si sirvo pri");
                setupLadders(ladders);
                return;
            }
            snakeList.add(ladder);
            System.out.println("Ladder: "+ ladder);
        } else{
            int ladder = (int)(1+(Math.random()*(totalCells-2)));
            if(ladderList.contains(ladder)){
                System.out.println("Si sirvo pri");
                setupLadders(ladders);
                return;
            }
            snakeList.add(ladder);
            System.out.println("Ladder: "+ ladder);
            setupLadders(ladders-1);
        }
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

    /*
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
     */

    public void addSnakes(int snakes){

    }
}
