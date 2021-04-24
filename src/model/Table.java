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
    private IntList usedCells;

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
        this.usedCells = new IntList();
        //setupSnakes(snakes);
        //setupLadders(ladders);
        //snakeList.mergeLists(ladderList);

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

    }

    //TODO recursive method to create and fill cells
    public void createTable() throws IntListIndexOutOfBounds {
        cells = new Cell(totalCells);

        //System.out.println("Cell number"+cells.getNumber());
        createTable(totalCells, null);
        //setupSnakes(snakes);
        //setupLadders(ladders);
        //setSnakes(snakeList.getSize()-1);

    }

    public void setupSnakes(int snakes) throws IntListIndexOutOfBounds {
        if(snakes == 1){
            int snake = (int)((columns+1)+(Math.random()*((totalCells-columns)-1)));
            if(usedCells.contains(snake)){
                System.out.println("papi, si sirvo");
                setupSnakes(snakes);
                return;
            }
            usedCells.add(snake);
            snakeList.add(snake);
            System.out.println("Snake: "+ snake);
        } else{
            int snake = (int)((columns+1)+(Math.random()*((totalCells-columns)-1)));
            if(usedCells.contains(snake)){
                System.out.println("papi, si sirvo");
                setupSnakes(snakes);
                return;
            }
            usedCells.add(snake);
            snakeList.add(snake);
            System.out.println("Snake: "+ snake);
            setupSnakes(snakes-1);
        }
        //;
    }

    public Cell getCellA(int index, int noCares){
        return getCellA(index-1);
    }
    public Cell getCellA(int index){
        if(index == 0 ){
            System.out.println("Aqui toy pri");
            return cells;
        }else{
            System.out.println(index);
            return getCellA(index-1).getNext()  ;
        }
    }

    public void setSnakes(int i) throws IntListIndexOutOfBounds {
        System.out.println(i);
        if(i > 0 && snakeList.getCell(i-1) != null){
            System.out.println("Entra a este if pri");
            int value = snakeList.getCell(i-1).getValue()-((snakeList.getCell(i-1).getValue())%columns);
            int val2 = (int)(1+(Math.random()*(value)));
            if(!usedCells.contains(val2)){
                System.out.println("Entra pri");
                System.out.println("Val2: "+val2);
                System.out.println("a"+snakeList.get(i-1));
                getCellA((snakeList.get(i-1))-1, 0).setSnake(getCellA(val2));
                System.out.println(i-1);
                setSnakes(i-1);
            }else{
                System.out.println("Else pri");
                setSnakes(i);
            }
        }
        Cell cell = cells.getNext();
        while(cell != null){
            if(cell.getSnake() != null) {
                //System.out.println("Añadiendo serpientes " + cell.getSnake().getNumber());
                cell = cell.getNext();
            }else{
                cell = cell.getNext();
            }
        }
    }
    //private void setSnakes()

    public void setupLadders(int ladders) throws IntListIndexOutOfBounds {
        if(ladders == 1){
            int ladder = (int)(1+(Math.random()*(totalCells-2)));
            if(usedCells.contains(ladder)){
                System.out.println("Si sirvo pri");
                setupLadders(ladders);
                return;
            }
            usedCells.add(ladder);
            ladderList.add(ladder);
            System.out.println("Ladder: "+ ladder);
        } else{
            int ladder = (int)(1+(Math.random()*(totalCells-2)));
            if(usedCells.contains(ladder)){
                System.out.println("Si sirvo pri");
                setupLadders(ladders);
                return;
            }
            usedCells.add(ladder);
            ladderList.add(ladder);
            System.out.println("Ladder: "+ ladder);
            setupLadders(ladders-1);
        }
    }

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

    public Cell getCells() {
        return cells;
    }

    public void setCells(Cell cells) {
        this.cells = cells;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getLadders() {
        return ladders;
    }

    public void setLadders(int ladders) {
        this.ladders = ladders;
    }

    public int getSnakes() {
        return snakes;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public IntList getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(IntList snakeList) {
        this.snakeList = snakeList;
    }

    public IntList getLadderList() {
        return ladderList;
    }

    public void setLadderList(IntList ladderList) {
        this.ladderList = ladderList;
    }

    public IntList getUsedCells() {
        return usedCells;
    }

    public void setUsedCells(IntList usedCells) {
        this.usedCells = usedCells;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public void setTotalCells(int totalCells) {
        this.totalCells = totalCells;
    }
}
