import java.util.*;

public class Dijkstra {
    public Node start;
    public Node target;
    public Queue<Node> queue = new PriorityQueue<>();
    public Set<Node> done = new HashSet<>();

    public Stack<Node> bestPath = new Stack<>();

    public static void main(String[] args) {
        new Dijkstra();
    }

    public Dijkstra() {
        createGraph();
        start.cost = 0;
        queue.add(start);
        while(!queue.isEmpty()) {
            Node next = queue.poll();
            if(next == target) break;
            iter(next);
        }
        done();
    }

    public void iter(Node current) {
        if(done.contains(current)) return; else done.add(current);

        current.neighbours.keySet().stream()
                .filter(n -> (current.neighbours.get(n) + current.cost) < n.cost)
                .forEach(n -> {
                    n.cost = current.cost + current.neighbours.get(n);
                    n.parent = current;
                    queue.add(n);
                });
    }

    public void done() {
        System.out.println("Done!");
        System.out.println("Cost: " + target.cost);
        System.out.print("Best Way");
        Node prior = target;
        while(prior != null) {
            bestPath.push(prior);
            prior = prior.parent;
        }
        while(!bestPath.isEmpty()) {
            System.out.print( " -> " + bestPath.pop());
        }
    }

    public void createGraph() {
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
        start = a;
        target = g;
    }
}
