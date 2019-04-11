import java.io.*;
import java.util.*;


/**
 * Incident matrix implementation for the AssociationGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2019.
 */
public class IncidenceMatrix extends AbstractAssocGraph {

    private static final int EDGE_NOT_EXIST = -1;
    MatrixDataStructure matrixDataStructure = new MatrixDataStructure();

    /**
     * Contructs empty graph.
     */
    public IncidenceMatrix() {
        // Implement me!
    } // end of IncidentMatrix()


    public void addVertex(String vertLabel) {
        // Implement me!
        if (matrixDataStructure.hasVertex(vertLabel))
            return;
        else matrixDataStructure.addVertex(vertLabel);
    } // end of addVertex()


    public void addEdge(String srcLabel, String tarLabel, int weight) {
        // Implement me!
        if (!matrixDataStructure.hasVertex(srcLabel) && matrixDataStructure.hasVertex(tarLabel)) {
            System.err.println("One or two vertex does not exit");
            return;
        }
        else if (matrixDataStructure.edgeMap.containsKey(srcLabel+tarLabel))
            return;
        else matrixDataStructure.addEdge(srcLabel, tarLabel, weight);
    } // end of addEdge()


    public int getEdgeWeight(String srcLabel, String tarLabel) {
        // Implement me!
        if (!matrixDataStructure.hasEdge(srcLabel, tarLabel))
            return EDGE_NOT_EXIST;
        else
            return matrixDataStructure.getEdgeWeight(srcLabel, tarLabel);
    } // end of existEdge()


    public void updateWeightEdge(String srcLabel, String tarLabel, int weight) {
        // Implement me!

        if (!matrixDataStructure.hasVertex(srcLabel) && matrixDataStructure.hasVertex(tarLabel)) {
            System.err.println("One or two vertex does not exit");
            return;
        } else if (!matrixDataStructure.hasEdge(srcLabel, tarLabel)) {
            System.err.println("The edge does not exist");
            return;
        } else if (weight != 0)
            matrixDataStructure.updateWeightEdge(srcLabel, tarLabel, weight);
        else matrixDataStructure.removeEdge(srcLabel, tarLabel);


    } // end of updateWeightEdge()


    public void removeVertex(String vertLabel) {
        // Implement me!
        if (!matrixDataStructure.hasVertex(vertLabel)) {
            System.err.println("The vertex does not exist");
            return;
        } else matrixDataStructure.removeVertex(vertLabel);
    } // end of removeVertex()


    public List<MyPair> inNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        // Implement me!
        if (!matrixDataStructure.hasVertex(vertLabel)) {
            System.err.println("The vertex does not exist");
            return neighbours;
        } else if(k == -1)
            return matrixDataStructure.returnAllInNeighbour(vertLabel);
        else if(k>matrixDataStructure.checkMaxInK(vertLabel)){
            System.out.println("The max number of k of vertex "+vertLabel+" is "+ matrixDataStructure.checkMaxInK(vertLabel));
            return matrixDataStructure.returnInKnearestNeighbour(matrixDataStructure.checkMaxInK(vertLabel), vertLabel);}
        else
            return matrixDataStructure.returnInKnearestNeighbour(k, vertLabel);
    } // end of inNearestNeighbours()


    public List<MyPair> outNearestNeighbours(int k, String vertLabel) {
        List<MyPair> neighbours = new ArrayList<MyPair>();
        // Implement me!
        if (!matrixDataStructure.hasVertex(vertLabel)) {
            System.err.println("The vertex does not exist");
            return neighbours;
        } else if(k == -1) {
            return matrixDataStructure.returnAllOutNeighbour(vertLabel);
        }
        else if(k>matrixDataStructure.checkMaxOutK(vertLabel)){
            System.out.println("The max number of k of vertex "+vertLabel+" is "+ matrixDataStructure.checkMaxOutK(vertLabel));
            return matrixDataStructure.returnOutKnearestNeighbour(matrixDataStructure.checkMaxOutK(vertLabel), vertLabel);}
        else return matrixDataStructure.returnOutKnearestNeighbour(k, vertLabel);
    } // end of outNearestNeighbours()


    public void printVertices(PrintWriter os) {
        String delimiter = " ";
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        for(String ver:matrixDataStructure.vertexMap.keySet()){
            stringJoiner.add(ver);
        }
        os.println(stringJoiner.toString());
    } // end of printVertices()


    public void printEdges(PrintWriter os) {
            for (String a : matrixDataStructure.edgeMap.keySet()) {
                os.println(a.substring(0, 1) + " " + a.substring(1) + " " + matrixDataStructure.edgeWeightArray[matrixDataStructure.vertexMap.get(a.substring(0, 1))][matrixDataStructure.edgeMap.get(a)]);
        }

    }

}
