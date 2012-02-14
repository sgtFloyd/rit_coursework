/**
 * @author Gabriel Smith
 * 
 * Discrete Math II, Project A-4
 * Finds and prints all optimal pairs of numbers between 1 and an upper limit.
 *
 */

import java.math.BigInteger;

public class ProjectMain {
	//constants, defined for convenience
	public static final BigInteger ONE = BigInteger.ONE;
	public static final BigInteger ZERO = BigInteger.ZERO;
	public static final BigInteger TEN = BigInteger.TEN;
	public static final BigInteger TWO = new BigInteger("2");
	public static final BigInteger THREE = new BigInteger("3");
	//test cases for max value
	public static final BigInteger TEST_A = TEN.pow(7); //10,000,000
	public static final BigInteger TEST_B = TEN.pow(12); //1,000,000,000,000
	public static final BigInteger TEST_X = new BigInteger("5000");
	//total number of optimal pairs found
	public static int totalPairs = 0;
	
	/**
	 * @param max the upper limit of the program
	 */
	public static void run(BigInteger max){
		//cycle through 1 to max
		for (BigInteger i=ONE; i.compareTo(max)==-1; i=i.add(ONE)){
			//cycle through i to max
			for (BigInteger j=i; j.compareTo(max)==-1; j=j.add(ONE)){
				if (relativelyPrime(i,j)){
					if(optimalPair(i,j)){
						//found an optimal pair!
						System.out.println(i.toString() + ", " + j.toString());
						totalPairs++;
					}
				}
			}
		}
	}
	
	/**
	 * @param a the first number
	 * @param b the second number
	 * @return true if the numbers are an optimal pair
	 */
	public static boolean optimalPair(BigInteger a, BigInteger b){
		BigInteger product = a.multiply(b);
		BigInteger sum = ZERO;
		//cycle through a to b, inclusive
		for (BigInteger i=a; i.compareTo(b.add(ONE))==-1; i=i.add(ONE)){
			if(isPrime(i)){
				sum = sum.add(i);
				//if sum > product, break
				if (sum.compareTo(product)==1){
					return false;
				}
			}
		}
		if (product.equals(sum)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param a the first number
	 * @param b the second number
	 * @return true if the numbers are relatively prime
	 */
	public static boolean relativelyPrime(BigInteger a, BigInteger b){
		if (GCD(a,b).equals(ONE)){
			return true;
		}
		return false;
	}
	
	/**
	 * @param a the first number
	 * @param b the second number
	 * @return the Greatest Common Divisor of the two numbers
	 */
	public static BigInteger GCD(BigInteger a, BigInteger b){
		return a.gcd(b);
	}
	
	/**
	 * @param bi the number to test
	 * @return true if the number is prime
	 */
	public static boolean isPrime(BigInteger n){
		boolean prime = true;
		BigInteger sR = new BigInteger("" + ((long) Math.sqrt(n.longValue())));
		//cycle through odds from 3 to sqrt(n), inclusive
		for (BigInteger i = THREE; i.compareTo(sR.add(ONE))==-1; i=i.add(TWO)){
			//if i divides n, not prime
			if ((n.mod(i)).equals(ZERO)){
				prime = false;
				break;
			}
		}
		//if n>2, isn't even and above loop returned true
		// -or- if n=2, number is prime
		if (( prime && !(n.mod(TWO)).equals(ZERO) && n.compareTo(TWO)==1)
				|| n.equals(TWO)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @param args unused
	 */
	public static void main(String[] args){
		BigInteger currentTest = TEST_X;
		long startTime = System.currentTimeMillis();
		run(currentTest);
		long runTime = System.currentTimeMillis() - startTime;
		System.out.println("Optimal pairs found between 0 and " +
							currentTest.toString() + ": " + totalPairs);
		System.out.println("Total run time: " + formatRunTime(runTime));
	}

	/**
	 * @param runTime the total run time calculated
	 * @return a string, formatted as H:MM:SS:mmm
	 */
	public static String formatRunTime(long runTime){
		long hour,min,sec,msec;
		hour = runTime / 3600000;
		runTime -= (hour * 3600000);
	    min = runTime / 60000;
	    runTime -= (min * 60000);
	    sec = runTime / 1000;
	    runTime -= (sec * 1000);
	    msec = runTime;
	    String hourS, minS, secS, msecS;
	    hourS = "" + hour;
	    if(min < 10) {
	    	minS = "0" + min;
	    } else {
	    	minS = "" + min;
	    }
	    if(sec < 10) {
	    	secS = "0" + sec;
	    } else {
	    	secS = "" + sec;
	    }
	    if (msec < 10){
	    	msecS = "00" + msec;
	    } else if(msec < 100) {
	    	msecS = "0" + msec;
	    } else {
	    	msecS = "" + msec;
	    }
		return hourS + ":" + minS + ":" + secS + ":" + msecS;
	}
	
}
