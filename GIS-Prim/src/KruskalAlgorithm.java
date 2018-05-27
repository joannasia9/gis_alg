import java.util.*;
import java.lang.*;

public class KruskalAlgorithm {
    private long time = 0;

        class EdgeK implements Comparable<EdgeK>
        {
            int src, dest, weight;

            public int compareTo(EdgeK compareEdge)
            {
                return this.weight - compareEdge.weight;
            }
        }

        class subset
        {
            int parent, rank;
        };

        int V, E;
        EdgeK edge[];

        KruskalAlgorithm(int v, int e)
        {
            V = v;
            E = e;
            edge = new EdgeK[E];
            for (int i=0; i<e; ++i)
                edge[i] = new EdgeK();
        }

        // znajduje subset z danym wierzchołkiem
        int find(subset subsets[], int i)
        {
            if (subsets[i].parent != i)
                subsets[i].parent = find(subsets, subsets[i].parent);

            return subsets[i].parent;
        }

        // realizuje scalanie zbiorów
        void Union(subset subsets[], int x, int y)
        {
            int xroot = find(subsets, x);
            int yroot = find(subsets, y);

            if (subsets[xroot].rank < subsets[yroot].rank)
                subsets[xroot].parent = yroot;
            else if (subsets[xroot].rank > subsets[yroot].rank)
                subsets[yroot].parent = xroot;

            else
            {
                subsets[yroot].parent = xroot;
                subsets[xroot].rank++;
            }
        }

        // realizuje algorytm Kruskala
        void KruskalMST()
        {
            long timeStartK = System.currentTimeMillis();

            EdgeK result[] = new EdgeK[V];  // wynik
            int e = 0;

            for (int i=0; i<V; ++i)
                result[i] = new EdgeK();

            // Sortuje krawedzie niemalejąco
            Arrays.sort(edge);

            subset subsets[] = new subset[V];
            for(int i=0; i<V; ++i)
                subsets[i]=new subset();

            for (int v = 0; v < V; ++v)
            {
                subsets[v].parent = v;
                subsets[v].rank = 0;
            }

            int i = 0;
            while (e < V - 1)
            {
                // Wybiera krawędź o najmniejszej wadze
                EdgeK next_edge = new EdgeK();
                next_edge = edge[i++];

                int x = find(subsets, next_edge.src);
                int y = find(subsets, next_edge.dest);

                // Jeżeli kawedz nie tworzy cklu dodaje ja do rozwiazania
                if (x != y)
                {
                    result[e++] = next_edge;
                    Union(subsets, x, y);
                }
            }

            long timeStopK = System.currentTimeMillis();


            time += timeStopK-timeStartK;
            // print the contents of result[] to display
            // the built MST
            //showResults(result,e);

        }

        public void  showTime(int iterations){
            System.out.println("CZAS WYKONYWANIA ALGORYTMU KRUSKALA WYNIÓSŁ: "+ (time/iterations) + " milisekund");
        }

        private void showResults(EdgeK[] results, int e){
            System.out.println("Na MST skladaja się nastepujace krawedzie:");
            for (int i = 0; i < e; ++i)
                System.out.println(results[i].src+" -- " +
                        results[i].dest+" == " + results[i].weight);
        }
}
