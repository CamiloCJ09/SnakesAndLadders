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

    private Participants listParticipants;
    private int participants;
    private String icons;
    private int totalCells;

    public Table(int rows, int columns, int ladders, int snakes, int participants) throws IntListIndexOutOfBounds {
        this.rows = rows;
        this.columns = columns;
        this.ladders = ladders;
        this.snakes = snakes;
        this.participants = participants;
        listParticipants = createParticipants(participants, 1);
        this.totalCells = rows*columns;
        this.snakeList = new IntList();
        this.ladderList = new IntList();
        this.usedCells = new IntList();
        icons = "";
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
        listParticipants = createParticipants(participants, icons, 1);
    }

    public Participants createParticipants(int participants, String icons, int position){
        if(position == participants){
            return new Participants(icons.charAt(position-1));
        }else{
            Participants participant = new Participants(icons.charAt(position-1));
            participant.setNext(createParticipants(participants, icons, position+1));
            return participant;
        }
    }

    public Participants createParticipants(int participants, int position){
        if(position == participants){
            return new Participants((char)(33+position));
        }else{
            Participants participant = new Participants((char)(33+position));
            participant.setNext(createParticipants(participants, position+1));
            return participant;
        }
    }

    public void fillIcons(int number, int position, Participants actualParticipant){
        if(position == 1){
            icons += actualParticipant.getIcon();
            fillIcons(number, position+1, actualParticipant);
        } else if(position == number){
            icons += actualParticipant.getNext().getIcon();
        }else{
            icons += actualParticipant.getNext().getIcon();
            fillIcons(number, position+1, actualParticipant.getNext());
        }
    }

    //TODO recursive method to create and fill cells
    public void createTable() throws IntListIndexOutOfBounds {

        //System.out.println("Cell number"+cells.getNumber());
        cells = createTable(totalCells,1);
        setupBehind(totalCells);
        fillIcons(participants, 1, listParticipants);
        //setupSnakes(snakes);
        //setupLadders(ladders);
        //setSnakes(snakeList.getSize()-1);
        //prueba1();
        //prueba2();
        //prueba3();
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

    /*public Cell getCellA(int index, int noCares){
        return getCellA(index);
    }*/
    public Cell getCellA(int index){
        if(index == 0){
            //System.out.println("Aqui toy pri");
            return cells;
        }else{
            return getCellA(index-1).getNext();
        }
    }

    public void setSnakes(int i) throws IntListIndexOutOfBounds {
        if(i > 0 && snakeList.getCell(i) != null){
            int mod = (snakeList.getCell(i).getValue())%columns;
            if(mod == 0){
                mod = snakeList.getCell(i).getValue()-columns;
            }
            int value = snakeList.getCell(i).getValue()-(mod);
            int val2 = (int)(1+(Math.random()*(value)));
            if(!usedCells.contains(val2)){
                getCellA((snakeList.get(i)-1)).setSnake(getCellA(val2-1));
                usedCells.add(val2);
                setSnakes(i-1);
            }else{
                setSnakes(i);
            }
        }
    }
    public void setLadders(int i) throws IntListIndexOutOfBounds {
        if(i > 0 && ladderList.getCell(i) != null){
            int mod = (snakeList.getCell(i).getValue())%columns;
            if(mod == 0){
                mod = ladderList.getCell(i).getValue()+1;
            }
            int value = ladderList.getCell(i).getValue()+(mod);
            int val2 = (int)(1+(Math.random()*(value)));
            if(!usedCells.contains(val2)){
                getCellA((ladderList.get(i)-1)).setLader(getCellA(val2-1));
                usedCells.add(val2);
                setLadders(i-1);
            }else{
                setLadders(i);
            }
        }
    }
    public void prueba3(){
        Cell cell = cells;
        while(cell != null){
            //System.out.println(cell.getNumber());
            if(cell.getSnake() != null) {
                System.out.println("La celda "+ cell.getNumber() +" baja serpiente hasta " + cell.getSnake().getNumber());
                cell = cell.getNext();
            }else{
                cell = cell.getNext();
            }
        }
    }

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

    private Cell createTable(int totalCells, int actualCell) {
        if(actualCell == totalCells){
            Cell cell = new Cell(actualCell);
            return cell;
        }else{
            Cell cell = new Cell(actualCell);
            //System.out.println(cell.getNumber());
            cell.setNext(createTable(totalCells, actualCell+1));
            return cell;
        }
    }

    private void setupBehind(int totalCells){
        if(totalCells != 1){
            getCellA(totalCells-1).setBehind(getCellA((totalCells-1)-1));
            setupBehind(totalCells-1);
        }
    }

    public void prueba1(){
        Cell cell = cells;
        while(cell != null){
            System.out.println("Prueba1");
            cell = cell.getNext();
        }

    }

    public void prueba2(){
        int counter = totalCells-1;
        while(counter > 0){
            System.out.println(counter);
            System.out.println(getCellA(counter).getBehind());
            System.out.println("El anterior a "+ getCellA(counter).getNumber()+" es "+(+getCellA(counter).getBehind().getNumber()));

            counter--;
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
