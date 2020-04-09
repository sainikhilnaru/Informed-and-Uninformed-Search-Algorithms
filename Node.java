import java.util.*;

public class Node {


    public List<Node> children = new ArrayList<>();
    public Node parent;
    //public Node currentNode;
    //public Node goalNode;
    public int[] puzzle = new int[9];
    public int x = 0;
    public int col =3;
    public int totalHeuristicCost;

    int a1;
    int b1;
    int a2;
    int b2;

    public int depth = 0;
    public int manhattanDistance;
    public int[] goalPuzzle={1,2,3,8,0,4,7,6,5};

    public int misplacedTiles;

    public Node(int[] p){
        setGoalNode(p);
        manhattanDistance=h2_manhattanDistance();
        misplacedTiles = h1_misplacedTiles();
    }
    public void setGoalNode(int[] p){
        for(int i =0; i < puzzle.length;i++){
            this.puzzle[i] = p[i];
        }

    }

    public int h_totalCost(Node currentNode){
        Node goalNode = new Node(goalPuzzle);
         totalHeuristicCost = misplacedTiles + manhattanDistance;
        return totalHeuristicCost;
    }

    public int  h1_misplacedTiles(){
        misplacedTiles =0;
        for (int i = 0; i <9 ; i ++) {
            if (puzzle[i]!= goalPuzzle[i] ){
                misplacedTiles ++;
            }
        }
        return  misplacedTiles;
    }

    public int setCoordinatesx(int index){
        int row= 0;
        switch(index) {
            case 0 :
                row++;
                break;
            case 1 :
                row++;
                break;
            case 2 :
                row++;
                break;
            case 3 :
                row =+2;
                break;
            case 4 :
                row =+2;
                break;
            case 5 :
                row =+2;
                break;
            case 6 :
                row =+3;
                break;
            case 7 :
                row =+3;
                break;
            case 8 :
                row =+3;
                break;
            default :
                System.out.println("Invalid index");
                break;
        }
        return row;
    }

    public int setCoordinatesy(int index){
        int column=0;
        switch(index) {
            case 0 :
                column++;
                break;
            case 1 :
                column=+2;
                break;
            case 2 :
                column=+3;
                break;
            case 3 :
                column++;
                break;
            case 4 :
                column=+2;
                break;
            case 5 :
                column=+3;
                break;
            case 6 :
                column++;
                break;
            case 7 :
                column=+2;
                break;
            case 8 :
                column=+3;
                break;
            default :
                System.out.println("Invalid index");
                break;
        }
        return column;
    }


    public int h2_manhattanDistance(){
        puzzles pz =new puzzles();
        int indexNode;
        int indexGoal;
        int md;
         a1=0;
         b1=0;
         a2=0;
         b2=0;

        manhattanDistance=0;
        for (int i =0; i<9;i++){
            for(int j=0;j<9;j++){
                if(this.puzzle[j]==i){
                    indexNode=j;
                    a1 = setCoordinatesx(indexNode);
                    b1 = setCoordinatesy(indexNode);
                }
            }

            for(int k =0;k<9;k++){
                if(pz.goalpuzzle[k]==i){
                    indexGoal=k;
                    a2 = setCoordinatesx(indexGoal);
                    b2 = setCoordinatesy(indexGoal);
                }
            }
            md = Math.abs((b2 - b1) + (a2 - a1));
            manhattanDistance = manhattanDistance + md;
        }
        return manhattanDistance;
    }



    public void ExpandNode(){
        for(int i =0; i < puzzle.length; i++){
            if(puzzle[i] == 0){
                x = i;
            }
        }
        up(puzzle,x);
        down(puzzle,x);
        left(puzzle, x);
        right(puzzle, x);


    }

    public void right(int[] p, int i){
        if( i % col < col - 1){
            int[] pc = new int[9];
            copyPuzzle(pc,p);

            int temp = pc[i+1];
            pc[i+1]=pc[i];
            pc[i]=temp;

            Node child = new Node(pc);
            children.add(child);
            child.parent =this;
        }
    }

    public void left(int[] p, int i){
        if( i % col > 0){
            int[] pc = new int[9];
            copyPuzzle(pc,p);

            int temp = pc[i-1];
            pc[i-1]=pc[i];
            pc[i]=temp;

            Node child = new Node(pc);
            children.add(child);
            child.parent =this;
        }
    }


    public void up(int[] p, int i){
        if( i - col >= 0){
            int[] pc = new int[9];
            copyPuzzle(pc,p);

            int temp = pc[i-3];
            pc[i-3]=pc[i];
            pc[i]=temp;

            Node child = new Node(pc);
            children.add(child);
            child.parent =this;
        }
    }

    public void down(int[] p, int i){
        if( i + col < puzzle.length){
            int[] pc = new int[9];
            copyPuzzle(pc,p);

            int temp = pc[i+3];
            pc[i+3]=pc[i];
            pc[i]=temp;

            Node child = new Node(pc);
            children.add(child);
            child.parent =this;
        }
    }

    public void printPuzzle(){
        System.out.println();
        int m = 0;
        for(int i = 0;i < col; i++){
            for(int j =0; j < col; j++ ){
                System.out.print(puzzle[m] + " ");
                m++;
            }
            System.out.println();
        }
    }

    public boolean isSamePuzzle(int[] p){
        boolean samePuzzle = true;
        for(int i =0; i < p.length; i++){
            if(puzzle[i] != p[i]){
                samePuzzle = false;
            }
        }
        return samePuzzle;
    }

    public void copyPuzzle(int a[] , int b[]){
        for(int i =0; i < b.length;i++){
            a[i] = b[i];
        }
    }

    public boolean goalTest(){
        boolean isGoal = false;
//        int m = puzzle[0];
//        for(int i =0; i < puzzle.length; i++){
//            if(m > puzzle[i]){
//                isGoal = false;
//            }
//            m = puzzle[i];
//        }

        if (puzzle[0] == 1 && puzzle[1] == 2 && puzzle[2] == 3
                && puzzle[3] == 8 && puzzle[4] == 0 && puzzle[5] == 4
                && puzzle[6] == 7 && puzzle[7] == 6 && puzzle[8] == 5){
            isGoal =true;
        }

        return isGoal;
    }
}
