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

        vertexMap.put(vertexName,indexOfVertex);

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
        edgeMap.put(edgeName,indexOfVertex);
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
        String [] relativeEdges = new String[100];
        for(String edge:edgeMap.keySet()) {
            if(edge.contains(vertex))
                 for(int ver: vertexMap.values())
                 edgeWeightArray[ver][edgeMap.get(edge)] = 0;
        }
        vertexMap.remove(vertex);

        for (String edge:edgeMap.keySet()) {
            if(edge.contains(vertex))

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
        List<MyPair> allNeighbours = this.returnAllNeighbour(vertex);
        int[] allWeights = new int[100];
        String[] allNearestNeighbours = new String[100];
        int[] nearestWeights = new int[k];
        String[] nearestNeighbours = new String[k];
        int maxIndex = 0;

        for(int i=0; i<allNeighbours.size();i++){
            allWeights[i] = allNeighbours.get(i).getValue();
            allNearestNeighbours[i] = allNeighbours.get(i).getKey();
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
        List<MyPair> allNeighbours = this.returnAllNeighbour(vertex);
        int[] allWeights = new int[100];
        String[] allNearestNeighbours = new String[100];
        int[] nearestWeights = new int[k];
        String[] nearestNeighbours = new String[k];
        int maxIndex = 0;

        for(int i=0; i<allNeighbours.size();i++){
            allWeights[i] = allNeighbours.get(i).getValue();
            allNearestNeighbours[i] = allNeighbours.get(i).getKey();
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
     * Return Out All real Neighbours (>0)
     *
     * @param1 The name of vertex
     * @returns The List of Out vertex, which obey k-nearest principle
     */
    public List<MyPair> returnAllOutNeighbour(String vertex){
        List<MyPair> allNeighbours = new ArrayList<>();
        String neighbour;
        int weightOfEdge;

        for(String e:edgeMap.keySet()){
            if(e.contains(vertex)&&edgeWeightArray[vertexMap.get(vertex)][edgeMap.get(e)]>0){
                if(e.endsWith(vertex))
                neighbour = e.substring(0,1);
                else neighbour = e.substring(1);
                        weightOfEdge = edgeWeightArray[vertexMap.get(vertex)][edgeMap.get(e)];
                        allNeighbours.add(new MyPair(neighbour,weightOfEdge));
                    }
                }return allNeighbours;
            }



    /**
     * Return Out All real Neighbours (>0)
     *
     * @param1 The name of vertex
     * @returns The List of Out vertex, which obey k-nearest principle
     */
    public List<MyPair> returnAllInNeighbour(String vertex){
        List<MyPair> allNeighbours = new ArrayList<>();
        String neighbour;
        int weightOfEdge;

        for(String e:edgeMap.keySet()){
            if(e.contains(vertex)&&edgeWeightArray[vertexMap.get(vertex)][edgeMap.get(e)]<0){
                if(e.endsWith(vertex))
                    neighbour = e.substring(0,1);
                else neighbour = e.substring(1);
                weightOfEdge = edgeWeightArray[vertexMap.get(vertex)][edgeMap.get(e)];
                allNeighbours.add(new MyPair(neighbour,weightOfEdge));
            }
        }return allNeighbours;
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
                if(e.endsWith(vertex))
                    neighbour = e.substring(0,1);
                else neighbour = e.substring(1);
                weightOfEdge = edgeWeightArray[vertexMap.get(vertex)][edgeMap.get(e)];
                allNeighbours.add(new MyPair(neighbour,weightOfEdge));
            }
        }return allNeighbours;
    }



    /**
     * Return the number of In-k of a vertex
     *
     * @param1 name of the vertex
     * @returns return the max number of in-k
     */
    public int checkMaxInK(String vertex) {
        int maxInK = 0;
        for (MyPair e : this.returnAllInNeighbour(vertex)){
            if (e.getValue() < 0)
                maxInK++;
        }return maxInK;
    }


    /**
     * Return the number of Out-k of a vertex
     *
     * @param1 name of the vertex
     * @returns return the max number of Out-k
     */
    public int checkMaxOutK(String vertex) {
        int maxOutK = 0;
        for (MyPair e : this.returnAllOutNeighbour(vertex)){
                maxOutK++;
        }return maxOutK;
    }

}
