import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class eightPuzzle {
    Queue<String> nodes = new LinkedList<>();
    Stack<String> statehistory = new Stack<>();

    String rootNode ="123456780";
    String goalNode = "123456087";
    int depth;

    void add(String newState, String oldState){
        if(oldState == null){
            nodes.add(newState);
        }
        else {
            nodes.add(newState);
        }
        statehistory.add(newState);
    }

    void up(String currentState){
        int a = currentState.indexOf("0");
        if(a>2){
            String nextState = currentState.substring(0,a-3)
                    +"0"
                    +currentState.substring(a-2,a)
                    +currentState.charAt(a-3)
                    +currentState.substring(a+1);
            //checkIfGoalNode(currentState, nextState);
            nodes.add(nextState);
            statehistory.add(nextState);
        }
    }

    void down(String currentState){
        int a = currentState.indexOf("0");
        if(a<6){
            String nextState = currentState.substring(0,a)+currentState.substring(a+3,a+4)+currentState.substring(a+1,a+3)+"0"+currentState.substring(a+4);
            //checkIfGoalNode(currentState, nextState);
            nodes.add(nextState);
            statehistory.add(nextState);
        }
    }
    void left(String currentState){
        int a = currentState.indexOf("0");
        if(a!=0 && a!=3 && a!=6){
            String nextState = currentState.substring(0,a-1)+"0"+currentState.charAt(a-1)+currentState.substring(a+1);
            //checkIfGoalNode(currentState, nextState);
            nodes.add(nextState);
            statehistory.add(nextState);
        }
    }
    void right(String currentState){
        int a = currentState.indexOf("0");
        if(a!=2 && a!=5 && a!=8){
            String nextState = currentState.substring(0,a)+currentState.charAt(a+1)+"0"+currentState.substring(a+2);
            //checkIfGoalNode(currentState, nextState);
            nodes.add(nextState);
            statehistory.add(nextState);
        }
    }

    Boolean checkIfDuplicate(String oldstate, String newstate){
        for(int i=0;i<statehistory.size();i++){
            if(newstate.equals(statehistory.get(i))){
                return false;
            }
        }
        return true;
    }

    void checkIfGoalNode(String oldstate, String newstate){
        if(newstate.equals(goalNode)){
            System.out.println("Solution Exists at Level "+ depth +" of the tree");
        }
    }


}
