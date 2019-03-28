import java.util.Arrays;

public class IncidenceMatrixArray {

    public int numbOfVertex;
    public int numbOfEdges;
    public int edgeWeight;
    String [] vertexArray;
    int [][] edgeArray;
    public String vertexName;


    /**Constructor by default*/

    public IncidenceMatrixArray(){

        vertexArray = new String[10];
        edgeArray = new int[10][10];

    }


    /**Getter of the 1D array
     * Store the Vertices
     */
    public String[] get1DArray() { return vertexArray; }

    public boolean hasVertex(String vertexName) { return true; }

    public void addVertex(String vertexName) { numbOfVertex++; }

    public int[][] get2DArray() { return edgeArray; }

    public boolean hasEdge(String vertex1, String vertex2) { return true; }

    public void addEdge(String vertex1, String vertext2, int weight) { numbOfEdges++; }

    public int getEdgeWeight(String vertex1, String vertex2) {return edgeWeight;}

    public int delEdge (String vertex1, String vertex2) { numbOfEdges--; }







}
