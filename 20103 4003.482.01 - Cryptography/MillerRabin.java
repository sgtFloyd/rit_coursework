import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * MillerRabin.java
 * @author Gabriel Smith (ges7506@rit.edu)
 * Created Apr 6, 2011
 */
public class MillerRabin {
    private static final DecimalFormat df = new DecimalFormat("0.0000000000");
    
    // Table of primes between 101000 and 102000, given by WolframAlpha
    private static final int[] primes = {
        101009, 101021, 101027, 101051, 101063, 101081, 101089, 101107, 101111,
        101113, 101117, 101119, 101141, 101149, 101159, 101161, 101173, 101183,
        101197, 101203, 101207, 101209, 101221, 101267, 101273, 101279, 101281,
        101287, 101293, 101323, 101333, 101341, 101347, 101359, 101363, 101377,
        101383, 101399, 101411, 101419, 101429, 101449, 101467, 101477, 101483,
        101489, 101501, 101503, 101513, 101527, 101531, 101533, 101537, 101561,
        101573, 101581, 101599, 101603, 101611, 101627, 101641, 101653, 101663,
        101681, 101693, 101701, 101719, 101723, 101737, 101741, 101747, 101749,
        101771, 101789, 101797, 101807, 101833, 101837, 101839, 101863, 101869,
        101873, 101879, 101891, 101917, 101921, 101929, 101939, 101957, 101963,
        101977, 101987, 101999 }; 
    
    // CONSTANTS
    private static final BigInteger NEG_ONE = new BigInteger(""+(-1));
    private static final BigInteger ZERO = new BigInteger(""+0);
    private static final BigInteger ONE = new BigInteger(""+1);
    private static final BigInteger TWO = new BigInteger(""+2);
    
    private static double largestError = -1.0;
    private static BigInteger largestErrorVal = null;
    
    public static void main( String args[] ) {
        // test all primes between 101000 and 102000
        for ( int i=101000; i<=102000; i++ ) {
            BigInteger n = new BigInteger(i+"");
            boolean isPrime = isPrime(n);
            // calculate m and k
            int k = 0;
            BigInteger m = n.subtract(ONE);
            while ( m.mod(TWO).equals(ZERO) ) {
                m = m.divide(TWO);
                k++;
            }
            // test values for random variable a, 1 <= a <= n-1
            int numErrors = 0;
            for (BigInteger a=ONE; a.compareTo(n)<0; a = a.add(ONE)) {
                boolean testsPrime = millerRabin( n, a, m, k );
                if ( isPrime && !testsPrime ) { numErrors++; }
                if ( !isPrime && testsPrime ) { numErrors++; }
            }
            
            // calculate error
            double possibleValuesA = n.intValue()-1;
            double percentError = numErrors / possibleValuesA;
            
            // keep track of largest percent error
            if ( percentError > largestError ) {
                largestError = percentError;
                largestErrorVal = n;
            }
            // System.out.println("e("+i+") = " + df.format(percentError) );
        }
        
        // print largest percent error w/ corresponging prime
        System.out.println("\nLargest percent errror: "
                            + df.format(largestError) );
        System.out.println(" for corresponding integer: "
                            + largestErrorVal.intValue() );
    }
    
    private static boolean millerRabin
            ( BigInteger n, BigInteger a, BigInteger m, int k ) {
        // b = a^m mod n
        BigInteger b = a.modPow(m, n);
        // if b is congruent to 1 mod n
        if ( ( b.mod(n) ).compareTo( ONE.mod(n) ) == 0 )
            // return prime
            return true;
        // for i = 0 to k-1
        for ( int i=0; i<k; i++ ) {
            // if b is congruent to -1 mod n
            if ( ( b.mod(n) ).compareTo( NEG_ONE.mod(n) ) == 0 )
                // return prime
                return true;
            else
                // b = b^2 mod n
                b = b.modPow(TWO, n);
        }
        // return composite
        return false;
    }
    
    /**
     * Check the list of primes to see if the given number is in it.
     * @param test  number to test
     * @return  true if the number is contained in the list of primes.
     */
    private static boolean isPrime( BigInteger bigTest ) {
        int test = bigTest.intValue();
        for ( int prime : primes )
            if ( test == prime )
                return true;
        return false;
    }
}
