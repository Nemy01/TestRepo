package dataTypes;

import java.util.ArrayList;
import java.util.List;

public class graphNode {
    private int val;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public List<graphNode> getConnections() {
        return connections;
    }

    public void addConnection(graphNode g){
        connections.add(g);
    }

    public void removeConnection(graphNode g){
        connections.remove(g);
    }

    public void removeConnections(ArrayList<graphNode> g){
        for (graphNode n : g ) {
            connections.remove(n);

        }
    }

    public void addConnections(ArrayList<graphNode> g){
        for ( graphNode n : g){
            connections.add(n);
        }
    }

    public void setConnections(List<graphNode> connections) {
        this.connections = connections;
    }

    private List<graphNode> connections;

    public void init(int nodeVal, List<graphNode> conn){
        val = nodeVal;
        if(conn != null) connections = conn;
        else connections = new ArrayList();
    }

    public void init(int val){
        init(val, null);
    }

}
