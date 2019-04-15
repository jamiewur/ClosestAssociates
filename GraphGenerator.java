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
    private List<Integer> newWeights;

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

    private void generateNewWeights() {
        newWeights = new ArrayList<>();
        for (int i = 0; i < numOfEdges; i++) {
            newWeights.add(generateRandomWeight());
        }
    }

    private void growGraph(AbstractAssocGraph graph) {
        System.out.println("Growing graph...");
        calculateNumOfEdges();
        generateNewWeights();
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
        String info = String.format("Generating graph with %s vertices and %s edges took: %.2f milliseconds.\n",
                numOfVerts, numOfEdges, elapsedTimeInMS);
        System.out.println(info);
    }

    public void testAdjList() {
        System.out.println("**Testing Adjacency List**\n");
        adjList = new AdjList();
        growGraph(adjList);
        testNearestNeighbors(adjList);
        testChangeWeights(adjList);
        testRemoveVertices(adjList);
        adjList = new AdjList();
        growGraph(adjList);
        testRemoveEdges(adjList);
        System.out.println("**End of Testing Adjacency List**\n");
    }

    public void testIncMat() {
        System.out.println("**Testing Incidence Matrix**\n");
        incMat = new IncidenceMatrix();
        growGraph(incMat);
        testNearestNeighbors(incMat);
        testChangeWeights(incMat);
        testRemoveVertices(incMat);
        incMat = new IncidenceMatrix();
        growGraph(incMat);
        testRemoveEdges(incMat);
        System.out.println("**End of Testing Incidence Matrix**\n");
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
        String info = String.format("Removing %s vertices took: %.2f milliseconds.\n", numOfVerts, elapsedTimeInMS);
        System.out.println(info);
    }

    private void testRemoveEdges(AbstractAssocGraph graph) {
        long startTime = System.nanoTime();
        for (Map.Entry<String, String> edge : addedEdges.entrySet()) {
            graph.updateWeightEdge(edge.getKey(), edge.getValue(), 0);
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double elapsedTimeInMS = elapsedTime / 1000000.0;
        String info = String.format("Removing %s edges took: %.2f milliseconds.\n", numOfEdges, elapsedTimeInMS);
        System.out.println(info);
    }

    private void testNearestNeighbors(AbstractAssocGraph graph) {
        int[] ks = {-1, 5, numOfVerts - 1};
        for (int i = 0; i < ks.length; i++) {
            testOutNeighbors(graph, ks[i]);
            testInNeighbors(graph, ks[i]);
        }
    }

    private void testInNeighbors(AbstractAssocGraph graph, int k) {
        long startTime = System.nanoTime();
        for (Integer vertex : vertices) {
            String vertLabel = vertex.toString();
            graph.inNearestNeighbours(k, vertLabel);
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double elapsedTimeInMS = elapsedTime / 1000000.0;
        String info = String.format("Getting %s nearest in-neighbors for %s vertices took: %.2f milliseconds.\n",
                k, numOfVerts, elapsedTimeInMS);
        System.out.println(info);
    }

    private void testOutNeighbors(AbstractAssocGraph graph, int k) {
        long startTime = System.nanoTime();
        for (Integer vertex : vertices) {
            String vertLabel = vertex.toString();
            graph.outNearestNeighbours(k, vertLabel);
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double elapsedTimeInMS = elapsedTime / 1000000.0;
        String info = String.format("Getting %s nearest out-neighbors for %s vertices took: %.2f milliseconds.\n",
                k, numOfVerts, elapsedTimeInMS);
        System.out.println(info);
    }

    private void testChangeWeights(AbstractAssocGraph graph) {
        long startTime = System.nanoTime();
        int i = 0;
        for (Map.Entry<String, String> edge : addedEdges.entrySet()) {
            int weight = newWeights.get(i++);
            graph.updateWeightEdge(edge.getKey(), edge.getValue(), weight);
        }
        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;
        double elapsedTimeInMS = elapsedTime / 1000000.0;
        String info = String.format("Changing weights for %s edges took: %.2f milliseconds.\n", numOfEdges, elapsedTimeInMS);
        System.out.println(info);

    }

    private int generateRandomWeight() {
        return (int) (Math.random() * 100) + 1;
    }

    public static void main(String[] args) {
        int totalVerts = 100;
        System.out.println("High Density Graph\n");
        GraphGenerator generator = new GraphGenerator(totalVerts, Density.HIGH);
        generator.testAdjList();
        generator.testIncMat();
        System.out.println("Medium Density Graph\n");
        generator = new GraphGenerator(totalVerts, Density.MEDIUM);
        generator.testAdjList();
        generator.testIncMat();
        System.out.println("Low Density Graph\n");
        generator = new GraphGenerator(totalVerts, Density.LOW);
        generator.testAdjList();
        generator.testIncMat();
    }

}
