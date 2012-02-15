
/**
 * @author Gabriel Smith
 * Created Jan 23, 2011
 *
 * Stores vertices which this edge connects, as well
 * as a weight. Contains accessors for all fields.
 */
public class Edge {

    private Vertex from, to;
    private double weight;

    /**
     * Constructor
     *
     * @param from      the starting vertex
     * @param to        the ending vertex
     * @param weight    the weight for this edge
     */
    public Edge( Vertex from, Vertex to, double weight ) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    
    /**
     * Accessors
     */
    public Vertex getFrom() { return from; }
    public Vertex getTo() { return to; }
    public double getWeight() { return weight; }
}
