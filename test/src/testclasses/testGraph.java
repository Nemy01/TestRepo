package testclasses;

import dataTypes.graphNode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Math.round;

public class testGraph {

    public List<graphNode> createRandGraph(int nodecount){
        graphNode gNodeArray[] = new graphNode[nodecount];
        Random r = new Random();
        int count = 0;
        //create graph nodes
        for(int i = 0; i < nodecount; i++){
            gNodeArray[i] = new graphNode();
            gNodeArray[i].init(i);
            //System.out.println(gNodeArray[i] + " at count " + count);
        }

        // ----connect graph nodes
        //max connections
        float  max = nodecount * 0.3f;
        int m = round(max);
        if(max < 1) max =1;
        //cycle through each node
        for(graphNode g : gNodeArray){
            //count = number of connections for graph
            //System.out.println(g);
            if(g != null) {
                count = r.nextInt(m);
                if (count != 0) {
                    //create array for connections
                    ArrayList<graphNode> gArr = new ArrayList<graphNode>();
                    for (int i = 0; i <= count; i++) {

                        graphNode gn = gNodeArray[r.nextInt(nodecount)];
                        if(!gArr.contains(gn))
                            gArr.add(gn);
                        else i--;
                    }
                    g.addConnections(gArr);
                }
            }

        }

        return Arrays.asList(gNodeArray);
    }

    public void printGraphInList(List<graphNode> gList){
        System.out.println("GNode : connections");
        for(graphNode g : gList){
            System.out.print(g.getVal() + " : ");
            for (graphNode gn : g.getConnections()){
                System.out.print(gn.getVal() + " ");
            }
            System.out.println();
        }

    }

    public List<graphNode> indUnreachableNodesf(List<graphNode> gList){
        List<graphNode> foundNodes = new ArrayList<graphNode>();
        //finding reachable nodes
        for(int i = 0; i < gList.size(); i++) {
            if (!foundNodes.contains(gList.get(i))) {
                foundNodes = DFS(foundNodes, gList.get(i));
            }

        }
        //now we have a list of all findable nodes, and all nodes
        //find nodes in all nodes but not in found nodes
        List<graphNode> unreachable = new ArrayList<graphNode>();
        List<graphNode> headNodes = new ArrayList<graphNode>();
        for(graphNode g : gList){
            if(!foundNodes.contains(g)){
                if(g.getConnections() == null) {
                    unreachable.add(g);
                    System.out.println("found unreachable " + g.getVal());
                }
                else {
                        headNodes.add(g);
                        System.out.println("found head " + g.getVal());
                }
            }



        }
        return headNodes;
    }
    public List<graphNode> DFS(List<graphNode> foundList, graphNode sNode){
        //if(sNode.getConnections() != null){
            for(graphNode g : sNode.getConnections()){
                //System.out.println(foundList.size());
                if(!foundList.contains(g)){
                    foundList.add(g);
                    foundList = DFS(foundList, g);

                }
            }
        //}
        return foundList;
    }

    public graphNode findGNodeVal(graphNode start, int targetVal){
        return DFSTargetVal(new ArrayList<graphNode>(), targetVal, start);
    }

    public graphNode DFSTargetVal (List<graphNode> foundList, int target, graphNode sNode){
        //if(sNode.getConnections() != null){
        graphNode t;
        for(graphNode g : sNode.getConnections()){
            //System.out.println(foundList.size());
            if(!foundList.contains(g)){
                foundList.add(g);
                if(g.getVal() == target)
                    return g;
                else {
                    t = DFSTargetVal(foundList, target, g);
                    //only return if successful, otherwise continue
                    if(t != null){
                        return t;
                    }
                }

            }
        }
        //}
        //the target was not found on this path, return null;
        return null;
    }


}
