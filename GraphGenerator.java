import java.util.stream.IntStream;

public class GraphGenerator {

    private AbstractAssocGraph graph;
    private Density density;
    private int numOfVerts;
    private int numOfEdges;
    private int maxNumEdges;
    private int[] vertices;

    private enum Density {
        LOW,
        MEDIUM,
        HIGH,
    }

    public GraphGenerator(int numOfVerts, Density density) {
        this.numOfVerts = numOfVerts;
        this.maxNumEdges = numOfVerts * (numOfVerts - 1);
        this.density = density;
        this.vertices = IntStream.range(1, numOfVerts + 1).toArray();
    }

    private int calculateNumOfEdges() {
        if (density == Density.HIGH) {
            return maxNumEdges;
        } else if (density == Density.MEDIUM) {
            return (int) (maxNumEdges * 0.66);
        } else {
            return (int) (maxNumEdges * 0.33);
        }
    }



}
