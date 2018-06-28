import java.util.*;

//Node object
public class Node {

    // size of these array list can not be larger than 2 in total
    ArrayList<ClusterPoint> pointsLeaf = new ArrayList<>();
    ArrayList<Node> nodeLeaf = new ArrayList<>();
    ArrayList<Node> parentNode = new ArrayList<>();

    public ArrayList<Node> getParent(){
        return this.parentNode;
    }

    public void setParent(Node p){
        this.parentNode.add(p);
    }

    public void insertPoint(ClusterPoint p) {
        this.pointsLeaf.add(p);
    }

    public void insertNode(Node n) {
        this.nodeLeaf.add(n);
    }

    public ArrayList<ClusterPoint> getPoints() {

        return this.pointsLeaf;
    }

    public ArrayList<Node> getNodes() {

        return this.nodeLeaf;
    }

    public boolean containLeaf() {
        boolean leafExist = false;

        if (this.pointsLeaf.size() > 0) {
            leafExist = true;
        }

        return leafExist;
    }

    public void clearPoints() {
        this.pointsLeaf.clear();
    }

    // just for test cases
    public boolean validNode() {
        boolean isValid = false;
        if (pointsLeaf.size() == 2 && nodeLeaf.size() == 0 || pointsLeaf.size() == 0 && nodeLeaf.size() == 2
                || pointsLeaf.size() == 1 && nodeLeaf.size() == 1) {

            isValid = true;

        }
        return isValid;
    }

}