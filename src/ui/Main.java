package ui;

import exceptions.IntListIndexOutOfBounds;
import model.Table;

public class Main {

    private static Table table;

    public Main() throws IntListIndexOutOfBounds {
        table = new Table(4,5,2,3,2);
    }

    public static void main(String[] args) throws IntListIndexOutOfBounds {
        Main main = new Main();
        table.createTable();
        table.setupSnakes(table.getSnakes());
        table.setupLadders(table.getColumns());
        table.setSnakes(table.getSnakeList().getSize()-1);
        table.prueba3();
        System.out.println("asd");
    }

}
