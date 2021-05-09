package ui;

import exceptions.IntListIndexOutOfBounds;
import model.Cell;
import model.Table;

import java.util.Scanner;

public class Main {

    private Table table;
    private Scanner sc;

    public Main() throws IntListIndexOutOfBounds {
        //table = new Table(6,6,2,3,2);
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws IntListIndexOutOfBounds {
        Main main = new Main();

        main.showMenu();
        int option = Integer.parseInt(main.sc.nextLine());
        main.executeMenu(option);
    }

    public void showMenu(){
        System.out.print("-----MENU-----\n 1.Play\n 2.Scores\n 3.Leave\n");
    }

    public void executeMenu(int option) throws IntListIndexOutOfBounds {
        switch (option){
            case 1:
                playOption();
                break;
            case 2:
                //Method
                break;
            case 3:
                //Method
                break;
        }
    }

    public void playOption() throws IntListIndexOutOfBounds {
        String line = sc.nextLine();
        String[] objects = line.split(" ");
        if(objects[4].charAt(0) < 58 && objects[4].charAt(0) > 47){
            this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), Integer.parseInt(objects[4]));
        }else{
            this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), objects[4]);
        }
        System.out.println(table.showTable());
    }
}
