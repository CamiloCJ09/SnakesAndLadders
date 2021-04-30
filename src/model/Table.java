package model;

import exceptions.IntListIndexOutOfBounds;

import java.util.Random;

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

    private Random random;

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
        random = new Random();
        //setupSnakes(snakes);
        //setupLadders(ladders);
        //snakeList.mergeLists(ladderList);

        //TODO recursive method to fill icons
    }

    public void prueba4(){
        for(int i = 0; i < totalCells-1; i+=2){

            moveParticipant(0,i);

            System.out.println("Movimiento "+i);

            int a = 0;
            Cell prv = cells;
            while(prv != null){
                System.out.println("Participants in cell "+prv.getNumber()+": "+prv.getParticipants());
                prv = prv.getNext();
            }
        }
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

    public Participants getParticipant(int index){
        if(index == 0){
            return listParticipants;
        }else{
            return getParticipant(index-1).getNext();
        }
    }

    public void moveParticipant(int participant, int moves){
        String a = " ";
        Character c = Character.MIN_VALUE;
        Participants part = getParticipant(participant);
        if(part.getPosition()+moves <= totalCells){
            Cell act = getPos(part.getPosition()-1);
            act.setParticipants(act.getParticipants().replaceAll(String.valueOf(part.getIcon()), ""));
            Cell nxt = getPos(part.getPosition()+moves-1);
            if(nxt.getLader() != null){
                Cell ladder = nxt.getLader();
                ladder.setParticipants(ladder.getParticipants() + part.getIcon());
                part.setPosition(ladder.getNumber());
            }else if(nxt.getSnake() != null){
                Cell snake = nxt.getSnake();
                snake.setParticipants(snake.getParticipants() + part.getIcon());
                part.setPosition(snake.getNumber());
            }else{
                nxt.setParticipants(nxt.getParticipants()+part.getIcon());
                part.setPosition(part.getPosition()+moves);
            }
        }
    }

    public String showTable(){
        return showTable(getCellA(totalCells-1),1,(totalCells-columns)+1, 1, "");
    }

    private String showTable(Cell cell1, int count,int cellsA, int row, String out){
        if(cellsA>0){
            Cell cell = getCellA(cellsA-1);
            if(cell != null){
                if(row%2==0){
                    if(count != columns){
                        if(cell.getSnakeLetter()!=((char)0)){
                            out += "["+(cellsA)+cell.getSnakeLetter()+cell.getParticipants()+"]"+"\t";
                            return showTable(cell.getBehind(), count+1, cellsA-1, row, out);
                        }else if(cell.getLadderNum() != 0){
                            out += "["+(cellsA)+"("+cell.getLadderNum()+")"+cell.getParticipants()+"]"+"\t";
                            return showTable(cell.getBehind(), count+1, cellsA-1, row, out);
                        }else{
                            out += "["+(cellsA)+cell.getParticipants()+"]"+"\t";
                            return showTable(cell.getBehind(), count+1, cellsA-1, row, out);
                        }
                    }else{
                        if(cell.getSnakeLetter()!=((char)0)){
                            out += "["+(cellsA)+cell.getSnakeLetter()+cell.getParticipants()+"]"+"\n";
                            return showTable(cell.getBehind(), 1, cellsA-columns, row+1, out);
                        }else if(cell.getLadderNum() != 0){
                            out += "["+(cellsA)+"("+cell.getLadderNum()+")"+cell.getParticipants()+"]"+"\n";
                            return showTable(cell.getBehind(), 1, cellsA-columns, row+1, out);
                        }else{
                            out += "["+(cellsA)+cell.getParticipants()+"]"+"\n";
                            return showTable(cell.getBehind(), 1, cellsA-columns, row+1, out);
                        }
                    }
                }else{
                    if(count != columns){
                        if(cell.getSnakeLetter()!=((char)0)){
                            out += "["+(cellsA)+cell.getSnakeLetter()+cell.getParticipants()+"]"+"\t";
                            return showTable(cell.getBehind(), count+1, cellsA+1, row, out);
                        }else if(cell.getLadderNum() != 0){
                            out += "["+(cellsA)+"("+cell.getLadderNum()+")"+cell.getParticipants()+"]"+"\t";
                            return showTable(cell.getBehind(), count+1, cellsA+1, row, out);
                        }else{
                            out += "["+(cellsA)+cell.getParticipants()+"]"+"\t";
                            return showTable(cell.getBehind(), count+1, cellsA+1, row, out);
                        }
                    }else{
                        if(cell.getSnakeLetter()!=((char)0)){
                            out += "["+(cellsA)+cell.getSnakeLetter()+cell.getParticipants()+"]"+"\n";
                            return showTable(cell.getBehind(), 1, (cellsA-columns), row+1, out);
                        }else if(cell.getLadderNum() != 0){
                            out += "["+(cellsA)+"("+cell.getLadderNum()+")"+cell.getParticipants()+"]"+"\n";
                            return showTable(cell.getBehind(), 1, (cellsA-columns), row+1, out);
                        }else{
                            out += "["+(cellsA)+cell.getParticipants()+"]"+"\n";
                            return showTable(cell.getBehind(), 1, (cellsA-columns), row+1, out);
                        }
                    }
                }
            }else{
                return out;
            }
        }
            return out;
    }


    public Cell getPos(int index){
        if(index == 0){
            return cells;
        }else{
            return getPos(index-1).getNext();
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

    public void setSnakes(int i, int character) throws IntListIndexOutOfBounds {
        if(i>-1 && snakeList.getCell(i) != null){
            int mod = (snakeList.getCell(i).getValue())%columns;
            if(mod == 0){
                mod = snakeList.getCell(i).getValue()-columns;
            }
            int value = snakeList.getCell(i).getValue()-(mod);
            int val2 = (int)(1+(Math.random()*(value)));
            if(!usedCells.contains(val2)){
                getCellA(snakeList.get(i)).setSnakeLetter((char) character);
                getCellA(val2).setSnakeLetter((char)character);
                getCellA((snakeList.get(i))).setSnake(getCellA(val2));
                usedCells.add(val2);
                setSnakes(i-1, character+1);
            }else{
                setSnakes(i, character);
            }
        }
    }
    public void setLadders(int i, int ladderNum) throws IntListIndexOutOfBounds {
        if(i > -1 && ladderList.getCell(i) != null){
            int mod = (ladderList.getCell(i).getValue())%columns;
            if(mod == 0){
                mod = 1;
            }
            int value = ladderList.getCell(i).getValue()+(mod);
            if(ladderList.getCell(i).getValue()>=((totalCells-columns)-1) && ladderList.getCell(i).getValue()<=(totalCells)){
                value = ladderList.getCell(i).getValue()+1;
            }
            int val2 = random.nextInt((totalCells)-value)+value;
            if(!usedCells.contains(val2)){
                getCellA(ladderList.get(i)).setLadderNum(ladderNum);
                getCellA(val2).setLadderNum(ladderNum);
                getCellA((ladderList.get(i))).setLader(getCellA(val2));
                usedCells.add(val2);
                setLadders(i-1, ladderNum+1);
            }else{
                setLadders(i, ladderNum);
            }
        }
    }

    public void prueba3(){
        Cell cell = cells;
        while(cell != null){
            //System.out.println(cell.getNumber());
            if(cell.getSnake() != null) {
                System.out.println("Snake");
                System.out.println("La celda "+ cell.getNumber() +" baja serpiente hasta " + cell.getSnake().getNumber());
                System.out.println("La celda "+ cell.getSnakeLetter() +" baja serpiente hasta " + cell.getSnake().getSnakeLetter());
                cell = cell.getNext();
            }else if(cell.getLader() != null){
                System.out.println("Ladder");
                System.out.println("La celda "+ cell.getNumber() +" sube escalera hasta " + cell.getLader().getNumber());
                System.out.println("La celda "+ cell.getLadderNum() +" sube escaler hasta " + cell.getLader().getLadderNum());
                cell = cell.getNext();
            }else{
                cell = cell.getNext();
            }

        }
    }

    public void setupLadders(int ladders) throws IntListIndexOutOfBounds {
        if(ladders == 1){
            int ladder = (1+random.nextInt(totalCells-columns));
            if(usedCells.contains(ladder)){
                System.out.println("Si sirvo pri");
                setupLadders(ladders);
                return;
            }
            usedCells.add(ladder);
            ladderList.add(ladder);
            System.out.println("Ladder: "+ ladder);
        } else{
            int ladder = (1+random.nextInt(totalCells-columns));
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
