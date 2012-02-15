import java.text.DecimalFormat;

/**
 * @author Gabriel Smith
 * Created Jan 23, 2011
 *
 */
public class GreedyTSP {

    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void main( String args[] ) {
        int n=0, seed=0;

        /* check command line arguments for validity */
        if ( args.length != 2 )
            error( "Usage: java OptimalTSP n seed" );
        try {
            n = Integer.parseInt( args[0] );
            seed = Integer.parseInt( args[1] );
        } catch ( NumberFormatException e ) {
            error( "Command line args must be integers" );
        }
        if ( n<1 )
            error( "Number of vertices must be greater than 0" );
        
        /* mark the start time of the current run */
        long startTime = System.currentTimeMillis();

        /* construct graph */
        Graph graf = new Graph( n, seed );
        graf.generateGraph();
        graf.buildMatrix();

        /* run greedy algorithm */
        graf.buildGreedyMatrix();
        int[] greedy = graf.getGreedyPath();

        /* if n <= 10, print everything */
        if ( n <= 10 ) { 
            graf.printVertices();
            graf.printMatrix();
            graf.printGreedyMatrix();
            graf.printGreedyEdges();
        }

        /* print greedy path with distance */
        System.out.print( "\nDistance using greedy: "
                + df.format( graf.pathDistance(greedy) )
                + " for path ");
        for ( int i : greedy )
            System.out.print( i + " ");

        /* print total runtime */
        long runTime = System.currentTimeMillis() - startTime;
        System.out.println("\nRuntime for greedy TSP   : "
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
