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

    public IntCell getCell(int index){
        if(index == 0){
            return first;
        } else if(index == (size - 1)){
            return last;
        }else{
            return getCell(index-1).getNext();
        }
    }

    public int getSize() {
        return size;
    }

    public boolean contains(int number) throws IntListIndexOutOfBounds {
        if(first == null){
            return false;
        }
        return contains(number, size-1);
    }

    private boolean contains(int number, int size) throws IntListIndexOutOfBounds {
        boolean is = false;
        if(size == 0){
            if(number == get(0)){
                is = true;
            } else{
                is = false;
            }
        } else{
            if(number == get(size)){
                is = true;
            } else{
                return contains(number, size-1);
            }
        }
        return is;
    }


    public IntList mergeLists(IntList list1) throws IntListIndexOutOfBounds {
        IntList result = new IntList();
        result.setFirst(first);
        result.setLast(last);
        if(result.getFirst() == null){
            result.setFirst(list1.getCell(0));
        }else{
            result.getLast().setNext(list1.getCell(list1.getSize()-1));
        }
        IntCell prueba = result.getFirst();
        while(prueba != null){
            System.out.println(prueba.getValue());
            prueba = prueba.getNext();
        }
        return result;
    }

    public IntCell getFirst() {
        return first;
    }

    public void setFirst(IntCell first) {
        this.first = first;
    }

    public IntCell getLast() {
        return last;
    }

    public void setLast(IntCell last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }
}