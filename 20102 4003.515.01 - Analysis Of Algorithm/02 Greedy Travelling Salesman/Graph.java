import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Gabriel Smith
 * Created Jan 23, 2011
 *
 */
public class Graph {

    private int n, seed;
    private Vertex[] vertices = null;
    private double[][] matrix = null;
    private DecimalFormat df = new DecimalFormat("0.00");

/**************************** Construction Methods ****************************/
    /**
     * Constructor. Initializes n, seed and vertices
     *
     * @param n     number of vertices
     * @param seed  seed for random number generation
     */
    public Graph ( int n, int seed ) {
        this.n = n;
        this.seed = seed;
        vertices = new Vertex[n];
    }

    /**
     * Generates 'n' random vertices for the graph using 'seed' as the seed for
     * random number generation. Credit for algorithm goes to Professor Canosa
     * in email sent out on 12/12/2010.
     */
    public void generateGraph() {
        Random xRand = new Random( seed );
        Random yRand = new Random( 2*seed );
        boolean x_taken[] = new boolean[n];
        int x, y;

        // Generate the x,y coords of each vertex. Be sure they are unique.
        for ( int i=0; i<n; i++ ) {
             do {
                  x = xRand.nextInt(n);
                  y = yRand.nextInt(n);
              } while ( (x_taken[x]) );
              x_taken[x] = true;
              vertices[i] = new Vertex( i, x, y );
        }
    }

    /**
     * Builds the adjacency matrix as a table of distances between all
     * possible pairs of vertices.
     */
    public void buildMatrix() {
        if ( vertices != null ) {
            matrix = new double[n][n];

            // calculate the distance between all pairs
            for (int i=0; i<n; i++)
                for (int j=0; j<n; j++)
                   matrix[i][j] = calcDistance( vertices[i], vertices[j] );
        }
    }

/****************************** Greedy Algorithm ******************************/
    private double[][] greedyMatrix = null;
    private int[] root = null;
    private int[] rank = null;
    private ArrayList<Edge> usedEdges = new ArrayList<Edge>();
    
    /**
     * The main algorithm for the Greedy TSP. Initializes data structures. Sorts
     * the edges with a quick sort in order of increasing weight. For each edge
     * in that sorted list, adds it to the greedy edge tree if it doesn't create
     * a cycle, and if it's not the third edge to or from a single vertex.
     */
    public void buildGreedyMatrix() {
        //initialize data structures
        greedyMatrix = new double[n][n];
        root = new int[n];
        rank = new int[n];

        // sort the edges in increasing order by weight
        ArrayList<Edge> allEdges = getAllEdges();
        ArrayList<Edge> sortedEdges = quickSort(allEdges);

        // initialize partition data structures
        for ( int i=0; i<n; i++ ) { root[i]=i; rank[i]=0; }

        // for every edge, add to the greedy tree as long as it doesn't create
        //      a cycle and it's not the 3rd edge to or from any vertex
        for ( Edge e : sortedEdges ) {
            int root1 = find( e.getFrom().getNum() );
            int root2 = find( e.getTo().getNum() );
            if ( (root1!=root2) || ((root1==root2)
                                        && ( usedEdges.size() == n-1 ) ))
                if ( !isThirdEdge( e ) ) {
                    addGreedyEdge( e );
                    union( root1, root2 );
                }
        }
    }

    /**
     * Performs a depth-first search starting with vertex 0 on the edges
     * contained in the greedy adjacency matrix.
     * 
     * @return  an integer array representing the path found via DFS
     */
    ArrayList<Integer> neighbors = new ArrayList<Integer>();
    ArrayList<Integer> visited = new ArrayList<Integer>();
    public int[] getGreedyPath() {
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(0);
        getNeighbors(0);
        while ( !neighbors.isEmpty() ) {
            int listEnd = neighbors.remove( neighbors.size()-1 );
            if ( !visited.contains( listEnd ) ) {
                path.add( listEnd );
                getNeighbors( listEnd );
            }
        }
        int[] retVal = new int[n+1];
        for ( int i=0; i<path.size(); i++)
            retVal[n-i] = path.get(i);
        return retVal;
    }

