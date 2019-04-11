import java.util.*;

public class MatrixDataStructure {

    private int indexOfVertex = 0;
    private int indexOfEdges = 0;
    private Map<String, Integer> vertexMap;
    private Map<String, Integer> edgeMap;
    private int[][] edgeWeightArray;

    /**
     * Constructor by default
     */
    public MatrixDataStructure() {
        vertexMap = new HashMap<>();
        edgeMap = new HashMap<>();
        edgeWeightArray = new int[30][30];
    }

    /**
     * Judgement of whether has the vertex
     *
     * @param vertexName String Name of Vertex
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
        int currNumRows = edgeWeightArray.length;
        if (currNumRows - indexOfVertex <= 5) {
            // Increase array size by 25 when the difference between current size and vertex index
            // is smaller than or equal to 5
            int newNumRows = currNumRows + 25;
            int currNumCols = edgeWeightArray[0].length;
            edgeWeightArray = updateArraySize(edgeWeightArray, newNumRows, currNumCols);
        }
        vertexMap.put(vertexName, indexOfVertex);
        indexOfVertex++;
    }

    /**
     * Judgement of whether has the edge
     *
     * @param1 The first vertex name
     * @param2 The second vertex name
     * @returns Whether has the edge If edge doesn't exist, return false,else return true;
     */
    public boolean hasEdge(String srcLabel, String tarLabel) {
        String edgeName = createEdgeName(srcLabel, tarLabel);
        return edgeMap.containsKey(edgeName);
    }

    /**
     * Add a new edge
     *
     * @param1 srcLabel is the source vertex name
     * @param2 tarLabel is the target vertex name
     * @param3 weight is the weight of the edge
     */
    public void addEdge(String srcLabel, String tarLabel, int weight) {
        int currNumCols = edgeWeightArray[0].length;
        if (currNumCols - indexOfEdges <= 5) {
            // Increase array size by 25 when the difference between current size and edge index
            // is smaller than or equal to 5
            int newNumCols = currNumCols + 25;
            int currNumRows = edgeWeightArray.length;
            edgeWeightArray = updateArraySize(edgeWeightArray, currNumRows, newNumCols);
        }
        String edgeName = createEdgeName(srcLabel, tarLabel);
        edgeMap.put(edgeName, indexOfEdges);
        edgeWeightArray[vertexMap.get(srcLabel)][edgeMap.get(edgeName)] = weight;
        edgeWeightArray[vertexMap.get(tarLabel)][edgeMap.get(edgeName)] = -weight;
        indexOfEdges++;
    }

    /**
     * Return the weight of the edge
     *
     * @param1 srcLabel is the source vertex name
     * @param2 tarLabel is the target vertex name
     * @param3 weight is the weight of the edge
     */
    public int getEdgeWeight(String srcLabel, String tarLabel) {
        String edgeName = createEdgeName(srcLabel, tarLabel);
        return edgeWeightArray[vertexMap.get(srcLabel)][edgeMap.get(edgeName)];
    }

    /**
     * Update the weight of the edge
     *
     * @param1 srcLabel is the source vertex name
     * @param2 tarLabel is the target vertex name
     * @param3 weight is the weight of the edge
     */
    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
        String edgeName = createEdgeName(srcLabel, tarLabel);
        edgeWeightArray[vertexMap.get(srcLabel)][edgeMap.get(edgeName)] = weight;
        edgeWeightArray[vertexMap.get(tarLabel)][edgeMap.get(edgeName)] = -weight;
    }

    /**
     * Remove the vertex
     *
     * @param1 vertex is the vertex name
     */
    public void removeVertex(String vertex) {
        edgeMap.entrySet().removeIf(item -> item.getKey().contains(vertex));
        vertexMap.remove(vertex);
    }

    /**
     * Remove the vertex
     *
     * @param1 srcLabel is first vertex name
     * @param2 tarLabel is second vertex name
     */
    public void removeEdge(String srcLabel, String tarLabel) {
        String edgeName = createEdgeName(srcLabel, tarLabel);
        int edgeMapIndex = edgeMap.get(edgeName);
        for (int i : vertexMap.values()) {
            edgeWeightArray[i][edgeMapIndex] = 0;
        }
        edgeMap.remove(edgeName);
    }

    /**
     * Return All Real Out Neighbours (>0)
     *
     * @param1 The name of vertex
     * @returns The List of Out vertex
     */
    public List<MyPair> returnAllOutNeighbour(String vertex) {
        return getNeighbors(vertex, "OUT");
    }

    /**
     * Return All Real In Neighbours (>0)
     *
     * @param1 The name of vertex
     * @returns The List of Out vertex
     */
    public List<MyPair> returnAllInNeighbour(String vertex) {
        return getNeighbors(vertex, "IN");
    }

    /**
     * Return All Neighbors of Type (In or Out)
     *
     * @param1 The name of vertex
     * @param2 Type of the neighbor ("IN" or "OUT")
     * @returns The List of Neighbors
     */
    private List<MyPair> getNeighbors(String vertex, String type) {
        if (!Objects.equals(type, "IN") && !Objects.equals(type, "OUT")) {
            throw new IllegalArgumentException("Type must be either IN or OUT");
        }
        List<MyPair> allNeighbours = new ArrayList<>();
        for (String edgeName : edgeMap.keySet()) {
            String[] vertices = edgeName.split("-");
            String matchVert, otherVert;
            if (Objects.equals(type, "IN")) {
                otherVert = vertices[0];
                matchVert = vertices[1];
            } else {
                matchVert = vertices[0];
                otherVert = vertices[1];
            }
            int weight = getEdgeWeight(vertices[0], vertices[1]);
            if (Objects.equals(vertex, matchVert) && weight > 0) {
                allNeighbours.add(new MyPair(otherVert, weight));
            }
        }
        return allNeighbours;
    }

    public Map<String, Integer> getVertexMap() {
        return vertexMap;
    }

    public Map<String, Integer> getEdgeMap() {
        return edgeMap;
    }

    private String createEdgeName(String srcLabel, String tarLabel) {
        return srcLabel + "-" + tarLabel;
    }

    private static int[][] updateArraySize(int[][] original, int newNumRows, int newNumCols) {
        int[][] newArray = new int[newNumRows][newNumCols];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, newArray[i], 0, Math.min(original[i].length, newNumCols));
        }
        return newArray;
    }

}
