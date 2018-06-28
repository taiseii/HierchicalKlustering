import java.util.*;

public class ExPoints{
    //ArrayList<ClusterPoint> U = new ArrayList<>();
    Set<ClusterPoint> U = new HashSet<>();
    //Implement U that is not Set
    ArrayList<ClusterPoint> Ulist = new ArrayList<>();
    Queue<Node> Q = new LinkedList<>();

    
    ExPoints(Node u){
        this.Q.add(u);
    }

    public ArrayList<ClusterPoint> getU(){
        ArrayList<ClusterPoint> U2 = new ArrayList<>();
        
        while(this.Q.size() != 0){

            // System.out.println("Q size 1 : "+this.Q.size());
            Node uu = this.Q.remove();

            // System.out.println("2: "+Q.size());

            // System.out.println("contain leaf ?: "+uu.containLeaf());

            if( uu.containLeaf() ){

                // System.out.println("number of points 1 : " +uu.getPoints().size() );
                // System.out.println("number of nodes : "+ uu.getNodes().size()   );

                if( uu.getPoints().size() == 2){
                    //when node contain only points
                    this.U.addAll(uu.getPoints());
                    // Ulist.addAll( this.uu.getPoints() );

                } else {
                    //When node contain one point and no node
                    this.U.addAll(uu.getPoints());
                    // Ulist.addAll( uu.getPoints() );

                    if( uu.getNodes().size()==1 ){
                        //When node contain one point and one node
                        this.Q.add( uu.getNodes().get(0) );
                    }
                    
                }
             } else {
                //when parent node only contain 2 nodes
                this.Q.addAll( uu.getNodes() );
            }

            // System.out.println("3: "+Q.size());

        }

        // System.out.println("size of the set U: "+ U.size() );

        Iterator<ClusterPoint> iterator = this.U.iterator();
        while(iterator.hasNext()){
            ClusterPoint setCP = iterator.next();
            U2.add(setCP);
        }

        // System.out.println("size of the list U2: "+U2.size());
        
    
        // for(int i=0; i<U2.size(); i++){
        //     System.out.println("pX: "+ U2.get(i).getCoords()[0] );
        //     System.out.println("pY: "+ U2.get(i).getCoords()[1] );
        //     System.out.println("------");
        // }

         

        return U2 ;
    }



    //testing area
    public static void main(String[] args){

        //testing the class by adding stuff articifially
        Node node = new Node();

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
        
        node3.insertPoint(cp3);
        node3.insertPoint(cp4);

        node2.insertPoint(cp1);
        node2.insertPoint(cp2);

        node.insertNode(node3);
        node.insertNode(node2);

        ExPoints exp = new ExPoints(node);

        System.out.println("size: " +exp.getU().size() );
        for(int i=0; i<exp.getU().size(); i++){
            System.out.println("pX: "+ exp.getU().get(i).getCoords()[0] );
            System.out.println("pY: "+ exp.getU().get(i).getCoords()[1] );
            System.out.println("------");
        }
                
 
    }

}