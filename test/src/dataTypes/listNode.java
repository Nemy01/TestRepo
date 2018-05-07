package dataTypes;

public class listNode {
    private int val;
    private listNode next;
    private boolean Init = false;

    public void init(int i, listNode n){
        val = i;
        next = n;
        Init = true;
    }

    public int getval()
    {
        return val;
    }
    public listNode getNext(){
        return next;
    }
    public void setNext(listNode n){
        next = n;
    }
    public void setVal(int i){
        val = i;
    }
}
