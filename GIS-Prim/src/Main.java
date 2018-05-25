
public class Main {


    public static void main(String[] args){
       GraphFileExtractor fileExtractor = new GraphFileExtractor();
       fileExtractor.getFileContent(1);

       Prim prim = new Prim(fileExtractor.getGraph());
       System.out.println("Suma wag: " + prim.getWeight());

    }





}