    /**
     * Searches the adjacency matrix to find all
     * neighboring vertices of vertex x.
     *
     * @param x     Vertex to find neighbors for
     */
    private void getNeighbors( int x ) {
        visited.add(x);
        for ( int i=0; i<n; i++ )
            if ( greedyMatrix[x][i] != 0 )
                neighbors.add( i );
    }

    /**
     * Path-compression algorithm. Finds the root of the tree for a given
     * vertex and makes all vertices in the subtree point to that root.
     *
     * @param x     Vertex to find the root for
     * @return      The root of the tree for the given vertex
     */
    private int find( int x ) {
        // perform the find algorithm with path compression
        if ( x != root[x] )
            root[x] = find( root[x] );
        return root[x];
    }

    /**
     * Union-by-Rank algorithm. Links two subtrees together. The root of the
     * smaller subtree is linked to the root of the larger. Attempts to keep
     * the height of the newly formed tree short.
     *
     * @param x     vertex of the first tree to join together
     * @param y     vertex of the second tree to join together
     */
    private void union( int x, int y ) {
        if ( rank[x] > rank[y] ) {
            root[y] = x;
        } else {
            root[x] = y;
            if ( rank[x] == rank[y] )
                rank[y] = rank[y]+1;
        }
    }

    /**
     * Adds an edge to the greedy adjacency matrix, keeping it symmetrical.
     * Also keeps track of the edges in the order they were added with usedEdges
     *
     * @param e     Edge to add
     */
    private void addGreedyEdge( Edge e ) {
        greedyMatrix[e.getFrom().getNum()][e.getTo().getNum()] = e.getWeight();
        greedyMatrix[e.getTo().getNum()][e.getFrom().getNum()] = e.getWeight();
        usedEdges.add(e);
    }

    /**
     * Checks if an edge would be the third edge to be added from vertex 'u'
     * If two edges have already been added to the greedy adjacency matrix with
     * the same 'from' coordinate as 'e', this would be the third.
     *
     * @param e     Edge to check
     * @return      true if 'e' would be the third edge from vertex 'u'
     */
    private boolean isThirdEdge( Edge e ) {
        int from = e.getFrom().getNum();
        int to = e.getTo().getNum();
        int fromCount = 0, toCount = 0;
        for (int i=0; i<n; i++) {
            if ( greedyMatrix[from][i] != 0 ) fromCount++;
            if ( greedyMatrix[i][to] != 0) toCount++;
        }
        return (fromCount >= 2) || (toCount >= 2);
    }

    /**
     * Builds an ArrayList containing all edges in the adjacency matrix.
     * Ignores edges from one vertex to the same.
     *
     * @return  ArrayList of all edges, in unsorted order
     */
    private ArrayList<Edge> getAllEdges() {
        ArrayList<Edge> retVal = new ArrayList<Edge>();
        for( Vertex i : vertices )
            for ( Vertex j : vertices )
                if ( getDistance( i.getNum(), j.getNum() ) != 0 )
                    retVal.add( new Edge( i, j,
                                getDistance( i.getNum(), j.getNum() ) ) );
        return retVal;
    }

    /**
     * In-place simple quick sort algorithm, the basis of which was found at
     *      http://en.wikipedia.org/wiki/Quicksort#Simple_version
     * It was modified to sort a list of edges based on weight, then left
     * vertex, followed by right vertex.
     *
     * @param array     ArrayList of edges to sort
     * @return          ArrayList of edges, sorted by increasing weight
     */
    private ArrayList<Edge> quickSort( ArrayList<Edge> array ) {
        ArrayList<Edge> less = new ArrayList<Edge>();
        ArrayList<Edge> greater = new ArrayList<Edge>();
        if ( array.size() <= 1 ) return array;
        Edge pivot = array.remove(0);
        for ( Edge e : array ) {
            if ( e.getWeight() < pivot.getWeight() ) {
                less.add(e);
            } else if ( e.getWeight() > pivot.getWeight() ) {
                greater.add(e);
            } else if ( e.getFrom().getNum() < pivot.getFrom().getNum() ) {
                less.add(e);
            } else if ( e.getFrom().getNum() > pivot.getFrom().getNum() ) {
                greater.add(e);
            } else if ( e.getTo().getNum() < pivot.getTo().getNum() ) {
                less.add(e);
            } else if ( e.getTo().getNum() > pivot.getTo().getNum() ) {
                greater.add(e);
            }
        }
        return concatenate(quickSort( less ), pivot, quickSort( greater ) );
    }

