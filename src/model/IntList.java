package model;

import exceptions.IntListIndexOutOfBounds;

public class IntList {

    private IntCell first;
    private IntCell last;
    private int size;

    public IntList(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void add(int value){
        if(first == null){
            first = new IntCell(value);
            last = first;
            size++;
        } else{
            IntCell cell = new IntCell(value);
            last.setNext(cell);
            last = cell;
            size++;
        }
    }

    public void add(int index, int value) throws IntListIndexOutOfBounds {
        if(index>=size){
            throw new IntListIndexOutOfBounds();
        } else if(index == 0){
            IntCell newCell = new IntCell(value);
            newCell.setNext(first);
            first = newCell;
            size++;
        } else{
            IntCell oldCell = getCell(index);
            IntCell newCell = new IntCell(value);
            IntCell behindCell = getCell(index-1);
            behindCell.setNext(newCell);
            newCell.setNext(oldCell);
            size++;
        }
    }

    public int get(int index) throws IntListIndexOutOfBounds {
        if(index>(size-1)){
            throw new IntListIndexOutOfBounds();
        }else{
            return getCell(index).getValue();
        }
    }

    private IntCell getCell(int index){
        if(index == 0){
            return first;
        } else{
            return getCell(index-1).getNext();
        }
    }

    public int getSize() {
        return size;
    }
}
