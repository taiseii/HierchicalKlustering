import java.util.*;

public class HKlusteringKai {
    ClusterPoint[] cPoints;
    int k, n;

    HKlusteringKai(ClusterPoint[] cp, int k, int n) {
        this.cPoints = cp;
        this.k = k;
        this.n = n;
    }

    public ArrayList<Node> getRoot() {
        ArrayList<Node> keepNode = new ArrayList<>();
        TreeMap<Double, List<Integer>> Q = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double curDistKey = cPoints[i].sqDistanceTo(cPoints[j]);
                    Double distKey = new Double(curDistKey);
                    List<Integer> valueIndex = new ArrayList<>();
                    valueIndex.add(i);
                    valueIndex.add(j);
                    Q.put(distKey, valueIndex);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            cPoints[i].setCluster(i);
        }

        // TreeMap is used therefore, Q is sorted upon creation
        for (Map.Entry q : Q.entrySet()) {

            List<Integer> IndexPoints = Q.get(q.getKey());

            if (cPoints[IndexPoints.get(0)].getCluster() != cPoints[IndexPoints.get(1)].getCluster()) {

                //parent
                Node w = new Node();

                //contain point i
                Node u = new Node();
                u.insertPoint(cPoints[IndexPoints.get(0)]);
                //contain point j
                Node v = new Node();
                v.insertPoint(cPoints[IndexPoints.get(1)]);

                FindRoot fru = new FindRoot(u);
                FindRoot frv = new FindRoot(v);
                Node uR = fru.getRoot();
                Node vR = frv.getRoot();
                w.insertNode(uR);
                w.insertNode(vR);
                uR.setParent(w);
                vR.setParent(w);
                keepNode.add(w);

                if( keepNode.size() >= n - k ){
                    break;
                }

                ArrayList<ClusterPoint> Upoints = new ArrayList<>();
                ExPoints exp = new ExPoints( w );
                Upoints = exp.getU();

                //updating the labeling
                for(int i=0; i<Upoints.size(); i++){
                    for(int j=0; j<n; j++){
                        if(Upoints.get(i).getCoords()[0] == cPoints[j].getCoords()[0]){
                            if(Upoints.get(i).getCoords()[1] == cPoints[j].getCoords()[1]){
                                cPoints[j].setCluster( IndexPoints.get(1) );
                            }
                        }
                    }
                }

            }
        }

        ArrayList<Node> F_prodakt = new ArrayList<>(keepNode.subList(keepNode.size()-k, keepNode.size()));
        return F_prodakt;
    }

}