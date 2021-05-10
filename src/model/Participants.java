package model;

/**
 * The type Participants.
 */
public class Participants {

    private char icon;
    private int position;
    private int moves;
    private String nickname;
    private Participants next;

    /**
     * Instantiates a new Participants.
     *
     * @param icon the icon
     */
    public Participants(char icon){
        this.icon = icon;
        position = 1;
        moves = 0;
        nickname = "";
        next = null;
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
     * Gets icon.
     *
     * @return the icon
     */
    public char getIcon() {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    public void setIcon(char icon) {
        this.icon = icon;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Gets moves.
     *
     * @return the moves
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Sets moves.
     *
     * @param moves the moves
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * Gets next.
     *
     * @return the next
     */
    public Participants getNext() {
        return next;
    }

    /**
     * Sets next.
     *
     * @param next the next
     */
    public void setNext(Participants next) {
        this.next = next;
    }
}
