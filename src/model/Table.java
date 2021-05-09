package model;

import exceptions.IntListIndexOutOfBounds;

import java.util.Random;

/**
 * The type Table.
 */
public class Table {

    private Cell cells;
    private Tree scores;
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

    /**
     * Instantiates a new Table.
     *
     * @param rows         the rows
     * @param columns      the columns
     * @param ladders      the ladders
     * @param snakes       the snakes
     * @param participants the participants
     * @throws IntListIndexOutOfBounds the int list index out of bounds
     */
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
        createTable();
        setupSnakes(snakes);
        setupLadders(ladders);
        setSnakes(getSnakeList().getSize()-1, 65);
        setLadders(getLadderList().getSize()-1, 1);
    }

    /**
     * Prueba 4.
     */
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

    /**
     * Instantiates a new Table.
     *
     * @param rows    the rows
     * @param columns the columns
     * @param ladders the ladders
     * @param snakes  the snakes
     * @param icons   the icons
     * @throws IntListIndexOutOfBounds the int list index out of bounds
     */
    public Table(int rows, int columns, int ladders, int snakes, String icons) throws IntListIndexOutOfBounds {
        this.rows = rows;
        this.columns = columns;
        this.ladders = ladders;
        this.snakes = snakes;
        this.icons = icons;
        this.totalCells = rows*columns;
        this.participants = icons.length();
        listParticipants = createParticipants(participants, icons, 1);
        random = new Random();
        createTable();
        setupSnakes(snakes);
        setupLadders(ladders);
        setSnakes(getSnakeList().getSize()-1, 65);
        setLadders(getLadderList().getSize()-1, 1);
    }

    /**
     * Create participants participants.
     *
     * @param participants the participants
     * @param icons        the icons
     * @param position     the position
     * @return the participants
     */
    public Participants createParticipants(int participants, String icons, int position){
        if(position == participants){
            return new Participants(icons.charAt(position-1));
        }else{
            Participants participant = new Participants(icons.charAt(position-1));
            participant.setNext(createParticipants(participants, icons, position+1));
            return participant;
        }
    }

    /**
     * Create participants participants.
     *
     * @param participants the participants
     * @param position     the position
     * @return the participants
     */
    public Participants createParticipants(int participants, int position){
        if(position == participants){
            return new Participants((char)(33+position));
        }else{
            Participants participant = new Participants((char)(33+position));
            participant.setNext(createParticipants(participants, position+1));
            return participant;
        }
    }

    /**
     * Get participant participants.
     *
     * @param index the index
     * @return the participants
     */
    public Participants getParticipant(int index){
        if(index == 0){
            return listParticipants;
        }else{
            return getParticipant(index-1).getNext();
        }
    }
    /**
     * Move participant.
     *
     * @param participant the participant
     * @param moves       the moves
     */
    public void moveParticipant(int participant, int moves){
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

    public String printTree(){
        return printTree(scores);
    }
    private String printTree(Tree scores){

        if(scores.getLeftChild() == null && scores.getRightChild() == null){
            return scores.getParticipant() + " " + scores.getScore() + "\n";
        }else{
            String sc = "";
            if(scores.getRightChild() != null){
                sc += printTree(scores.getRightChild());
            }
            if(scores.getLeftChild() != null){
                sc += printTree(scores.getLeftChild());
            }
            sc += scores.getParticipant() + " " + scores.getScore() + "\n";
            return sc;
        }
    }



    public void insertScores(String participant ,int score){
        scores = insertRec(scores,participant, score);
    }
    private Tree insertRec(Tree scores,String participant, int score){
        if(scores == null){
            scores = new Tree(participant, score);
            return scores;
        }

        if(score <= scores.getScore()){
            scores.setLeftChild(insertRec(scores.getLeftChild(), participant, score));
        }else if(score > scores.getScore()){
            scores.setRightChild(insertRec(scores.getRightChild(), participant, score));
        }
        return scores;
    }


    /**
     * Show table string.
     *
     * @return the string
     */
    public String showTable(){
        String out = "";
        return showTable(1);
    }

    private String showTable(int row){
        if(row == rows){
            if(row%2!=0){
                String out = showTableLine(((row-1)*columns)+1,row*columns, true);
                return out;
            }else{
                String out = showTableLine(((row-1)*columns)+1,row*columns, false);
                return out;
            }
        }else{
            if(row%2!=0){
                String out = showTable(row+1);
                out += showTableLine(((row-1)*columns)+1,row*columns, true);
                return out;
            }else{
                String out = showTable(row+1);
                out += showTableLine(((row-1)*columns)+1,row*columns, false);
                return out;
            }
        }
    }

    private String showTableLine(int from, int to, boolean odd){
        if(odd){
            if(from == to){
                return getCellA(from-1).toString();
            }else if(from == ((to-columns)+1)){
                String out = showTableLine(from+1, to, odd);
                out = getCellA(from-1).toString() + out + "\n";
                return out;
            }else{
                String out = showTableLine(from+1, to, odd);
                out = getCellA(from-1).toString() + out;
                return out;
            }
        }else{
            if(from == to){
                return getCellA(from-1).toString();
            }else if(from == ((to-columns)+1)){
                String out = showTableLine(from+1, to, odd);
                out += getCellA(from-1).toString() + "\n";
                return out;
            }else{
                String out = showTableLine(from+1, to, odd);
                out += getCellA(from-1).toString();
                return out;
            }
        }
    }

    public String showTable2(){
        String out = "";
        return showTable2(1);
    }

    /**
     * Get pos cell.
     *
     * @param index the index
     * @return the cell
     */
    public Cell getPos(int index){
        if(index == 0){
            return cells;
        }else{
            return getPos(index-1).getNext();
        }
    }

    /**
     * Fill icons.
     *
     * @param number            the number
     * @param position          the position
     * @param actualParticipant the actual participant
     */
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

    /**
     * Create table.
     *
     * @throws IntListIndexOutOfBounds the int list index out of bounds
     */
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

    /**
     * Sets snakes.
     *
     * @param snakes the snakes
     * @throws IntListIndexOutOfBounds the int list index out of bounds
     */
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

    /**
     * Gets cell a.
     *
     * @param index the index
     * @return the cell a
     */
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

    /**
     * Sets snakes.
     *
     * @param i         the
     * @param character the character
     * @throws IntListIndexOutOfBounds the int list index out of bounds
     */
    public void setSnakes(int i, int character) throws IntListIndexOutOfBounds {
        if(i>-1 && snakeList.getCell(i) != null){
            int mod = (snakeList.getCell(i).getValue())%columns;
            if(mod == 0){
                mod = snakeList.getCell(i).getValue()-columns;
            }
            int value = snakeList.getCell(i).getValue()-(mod);
            int val2 = (int)(1+(Math.random()*(value-1)));
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

    /**
     * Sets ladders.
     *
     * @param i         the
     * @param ladderNum the ladder num
     * @throws IntListIndexOutOfBounds the int list index out of bounds
     */
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

    /**
     * Prueba 3.
     */
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

    /**
     * Sets ladders.
     *
     * @param ladders the ladders
     * @throws IntListIndexOutOfBounds the int list index out of bounds
     */
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

    /**
     * Prueba 1.
     */
    public void prueba1(){
        Cell cell = cells;
        while(cell != null){
            System.out.println("Prueba1");
            cell = cell.getNext();
        }

    }

    /**
     * Prueba 2.
     */
    public void prueba2(){
        int counter = totalCells-1;
        while(counter > 0){
            System.out.println(counter);
            System.out.println(getCellA(counter).getBehind());
            System.out.println("El anterior a "+ getCellA(counter).getNumber()+" es "+(+getCellA(counter).getBehind().getNumber()));

            counter--;
        }
    }

    /**
     * Gets cells.
     *
     * @return the cells
     */
    public Cell getCells() {
        return cells;
    }

    /**
     * Sets cells.
     *
     * @param cells the cells
     */
    public void setCells(Cell cells) {
        this.cells = cells;
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets rows.
     *
     * @param rows the rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Gets columns.
     *
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets columns.
     *
     * @param columns the columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Gets ladders.
     *
     * @return the ladders
     */
    public int getLadders() {
        return ladders;
    }


    /**
     * Gets snakes.
     *
     * @return the snakes
     */
    public int getSnakes() {
        return snakes;
    }

    /**
     * Gets snake.
     *
     * @return the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Sets snake.
     *
     * @param snake the snake
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * Gets snake list.
     *
     * @return the snake list
     */
    public IntList getSnakeList() {
        return snakeList;
    }

    /**
     * Sets snake list.
     *
     * @param snakeList the snake list
     */
    public void setSnakeList(IntList snakeList) {
        this.snakeList = snakeList;
    }

    /**
     * Gets ladder list.
     *
     * @return the ladder list
     */
    public IntList getLadderList() {
        return ladderList;
    }

    /**
     * Sets ladder list.
     *
     * @param ladderList the ladder list
     */
    public void setLadderList(IntList ladderList) {
        this.ladderList = ladderList;
    }

    /**
     * Gets used cells.
     *
     * @return the used cells
     */
    public IntList getUsedCells() {
        return usedCells;
    }

    /**
     * Sets used cells.
     *
     * @param usedCells the used cells
     */
    public void setUsedCells(IntList usedCells) {
        this.usedCells = usedCells;
    }

    /**
     * Gets participants.
     *
     * @return the participants
     */
    public int getParticipants() {
        return participants;
    }

    /**
     * Sets participants.
     *
     * @param participants the participants
     */
    public void setParticipants(int participants) {
        this.participants = participants;
    }

    /**
     * Gets icons.
     *
     * @return the icons
     */
    public String getIcons() {
        return icons;
    }

    /**
     * Sets icons.
     *
     * @param icons the icons
     */
    public void setIcons(String icons) {
        this.icons = icons;
    }

    /**
     * Gets total cells.
     *
     * @return the total cells
     */
    public int getTotalCells() {
        return totalCells;
    }

    /**
     * Sets total cells.
     *
     * @param totalCells the total cells
     */
    public void setTotalCells(int totalCells) {
        this.totalCells = totalCells;
    }
}
