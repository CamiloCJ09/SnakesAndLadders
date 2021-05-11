package ui;

import exceptions.IntListIndexOutOfBounds;
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
                System.out.println(table.printTree());
                showMenu();
                int opt = Integer.parseInt(sc.nextLine());
                executeMenu(opt);
                break;
            case 3:
                //Method
                break;
        }
    }

    public void playOption() throws IntListIndexOutOfBounds {
        String line = sc.nextLine();
        String[] objects = line.split(" ");
        if(table == null){
            if(objects[4].charAt(0) < 58 && objects[4].charAt(0) > 47){
                this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), Integer.parseInt(objects[4]), null);
            }else{
                this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), objects[4], null);
            }
        }else{
            if(objects[4].charAt(0) < 58 && objects[4].charAt(0) > 47){
                this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), Integer.parseInt(objects[4]), table.getScores());
            }else{
                this.table = new Table(Integer.parseInt(objects[0]), Integer.parseInt(objects[1]), Integer.parseInt(objects[2]), Integer.parseInt(objects[3]), objects[4], table.getScores());
            }
        }
        System.out.print(table.showTable());
        System.out.print("\n" + table.showTable2());
        String text = sc.nextLine();
        turn = 0;
        runGame(text, false);
    }

    public void runGame(String text, boolean sim) throws IntListIndexOutOfBounds {
        if(text.equals("")){
            boolean win = throwDice();
            if(sim){
                if(!win){
                    try{
                        Thread.sleep(2000);
                        System.out.print("\n" + table.showTable2());
                        runGame("", true);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }else{
                    //TODO: pedir datos del ganador
                    showMenu();
                    int option = Integer.parseInt(sc.nextLine());
                    executeMenu(option);
                }
            }else{
                if(!win){
                    System.out.print("\n" + table.showTable2());
                    String txt = sc.nextLine();
                    runGame(txt, false);
                }else{
                    //TODO: pedir datos del ganador
                    showMenu();
                    int option = Integer.parseInt(sc.nextLine());
                    executeMenu(option);
                }
            }
        }else if(text.equals("num")){
            System.out.print(table.showTable());
            String txt = sc.nextLine();
            runGame(txt, false);
        }else if(text.equals("simul")){
            runGame("", true);

            //TODO Method to simulate
        }else if(text.equals("menu")){
            showMenu();
            int option = Integer.parseInt(sc.nextLine());
            executeMenu(option);
        }
    }

    public boolean throwDice(){
        int run = (int) ((Math.random()*5)+1);
        System.out.println("El jugador: "+table.getParticipant(turn).getIcon() + " ha lanzado el dado y obtuvo el puntaje de "+run);
        boolean win = table.moveParticipant(turn, run);
        if(win){
            System.out.println("El participante "+ table.getParticipant(turn).getIcon()+ " ganó el juego, con "+ table.getParticipant(turn).getMoves()+" movimientos");
            System.out.println("Ingrese un nombre:");
            table.getParticipant(turn).setNickname(sc.nextLine());
            table.insertScores(table.getParticipant(turn));
            turn = 0;
            return true;
        }else{
            if(turn == table.getParticipants()-1){
                turn = 0;
            }else{
                turn++;
            }
            return false;
        }

    }
}
