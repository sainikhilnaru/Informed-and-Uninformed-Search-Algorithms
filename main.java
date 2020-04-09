import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class main {

    public static void main(String[] args)
    {

    puzzles p = new puzzles();
    Node root = new Node(p.hard);



    UninformedSearch ui = new UninformedSearch();
    InformedSearch is = new InformedSearch();

    List<Node> solution = ui.BFS(root);
    //List<Node> solution = ui.DFS(root);
    //List<Node> solution = ui.IDS(root);
     //List<Node> solution = is.GreedyBFS(root);
    //List<Node> solution = is.aStar(root);
    if(solution.size() > 0){
        Collections.reverse(solution);
        for (int i = 0; i < solution.size(); i++){
            solution.get(i).printPuzzle();
        }
        //System.out.println("The depth of the tree is : " + (solution.size() - 1) );
    }
    else {
        System.out.println("No path to solution found");
    }

//        eightPuzzle initialState = new eightPuzzle();
//
//
//        initialState.add(initialState.rootNode,null);
//        initialState.up(initialState.statehistory.lastElement());
//        initialState.down(initialState.statehistory.lastElement());
//        initialState.left(initialState.statehistory.lastElement());
//        initialState.right(initialState.statehistory.lastElement());
//
//        System.out.println("Elements of queue are : "+ initialState.nodes);
//

    }

}
