import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * @author Gabriel Smith
 * Created Feb 4, 2011
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
    
    /**
     * Finds all the neighboring vertices for give vertex.
     *
     * @param v     Vertex to examine
     * @return      List of neighbors for given vertex
     */
    private ArrayList<Vertex> getNeighbors( Vertex v ) {
        ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
        int x = v.getNum();
        for ( int i=0; i<n; i++ )
            if ( matrix[x][i] != 0 )
                neighbors.add( vertices[i] );
        return neighbors;
    }
/****************************** Bitonic Algorithm *****************************/
    double[][] L;   //table of distances
    int[][] N;      // table of neighbors
    ArrayList<Vertex> sortedVertices;

    /**
     * Builds the L and N tables on the list of vertices sorted on
     * increasing x values. This algorithm was provided by Professor
     * Canosa in a handout titled "Bitonic TSP"
     */
    public void runBitonicTSP(){
        L = new double[n][n];
        N = new int[n][n];
        for (int y=0;y<n;y++) for (int z=0;z<n;z++) N[y][z]=-1;
        sortedVertices = sortOnX( getVertexList() );
        // compute l(i,j) and N(i,j) for all 0<=i<j<n
        for ( int j=1; j<n; j++ ) {
            for ( int i=0; i<j; i++ ) {
                if ( i==0 && j==1 ) {
                    L[i][j] = getSortedDistance(i, j);
                    N[i][j] = i;
                } else if ( i < j-1 ) {
                    L[i][j] = L[i][j-1] + getSortedDistance(j-1, j);
                    N[i][j] = j-1;
                } else {
                    L[i][j] = Double.MAX_VALUE;
                    for ( int k=0; k<i; k++ ){
                        double q = L[k][i] + getSortedDistance(k, j);
                        if ( q < L[i][j] ) {
                            L[i][j] = q;
                            N[i][j] = k;
                        }
                    }
                }
            }
        }
    }

    /**
     * Uses the N-table of neighbors to build a bitonic path beginning
     * with the bottom right entry in the table. The basi: s for this
     * algorithm was found at:
     *      http://www.cs.huji.ac.il/course/2004/algo/Solutions/bitonic.pdf
     * Without the "correction" portion of the algorithm.
     *
     * @return  Array of integers representing the generated path
     */
    public int[] getBitonicPath() {
        Stack[] S = new Stack[2];
        S[0] = new Stack<Integer>();
        S[1] = new Stack<Integer>();

        int k = 0;
        int i = n-2;
        int j = n-1;
        // travrerse the neighbor table, build two stacks, represeting
        // the left-to-right, and right-to-left paths
        while ( j>0 ) {
            S[k].push(j);
            j = N[i][j];
            if ( j < i ) {
                int temp = i; i=j; j=temp;  // swap i & j
                k ^= 1;                     // toggle k
            }
        }
        // join the two stacks
        S[0].push(0);
        while ( !S[1].empty() )
            S[0].push( S[1].pop() );

        // copy the stack into an array
        int[] T = new int[n];
        for ( i=0; i<n; i++ )
            T[i] = sortedVertices.get((Integer)S[0].pop()).getNum();

        return correct(T);
    }

    /**
     * Corrects a path generated by getBitonicPath by ensuring it begins
     * and ends with the 0th node.
     *
     * @param T path generated by getBitonicPath
     * @return  corrected path beginning and ending with 0
     */
    private int[] correct( int[] T ) {
        ArrayList<Integer> correctPath= new ArrayList<Integer>();

        int zeroLocation = -1;
        for ( int i=0; i<T.length; i++ ) {
            if ( T[i] == 0 )
                zeroLocation = i;
            if ( zeroLocation != -1 )
                correctPath.add( T[i] );
        }

        for ( int i=0; i<zeroLocation; i++ )
            correctPath.add(T[i] );

        correctPath.add(0);

        int[] retVal = new int[T.length+1];
        for ( int i=0; i<retVal.length; i++ )
            retVal[i] = correctPath.get(i);

        return retVal;
    }

    /**
     * Builds an array list from the array of vertices
     *
     * @return  Array list of all vertices
     */
    private ArrayList<Vertex> getVertexList() {
        ArrayList<Vertex> retVal = new ArrayList<Vertex>();
        for ( Vertex v : vertices )
            retVal.add(v);
        return retVal;
    }

    /**
     * Sorts a list of vertices on increasing x values.
     *
     * @param array List to sort
     * @return  Sorted list of vertices
     */
    private ArrayList<Vertex> sortOnX( ArrayList<Vertex> array ) {
        ArrayList<Vertex> less = new ArrayList<Vertex>();
        ArrayList<Vertex> greater = new ArrayList<Vertex>();
        if ( array.size() <= 1 ) return array;
        Vertex pivot = array.remove(0);
        for ( Vertex v : array ) {
            if ( v.getX() < pivot.getX() ) {
                less.add(v);
            } else {
                greater.add(v);
            }
        }
        return concatenate(sortOnX( less ), pivot, sortOnX( greater ) );
    }

    /**
     * Used for sortOnX quick sort, merged two lists with an object in between.
     *
     * @param a First list
     * @param x object to be placed in the middle
     * @param b Second list
     * @return  One large list containing all the passed in elements.
     */
    private ArrayList<Vertex> concatenate
            ( ArrayList<Vertex> a, Vertex x, ArrayList<Vertex> b ) {
        ArrayList<Vertex> retVal = new ArrayList<Vertex>();
        retVal.addAll(a);
        retVal.add(x);
        retVal.addAll(b);
        return retVal;
    }
    
