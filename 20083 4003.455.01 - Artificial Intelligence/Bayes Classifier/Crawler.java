import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * @author gabe smith (ges7506@rit.edu)
 *
 *	Takes a file as input and parses it, line by line,
 *	taking measurements of a few key attributes. It
 *	measures word length, sentence length, commas per
 *	sentence and semicolons per sentence. From the data
 *	it collects, it is able to calculate the average
 *	and standard deviation for each attribute.
 */
public class Crawler {
	private BufferedReader in;
	private LinkedList<Integer> charsPerWord; // one entry per word
	private LinkedList<Integer> wordsPerSentence; // one entry per sentence
	private LinkedList<Integer> commasPerSentence;
	private LinkedList<Integer> semicolonsPerSentence;
	private int wordCount; // words since last sentence break
	private int commaCount;
	private int semicolonCount;
	// global constants for switch statements
	public final int CHARS = 1;
	public final int WORDS = 2;
	public final int SENTENCES = 3;
	public final int COMMAS = 4;
	public final int SEMICOLONS = 5;
	
	/**
	 * @param file	the document to crawl
	 * 
	 * 	Constructor. Instantiates variables and opens file.
	 */
	public Crawler(String file) {
		wordCount = commaCount = semicolonCount = 0;
		wordsPerSentence = new LinkedList<Integer>();
		charsPerWord = new LinkedList<Integer>();
		commasPerSentence = new LinkedList<Integer>();
		semicolonsPerSentence = new LinkedList<Integer>();
		
		System.out.println("Opening: " + file);
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (Exception E) {
			System.err.println(E.getMessage());
		}
	}

	/**
	 * 	Begins the crawler, which parses,
	 * 	trims and counts all the elements.
	 */
	public void crawl() {
		try {
			String line = in.readLine();
			while(line != null) {
				line = line.replaceAll("[:()\"'`*]", "");
				line = line.replaceAll("[-]", " ");
				String[] split = line.split("[ ]");
				for (String currStr : split) {
					countCommas(currStr);
					currStr = currStr.replaceAll("[,]", "");
					countSemicolons(currStr);
					currStr = currStr.replaceAll("[;]", "");
					countWords(currStr);
					currStr = currStr.replaceAll("[.!?]", "");
					charsPerWord.add(currStr.length());
				}
				line = in.readLine();
			}
		} catch (Exception E) {
			System.err.println(E.getMessage());
		}
	}

	/**
	 * 	Counts the number of words per sentence
	 * 
	 * @param str	string to search
	 */
	private void countWords(String str) {
		wordCount++;
		if (str.startsWith("Mr.")
				|| str.startsWith("Mrs.") 
				|| str.startsWith("Ms.")
				|| str.startsWith("Dr.")
				|| str.startsWith("St.")) {
			// not a sentence break, do nothing
		} else if (str.endsWith(".")
				|| str.contains("?")
				|| str.contains("!")) {
			endSentence();
		}
	}
	/**
	 *	Marks the end of a sentence and starts a new one.
	 */
	private void endSentence() {
		wordsPerSentence.add(wordCount);
		wordCount = 0;
		commasPerSentence.add(commaCount);
		commaCount=0;
		semicolonsPerSentence.add(semicolonCount);
		semicolonCount=0;
	}
	/**
	 *	Searches a string for a comma
	 * 
	 * @param str	string to search
	 */
	private void countCommas(String str) {
		if (str.contains(","))
			commaCount++;
	}
	/**
	 * 	Searches a string for a semicolon
	 * 
	 * @param str	string to search
	 */
	private void countSemicolons(String str) {
		if (str.contains(";"))
			semicolonCount++;
	}
		
	// Getters

	/**
	 * 	Returns the total for a given attribute.
	 * 
	 * @param val	the requested attribute
	 * @return		the total for that attribute
	 */
	public int getTotal(int val) {
		int retVal=0;
		switch (val) {
			case CHARS: retVal = calcTotalChars(); break;
			case WORDS: retVal = charsPerWord.size(); break;
			case SENTENCES: retVal = wordsPerSentence.size(); break;
			case COMMAS: retVal = calcTotalCommas(); break;
			case SEMICOLONS: retVal = calcTotalSemicolons(); break;
		}
		return retVal;
	}
	/**
	 *	Returns the average value for a given attribute.
	 * 
	 * @param val	the requested attribute
	 * @return		the average for the attribute
	 */
	public double getAverage(int val) {
		double retVal=0;
		switch (val) {
			case WORDS: retVal = (double)getTotal(CHARS)/(double)getTotal(WORDS); break;
			case SENTENCES: retVal = (double)getTotal(WORDS)/(double)getTotal(SENTENCES); break;
			case COMMAS: retVal = (double)getTotal(COMMAS)/(double)getTotal(SENTENCES); break;
			case SEMICOLONS: retVal = (double)getTotal(SEMICOLONS)/(double)getTotal(SENTENCES); break;
		}
		return retVal;
	}
	/**
	 *	Returns the standard deviation for a given attribue
	 * 
	 * @param val	the requested attribute
	 * @return		the std dev for the attribute
	 */
	public double getStdDev(int val) {
		double retVal=0;
		switch (val) {
			case WORDS: retVal = stdDev(charsPerWord, getAverage(WORDS)); break;
			case SENTENCES: retVal = stdDev(wordsPerSentence, getAverage(SENTENCES)); break;
			case COMMAS: retVal = stdDev(commasPerSentence, getAverage(COMMAS)); break;
			case SEMICOLONS: retVal = stdDev(semicolonsPerSentence, getAverage(SEMICOLONS)); break;
		}
		return retVal;
	}
	
	/**
	 * 	Counts the total number of characters.
	 * 
	 * @return	the total number of characters.
	 */
	private int calcTotalChars() {
		int sum=0;
		for (int i : charsPerWord) {
			sum += i;
		}
		return sum;
	}
	/**
	 * Counts the total number of commas found.
	 * 
	 * @return	the total number of commas
	 */
	private int calcTotalCommas() {
		int sum=0;
		for (int i : commasPerSentence) {
			sum += i;
		}
		return sum;
	}
	/**
	 * 	Counts the total number of semicolons found.
	 * 
	 * @return	the total number of semicolons
	 */
	private int calcTotalSemicolons() {
		int sum=0;
		for (int i : semicolonsPerSentence) {
			sum += i;
		}
		return sum;
	}
	
	/**
	 * 	Calculates the Standard Deviation of a list of values.
	 * 
	 * @param list	set of values
	 * @param mean	average of those values
	 * @return		standard deviation of all the values in the list
	 */
	private static double stdDev(LinkedList<Integer> list, double mean){
		double sum=0;
		for(Integer num : list){
			sum += Math.pow((num-mean), 2);
		}
		return Math.sqrt(sum/list.size());
	}
	
	/**
	 * 	Closes up the crawler.
	 */
	public void close() {
		try {
			charsPerWord = null;
			wordsPerSentence = null;
			commasPerSentence = null;
			semicolonsPerSentence = null;
			in.close();
		} catch (Exception E) {
			System.err.println(E.getMessage());
		}
	}}