    /**
     * Method for quick sort to join two ArrayLists and a pivot
     * element into one long ArrayList.
     *
     * @param a     Array to be placed at beginning
     * @param x     Edge to be places in middle
     * @param b     Array to be placed at end
     * @return      Completely joined array
     */
    private ArrayList<Edge> concatenate
            ( ArrayList<Edge> a, Edge x, ArrayList<Edge> b ) {
        ArrayList<Edge> retVal = new ArrayList<Edge>();
        retVal.addAll(a);
        retVal.add(x);
        retVal.addAll(b);
        return retVal;
    }
/***************************** Optimal Algorithm ******************************/
    /**
     * Begins from the first lexicographic permutation and generates all
     * following permutations. The best permutation is stored and updated
     * whenever a new permutation is encountered.
     *
     * @return  The best possible "optimal" permutation
     */
    public int[] getBestPath() {
        // Build the first permutation
        int[] curPerm = new int[n+1];
        for (int i=0; i<n; i++) curPerm[i]=i;
        curPerm[curPerm.length-1] = 0;

        // loop through all permutations until we've exhausted all possibilities
        do {
            double pathDistance = pathDistance( curPerm );
            setBestPath( curPerm, pathDistance );

            if ( n <= 5) { // print all paths in lexicographical order
                System.out.print("Path: ");
                for ( int i : curPerm )
                    System.out.print( i + " " );
                System.out.println( " distance = " + df.format(pathDistance) );
            }
            curPerm = getNextPerm(curPerm);
        } while ( curPerm != null );

        return bestPath;
    }
    
    /**
     * Checks the current best path between vertices and against given one. If
     * the new path is better, set the best path to new path.
     *
     * @param path  Path to test
     */
    private int[] bestPath = null;
    private double bestDistance = -1;
    private void setBestPath( int[] path, double pathDistance ) {
        // initilize storage if first call
        if ( bestPath == null )
            bestPath = new int[ path.length ];
        if ( bestDistance == -1 )
            bestDistance = pathDistance;

        // check distance against the current best
        if ( pathDistance < bestDistance ) {
            System.arraycopy(path, 0, bestPath, 0, path.length);
            bestDistance = pathDistance;
        }
    }

    /**
     * Generates the next lexicographic permutation from the current one.
     * The description for this algorithm was found at:
     *      http://compprog.wordpress.com/2007/10/08/generating-permutations-2/
     *
     * @param curPerm   The current permutation
     * @return          The next permutation by lexicographical ordering
     */
    private int[] getNextPerm( int[] perm ) {
         // set the max bound to length-1 to ignore the trailing 0
        int len = perm.length - 1;

        // find the largest i, such that i+1 < i, ignoring the leading 0
        int i = len - 2;
        while ( (i >= 1) && (perm[i] > perm[i + 1]) )
             --i;

        // if i<1, the numbers are in reverse order and we are finished
        if (i < 1)
            return null;

        // Find the largest element after i but not larger than i
        int k = len - 1;
        while (perm[i] > perm[k])
            --k;
        swap(perm, i, k);

        // reverse the last n-i elements
        k = 0;
        for ( int j = i+1; j < (len+i)/2 + 1; ++j, ++k)
            swap( perm, j, len-k-1 );

        return perm;
    }

