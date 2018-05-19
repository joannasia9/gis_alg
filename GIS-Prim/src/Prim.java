import java.util.*;

public class Prim {
    private boolean[] visitedVertex;
    private Queue<Edge> msTree;
    private Queue<Edge> queue;
    private long weight;

    public Prim(Graph graph) {
        visitedVertex = new boolean[graph.getNumberOfVertices()];
        msTree = new LinkedList<>();
        queue = new PriorityQueue<>(graph.getNumberOfVertices());


        Random rand = new Random();
        int randV = rand.nextInt(graph.getNumberOfVertices()-1);
        visit(graph, randV);
        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            int v = e.getStartVertex();
            int w = e.getEndVertex(v);

            if (visitedVertex[v] && visitedVertex[w]) {
                continue;
            }

            msTree.offer(e);

            weight += e.getWeight();

            if (!visitedVertex[v]) {
                visit(graph, v);
            }
            if (!visitedVertex[w]) {
                visit(graph, w);
            }
        }

    }

    public Iterable<Edge> edges() {
        return msTree;
    }

    public long getWeight() {
        return weight;
    }

    private void visit(Graph graph, int v) {
        visitedVertex[v] = true;
        for (Edge edge : graph.getAdjacencyList(v)) {
            if (!visitedVertex[edge.getEndVertex(v)]) {
                queue.offer(edge);
            }
        }
    }

}
