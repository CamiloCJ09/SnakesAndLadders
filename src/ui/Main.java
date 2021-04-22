package ui;

import model.Table;

public class Main {

    private static Table table;

    public Main(){
        table = new Table(4,5,2,7,2);
    }

    public static void main(String[] args){
        Main main = new Main();
        table.createTable();
    }

}