    /**
     * Swaps two elements within an array.
     *
     * @param perm  The array to use
     * @param a     The position of the first element
     * @param b     The position of the second element
     */
    private void swap( int[] perm, int a, int b ) {
        int temp = perm[a];
        perm[a] = perm[b];
        perm[b] = temp;
    }

/****************************** Distance Methods ******************************/
    /**
     * Calculates the distance between two vertices using the equation:
     *
     * D = sqrt( (x2 - x1)^2 + (y2 - y1)^2 )
     *
     * @param i     The first vertex
     * @param j     The second vertex
     * @return      The distance between the two vertices
     */
    private double calcDistance( Vertex i, Vertex j ) {
        if ( i==j ) return 0;
        int x1 = i.getX();
        int y1 = i.getY();
        int x2 = j.getX();
        int y2 = j.getY();

        return Math.sqrt( Math.pow((x2 - x1),2) + Math.pow((y2 - y1),2) );
    }
    
    /**
     * Follows a path through the adjacency matrix and calculate the complete
     * distance traveled.
     *
     * @param path  Order to traverse vertices
     * @return      Total distance traveled
     */
    public double pathDistance ( int[] path ) {
        double sum = 0;
        for ( int i=0; i<path.length-1; i++ )
            sum += getDistance( path[i], path[i+1] );
        return sum;
    }

    /**
     * Grab a value out of the adjacency matrix at position a, b
     *
     * @param a     the first vertex
     * @param b     the second vertex
     * @return      The stored distance between the two vertices or
     *              -1 if the matrix has not yet been generated
     */
    public double getDistance( int a, int b ) {
        if (matrix != null)
            return matrix[a][b];
        return -1;
    }

    /**
     * Grab a value out of the greedy adjacency matrix at position a, b
     *
     * @param a     the first vertex
     * @param b     the second vertex
     * @return      The stored distance between the two vertices or
     *              -1 if the matrix has not yet been generated
     */
    public double getGreedyDistance( int a, int b ) {
        if (greedyMatrix != null)
            return greedyMatrix[a][b];
        return -1;
    }

    
/****************************** Printing Methods ******************************/
    /**
     * Print the vertices with x and y coordinates in order.
     */
    public void printVertices() {
        System.out.println("X-Y Coordinates:");
        for ( Vertex v : vertices )
            System.out.print( "v" + v.getNum() + ": "
                                + "(" + v.getX() + "," + v.getY() + ") ");
        System.out.println("\n");
    }

    /**
     * Prints the adjacency matrix of Euclidean weights with labels
     * for the rows and columns.
     */
    public void printMatrix() {
        System.out.println("Adjacency matrix of graph weights:\n");
        for ( int i=0; i<n; i++ )
            System.out.print( "      "+i );
        System.out.println("\n");
        for ( int i=0; i<n; i++ ) {
            System.out.print(i);
            for ( int j=0; j<n; j++ ) {
                double curDist = getDistance(i,j);
                System.out.print( ( ( curDist < 10 )? "   " : "  " )
                                    + df.format( curDist ) );
            }
            System.out.println("\n");
        }
    }

    /**
     * Prints the sub-graph of the original adjacency matrix of Euclidean
     * weights with labels for the rows and columns. Represents the graph used
     * by the greedy algorithm.
     */
    public void printGreedyMatrix() {
        System.out.println("Greedy graph:");
        System.out.println("Adjacency matrix of graph weights:\n");
        for ( int i=0; i<n; i++ )
            System.out.print( "      "+i );
        System.out.println("\n");
        for ( int i=0; i<n; i++ ) {
            System.out.print(i);
            for ( int j=0; j<n; j++ ) {
                double curDist = getGreedyDistance(i,j);
                System.out.print( ( ( curDist < 10 )? "   " : "  " )
                                    + df.format( curDist ) );
            }
            System.out.println("\n");
        }
    }

    /**
     * Prints the list of edges found by the greedy algorithm
     * in the order in which they were found.
     */
    public void printGreedyEdges() {
        System.out.println("Edges of tour from greedy graph:");
        for ( Edge e : usedEdges ) {
            System.out.println( e.getFrom().getNum() + " " + e.getTo().getNum()
                    + " weight = " + df.format( e.getWeight() ) );
        }
    }
}
