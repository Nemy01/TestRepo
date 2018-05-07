package testclasses;

import dataTypes.listNode;

public class testlist {

    public listNode buildList(){
        listNode head = new listNode();
        head.init(10,null);
        listNode curr;
        listNode prev = head;
        for(int c=0; c< 10; c++){
            curr = new listNode();
            curr.init(c,null);
            prev.setNext(curr);
            prev = curr;
        }
        return head;

    }

    public void printlist(listNode curr){
        int i = 0;
        do{

            System.out.println(curr.getval());
            curr = curr.getNext();
            i++;
        }
        while(curr != null);


    }

    public listNode reverseList(listNode head){
        if (head.getNext() == null)return head; //one node list

        if (head.getNext().getNext() == null){
            // two node list
            //System.out.print("in second if");
            listNode next = head.getNext();
            next.setNext(head);
            head.setNext(null);
            return next;
        }
        listNode curr = head, next = head.getNext(), nnext = next.getNext();
        int i = 0;
        //System.out.print("after second if");
        curr.setNext(null);
        while(true){
            next.setNext(curr);
            curr = next;
            next = nnext;
            if(next.getNext() == null){
                //end of list
                //break clause

                next.setNext(curr);
                return next;
            }
            nnext = next.getNext();
            i++;
            if(i > 20000) break; //break out of looped list. arbitrary value.
        }
        System.out.println("shouldn't get here, returning list");
        return head;
    }



}
