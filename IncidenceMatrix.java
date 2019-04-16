import java.io.*;
import java.util.*;


/**
 * Incident matrix implementation for the AssociationGraph interface.
 * <p>
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class IncidenceMatrix extends AbstractAssocGraph {

    private final MatrixDataStructure matrix;

    /**
     * Contructs empty graph.
     */
    public IncidenceMatrix() {
        matrix = new MatrixDataStructure();
    } // end of IncidentMatrix()

    public IncidenceMatrix(int initialEdgeSize) {
        matrix = new MatrixDataStructure(initialEdgeSize);
    }

    public void addVertex(String vertLabel) {
        if (matrix.hasVertex(vertLabel)) {
            return;
        }
        matrix.addVertex(vertLabel);
    } // end of addVertex()

    public void addEdge(String srcLabel, String tarLabel, int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight has to be positive.");
        }
        if (!matrix.hasVertex(srcLabel)) {
            throw new IllegalArgumentException("Source vertex doesn't exist. Cannot add edge.");
        }
        if (matrix.hasEdge(srcLabel, tarLabel)) {
            throw new IllegalArgumentException("Edge already exists. Considering updating the weight.");
        }
        matrix.addEdge(srcLabel, tarLabel, weight);
    } // end of addEdge()

    public int getEdgeWeight(String srcLabel, String tarLabel) {
        if (!matrix.hasVertex(srcLabel)) {
            return EDGE_NOT_EXIST;
        }
        if (!matrix.hasEdge(srcLabel, tarLabel)) {
            return EDGE_NOT_EXIST;
        }
        return matrix.getEdgeWeight(srcLabel, tarLabel);
    } // end of existEdge()

    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
        if (!matrix.hasVertex(srcLabel)) {
            throw new IllegalArgumentException("Source vertex doesn't exist. Cannot update edge weight.");
        }
        if (!matrix.hasEdge(srcLabel, tarLabel)) {
            throw new IllegalArgumentException("Edge doesn't exist. Cannot update weight.");
        }
        if (weight <= 0) {
            matrix.removeEdge(srcLabel, tarLabel);
            return;
        }
        matrix.updateWeightEdge(srcLabel, tarLabel, weight);

    } // end of updateWeightEdge()

    public void removeVertex(String vertLabel) {
        if (!matrix.hasVertex(vertLabel)) {
            throw new IllegalArgumentException("The vertex does not exist.");
        }
        matrix.removeVertex(vertLabel);
    } // end of removeVertex()

    public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
        if (!matrix.hasVertex(vertLabel)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        List<MyPair> neighbors = matrix.returnAllInNeighbour(vertLabel);
        return getKNearestNeighbors(k, neighbors);
    } // end of inNearestNeighbours()

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        if (!matrix.hasVertex(vertLabel)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        List<MyPair> neighbors = matrix.returnAllOutNeighbour(vertLabel);
        return getKNearestNeighbors(k, neighbors);
    } // end of outNearestNeighbours()

    public void printVertices(PrintWriter os) {
        String delimiter = " ";
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        for (String ver : matrix.getVertexMap().keySet()) {
            stringJoiner.add(ver);
        }
        os.println(stringJoiner.toString());
    } // end of printVertices()

    public void printEdges(PrintWriter os) {
        for (String edgeName : matrix.getEdgeMap().keySet()) {
            String[] vertices = edgeName.split("-");
            String srcLabel = vertices[0];
            String tarLabel = vertices[1];
            int weight = matrix.getEdgeWeight(srcLabel, tarLabel);
            os.println(srcLabel + " " + tarLabel + " " + weight);
        }
    }

    private List<MyPair> getKNearestNeighbors(int k, List<MyPair> neighbours) {
        // If total number of neighbors is less than k or k equals -1
        // then return all the neighbors
        if (neighbours.size() < k || k == -1) {
            return neighbours;
        }
        // Sort and return the top k neighbors
        Collections.sort(neighbours, Collections.reverseOrder());
        return neighbours.subList(0, k);
    }

}
