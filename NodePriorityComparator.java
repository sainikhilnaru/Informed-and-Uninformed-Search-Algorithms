import java.util.Comparator;

public class NodePriorityComparator implements Comparator<Node> {

    @Override
    public int compare(Node x, Node y) {
        int x_h1 = x.h1_misplacedTiles();
        int y_h1 = y.h1_misplacedTiles();
        if (x_h1 > y_h1) {
            return 1;
        }
        if (x_h1 < y_h1) {
            return -1;
        }
        return 0;
    }
}