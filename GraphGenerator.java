import java.util.stream.IntStream;

public class GraphGenerator {

    private AbstractAssocGraph graph;
    private Density density;
    private int numOfVerts;
    private int[] vertices;
    private enum Density {
        LOW,
        MEDIUM,
        HIGH,
    }

    public GraphGenerator(int numOfVerts, Density density) {
        this.numOfVerts = numOfVerts;
        this.density = density;
        this.vertices = IntStream.range(1, numOfVerts + 1).toArray();
    }



}
