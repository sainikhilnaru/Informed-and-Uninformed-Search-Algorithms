import java.util.Comparator;

public class NodePriorityComparator1 implements Comparator<Node> {

    @Override
    public int compare(Node x, Node y) {
        int x_h1 = x.h_totalCost(x);
        int y_h1 = y.h_totalCost(y);
        if (x_h1 > y_h1) {
            return 1;
        }
        if (x_h1 < y_h1) {
            return -1;
        }
        return 0;
    }
}