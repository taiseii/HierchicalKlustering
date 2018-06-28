public class FindRoot {
    Node y;

    FindRoot(Node y) {
        this.y = y;
    }

    public Node getRoot(){
        Node x = y;
        // System.out.println("parent size: "+x.getParent().size());
        while(x.getParent().size() > 0){
            x = x.getNodes().get(0);
        }


        return x;
    }
    
}