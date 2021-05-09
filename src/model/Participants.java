package model;

public class Participants {

    private char icon;
    private int position;
    private int moves;
    private String nickname;
    private Participants next;

    public Participants(char icon){
        this.icon = icon;
        position = 1;
        moves = 0;
        nickname = "";
        next = null;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public char getIcon() {
        return icon;
    }

    public void setIcon(char icon) {
        this.icon = icon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public Participants getNext() {
        return next;
    }

    public void setNext(Participants next) {
        this.next = next;
    }
}
