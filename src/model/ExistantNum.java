package model;

public class ExistantNum {

    private int existNum;
    private ExistantNum connection;

    public ExistantNum(int existNum, ExistantNum connection){
        this.existNum = existNum;
        this.connection = connection;
    }

    public int getExistNum() {
        return existNum;
    }

    public void setExistNum(int existNum) {
        this.existNum = existNum;
    }

    public ExistantNum getConnection() {
        return connection;
    }

    public void setConnection(ExistantNum connection) {
        this.connection = connection;
    }
}
