import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GraphGenerator {

    private AdjList adjList;
    private IncidenceMatrix incMat;
    private Density density;
    private int numOfVerts;
    private int numOfEdges;
    private int maxNumEdges;
    private List<Integer> vertices;
    private Map<String, String> addedEdges;

    private enum Density {
        LOW,
        MEDIUM,
        HIGH,
    }

    public GraphGenerator(int numOfVerts, Density density) {
        this.numOfVerts = numOfVerts;
        this.maxNumEdges = numOfVerts * (numOfVerts - 1);
        this.density = density;
        this.vertices = IntStream.rangeClosed(1, numOfVerts).boxed().collect(Collectors.toList());
        Collections.shuffle(vertices);
    }

    private void calculateNumOfEdges() {
        if (density == Density.HIGH) {
            numOfEdges = (int) (maxNumEdges * 0.3);
        } else if (density == Density.MEDIUM) {
            numOfEdges = (int) (maxNumEdges * 0.1);
        } else {
            numOfEdges = (int) (maxNumEdges * 0.05);
        }
    }

    private void growGraph(AbstractAssocGraph graph) {
        System.out.println("Growing graph...");
        calculateNumOfEdges();
        long startTime = System.nanoTime();
        for (Integer vertex : vertices) {
            String vertLabel = vertex.toString();
            graph.addVertex(vertLabel);
        }
        int numOfAddedEdges = 0;
        addedEdges = new HashMap<>();
        outer:
        for (int i = 0; i < vertices.size(); i++) {
            String srcLabel = vertices.get(i).toString();
            inner:
            for (int j = 0; j < vertices.size(); j++) {
                if (numOfAddedEdges == numOfEdges) break outer;
                if (j == i) continue inner;
                String tarLabel = vertices.get(j).toString();
                int weight = generateRandomWeight();
                graph.addEdge(srcLabel, tarLabel, weight);
                addedEdges.put(srcLabel, tarLabel);
                numOfAddedEdges++;
            }
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double elapsedTimeInMS = elapsedTime / 1000000.0;
        String info = String.format("Generating graph with %s vertices and %s edges took: %.2f milliseconds.",
                numOfVerts, numOfEdges, elapsedTimeInMS);
        System.out.println(info);
    }

    public void testAdjList() {
        System.out.println("**Testing Adjacency List**");
        adjList = new AdjList();
        growGraph(adjList);
        testRemoveVertices(adjList);

        System.out.println("**End of Testing Adjacency List**");
    }

    public void testIncMat() {
        System.out.println("**Testing Incidence Matrix**");
        incMat = new IncidenceMatrix();
        growGraph(incMat);
        testRemoveVertices(incMat);

        System.out.println("**End of Testing Incidence Matrix**");
    }

    private void testRemoveVertices(AbstractAssocGraph graph) {
        long startTime = System.nanoTime();
        for (Integer vertex : vertices) {
            String vertLabel = vertex.toString();
            graph.removeVertex(vertLabel);
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double elapsedTimeInMS = elapsedTime / 1000000.0;
        String info = String.format("Removing %s vertices took: %.2f milliseconds.", numOfVerts, elapsedTimeInMS);
        System.out.println(info);
    }

    private int generateRandomWeight() {
        return (int) (Math.random() * 100) + 1;
    }

    public static void main(String[] args) {
        GraphGenerator g = new GraphGenerator(500, Density.LOW);
        g.testAdjList();
        g.testIncMat();
    }

}
