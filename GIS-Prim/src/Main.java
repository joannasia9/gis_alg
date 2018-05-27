
public class Main {


    public static void main(String[] args){
       GraphFileExtractor fileExtractor = new GraphFileExtractor();
       fileExtractor.getFileContent(1);

       Graph graph = fileExtractor.getGraph();


       //Alg. Prima
       Prim prim = new Prim(graph);

        for(int j = 0; j<1000;j++){
            prim.primMST();
        }
        prim.showTime(1000);

       //Alg. Kruskala
        KruskalAlgorithm kruskal = new KruskalAlgorithm(graph.getNumberOfVertices(), graph.getNumberOfEdges());

        int i = 0;
        for (Edge edge : prim.edges()) {
            kruskal.edge[i].src = edge.getStartVertex();
            kruskal.edge[i].dest = (int)edge.getEndV();
            kruskal.edge[i].weight = (int)edge.getWeight();
            i++;
        }

        for(int j = 0; j<1000;j++){
            kruskal.KruskalMST();
        }
        kruskal.showTime(1000);

        System.out.println("Suma wag: " + prim.getWeight());
    }





}


