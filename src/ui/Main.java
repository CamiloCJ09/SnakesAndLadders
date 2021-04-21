package ui;

import model.Table;

public class Main {

    private static Table table;

    public Main(){
        table = new Table(5,6,2,3,2);
    }

    public static void main(String[] args){
        Main main = new Main();
        table.createTable();
    }

}
