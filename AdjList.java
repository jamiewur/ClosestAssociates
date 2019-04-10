import java.io.*;
import java.util.*;

/**
 * Adjacency list implementation for the AssociationGraph interface.
 * <p>
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class AdjList extends AbstractAssocGraph {

    /**
     * Contructs empty graph.
     */
    public AdjList() {
        // Implement me!

    } // end of AdjList()

    public void addVertex(String vertLabel) {
        // Implement me!
    } // end of addVertex()

    public void addEdge(String srcLabel, String tarLabel, int weight) {
        // Implement me!
    } // end of addEdge()

    public int getEdgeWeight(String srcLabel, String tarLabel) {
        // Implement me!

        // update return value
        return EDGE_NOT_EXIST;
    } // end of existEdge()

    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
        // Implement me!
    } // end of updateWeightEdge()

    public void removeVertex(String vertLabel) {
        // Implement me!
    } // end of removeVertex()

    public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!

        return neighbours;
    } // end of inNearestNeighbours()

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!

        return neighbours;
    } // end of outNearestNeighbours()

    public void printVertices(PrintWriter os) {
        // Implement me!
    } // end of printVertices()

    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()

    private void updateArrayLength() {
        int currentLen = rows.length;
        if (currentLen - numOfVerts <= 5) {
            // Increase array size by 25 when the difference between current size and number of vertices
            // is smaller than or equal to 5
            int newLen = currentLen + 25;
            rows = Arrays.copyOf(rows, newLen);
        } else if (currentLen - numOfVerts >= 55) {
            // Decrease array size by 25 when the difference between current size and number of vertices
            // is greater than or equal to 55
            int newLen = currentLen - 25;
            rows = Arrays.copyOf(rows, newLen);
        }
    }

} // end of class AdjList
