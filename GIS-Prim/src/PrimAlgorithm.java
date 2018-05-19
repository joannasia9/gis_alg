
import java.util.*;

public class PrimAlgorithm{
    /**
     * Element losowości
     */
    Random rand;
    /**
     * Lista odwiedzonych wierzchołków
     */
    private ArrayList<Integer> visitedVertices;

    /**
     * Lista wszystkich wierzchołków
     */
    private ArrayList<Edge> adjacencyEdgesList;


    /**
     * Konstruktor
     * Odpowiada za wykonanie poszczególnych kroków algorytmu
     *
     * @param  graph  macierz sąsiedztwa reprezentująca graf
     *
     */
    public PrimAlgorithm(final int[][] graph){
        this.adjacencyEdgesList = new ArrayList<>();

        this.visitedVertices = new ArrayList<>();

        /**
         * Zbiór krawędzi
         * Reprezentuje minimalne drzewo rozpinające
         */
        Set<Edge> minSpanningTree = new HashSet<>();

        /**
         * Zmienna pomocnicza
         * Lista krawędzi
         * Reprezentuje krawędzie incydentne do odwiedzonych wierzchołków grafu
         */
        ArrayList<Edge> adjacencyEdges;

        /**
         * Zmienna pomocnicza
         * Element odpowiedzialny za losowy wybór wierzchołka początkowego
         */
    this.rand = new Random();

        /**
         * Losowy wybór wierzchołka początkowego
         * Z zakresu od 0 do n-1,
         * gdzie n-liczba wierzchołków grafu
         */
    int startV = rand.nextInt(graph.length-1);

        /**
         * Oznaczenie wierzchołka startowego jako odwiedzony
         * Dodanie wierzchołka startV do listy odwiedzonych wierzchołków
         */
    setVertexVisited(startV,visitedVertices);


        /**
         * Pobranie do zmiennej adjacencyEdges listy krawędzi incydentnych do startV
         */
    adjacencyEdges = getAllAdjacencyEdges(startV,graph, adjacencyEdgesList);

        /**
         * Pętla warunkowa while()
         * Wykonuje się dopóki lista odwiedzonych wierzchołków visitedVertices
         * nie zawiera wszystkich wierzchołków grafu
         */
    while(visitedVertices.size()!= graph.length) {

        /**
         * Zmienna reprezentująca krawędź z listy krawędzi incydentnych
         * do punktów odwiedzonych adjacencyEdges, która ma minimalną wagę
         */
        Edge minEdge = adjacencyEdges.get(0);

        /**
         * Instrukcja warunkowa if()
         * Pod warunkiem, że lista odwiedzonych wierzchołków visitedVertices
         * nie zawiera wierzchołka końcowego krawędzi minEdge:
         *
         * Do MST zostaje dodana krawędź minEdge
         * Wierzchołek startV zmienia wartość na wierzchołek końcowy minEdge
         * Krawędź minEdge zostaje usunięta z listy krawędzi incydentnych do rozpatrzenia
         * Lista krawędzi incydentnych adjacencyEdges zostaje zaktualizowana o krawędzie
         * incydentne do wierzchołka startV
         *
         * W przeciwnym wypadku:
         * Krawędź minEdge zostaje usunięta z listy krawędzi do rozpatrzenia
         */
        if(!visitedVertices.contains(minEdge.getEnd())){
            minSpanningTree.add(minEdge);
            setVertexVisited(minEdge.getEnd(), visitedVertices);
            startV = minEdge.getEnd();
            adjacencyEdges.remove(minEdge);
            adjacencyEdges = getAllAdjacencyEdges(startV,graph,adjacencyEdgesList);

        } else {
            adjacencyEdges.remove(minEdge);
        }
    }

        /**
         * Wypisanie otrzymanego MST w konsoli
         */
    printMST(minSpanningTree);

    }


    /**
     *
     * @param vertex
     * @param list
     *
     * Oznacza wierzchołek jako odwiedzony poprzez dodanie wierzchołka vertex
     * do listy wierzchołków odwiedzonych list
     */
    private void setVertexVisited(int vertex, ArrayList<Integer> list){
        /**
         * Instrukcja warunkowa if()
         * Blok instrukcji zostaje wykonany, gdy lista wierzchołków odwiedzonych nie zawiera jeszcze
         * wierzchołka vertex
         */
        if(!list.contains(vertex))
        list.add(vertex);
    }

    /**
     *
     * @param vertex
     * @param graph
     * @return
     *
     * Dodaje do listy adjacencyEdgesList krawędzie incydentne do wierzchołka vertex, które nie tworzą cyklu
     */
    private ArrayList<Edge> getAllAdjacencyEdges(int vertex, int[][] graph, ArrayList<Edge> adjacencyEdgesList){

        /**
         * Instrukcja iteracyjna for()
         * Blok instrukcji jest wykonywany dla każdego wierzchołka grafu
         */
        for(int i=0; i<graph.length; i++){
            /**
             * Instrukcja warunkowa if()
             * Operacja dodania krawędzi incydentnej do wierzchołka vertex zostaje wykonana pod warunkiem, że:
             * Krawędź (vertex-i) istnieje, czyli jej waga jest różna od 0 oraz
             * Lista odwiedzonych wierzchołków visitedVertices nie zawiera jeszcze i-tego wierzchołka
             */
            if(graph[vertex][i]!= 0 && !visitedVertices.contains(i))adjacencyEdgesList.add(new Edge(vertex,i,graph[vertex][i]));
        }

        /**
         * Sortowanie otrzymanej listy krawędzi względem wag
         */
        Collections.sort(adjacencyEdgesList);

        return adjacencyEdgesList;
    }

    /**
     *
     * @param set
     *
     * Wyświetla w konsoli listę krawędzi otrzymanego drzewa
     * wraz z sumą wag krawędzi
     */
    private void printMST(Set<Edge> set){
        int sum = 0;
        for(Edge item : set){
            System.out.println(item);
            sum+=item.getWeight();
        }
        System.out.println("SUMA WAG WYNOSI: " + sum);
    }

    /**
     * Klasa Edge, której obiekty reprezentują krawędzie grafu
     */
    private class Edge implements Comparable<Edge> {
        /**
         * Wierzchołek początkowy krawędzi
         */
        private int start;
        /**
         * Wierzchołek końcowy krawędzi
         */
        private int end;
        /**
         * Waga krawędzi (start - end)
         */
        private int weight;

        /**
         *
         * @param s
         * @param e
         * @param w
         *
         * Konstruktor krawędzi
         */
        Edge(int s, int e, int w){
            setStart(s);
            setEnd(e);
            setWeight(w);
        }

        private int getWeight(){
            return weight;
        }

        private int getStart() {
            return start;
        }

        private void setStart(int start) {
            this.start = start;
        }

        private int getEnd() {
            return end;
        }

        private void setEnd(int end) {
            this.end = end;
        }

        private void setWeight(int weight) {
            this.weight = weight;
        }

        /**
         *
         * @param e
         * @return
         * Metoda odpowiedzialna za porównywanie krawędzi, wykorzystywana podczas sortowania krawędzi w liście
         * @see Collections
         */
        @Override
        public int compareTo(Edge e) {
            if (getWeight() < e.getWeight()){
                return -1;
            } else if (getWeight() > e.getWeight()) {
                return 1;
            }
            return 0;
        }

        /**
         * @return
         *
         * Metoda odpowiedzialna za postać wypisanej w konsoli krawędzi
         */
        @Override
        public String toString() {
            return String.format("%d--%d\tw:%d ", start, end, weight);
        }
    }


}
