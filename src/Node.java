import java.util.HashMap;
import java.util.Map;

public class Node implements Comparable<Node> {
    public Map<Node, Integer> neighbours;
    public int cost;
    public Node parent;

    private final String name;

    public Node(String name) {
        neighbours = new HashMap<>();
        cost = Integer.MAX_VALUE;
        this.name = name;
    }

    public void addNeighbour(Node node, int weight) {
        neighbours.put(node, weight);
        node.neighbours.put(this, weight);
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }

    @Override
    public String toString() {
        return name;
    }
}
