package testclasses;

import dataTypes.binaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.round;

public class buildBTree {

    public binaryTreeNode createRandBTree(int nodeCount) {
        //create root
        List<binaryTreeNode> bTList = new ArrayList<binaryTreeNode>();
        if (nodeCount <= 0) {
            System.out.println("null return tree");
            return null;
        }
        binaryTreeNode root = new binaryTreeNode();
        root.init(0);
        if (nodeCount == 1) {
            return root;
        }

        else{

            double dNodeCount = (double) nodeCount;
            double dAverageDepth = Math.log(dNodeCount);
            int averageDepth = round((float) dAverageDepth + .99f);

            int maxdepth = (int) (averageDepth * 1.3);
            int mindepth = (int) (averageDepth * 0.7 + 0.5);
            if(averageDepth == maxdepth) maxdepth ++;
            System.out.println(averageDepth + " " + mindepth + " " + maxdepth);
            System.out.println(buildTree(root,1,maxdepth,mindepth,nodeCount-1) + " = nodes left when finished bt");

        }

        return root;
    }


    private int buildTree(binaryTreeNode parent, int currDepth, int maxDepth, int MinDepth, int remainingNodes) {
        //System.out.println("in build tree remainingNodes " + remainingNodes);
        boolean createleft=false, createright=false;
        Random r = new Random();
        if(currDepth <= MinDepth){
            createleft = true; createright=true;
        }
        else if(currDepth < maxDepth){
            //calc node chance
            float nodechance;
            int diff = maxDepth - MinDepth;
            if(diff == 1){
                //one differance, 70%
                int i = r.nextInt(100);
                if(i < 70) createleft=true;
                i = r.nextInt(100);
                if(i < 70) createright=true;

            }
            else if(currDepth == maxDepth ){
                int i = r.nextInt(100);
                if(i>90) createleft = true;
                i = r.nextInt(100);
                if(i>90) createright = true;
            }
            else{
                //get relative weight
                int currDiff = currDepth - MinDepth;
                float diffperpoint = (float)80/diff;
                //System.out.println(diffperpoint + " Diffperpoint");
                float chance = 100 - currDiff * diffperpoint;
                //System.out.println(chance + " chance");
                int i = r.nextInt(100);
                if(i<chance) createleft = true;
                i = r.nextInt(100);
                if(i<chance) createright = true;
            }

        }

        //now we know if we are creating each node

        if(remainingNodes > 0 &&createleft){
            binaryTreeNode left = new binaryTreeNode();
            left.init(remainingNodes);
            parent.setLeft(left);
            remainingNodes = buildTree(left,currDepth+1, maxDepth,MinDepth, remainingNodes-1);
        }
        if(remainingNodes > 0 &&createright){
            binaryTreeNode right = new binaryTreeNode();
            right.init(remainingNodes);
            parent.setRight(right);
            remainingNodes = buildTree(right,currDepth+1, maxDepth,MinDepth, remainingNodes-1);
        }

    return remainingNodes;


    }

    public void printBTreeDFS(binaryTreeNode root, int depth){
        System.out.println("val: " + root.getVal() + " depth: " + depth);
        if(root.getLeft() != null)
            printBTreeDFS(root.getLeft(), depth+1);

        if(root.getRight() != null)
        printBTreeDFS(root.getRight(), depth+1);
    }

    public binaryTreeNode balanceTree(binaryTreeNode root){
        int[] DAC = getDepthAndCountDFS(root, 1,0);
        System.out.println("depth: " + DAC[0] + " count: " + DAC[1]);
        double dAverageDepth = Math.log(DAC[1]);
        int max = round((float) dAverageDepth + .5f);
        int min = round((float) dAverageDepth - .5f);

        //run a dfs, flag any nodes beyond max depth, move them to a node above.
        //record list of nodes that have a free node where they shouldn't
        //add nodes below max depth to the node on the list, if no free nodes left, remove node from list.

        return  root;
    }

    private List<List<binaryTreeNode>> balTreeDFS(List<binaryTreeNode> freeChild, List<binaryTreeNode> moveChild, int minDepth, int maxDepth, int curDepth, binaryTreeNode root){
        binaryTreeNode left = root.getLeft();
        binaryTreeNode right = root.getRight();
        if(curDepth < minDepth && left != null && right != null){
            balTreeDFS(freeChild,moveChild, minDepth, maxDepth, curDepth+1, left);
            balTreeDFS(freeChild,moveChild, minDepth, maxDepth, curDepth+1, right);
        }

        return null;
    }

    private int[] getDepthAndCountDFS(binaryTreeNode root, int cDepth, int count){
        //max depth = retval[0]
        //count = retval[1]
        binaryTreeNode left = root.getLeft(), right = root.getRight();
        int[] retval = new int[2];

        if(left != null && right != null) {
            System.out.println("in both");
            //has both child nodes, find which is deeper, and the count of both.
            int[] leftBranch = getDepthAndCountDFS(root.getLeft(), cDepth+1, count+1);
            int[] rightBranch = getDepthAndCountDFS(root.getRight(), cDepth+1, count+1);

            if(leftBranch[0] >= rightBranch[0]) {
                retval[0] = leftBranch[0];
                retval[1] = leftBranch[1] + (rightBranch[1]-count-1);

            }
            else{
                retval[0] = rightBranch[0];
                retval[1] = leftBranch[1] + (rightBranch[1]-count-1);

            }
        }
        else if(left != null){
            System.out.println("in left");
            retval = getDepthAndCountDFS(root.getLeft(), cDepth+1,count+1);

            //only left node present
        }
        else if (right != null){
            System.out.println("in right");
            //only right node present
            retval = getDepthAndCountDFS(root.getRight(), cDepth+1,count+1);
        }
        else{

            //leaf node
            System.out.println("else");
            retval[0] = cDepth +1;
            retval[1] = count +1;
        }

        System.out.println("count: " + retval[1]);
        return retval;

    }



}