/********************** Minimum Spanning Tree Algorithm ***********************/
    PriorityQueue pq = null;
    int[] parent = null;
    double[][] mstMatrix;
    double totalMstWeight = 0;
    ArrayList<Integer> traversal;

    /**
     * Builds the minimum spanning tree using the algorithm found in the
     * "Minimum Spanning Trees and Shortest Path" lecture notes, page 5.
     * Uses a priority queue to keep track of the fringe nodes while
     * building the tree, and the "parent" array to keep track of the MST
     * at every stage of the algorithm.
     */
    public void buildMST() {
        pq = new PriorityQueue( vertices );
        parent = new int[n];

        Vertex startingNode = vertices[0];
        pq.change( startingNode, 0. );
        parent[0] = -1;

        while ( !pq.isEmpty() ) {
            Vertex priorityNode = pq.extractMin();
            for ( Vertex v : getNeighbors( priorityNode ) ) {
                if ( pq.contains(v) ) {
                    double testDistance = getDistance( priorityNode, v );
                    if ( testDistance < pq.get(v) ) {
                        pq.change( v, testDistance );
                        parent[v.getNum()] = priorityNode.getNum();
                    }
                }
            }
        }
        buildMstMatrix();
    }

    /**
     * Uses the parent array to build the minimum spanning tree as a
     * matrix of Euclidean weights. Builds the matrix symmetrically.
     */
    private void buildMstMatrix() {
        mstMatrix = new double[n][n];
        for ( int i=0; i<n; i++ ) {
            if ( i != 0 ) { // skip node 0 since it has no parent
                double distance = getDistance( i, parent[i] );
                mstMatrix[i][parent[i]] = distance;
                mstMatrix[parent[i]][i] = distance;
                totalMstWeight += distance;
            }
        }
    }

    /**
     * Recursively performs a pre-order depth-first traversal on the MST,
     * starting with the given root node.
     */
    public void buildTraversal( int root ) {
        if ( traversal == null ) traversal = new ArrayList<Integer>();
        traversal.add( root );
        if ( traversal.size() < n ) {
            ArrayList<Integer> neighbors = getMstNeighbors( root );
            for ( Integer i : neighbors )
                if ( !traversal.contains(i) )
                    buildTraversal( i );
        }
    }

    /**
     * Inspects the built Minimum Spanning Tree matrix and gets all neighbors
     * for a given vertex.
     *
     * @param vertex    Vertex to get neighbors for
     * @return          ArrayList of vertices which neighbor the given vertex
     */
    private ArrayList<Integer> getMstNeighbors( int vertex ) {
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        for ( int i=0; i<n; i++ )
            if ( mstMatrix[vertex][i] != 0 )
                neighbors.add( i );
        return neighbors;
    }

    /**
     * Uses the order nodes were visited during the pre-order traversal to
     * build an approximately-shortest path, beginning and ending at node 0.
     *
     * @return  array representing the approx. shortest path
     */
    public int[] getMstPath() {
        int[] traversalPath = new int[n+1];
        int i;
        for ( i=0; i< traversal.size(); i++ )
            traversalPath[i] = traversal.get(i);
        traversalPath[i] = 0;
        return traversalPath;
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
    ArrayList<Integer> greedyNeighbors = new ArrayList<Integer>();
    ArrayList<Integer> visited = new ArrayList<Integer>();
    public int[] getGreedyPath() {
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(0);
        getGreedyNeighbors(0);
        while ( !greedyNeighbors.isEmpty() ) {
            int listEnd = greedyNeighbors.remove( greedyNeighbors.size()-1 );
            if ( !visited.contains( listEnd ) ) {
                path.add( listEnd );
                getGreedyNeighbors( listEnd );
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
    private void getGreedyNeighbors( int x ) {
        visited.add(x);
        for ( int i=0; i<n; i++ )
            if ( greedyMatrix[x][i] != 0 )
                greedyNeighbors.add( i );
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
     * Grab a value out of the adjacency matrix at position [a][b],
     * according to the values of the vertices passed in.
     *
     * @param a     the first vertex
     * @param b     the second vertex
     * @return      The stored distance between the two vertices or
     *              -1 if the matrix has not yet been generated
     */
    public double getDistance( Vertex a, Vertex b ) {
        return getDistance( a.getNum(), b.getNum() );
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

    /**
     * Grab a value out of the greedy adjacency matrix at position a, b
     *
     * @param a     the first vertex
     * @param b     the second vertex
     * @return      The stored distance between the two vertices or
     *              -1 if the matrix has not yet been generated
     */
    public double getMstDistance( int a, int b ) {
        if ( mstMatrix != null)
            return mstMatrix[a][b];
        return -1;
    }

    /**
     * Calculates the distance between two vertices in the sortedVertices list.
     *
     * @param a Index of first vertex
     * @param b Index of second vertex
     * @return  distance between the two vertices
     */
    private double getSortedDistance( int a, int b ) {
        return calcDistance( sortedVertices.get(a), sortedVertices.get(b) );
    }

    
/****************************** Printing Methods ******************************/
    /**
     * Print the vertices with x and y coordinates in order of vertex number.
     */
    public void printVertices() {
        System.out.println("X-Y Coordinates:");
        for ( Vertex v : vertices )
            System.out.print( "v" + v.getNum() + ": "
                                + "(" + v.getX() + "," + v.getY() + ") ");
        System.out.println("\n");
    }
    
    /**
     * Print the vertices with x and y coordinates in order of x-coordinate.
     */
    public void printSortedVertices() {
        System.out.println("Sorted X-Y Coordinates:");
        for ( Vertex v : sortedVertices )
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

    /**
     * Prints the sub-graph of the original adjacency matrix of Euclidean
     * weights with labels for the rows and columns. Represents the graph used
     * by the minimum spanning tree algorithm.
     */
    public void printMstMatrix() {
        System.out.println("Minimum Spanning Tree:");
        System.out.println("Adjacency matrix of graph weights:\n");
        for ( int i=0; i<n; i++ )
            System.out.print( "      "+i );
        System.out.println("\n");
        for ( int i=0; i<n; i++ ) {
            System.out.print(i);
            for ( int j=0; j<n; j++ ) {
                double curDist = getMstDistance(i,j);
                System.out.print( ( ( curDist < 10 )? "   " : "  " )
                                    + df.format( curDist ) );
            }
            System.out.println("\n");
        }
        System.out.println( "Total weight of mst: " + df.format(totalMstWeight) 
                            + "\n");
    }

    /**
     * Prints the list of vertices generated by the pre-order traversal of
     * the minimum spanning tree. Vertices are printed in the order in which
     * they were visited.
     */
    public void printMstTraversal() {
        System.out.println("Pre-order traversal: ");
        for ( int i=0; i<traversal.size(); i++ ) {
            System.out.println( "Parent of " + traversal.get(i)
                                    + " is " + parent[traversal.get(i)] );
        }
    }

    /**
     * Print the values of Bitonic TSP's L-Table
     */
    public void printLTable() {
        System.out.println("L-Table:");
        for ( int i=0; i<n; i++ ) {
            for ( int j=0; j<n; j++ ) {
                System.out.print(" " + df.format( L[i][j] ) );
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Print the values of Bitonic TSP's N-Table
     */
    public void printNTable() {
        System.out.println("N-Table:");
        for ( int i=0; i<n; i++ ) {
            for ( int j=0; j<n; j++ ) {
                System.out.print( ((N[i][j]<0)? "":" ") + N[i][j] + " " );
            }
            System.out.println();
        }
    }
}
