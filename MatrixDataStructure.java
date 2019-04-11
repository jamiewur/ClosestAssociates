import java.io.PrintWriter;
import java.util.*;

public class MatrixDataStructure {

    public int numbOfVertex = 0;
    public int numbOfEdges = 0;
    public int indexOfVertex = 0;
    public int indexOfEdges = 0;
    public Map<String, Integer> vertexMap;
    public Map<String, Integer> edgeMap;
    public int[][] edgeWeightArray;
    public MyPair mypair;



    /**Constructor by default*/

    public MatrixDataStructure(){
        vertexMap = new HashMap<>();
        edgeMap = new HashMap<>();
        edgeWeightArray = new int[100][100];
    }


    /**
     * Judgement of whether has the vertex
     *
     * @param vertexName String Name of Vertex
     *
     * @returns Whether has the vertex  If vertex doesn't exist, return false,else return true;
     */
    public boolean hasVertex(String vertexName) {
        return vertexMap.containsKey(vertexName);
    }

    /**
     * Add a new vertex
     *
     * @param vertexName String Name of the new Vertex
     */
    public void addVertex(String vertexName) {

        vertexMap.put(vertexName,numbOfVertex);

        numbOfVertex++;
        indexOfVertex++;
    }

    /**
     * Judgement of whether has the edge
     *
     * @param1 The first vertex name
     * @param2 The second vertex name
     *
     * @returns Whether has the edge If edge doesn't exist, return false,else return true;
     */
    public boolean hasEdge(String vertex1, String vertex2) {
        String edgeName = vertex1 + vertex2;
        return edgeMap.containsKey(edgeName);
         }

    /**
     * Add a new edge
     *
     * @param1 vertex1 is the source vertex name
     * @param2 vertex2 is the target vertex name
     * @param3 weight is the weight of the edge
     */
    public void addEdge(String vertex1, String vertex2, int weight) {

        String edgeName = vertex1 + vertex2;
        edgeMap.put(edgeName,numbOfEdges);
        edgeWeightArray[vertexMap.get(vertex1)][edgeMap.get(edgeName)] = weight;
        edgeWeightArray[vertexMap.get(vertex2)][edgeMap.get(edgeName)] = -weight;
        numbOfEdges++;
        indexOfEdges++;
    }

    /**
     * Return the weight of the edge
     *
     * @param1 vertex1 is the source vertex name
     * @param2 vertex2 is the target vertex name
     * @param3 weight is the weight of the edge
     */
    public int getEdgeWeight(String vertex1, String vertex2) {
        String edgeName = vertex1 + vertex2;
        return edgeWeightArray[vertexMap.get(vertex1)][edgeMap.get(edgeName)];
    }


    /**
     * Update the weight of the edge
     *
     * @param1 vertex1 is the source vertex name
     * @param2 vertex2 is the target vertex name
     * @param3 weight is the weight of the edge
     */
    public void updateWeightEdge(String vertex1, String vertex2, int weight){

        String edgeName = vertex1 + vertex2;
        edgeWeightArray[vertexMap.get(vertex1)][edgeMap.get(edgeName)] = weight;
        edgeWeightArray[vertexMap.get(vertex2)][edgeMap.get(edgeName)] = -weight;
    }

    /**
     * Remove the vertex
     *
     * @param1 vertex is the vertex name
     */
    public void removeVertex (String vertex) {

        for(String edge:edgeMap.keySet()
             ) {if(edge.contains(vertex))
                 for(int ver: vertexMap.values())
                 edgeWeightArray[ver][edgeMap.get(edge)] = 0;
        }
        vertexMap.remove(vertex);
        for (String edge:edgeMap.keySet()
             ) {if(edge.contains(vertex))
                 vertexMap.remove(edge);
        }
        numbOfVertex--;
        numbOfEdges--;
    }

    /**
     * Remove the vertex
     *
     * @param1 vertex1 is first vertex name
     * @param2 vertex2 is second vertex name
     */
    public void removeEdge (String vertex1, String vertex2){
        String edgeName = vertex1 + vertex2;
        int edgeMapValue = edgeMap.get(edgeName);
        for(int a:vertexMap.values()){
            edgeWeightArray[a][edgeMapValue]=0;
        }
        edgeMap.remove(edgeName);
        numbOfEdges--;
    }

