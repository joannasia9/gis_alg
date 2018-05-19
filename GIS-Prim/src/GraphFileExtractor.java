import java.io.*;

public class GraphFileExtractor {
    private Graph graph;
    private int[][] adjacencyMatrix;

    public void getFileContent(int iterator) {
        String home = System.getProperty("user.home");
        File file = new File(home + File.separator + "Desktop" + File.separator + "G" + iterator + ".txt");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
            String line = br.readLine();
            initiateMatrix(line);

            int i = 0;
            while (line != null) {
                parseLine(i,line);
                line = br.readLine();
                i+=1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(int i, String line){
        String delim = "\t";
        String[] tokens = line.split(delim);

        for(int j=0; j<tokens.length;j++){
            if(Integer.parseInt(tokens[j])!=0)
            graph.addEdge(new Edge(i,j,Integer.parseInt(tokens[j])));
            adjacencyMatrix[i][j] = Integer.parseInt(tokens[j]);
        }

    }

    private void initiateMatrix(String line){
        String delim = "\t";
        String[] tokens = line.split(delim);
        graph = new Graph(tokens.length);
        adjacencyMatrix = new int[tokens.length][tokens.length];
    }

    public Graph getGraph(){
        return graph;
    }

    public int[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
    }
}
