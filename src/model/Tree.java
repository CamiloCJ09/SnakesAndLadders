package model;

/**
 * The type Tree.
 */
public class Tree {

    private String participant;
    private String nickname;
    private int score;

    private int snakes;
    private int ladders;
    private int rows;
    private int columns;
    private int numOfPlayers;
    private String allIcons;

    private Tree parent;
    private Tree leftChild;
    private Tree rightChild;

    /**
     * Instantiates a new Tree.
     *
     * @param participant  the participant
     * @param nickname     the nickname
     * @param score        the score
     * @param snakes       the snakes
     * @param ladders      the ladders
     * @param rows         the rows
     * @param columns      the columns
     * @param numOfPlayers the num of players
     * @param allIcons     the all icons
     */
    public Tree(String participant, String nickname, int score, int snakes, int ladders, int rows, int columns, int numOfPlayers, String allIcons){
        this.participant = participant;
        this.score = score;
        this.nickname = nickname;

        this.snakes = snakes;
        this.ladders = ladders;
        this.rows = rows;
        this.columns = columns;
        this.numOfPlayers = numOfPlayers;
        this.allIcons = allIcons;

        parent = null;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Instantiates a new Tree.
     *
     * @param participant  the participant
     * @param score        the score
     * @param parent       the parent
     * @param snakes       the snakes
     * @param ladders      the ladders
     * @param rows         the rows
     * @param columns      the columns
     * @param numOfPlayers the num of players
     * @param allIcons     the all icons
     */
    public Tree(String participant, int score, Tree parent, int snakes, int ladders, int rows, int columns, int numOfPlayers, String allIcons){
        this.participant = participant;
        this.score = score;
        this.parent = parent;

        this.snakes = snakes;
        this.ladders = ladders;
        this.rows = rows;
        this.columns = columns;
        this.numOfPlayers = numOfPlayers;
        this.allIcons = allIcons;

        leftChild = null;
        rightChild = null;
    }

    /**
     * P to string string.
     *
     * @return the string
     */
    public String pToString(){
        String ret = "";
        ret =   "-------------------------"+"\n"+
                "Nicknae: "+nickname+"\n"+
                "Icono: "+participant+"\n"+
                "Puntaje: "+score+"\n"+
                "Dimension del tablero: "+rows +"x"+columns+"\n"+
                "Serpientes: "+snakes+"\n"+
                "Escaleras: "+ladders+"\n"+
                "Numero de jugadores: "+ numOfPlayers+"\n"+
                "Simbolo de jugadores: "+allIcons+"\n";
        return ret;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets nickname.
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Gets participant.
     *
     * @return the participant
     */
    public String getParticipant() {
        return participant;
    }

    /**
     * Sets participant.
     *
     * @param participant the participant
     */
    public void setParticipant(String participant) {
        this.participant = participant;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets parent.
     *
     * @return the parent
     */
    public Tree getParent() {
        return parent;
    }

    /**
     * Sets parent.
     *
     * @param parent the parent
     */
    public void setParent(Tree parent) {
        this.parent = parent;
    }

    /**
     * Gets left child.
     *
     * @return the left child
     */
    public Tree getLeftChild() {
        return leftChild;
    }

    /**
     * Sets left child.
     *
     * @param leftChild the left child
     */
    public void setLeftChild(Tree leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * Gets right child.
     *
     * @return the right child
     */
    public Tree getRightChild() {
        return rightChild;
    }

    /**
     * Sets right child.
     *
     * @param rightChild the right child
     */
    public void setRightChild(Tree rightChild) {
        this.rightChild = rightChild;
    }
}
