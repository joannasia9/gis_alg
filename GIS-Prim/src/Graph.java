import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int vertices;
    private int edges;
    private List<Edge>[] adjacencyLists;


    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;
        this.adjacencyLists = (List<Edge>[]) new List[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyLists[i] = new ArrayList<>();
        }
    }

    public int getNumberOfEdges() {
        return edges;
    }

    public int getNumberOfVertices() {
        return vertices;
    }

    public void addEdge(Edge edge) {
        int v = edge.getStartVertex();
        int w = edge.getEndVertex(v);
        adjacencyLists[v].add(edge);
        adjacencyLists[w].add(edge);
        edges++;
    }

    public Iterable<Edge> getAdjacencyList(int v) {
        return adjacencyLists[v];
    }
}
