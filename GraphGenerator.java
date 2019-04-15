import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            numOfEdges = maxNumEdges;
        } else if (density == Density.MEDIUM) {
            numOfEdges = (int) (maxNumEdges * 0.66);
        } else {
            numOfEdges = (int) (maxNumEdges * 0.33);
        }
    }

    private void growGraph(AbstractAssocGraph graph) {
        calculateNumOfEdges();
        for (Integer vertex : vertices) {
            String vertLabel = vertex.toString();
            graph.addVertex(vertLabel);
        }
        int numOfAddedEdges = 0;
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
                numOfAddedEdges++;
            }
        }
    }

    private int generateRandomWeight() {
        return (int) (Math.random() * 100) + 1;
    }

    public static void main(String[] args) {
        GraphGenerator g = new GraphGenerator(1000, Density.HIGH);
        AdjList adjlist = new AdjList();
        g.growGraph(adjlist);
        adjlist.printVertices(new PrintWriter(System.out, true));
        adjlist.printEdges(new PrintWriter(System.out, true));
    }

}
