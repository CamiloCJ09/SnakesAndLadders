package model;

public class Tree {

    private String participant;
    private String nickname;
    private int score;

    private Tree parent;
    private Tree leftChild;
    private Tree rightChild;

    public Tree(String participant, String nickname, int score){
        this.participant = participant;
        this.score = score;
        this.nickname = nickname;

        parent = null;
        leftChild = null;
        rightChild = null;
    }

    public Tree(String participant, int score, Tree parent){
        this.participant = participant;
        this.score = score;
        this.parent = parent;

        leftChild = null;
        rightChild = null;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Tree getParent() {
        return parent;
    }

    public void setParent(Tree parent) {
        this.parent = parent;
    }

    public Tree getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Tree leftChild) {
        this.leftChild = leftChild;
    }

    public Tree getRightChild() {
        return rightChild;
    }

    public void setRightChild(Tree rightChild) {
        this.rightChild = rightChild;
    }
}
