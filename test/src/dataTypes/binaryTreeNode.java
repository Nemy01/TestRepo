package dataTypes;

import java.util.ArrayList;
import java.util.List;

public class binaryTreeNode {

    public boolean isWellFormed(boolean SimplefixError) {
        List<binaryTreeNode> btlist =
                checktreeForm(new ArrayList<binaryTreeNode>(), new ArrayList<binaryTreeNode>(), this, SimplefixError);
        if(btlist.size() == 0){
            wellFormed = true;
        }
        else{
            wellFormed = false;
        }
        return wellFormed;
    }

    public void init(int value, binaryTreeNode leftNode, binaryTreeNode rightNode){
            val = value;
            left = leftNode;
            right=rightNode;
    }

    public void init(int value){
        init(value, null, null);
    }

    public void init(int value, binaryTreeNode Node, boolean left){
        if(left)
            init(value, Node, null);
        else
            init(value, null, Node);
    }


    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public binaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(binaryTreeNode left) {
        this.left = left;
    }

    public binaryTreeNode getRight() {
        return right;
    }

    public void setRight(binaryTreeNode right) {
        this.right = right;
    }

    private boolean wellFormed = true;
    private int val;
    private binaryTreeNode left, right;

    //checks if any children have multaple parents within the same tree
    private List<binaryTreeNode> checktreeForm(List<binaryTreeNode> foundNodes, List<binaryTreeNode> twoParents, binaryTreeNode bTNode, boolean Fix){
        /*if(!foundNodes.contains(bTNode)){
            foundNodes.add(bTNode);
        }
        else {
            twoParents.add(bTNode);
        }*/
        if(!foundNodes.contains(bTNode.getLeft())){
            foundNodes.add(bTNode.getLeft());
        }
        else {
            if(Fix)
                bTNode.setRight(null);
            else
                twoParents.add(bTNode);

        }
        if(!foundNodes.contains(bTNode.getRight())){
            foundNodes.add(bTNode.getRight());
        }
        else {
            if(Fix)
                bTNode.setRight(null);
            else
                twoParents.add(bTNode.getRight());

        }



        //boolean left=true, right=true;
        if(bTNode.getLeft() != null)
            twoParents = checktreeForm(foundNodes, twoParents, bTNode.getLeft(), Fix);
        if(bTNode.getRight() != null)
            twoParents = checktreeForm(foundNodes, twoParents, bTNode.getRight(), Fix);

        return twoParents;
    }





}
