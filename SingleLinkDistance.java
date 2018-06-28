import java.util.*;

public class SingleLinkDistance{
    ArrayList<ClusterPoint> U = new ArrayList<>();
    ArrayList<ClusterPoint> V = new ArrayList<>();

    SingleLinkDistance(Node u, Node v){
        ExPoints EU = new ExPoints(u);
        U.addAll(EU.getU());
        ExPoints EV = new ExPoints(v);
        V.addAll(EV.getU());
    }



    public double getMinDist(){

        // System.out.println("U size:: "+U.size());
        // System.out.println("V size:: "+V.size());

        double minDist = Double.MAX_VALUE;

        for(int i=0; i<U.size(); i++){
            for(int j=0; j<V.size(); j++){
                double curDist = U.get(i).sqDistanceTo(V.get(j));
                if(curDist<minDist){
                    minDist = curDist;
                }
            }
        }
        // System.out.println("minDist: "+minDist);
        return minDist;
    }

    //testing of the component
    public static void main(String[] args){

        Node node2 = new Node();
        Node node3 = new Node();

        double[] cord1 = {3.5, 6.7};
        ClusterPoint cp1 = new ClusterPoint(2, cord1);
        double[] cord2 = {4.5, 7.9};
        ClusterPoint cp2 = new ClusterPoint(2, cord2);
        double[] cord3 = {8.5, 3.9};
        ClusterPoint cp3 = new ClusterPoint(2, cord3);
        double[] cord4 = {9.1, 6.1};
        ClusterPoint cp4 = new ClusterPoint(2, cord4);

        node2.insertPoint(cp1);
        node2.insertPoint(cp2);

        node3.insertPoint(cp3);
        node3.insertPoint(cp4);


        SingleLinkDistance sld = new SingleLinkDistance(node2, node3);
        System.out.println("Min dist: "+sld.getMinDist() );


        
    }


}