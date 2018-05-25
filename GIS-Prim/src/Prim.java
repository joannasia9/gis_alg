import java.util.*;

public class Prim {
    private boolean[] visitedVertices;
    private Deque<Edge> minSpanningTree;
    private PriorityQueue<Edge> adjacencyEdges;
    private long weight;
    private int v;

    public Prim(Graph graph) {
        v = graph.getNumberOfVertices();
        visitedVertices = new boolean[v];
        minSpanningTree = new ArrayDeque<>();
        adjacencyEdges = new PriorityQueue<>(v);


        Random rand = new Random();

        long timeStart = System.currentTimeMillis();
        int randV = rand.nextInt(v - 1);

        // Oznacz wierzchołek jako odwiedzony
        // Dodaj do kolejki krawędzie incydentne do odwiedzonego wierzchołka
        visitVertex(graph, randV);


        while (!adjacencyEdges.isEmpty() || minSpanningTree.size()!= (v - 1)) {
            Edge edge = adjacencyEdges.poll(); // pobierz z kolejki krawędź
            int startVertex = edge.getStartVertex();
            int endVertex = edge.getEndVertex(startVertex);

            if (visitedVertices[startVertex] && visitedVertices[endVertex]) {
                continue;
            }

            minSpanningTree.push(edge);

            weight += edge.getWeight(); // dodaj wagę krawędzi do sumy

            if (!visitedVertices[startVertex]) {
                visitVertex(graph, startVertex);
            }
            if (!visitedVertices[endVertex]) {
                visitVertex(graph, endVertex);
            }
        }

        long timeStop = System.currentTimeMillis();
        System.out.println("CZAS WYKONYWANIA I PROGRAMU WYNIÓSŁ: "+ (timeStop-timeStart) + " milisekund");

    }

    public Iterable<Edge> edges() {
        return minSpanningTree;
    }

    public long getWeight() {
        return weight;
    }

    private void visitVertex(Graph graph, int v) {
        visitedVertices[v] = true;
        for (Edge edge : graph.getAdjacencyList(v)) {
            if (!visitedVertices[edge.getEndVertex(v)]) {
                adjacencyEdges.offer(edge); // dodaj krawędź do kolejki
            }
        }
    }

}