    /**
     * Return In K-nearest Neighbours
     *
     * @param1 k value of K-nearest
     * @param2 The vertex
     * @returns The List of In vertex, which obey k-nearest principle
     */
    public List<MyPair> returnInKnearestNeighbour(int k, String vertex){
        List<MyPair> neighbours = new ArrayList<MyPair>();
        List<MyPair> allInNeighbours = new ArrayList<>();
        int[] allWeights = new int[100];
        String[] allNearestNeighbours = new String[100];
        int[] nearestWeights = new int[k];
        String[] nearestNeighbours = new String[k];
        int maxIndex = 0;
        int weightOfEdge;
        String neighbour;

        for(String e:edgeMap.keySet()){
            if(e.contains(vertex)){
               for(String a: vertexMap.keySet()){
                   if(edgeWeightArray[vertexMap.get(a)][edgeMap.get(e)]<0){
                       neighbour = a;
                       weightOfEdge = edgeWeightArray[vertexMap.get(a)][edgeMap.get(e)];
                       allInNeighbours.add(new MyPair(neighbour,weightOfEdge));
                   }
               }
            }
        }
        for(int i=0; i<allInNeighbours.size();i++){
            allWeights[i] = allInNeighbours.get(i).getValue();
            allNearestNeighbours[i] = allInNeighbours.get(i).getKey();
        }
        for(int i=0; i<k;i++) {
            for (int j = 0; j < allWeights.length; j++) {
                if (-allWeights[j] >= -allWeights[maxIndex])
                    maxIndex = j;
            }
            nearestWeights[i] = allWeights[maxIndex];
            nearestNeighbours[i] = allNearestNeighbours[maxIndex];
            allWeights[maxIndex] = 0;
            maxIndex = 0;
        }

        for(int i=0;i<nearestWeights.length;i++){
            neighbours.add(new MyPair(nearestNeighbours[i],nearestWeights[i]));
        }

        return neighbours;

    }

    /**
     * Return Out K-nearest Neighbours
     *
     * @param1 k value of K-nearest
     * @param2 The vertex
     * @returns The List of Out vertex, which obey k-nearest principle
     */

    public List<MyPair> returnOutKnearestNeighbour(int k, String vertex){
        List<MyPair> neighbours = new ArrayList<MyPair>();
        List<MyPair> allOutNeighbours = new ArrayList<>();
        int[] allWeights = new int[100];
        String[] allNearestNeighbours = new String[100];
        int[] nearestWeights = new int[k];
        String[] nearestNeighbours = new String[k];
        int maxIndex = 0;
        int weightOfEdge;
        String neighbour;

        for(String e:edgeMap.keySet()){
            if(e.contains(vertex)){
                for(String a: vertexMap.keySet()){
                    if(edgeWeightArray[vertexMap.get(a)][edgeMap.get(e)]>0){
                        neighbour = a;
                        weightOfEdge = edgeWeightArray[vertexMap.get(a)][edgeMap.get(e)];
                        allOutNeighbours.add(new MyPair(neighbour,weightOfEdge));
                    }
                }
            }
        }
        for(int i=0; i<allOutNeighbours.size();i++){
            allWeights[i] = allOutNeighbours.get(i).getValue();
            allNearestNeighbours[i] = allOutNeighbours.get(i).getKey();
        }
        for(int i=0; i<k;i++) {
            for (int j = 0; j < allWeights.length; j++) {
                if (allWeights[j] >= allWeights[maxIndex])
                    maxIndex = j;
            }
            nearestWeights[i] = allWeights[maxIndex];
            nearestNeighbours[i] = allNearestNeighbours[maxIndex];
            allWeights[maxIndex] = 0;
            maxIndex = 0;
        }

        for(int i=0;i<nearestWeights.length;i++){
            neighbours.add(new MyPair(nearestNeighbours[i],nearestWeights[i]));
        }

        return neighbours;
    }


    /**
     * Return Out All Neighbours
     *
     * @param1 The name of vertex
     * @returns The List of Out vertex, which obey k-nearest principle
     */
    public List<MyPair> returnAllNeighbour(String vertex){
        List<MyPair> allNeighbours = new ArrayList<>();
        String neighbour;
        int weightOfEdge;

        for(String e:edgeMap.keySet()){
            if(e.contains(vertex)){
                for(String a: vertexMap.keySet()){
                        neighbour = a;
                        weightOfEdge = edgeWeightArray[vertexMap.get(a)][edgeMap.get(e)];
                        allNeighbours.add(new MyPair(neighbour,weightOfEdge));
                    }
                }
            }
        return allNeighbours;
    }


    /**
     * Print the vertex to System
     *
     * @param1 object of PrintWriter
     */
    public void printVertex(PrintWriter os){
        for(String a:vertexMap.keySet()){
            os.print(a);
        }
    }


    /**
     * Print the edges to System
     *
     * @param1 object of PrintWriter
     */
    public void printEdge(PrintWriter os){
        for(String a:edgeMap.keySet()){
            os.print(a);
        }
    }

}
