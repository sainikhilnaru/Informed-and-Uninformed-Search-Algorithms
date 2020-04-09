import java.util.ArrayList;
import java.util.List;

public class UninformedSearch {
    public UninformedSearch(){}

    public List<Node> BFS(Node root){
        List<Node> pathToSolution = new ArrayList<Node>();
        List<Node> openList = new ArrayList<Node>();
       List<Node> closedList = new ArrayList<Node>();

        openList.add(root);
        boolean goalFound =false;
        int nodesVisted =0;

        int maxLength = 0;
        while (openList.size() > 0 && !goalFound){
            if(openList.size()> maxLength) {maxLength=openList.size();}
            Node currentNode = openList.get(0); //changed from [] to get
                closedList.add(currentNode);
                openList.remove(0);
                nodesVisted++;
                //System.out.println("The number of nodes visited are: " + nodesVisted);

                currentNode.ExpandNode();
                //print puzzle for debuggging below
                //currentNode.printPuzzle();


                for (int i = 0; i < currentNode.children.size(); i++) {
                    Node currentChild = currentNode.children.get(i);
                    if (currentChild.goalTest()) {
                        System.out.println("GOAL IS FOUND!!!!!!!");
                        goalFound = true;
                        //trace path to root node
                        pathToNode(pathToSolution, currentChild);
                    }

                    if (!Contains(openList, currentChild) && !Contains(closedList, currentChild)) {
                        //if open list contains current child and
                        //closed list contains current child
                        openList.add(currentChild);
                    }
                }

        }
        System.out.println("The maximum length of the node list is: " + maxLength);
        System.out.println("The number of nodes visited are: " + closedList.size());

        return pathToSolution;
    }


    public List<Node> DFS(Node root){
        List<Node> pathToSolution = new ArrayList<Node>();
        List<Node> openList = new ArrayList<Node>();
        List<Node> closedList = new ArrayList<Node>();

        openList.add(root);
        boolean goalFound =false;
        int nodesVisited=0;
        int maxLength = 0;
        while (openList.size() > 0 && !goalFound){
            if(openList.size()> maxLength) {maxLength=openList.size();}
            Node currentNode = openList.get(0); //changed from [] to get
            closedList.add(currentNode);
            openList.remove(0);
            //System.out.println("The number of nodes visited are: " + nodesVisited);

            if (currentNode.goalTest()) {
                System.out.println("GOAL IS FOUND!!!!!!!");
                goalFound = true;
                //trace path to root node
                pathToNode(pathToSolution, currentNode);
            }

            if(currentNode.children != null) {
                currentNode.ExpandNode();
            }
            //print puzzle for debuggging below
            //currentNode.printPuzzle();


           for (int i = 0; i < currentNode.children.size(); i++) {
               if (!Contains(openList, currentNode.children.get(i)) && !Contains(closedList, currentNode.children.get(i))) {
                   openList.add(currentNode.children.get(i));
               }
           }
        }
        System.out.println("The number of nodes visited are: " + closedList.size());
        System.out.println("The maximum length of the node list is: " + maxLength);

        return pathToSolution;
    }


    public List<Node> IDS(Node root){
        //depthlimit starts at depth = 0
        int depthLimit = 10;
        int depth = 0;
        while(depth <=depthLimit) {

            List<Node> pathToSolution = new ArrayList<Node>();
            List<Node> pathToParent = new ArrayList<Node>();

            List<Node> openList = new ArrayList<Node>();
            List<Node> closedList = new ArrayList<Node>();

            openList.add(root);
            boolean goalFound = false;
            int nodesVisited= 0;
            //double max_nodes_closed_list= ((Math.pow(4,depthLimit+1))-1)/3;
            //&& closedList.size() <= max_nodes_closed_list
            int maxLength =0;
            while (openList.size() > 0 && !goalFound) {
                //closed list cannot exceed (4^0 + 4^1...4^depthlimit)
                if(openList.size()> maxLength) {maxLength=openList.size();}

                Node currentNode = openList.get(0); //changed from [] to get
                closedList.add(currentNode);
                openList.remove(0);
                //System.out.println("The number of nodes visited are: " + nodesVisited);

                pathToNode(pathToParent, currentNode);
                depth++;
                if (pathToParent.size() - 1 <= (depth)) {
                    if (currentNode.goalTest()) {
                        System.out.println("GOAL IS FOUND!!!!!!!");
                        goalFound = true;
                        //trace path to root node
                        pathToNode(pathToSolution, currentNode);
                    }

                    if (currentNode.children != null) {
                        currentNode.ExpandNode();
                    }
                    //print puzzle for debuggging below
                    //currentNode.printPuzzle();

                    for (int i = 0; i < currentNode.children.size(); i++) {
                        openList.add(currentNode.children.get(i));
                    }

                }
                pathToParent.clear();
            }
            System.out.println("The number of nodes visited are: " + closedList.size());
            System.out.println("The maximum length of the node list is: " + maxLength);

            return pathToSolution;
        }
        return null;

    }





    public void pathToNode(List<Node> path, Node n){
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
}


//            while(currentNode.children.get(0) != null){
//                Node currentChild = currentNode.children.get(0);
//                if (currentChild.goalTest()) {
//                    System.out.println("GOAL IS FOUND!!!!!!!");
//                    goalFound = true;
//                    //trace path to root node
//                    pathToNode(pathToSolution, currentChild);
//                }
//
//                if (!Contains(openList, currentChild) && !Contains(closedList, currentChild)) {
//                    //if open list contains current child and
//                    //closed list contains current child
//                    openList.add(currentNode.children.get(i));
//                }
//            }
//            currentNode = currentNode.parent;