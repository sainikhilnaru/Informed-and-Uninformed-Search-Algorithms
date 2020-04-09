import org.omg.CORBA.NO_IMPLEMENT;

import java.util.*;

public class InformedSearch {
    public InformedSearch(){}

    public List<Node> GreedyBFS(Node root){
        List<Node> pathToSolution = new ArrayList<Node>();
        //List<Node> ol = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();

        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();
        PriorityQueue<Node>  openList= new PriorityQueue<Node>(nodePriorityComparator);

        openList.add(root);
        //ol.add(root);
        boolean goalFound =false;
        int nodesVisited = 0;
        int maxLength =0;
        while (openList.size() > 0 && !goalFound){
            if(openList.size()> maxLength) {maxLength=openList.size();}

            Node currentNode = openList.poll(); //changed from [] to get
            //System.out.println("The number of nodes visited are: " + nodesVisited);

            //Node currentNode1 = ol.get(0);
            closedList.add(currentNode);
            //openList.clear();

            //ol.remove(0);
            //currentNode.ExpandNode();

            if (currentNode.goalTest()) {
                System.out.println("GOAL IS FOUND!!!!!!!");
                goalFound = true;
                //trace path to root node
                pathTrace(pathToSolution, currentNode);
            }

            if(currentNode.children != null) {
                currentNode.ExpandNode();
               // currentNode1.ExpandNode();
            }
            //print puzzle for debuggging below
            //currentNode.printPuzzle();

            for (int i = 0; i < currentNode.children.size(); i++) {
               if (!Contains1(openList, currentNode.children.get(i)) && !Contains(closedList, currentNode.children.get(i))) {
                    openList.add(currentNode.children.get(i));
                    //openList.add(currentNode.children.get(i));
                    //System.out.println(openList);
               }
            }
            //System.out.println();
        }

        System.out.println("The number of nodes visited are: " + closedList.size());
        System.out.println("The maximum length of the node list is: " + maxLength);

        return pathToSolution;


    }

    public List<Node> aStar(Node root){
        List<Node> pathToSolution = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();

        NodePriorityComparator1 nodePriorityComparator = new NodePriorityComparator1();
        PriorityQueue<Node>  openList= new PriorityQueue<Node>(nodePriorityComparator);

        openList.add(root);
        boolean goalFound =false;
        int nodesVisited =0;
        int maxLength =0;
        while (openList.size() > 0 && !goalFound){
            if(openList.size()> maxLength) {maxLength=openList.size();}

            Node currentNode = openList.poll(); //changed from [] to get
            //System.out.println("The number of nodes visited are: " + nodesVisited);

            closedList.add(currentNode);

            if (currentNode.goalTest()) {
                System.out.println("GOAL IS FOUND!!!!!!!");
                goalFound = true;
                //trace path to root node
                pathTrace(pathToSolution, currentNode);
            }

            if(currentNode.children != null) {
                currentNode.ExpandNode();
            }
            //print puzzle for debuggging below
            //currentNode.printPuzzle();

            for (int i = 0; i < currentNode.children.size(); i++) {
                if (!Contains1(openList, currentNode.children.get(i)) && !Contains(closedList, currentNode.children.get(i))) {
                    openList.add(currentNode.children.get(i));
                }
            }
        }

        System.out.println("The number of nodes visited are: " + closedList.size());
        System.out.println("The maximum length of the node list is: " + maxLength);

        return pathToSolution;


    }

    public void pathTrace(List<Node> path, Node n){
        //System.out.println("Tracing path.........");
        Node current = n;
        path.add(current);

        while (current.parent != null){
            current = current.parent;
            path.add(current);
        }
    }

    public  static boolean Contains(List<Node> list, Node c){
        boolean contains = false;
        for (int i = 0; i < list.size();i++){
            if(list.get(i).isSamePuzzle(c.puzzle)){
                contains =true;
            }
        }
        return contains;
    }

    public  static boolean Contains1(PriorityQueue<Node> list, Node c){


        Iterator<Node> it = list.iterator();
        while (it.hasNext() ){
            if(it.next().isSamePuzzle(c.puzzle)){

                return true;
            }
        }
        return false;
    }

}
