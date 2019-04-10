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

    private HashMap<String, Integer> vertToIndex;
    private MyLinkedList[] rows;
    private int numOfVerts;
    private int pointer = 0; // Keep track of next vertex index

    /**
     * Contructs empty graph.
     */
    public AdjList() {
        vertToIndex = new HashMap<>();
        rows = new MyLinkedList[30];
        numOfVerts = 0;
    } // end of AdjList()

    public void addVertex(String vertLabel) {
        updateArrayLength();
        vertToIndex.put(vertLabel, pointer);
        rows[pointer] = new MyLinkedList();
        pointer++;
        numOfVerts++;
    } // end of addVertex()

    public void addEdge(String srcLabel, String tarLabel, int weight) {
        MyPair newEdge = new MyPair(tarLabel, weight);
        MyLinkedList srcVertList = getListOfEdgesForVert(srcLabel);
        if (srcVertList == null) {
            System.out.println("Source vertex doesn't exist. Cannot add edge.");
            return;
        }
        MyPair existingEdge = srcVertList.find(tarLabel);
        if (existingEdge != null) {
            System.err.println("Edge already exists. Consider updating the weight.");
            return;
        }
        srcVertList.add(newEdge);
    } // end of addEdge()

    public int getEdgeWeight(String srcLabel, String tarLabel) {
        MyLinkedList srcVertList = getListOfEdgesForVert(srcLabel);
        if (srcVertList == null) {
            return EDGE_NOT_EXIST;
        }
        MyPair edge = srcVertList.find(tarLabel);
        if (edge == null) {
            return EDGE_NOT_EXIST;
        }
        return edge.getValue();
    } // end of existEdge()

    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
        MyLinkedList srcVertList = getListOfEdgesForVert(srcLabel);
        if (srcVertList == null) {
            System.out.println("Source vertex doesn't exist. Cannot update edge weight.");
            return;
        }
        MyPair edge = srcVertList.find(tarLabel);
        if (edge == null) {
            System.out.println("Edge doesn't exist. Cannot update weight.");
            return;
        }
        edge.setValue(weight);
    } // end of updateWeightEdge()

    public void removeVertex(String vertLabel) {
        Integer indexToRemove = vertToIndex.remove(vertLabel);
        if (indexToRemove == null) {
            return;
        }
        rows[indexToRemove] = null;
        for (int i = 0; i < pointer; i++) {
            MyLinkedList edges = rows[i];
            if (edges != null) {
                edges.remove(vertLabel);
            }
        }
        numOfVerts--;
    } // end of removeVertex()

    public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();

        // Implement me!

        return neighbours;
    } // end of inNearestNeighbours()

    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<>();
        MyLinkedList srcVertList = getListOfEdgesForVert(vertLabel);
        if (srcVertList == null) {
            return neighbours;
        }
        neighbours.addAll(srcVertList.getAllValues());
        return getKNearestNeighbors(k, neighbours);
    } // end of outNearestNeighbours()

    public void printVertices(PrintWriter os) {
        // Implement me!
    } // end of printVertices()

    public void printEdges(PrintWriter os) {
        // Implement me!
    } // end of printEdges()

    private void updateArrayLength() {
        int currentLen = rows.length;
        if (currentLen - pointer <= 5) {
            // Increase array size by 25 when the difference between current size and vertex pointer
            // is smaller than or equal to 5
            int newLen = currentLen + 25;
            rows = Arrays.copyOf(rows, newLen);
        }
    }

    private MyLinkedList getListOfEdgesForVert(String srcLabel) {
        Integer index = vertToIndex.get(srcLabel);
        if (index == null) {
            return null;
        }
        return rows[index];
    }

    private List<MyPair> getKNearestNeighbors(int k, List<MyPair> neighbours) {
        if (neighbours.size() < k || k == -1) {
            return neighbours;
        }
        Collections.sort(neighbours, Collections.reverseOrder());
        return neighbours.subList(0, k - 1);
    }

} // end of class AdjList
