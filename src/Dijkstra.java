import java.util.*;

public class Dijkstra {
    public static Node current;
    public static Queue<Node> queue = new PriorityQueue<>();
    public static Set<Node> done = new HashSet<>();

    public static Stack<Node> bestPath = new Stack<>();

    public static void main(String[] args) {
        createGraph();
        current.cost = 0;
        queue.add(current);
        while(!queue.isEmpty()) {
            iter();
        }
        done();
    }

    public static void iter() {
        current = queue.poll();
        done.add(current);

        current.neighbours.keySet().stream()
                .filter(n -> (current.neighbours.get(n) + current.cost) < n.cost && !done.contains(n))
                .forEach(n -> {
                    n.cost = current.cost + current.neighbours.get(n);
                    n.parent = current;
                    queue.add(n);
                });
    }

    public static void done() {
        System.out.println("Done!");
        System.out.println("Cost: " + current.cost);
        System.out.print("Best Way");
        while(current != null) {
            bestPath.push(current);
            current = current.parent;
        }
        while(!bestPath.isEmpty()) {
            System.out.print( " -> " + bestPath.pop());
        }
    }

    public static void createGraph() {
        Node a = new Node("A"), b = new Node("B"), c = new Node("C"), d = new Node("D"), e = new Node("E"), f = new Node("F"), g = new Node("G");
        a.addNeighbour(b, 4);
        a.addNeighbour(c, 8);
        a.addNeighbour(d, 3);
        b.addNeighbour(e, 7);
        b.addNeighbour(c, 2);
        c.addNeighbour(e, 3);
        c.addNeighbour(f, 2);
        c.addNeighbour(d, 4);
        d.addNeighbour(f, 2);
        e.addNeighbour(g, 2);
        e.addNeighbour(f, 6);
        f.addNeighbour(g, 7);
        current = a;
    }
}
