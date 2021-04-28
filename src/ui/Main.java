package ui;

import exceptions.IntListIndexOutOfBounds;
import model.Cell;
import model.Table;

public class Main {

    private static Table table;

    public Main() throws IntListIndexOutOfBounds {
        table = new Table(5,5,2,3,10);
    }

    public static void main(String[] args) throws IntListIndexOutOfBounds {
        Main main = new Main();
        table.createTable();
        table.setupSnakes(table.getSnakes());
        table.setupLadders(table.getColumns());
        table.setSnakes(table.getSnakeList().getSize()-1, 65);
        table.setLadders(table.getLadderList().getSize()-1, 1);
        //table.prueba3();
        System.out.println("asd");
    }

    public String printTable(int rows, int columns){
        int totalCells = table.getRows()*table.getColumns();
        return printTable(columns, totalCells, 1, "", table.getCells());
    }
    private String printTable(int columns, int totalCells, int lineBreak, String out, Cell lastCell){
        String ret = "";
        return ret;
    }

}
