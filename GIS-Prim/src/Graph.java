import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int v;
    private int e;
    private List<Edge>[] adjacencyLists;

    @SuppressWarnings("unchecked")
    public Graph(int v) {
        this.v = v;
        this.e = 0;
        this.adjacencyLists = (List<Edge>[]) new List[v];
        for (int i = 0; i < v; i++) {
            adjacencyLists[i] = new ArrayList<>();
        }
    }

    public int getNumberOfEdges() {
        return e;
    }

    public int getNumberOfVertices() {
        return v;
    }

    public void addEdge(Edge edge) {
        int v = edge.getStartVertex();
        int w = edge.getEndVertex(v);
        adjacencyLists[v].add(edge);
        adjacencyLists[w].add(edge);
        e++;
    }

    public Iterable<Edge> getAdjacencyList(int v) {
        return adjacencyLists[v];
    }
}
