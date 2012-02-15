
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gabriel Smith
 * Created Feb 6, 2011
 *
 */
public class PriorityQueue {

    Map<Vertex, Double> pq = new HashMap<Vertex, Double>();

    /**
     * Default constructor
     */
    public PriorityQueue() {}

    /**
     * Constructor to take an array of vertices and insert
     * them all with an associated value of positive infinity
     *
     * @param vertices  Array of vertices to use
     */
    public PriorityQueue( Vertex[] vertices ) {
        for ( Vertex v : vertices )
            insert( v, Double.MAX_VALUE );
    }

    /**
     * Inserts a new entry with associated value if that entry doesn't
     * already exist.
     *
     * @param entry     Entry to insert
     * @param key       Value to associate with entry
     * @return      true if entry was successfully added.
     */
    public boolean insert( Vertex entry, Double value ) {
        if ( !pq.containsKey( entry ) ) {
            pq.put(entry, value);
            return true;
        }
        return false;
    }

    /**
     * Changes a value in the priority queue as long as the entry is
     * already there
     *
     * @param entry     Entry to update
     * @param newVal    new value to associate with the entry
     * @return      true if the entry was successfully updated
     */
    public boolean change( Vertex entry, Double newVal ) {
        if ( pq.containsKey( entry ) ) {
            pq.put(entry, newVal);
            return true;
        }
        return false;
    }

    /**
     * Find the max value associated with an entry, and return that entry.
     *
     * @return  entry with the highest value associated with it.
     */
    public Vertex max() {
        Double max = Double.MIN_VALUE;
        Vertex maxV = null;
        for ( Vertex v : pq.keySet() ) {
            Double curVal = pq.get(v);
            if ( curVal > max ) {
                max = curVal;
                maxV = v;
            }
        }
        return maxV;
    }

    /**
     * Finds the max value associated with an entry, remove that entry and
     * return it.
     *
     * @return  entry with the highest value associated with it.
     */
    public Vertex extractMax() {
        Vertex max = max();
        if ( max != null ) pq.remove(max);
        return max;
    }

    /**
     * Find the min value associated with an entry, and return that entry.
     *
     * @return  entry with the smallest value associated with it.
     */
    public Vertex min() {
        Double min = Double.MAX_VALUE;
        Vertex minV = null;
        for ( Vertex v : pq.keySet() ) {
            Double curVal = pq.get(v);
            if ( curVal < min ) {
                min = curVal;
                minV = v;
            }
        }
        return minV;
    }

    /**
     * Finds the min value associated with an entry, remove that entry and
     * return it.
     *
     * @return  entry with the lowest value associated with it.
     */
    public Vertex extractMin() {
        Vertex min = min();
        if ( min != null ) pq.remove(min);
        return min;
    }

    /**
     * Returns true if the queue already contains the given entry.
     *
     * @param entry     Entry to search for
     * @return      true if the entry is found
     */
    public boolean contains( Vertex entry ) {
        return pq.containsKey( entry );
    }

    /**
     * Returns the value associated with the given entry
     *
     * @param entry     Entry to get value for
     * @return      value associated with given entry.
     */
    public Double get( Vertex entry ) {
        return pq.get( entry );
    }

    /**
     * Returns true if the priority queue is empty.
     *
     * @return  true if the priority queue is empty
     */
    public boolean isEmpty() {
        return pq.isEmpty();
    }
}
