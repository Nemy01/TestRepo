import dataTypes.binaryTreeNode;
import dataTypes.graphNode;
import dataTypes.listNode;
import testclasses.buildBTree;
import testclasses.testGraph;
import testclasses.testlist;

import java.util.List;

public class idea {


    public static void main(String[] args){
        //list test
        //testlist t = new testlist();
        //listNode head = t.buildList();
        //t.printlist(head);
        //t.printlist(t.reverseList(head));

        //graph Test
        /*
        testGraph g = new testGraph();
        List<graphNode> gList = g.createRandGraph(20);
        g.printGraphInList(gList);
        List<graphNode> headNodes = g.findUnreachableNodes(gList);
        for(graphNode gn : headNodes){
            graphNode valNode = g.findGNodeVal(gn, 10);
            System.out.println(valNode.getVal() + " value found from headNodeVal: " + gn.getVal());
        }
        */

        buildBTree bT = new buildBTree();
        binaryTreeNode root = bT.createRandBTree(20);
        bT.printBTreeDFS(root, 1);
        bT.balanceTree(root);




    }




}


