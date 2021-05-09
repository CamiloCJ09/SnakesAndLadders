package ui;

import exceptions.IntListIndexOutOfBounds;
import model.Cell;
import model.Table;

import java.util.Scanner;

public class Main {

    private Table table;
    private Scanner sc;
    private int turn;

    public Main() throws IntListIndexOutOfBounds {
        //table = new Table(6,6,2,3,2);
        sc = new Scanner(System.in);
        turn = 0;
    }

    public static void main(String[] args) throws IntListIndexOutOfBounds {
        Main main = new Main();

        main.showMenu();
        int option = Integer.parseInt(main.sc.nextLine());
        main.executeMenu(option);
    }

    public void showMenu() {
        System.out.print("-----MENU-----\n 1.Play\n 2.Scores\n 3.Leave\n");
    }

    public void executeMenu(int option) throws IntListIndexOutOfBounds {
        switch (option) {
            case 1:
                System.out.println("Format: Rows Columns Snakes Ladders Players");
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
            System.out.println("El 1 pri");
            this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), Integer.parseInt(objects[4]));
        }else{
            System.out.println("El 2 pri");
            this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), objects[4]);
        }
        System.out.print(table.showTable());
        System.out.print("\n" + table.showTable2());
        String text = sc.nextLine();
        turn = 0;
        runGame(text);
    }

    public void runGame(String text) throws IntListIndexOutOfBounds {
        if(text.equals("")){
            throwDice();
            System.out.print("\n" + table.showTable2());
            String txt = sc.nextLine();
            runGame(txt);
        }else if(text.equals("num")){
            System.out.print(table.showTable());
            String txt = sc.nextLine();
            runGame(txt);
        }else if(text.equals("simul")){
            //TODO Method to simulate
        }else if(text.equals("menu")){
            showMenu();
            int option = Integer.parseInt(sc.nextLine());
            executeMenu(option);
        }
    }

    public void throwDice(){
        int run = (int) ((Math.random()*5)+1);
        System.out.println("El jugador: "+table.getParticipant(turn).getIcon() + " ha lanzado el dado y obtuvo el puntaje de "+run);
        table.moveParticipant(turn, run);
        if(turn == table.getParticipants()-1){
            turn = 0;
        }else{
            turn++;
        }
    }
}
