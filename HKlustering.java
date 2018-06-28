import java.util.*;

public class HKlustering {

    ClusterPoint[] points;
    int n, k;

    HKlustering(ClusterPoint[] points, int n, int k) {
        this.points = points;
        this.n = n;
        this.k = k;
    }

    // helper function to check whether 2 nodes are the same or not
    public boolean sameNode(Node u, Node v) {
        boolean isSame = false;

        // call extractPoints and compare all the points
        ExPoints pU = new ExPoints(u);
        ExPoints pV = new ExPoints(v);
        ArrayList<ClusterPoint> sameU = new ArrayList<>();
        ArrayList<ClusterPoint> sameV = new ArrayList<>();

        System.out.println(pU.Q.size());
        System.out.println(pV.Q.size());

        // these 2 arrayList contain only ClusterPoints
        sameU = pU.getU();
        sameV = pV.getU();

        System.out.println("sameU size: " + pU.getU().size());
        System.out.println("sameV size: " + pV.getU().size());

        // compare amount of points
        if (sameV.size() == 0) {
            // using the bug property here :(
            isSame = true;
        } else if (sameU.size() != sameV.size()) {
            isSame = false;
        } else {
            int target = sameU.size() * 2;
            int targetCounter = 0;
            for (int i = 0; i < sameU.size(); i++) {
                for (int d = 0; d < 2; d++) {
                    if (sameU.get(i).getCoords()[d] == sameV.get(i).getCoords()[d]) {
                        targetCounter++;
                    }
                }
            }
            if (target == targetCounter) {
                isSame = true;
            }
        }

        System.out.println(isSame);
        return isSame;
    }

    public ArrayList<Node> computeRoot() {
        ArrayList<Node> Nodes = new ArrayList<>();
        // Creating n number of nodes here
        for (int i = 0; i < n; i++) {
            Node node_u = new Node();
            node_u.insertPoint(points[i]);
            Nodes.add(node_u);
        }

        
        ArrayList<Node> NodeU_carrier = new ArrayList<>();
        ArrayList<Node> NodeV_carrier = new ArrayList<>();

        while (Nodes.size() != k) {

            

            double minDist = Double.MAX_VALUE;
            for (int i = 0; i < Nodes.size(); i++) {
                for (int j = 0; j < Nodes.size(); j++) {

                    if (i != j) {

                        SingleLinkDistance sld = new SingleLinkDistance(Nodes.get(i), Nodes.get(j));
                        double curDist = sld.getMinDist();
                        
                        // System.out.println("Min dist: " + minDist);
                        // System.out.println("Cur dist: " + curDist);

                        if (minDist > curDist) {
                            minDist = curDist;
                            NodeU_carrier.add(Nodes.get(i));
                            NodeV_carrier.add(Nodes.get(j));
                        }
                        // System.out.println(NodeU_carrier.size() +" :U_0_0_V: "+NodeV_carrier.size());
                        if(NodeU_carrier.size() > 1 || NodeV_carrier.size()>1){
                            // System.out.println(NodeU_carrier.size() +" :U_1_1_V: "+NodeV_carrier.size());
                            NodeU_carrier.remove(0);
                            NodeV_carrier.remove(0);
                        }
                    }
                }
            }

            if( !NodeU_carrier.isEmpty() && !NodeV_carrier.isEmpty() ){
                Node W = new Node();
                W.insertNode(NodeU_carrier.get(0));
                W.insertNode(NodeV_carrier.get(0));
                NodeU_carrier.get(0).setParent(W);
                NodeV_carrier.get(0).setParent(W);
                Nodes.remove(Nodes.indexOf( NodeU_carrier.get(0) ));
                Nodes.remove(Nodes.indexOf( NodeV_carrier.get(0) ));
                Nodes.add(W);
            }


            //  System.out.println("Nodes size: " + Nodes.size());
        }

        
        return Nodes;

    }

    // Testing purpose only
    public static void main(String[] args) {
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();

        // 6 unique ClusterPoints
        double[] cord1 = { 3.5, 6.7 };
        ClusterPoint cp1 = new ClusterPoint(2, cord1);
        double[] cord2 = { 4.5, 7.9 };
        ClusterPoint cp2 = new ClusterPoint(2, cord2);
        double[] cord3 = { 8.5, 3.9 };
        ClusterPoint cp3 = new ClusterPoint(2, cord3);
        double[] cord4 = { 9.1, 6.1 };
        ClusterPoint cp4 = new ClusterPoint(2, cord4);
        double[] cord5 = { 1.5, 3.5 };
        ClusterPoint cp5 = new ClusterPoint(2, cord5);
        double[] cord6 = { 4.1, 9.1 };
        ClusterPoint cp6 = new ClusterPoint(2, cord6);

        node2.insertPoint(cp1);
        node2.insertPoint(cp2);

        node3.insertPoint(cp3);
        node3.insertPoint(cp4);

        node4.insertPoint(cp5);
        node4.insertPoint(cp6);

        node5.insertNode(node4);
        node5.insertNode(node3);

        Node node3cp = node3;
        // Node node4cp = node4.clone();

        System.out.println("node4: " + node4.getPoints().size());
        // System.out.println("node4cp: " + node4cp.getPoints().size());
        System.out.println("node3: " + node3.getPoints().size());
        System.out.println("node3cp: " + node3cp.getPoints().size());

        System.out.println("-----------------");

        // HKlustering hkl = new HKlustering();

        // System.out.println( hkl.sameNode(node4, node4cp) );

    }

}