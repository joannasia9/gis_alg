
public class Main {


    public static void main(String[] args){
       GraphFileExtractor fileExtractor = new GraphFileExtractor();
       fileExtractor.getFileContent(1);
       long timeStart = System.currentTimeMillis();
       Prim prim = new Prim(fileExtractor.getGraph());

        for (Edge edge : prim.edges()) {
            System.out.println(edge);
        }
        System.out.println("Suma wag: " + prim.getWeight());
        long timeStop = System.currentTimeMillis();
        System.out.println("CZAS WYKONYWANIA I PROGRAMU WYNIÓSŁ: "+ (timeStop-timeStart) + " milisekund");

        int[][] adjMatrix = fileExtractor.getAdjacencyMatrix();
        timeStart = System.currentTimeMillis();
        PrimAlgorithm algorytmPrima = new PrimAlgorithm(adjMatrix);
        timeStop = System.currentTimeMillis();
        System.out.println("CZAS WYKONYWANIA II PROGRAMU WYNIÓSŁ: "+ (timeStop-timeStart) + " milisekund");
    }





}


