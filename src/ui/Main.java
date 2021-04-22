package ui;

import exceptions.IntListIndexOutOfBounds;
import model.Table;

public class Main {

    private static Table table;

    public Main() throws IntListIndexOutOfBounds {
        table = new Table(4,5,2,7,2);
    }

    public static void main(String[] args) throws IntListIndexOutOfBounds {
        Main main = new Main();
        table.createTable();
        System.out.println("asd");
    }

}
