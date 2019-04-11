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

    private MatrixDataStructure matrix;

    /**
     * Contructs empty graph.
     */
    public IncidenceMatrix() {
        matrix = new MatrixDataStructure();
    } // end of IncidentMatrix()

    public void addVertex(String vertLabel) {
        if (matrix.hasVertex(vertLabel)) {
            throw new IllegalArgumentException("Vertex already exists.");
        }
        matrix.addVertex(vertLabel);
    } // end of addVertex()

    public void addEdge(String srcLabel, String tarLabel, int weight) {
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
        if (weight == 0) {
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
        List<MyPair> neighbours = new ArrayList<MyPair>();
        if (!matrix.hasVertex(vertLabel)) {
            System.err.println("The vertex does not exist");
            return neighbours;
        } else if (k == -1)
            return matrix.returnAllInNeighbour(vertLabel);
        else if (k > matrix.checkMaxInK(vertLabel)) {
            System.out.println("The max number of k of vertex " + vertLabel + " is " + matrix.checkMaxInK(vertLabel));
            return matrix.returnInKnearestNeighbour(matrix.checkMaxInK(vertLabel), vertLabel);
        } else
            return matrix.returnInKnearestNeighbour(k, vertLabel);
    } // end of inNearestNeighbours()

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        if (!matrix.hasVertex(vertLabel)) {
            System.err.println("The vertex does not exist");
            return neighbours;
        } else if (k == -1) {
            return matrix.returnAllOutNeighbour(vertLabel);
        } else if (k > matrix.checkMaxOutK(vertLabel)) {
            System.out.println("The max number of k of vertex " + vertLabel + " is " + matrix.checkMaxOutK(vertLabel));
            return matrix.returnOutKnearestNeighbour(matrix.checkMaxOutK(vertLabel), vertLabel);
        } else return matrix.returnOutKnearestNeighbour(k, vertLabel);
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
        for (String a : matrix.getEdgeMap().keySet()) {
            os.println(a.substring(0, 1) + " " + a.substring(1) + " " + matrix.getEdgeWeightArray()[matrix.getVertexMap().get(a.substring(0, 1))][matrix.getEdgeMap().get(a)]);
        }
    }

}
