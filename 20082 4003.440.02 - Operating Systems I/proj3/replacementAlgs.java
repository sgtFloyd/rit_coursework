/**
 * @author Gabriel Smith
 *
 */

import java.util.Scanner;

public class replacementAlgs {

	public static final String ALG_INPUT = "Enter a page replacement algorithm (fifo or lru) >> ";
	public static final String FRAME_INPUT = "Enter the number of frames available >> ";
	public static final String REF_INPUT = "Enter the reference string, each character must be 0-7 >> ";
	public static final String ALG_ERROR = "*ERROR* Algorithm must be lru or fifo >> ";
	public static final String FRAME_ERROR = "*ERROR* Number of frames must be greater than 0 and less than 21 >> ";
	public static final String REF_LEN_ERROR = "*ERROR* Length of reference string must be greater than 0 and less than 21 >> ";
	public static final String REF_CHAR_ERROR = "*ERROR* Reference string characters must be 0-7.";
	public static final String STAR_STRING = "***************************************************";
	
	public static String algorithm; // algorithm to use, either LRU or FIFO
	public static int numFrames; // number of frames to use
	public static String ref; // reference string to use
	public static int[] frame; // array used to represent memory space
	
	/**
	 * @param args	Not Used
	 */
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
		while (true) {
			if (getInput(in) != 0)
				break;
			
			System.out.println("\nAlgorithm: " + algorithm);
			System.out.println("Number of frames: " + numFrames);
			System.out.println("Reference string: " + ref + "\n");
			System.out.println("**********************");
			System.out.println("Starting simulation...");
			System.out.println("**********************");
			
			int numFaults = 0;
			if (algorithm.equals("FIFO") || algorithm.equals("fifo"))
				numFaults = runFIFO();
			if (algorithm.equals("LRU") || algorithm.equals("lru"))
				numFaults = runLRU();
			
			System.out.println("\nNumber of page faults: " + numFaults + "\n");
			System.out.println(STAR_STRING + "\n" + STAR_STRING);

		}
	}

	/**
	 * Collects all the input from the user and checks it for errors.
	 * 
	 * @param in	The scanner object to use.
	 * @return 		1 if "q" is entered to quit
	 * 				0 otherwise
	 */
	public static int getInput(Scanner in) {
        // Read in algorithm type and check for quit and errors
        boolean loop = true;
    	System.out.println(ALG_INPUT);
        while (loop) {
        	algorithm = in.nextLine();
        	if (algorithm.equals("Q") || algorithm.equals("q")) {
        		return 1; // quit
        	}
        	if (algorithm.equals("FIFO") || algorithm.equals("LRU") ||
        			algorithm.equals("fifo") || algorithm.equals("lru")) {
        		loop = false; // received acceptable input
        	} else {
        		System.out.println(ALG_ERROR);
        	}
        }
        
        // Read in number of frames and check for errors
        loop = true;
    	System.out.println(FRAME_INPUT);
        while (loop) {
        	numFrames = Integer.parseInt(in.nextLine());
        	if (numFrames < 1 || numFrames > 20) {
        		System.out.println(FRAME_ERROR); 
        	} else {
        		loop = false; // received acceptable input
        	}
        }
        //initialize the array to the correct size and fill with -1
        frame = new int[numFrames];
        for (int i=0; i<frame.length; i++){
        	frame[i] = -1;
        }
        
        // Read in reference string and check for errors
        loop = true;
    	System.out.println(REF_INPUT);
        while (loop) {
        	ref = in.nextLine();
        	if (ref.length() > 20) {
        		System.out.println(REF_LEN_ERROR);
        	} else {
        		loop = false;  // received acceptable length string
        		// check each individual character for errors
        		for (int i=0; i<ref.length(); i++) {
        			char c = ref.charAt(i);
        			if (c != '0' && c != '1' && c != '2' && c != '3' &&
        					c != '4' && c != '5' && c != '6' && c != '7') {
        				System.out.println(REF_CHAR_ERROR);
        	        	System.out.println(REF_INPUT);
        	        	// stop and loop again once you've found one bad char
        	        	i = ref.length();
        	        	loop = true;
        			}
        		}
        	}
        }
        return 0;
	}
	
	/**
	 * Runs the FIFO algorithm based on the user's input.
	 * 
	 * @return	the number of page faults
	 */
	public static int runFIFO() {
		int c, faults = 0;
		boolean inMemory;
		printFrames();
		for (int i=0; i<ref.length(); i++){
			c = ref.charAt(i) - 48;
			inMemory = false;
			for (int j=0; j<frame.length; j++){
				if(frame[j] == c)
					inMemory = true;
			}
			if (!inMemory){
				frame[faults%frame.length] = c;
				faults++;
				printFrames();
			}
		}
		return faults;
	}
	
	/**
	 * Runs the LRU algorithm based on the user's input.
	 * 
	 * @return	the number of page faults
	 */
	public static int runLRU() {
		int c, faults = 0;
		boolean inMemory;
		int[] count = new int[frame.length];
		printFrames();
		for (int i=0; i<ref.length(); i++){
			c = ref.charAt(i) - 48;
			inMemory = false;
			for (int j=0; j<frame.length; j++){
				if(frame[j] == c) {
					inMemory = true;
					count[j] = 0;
				} else {
					count[j]++;
				}
			}
			if (!inMemory){
				int victim=0;
				for (int j=0; j<count.length; j++){
					if (count[j] > count[victim]){
						victim = j;
					}
				}
				frame[victim] = c;
				count[victim] = 0;
				faults++;
				printFrames();
			}
		}
		return faults;
	}
	
	/**
	 * Prints the contents of the frame array, formatted properly.
	 */
	public static void printFrames() {
		for (int i=0; i<frame.length; i++){
			switch (frame[i]) {
				case -1: System.out.print("[E]"); break;
				default: System.out.print("[" + frame[i] + "]"); break;
			}
		}
		System.out.println("");
	}
}
