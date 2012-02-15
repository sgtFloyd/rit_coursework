import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author Gabriel Smith
 * Created Dec 14, 2010
 *
 * Stores the list of vertices and all data about the generated graph. Contains
 * code to generate random vertices, build an adjacency matric from those
 * vertices, generate all possible permutations of the vertices and keep track
 * of the best path found.
 */
public class Graph {

    private int n, seed;
    private Vertex[] vertices = null;
    private double[][] matrix = null;
    private DecimalFormat df = new DecimalFormat("0.00");

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

            if ( n <= 10 ) { // if n <= 10, print vertices and matrix
                printVertices();
                printMatrix();
            }
        }
    }

    /**
     * Print the vertices with x and y coordinates in order.
     */
    private void printVertices() {
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
    private void printMatrix() {
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

            if ( n <= 5 ) { // print all paths in lexicographical order
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
        if ( bestPath == null ) {
            bestPath = new int[ path.length ];
            bestDistance = pathDistance;
	}

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

        // find the largest element after i but not larger than i
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
    private void swap( int[] ar, int a, int b ) {
        int temp = ar[a];
        ar[a] = ar[b];
        ar[b] = temp;
    }
}
