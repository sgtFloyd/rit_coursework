import java.text.DecimalFormat;

/**
 * @author Gabriel Smith
 * Created Dec 13, 2010
 *
 * Processes the command line arguments, initializes the graph and call all
 * functions in the appropriate order. Prints the optimal path found as well as
 * total runtime to find said path.
 */
public class OptimalTSP {

    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void main( String args[] ) {
        int n=0;
        int seed=0;
        
        /* check command line arguments for validity */
        if ( args.length != 2 )
            error( "Usage: java OptimalTSP n seed" );

        try {
            n = Integer.parseInt( args[0] );
            seed = Integer.parseInt( args[1] );
        } catch ( NumberFormatException e ) {
            error( "Command line args must be integers" );
        }

        if ( n<1 || n>13 )
            error( "Number of vertices must be between 1 and 13" );

        /* mark the start time of the current run */
        long startTime = System.currentTimeMillis();

        /* generate the graph and build adjacancy matrix */
        Graph graf = new Graph( n, seed );
        graf.generateGraph();
        graf.buildMatrix();

        /* have the graph find the optimal path between vertices */
        int[] optimal = graf.getBestPath();

        /* print optimal path with distance */
        System.out.print( "\nOptimal distance: "
                + df.format( graf.pathDistance(optimal) )
                + " for path ");
        for ( int i : optimal )
            System.out.print( i + " " );

        /* print total runtime */
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("\nRuntime for optimal TSP   : "
                + runTime + " milliseconds");
    }

    /**
     * Print an error message and exit the current run.
     *
     * @param msg   Message to print
     */
    private static void error( String msg ) {
        System.out.println( msg );
        System.exit(1);
    }
    
}
