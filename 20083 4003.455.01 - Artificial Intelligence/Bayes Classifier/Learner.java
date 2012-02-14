import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author gabriel smith (ges7506@rit.edu)
 *
 *	Uses Crawler to build a collection of data for any number
 *	of input files with any number of authors. The data attributes
 *	used are word length, sentence length, commas per sentence,
 *	and semicolons per sentence (average and standard deviation
 *	for each). Outputs a block of code representing the data
 *	collected to be copied into and used by the Classifier.
 *
 *	Usage:
 *		java Learner file1 file2 file3...
 */
public class Learner {
	// unique list of author's names
	private static LinkedList<String> authorNames;
	// number of files input for each author
	private static LinkedList<Integer> filesPerAuthor;
	// authors belonging to each individual file
	private static String[] fileAuthors;
	// classifications
	private static double[] wordAvg, wordDev, sentAvg, sentDev,
							commAvg, commDev, semiAvg, semiDev;
	
	/**
	 * @param args	array of files to read
	 * 
	 * 	Assigns an author to each file based on user input
	 * 	and counts the number of files per author. Then calls
	 * 	the crawler to collect the data for each file.
	 */
	public static void main(String[] args) {
		authorNames = new LinkedList<String>();
		filesPerAuthor = new LinkedList<Integer>();
		fileAuthors = new String[args.length];
		Scanner in = new Scanner(System.in);
		for (int i=0; i<args.length; i++) {
			System.out.print("Enter author for " + args[i] + ": ");
			fileAuthors[i] = in.nextLine();
			if (!authorNames.contains(fileAuthors[i])){
				authorNames.add(fileAuthors[i]);
				filesPerAuthor.add(1);
			} else {
				int indexOfAuthor = authorNames.indexOf(fileAuthors[i]);
				filesPerAuthor.set(indexOfAuthor, filesPerAuthor.get(indexOfAuthor)+1);
			}
		}
		int arraySize = authorNames.size();
		wordAvg = new double[arraySize];
		wordDev = new double[arraySize];
		sentAvg = new double[arraySize];
		sentDev = new double[arraySize];
		commAvg = new double[arraySize];
		commDev = new double[arraySize];
		semiAvg = new double[arraySize];
		semiDev = new double[arraySize];
		for (int i=0; i<args.length; i++) {
			Crawler crawler = new Crawler(args[i]);
			crawler.crawl();
			storeData(crawler, fileAuthors[i]);
			crawler.close();
		}
		printData();
	}
	
	/**
	 * @param crawler	the Crawler which collected the data
	 * @param author	the author to assign the data to
	 * 
	 * 	Stores the sum of the total values for each attribute.
	 * 	After the crawler finishes, the average value for each
	 * 	attribute is calculated.
	 */
	private static void storeData(Crawler crawler, String author) {
		int index = authorNames.indexOf(author);
		wordAvg[index] += crawler.getAverage(crawler.WORDS);
		wordDev[index] += crawler.getStdDev(crawler.WORDS);
		sentAvg[index] += crawler.getAverage(crawler.SENTENCES);
		sentDev[index] += crawler.getStdDev(crawler.SENTENCES);
		commAvg[index] += crawler.getAverage(crawler.COMMAS);
		commDev[index] += crawler.getStdDev(crawler.COMMAS);
		semiAvg[index] += crawler.getAverage(crawler.SEMICOLONS);
		semiDev[index] += crawler.getStdDev(crawler.SEMICOLONS);
	}
	/**
	 * 	After the data is collected, take the average value for
	 *  each one of the attributes and outputs it in a format
	 *  that can easily be copied and pasted into the Classifier.
	 */
	private static void printData() {
		int numAuthors = authorNames.size();
		System.out.print("final static String[] name = {\"");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(authorNames.get(i) + "\", \"");
			else
				System.out.println(authorNames.get(i) + "\"};");
		}
		System.out.print("final static double[] wordAvg = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(wordAvg[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(wordAvg[i]/filesPerAuthor.get(i) + "};");
		}
		System.out.print("final static double[] wordDev = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(wordDev[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(wordDev[i]/filesPerAuthor.get(i) + "};");
		}
		System.out.print("final static double[] sentAvg = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(sentAvg[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(sentAvg[i]/filesPerAuthor.get(i) + "};");
		}
		System.out.print("final static double[] sentDev = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(sentDev[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(sentDev[i]/filesPerAuthor.get(i) + "};");
		}
		System.out.print("final static double[] commAvg = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(commAvg[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(commAvg[i]/filesPerAuthor.get(i) + "};");
		}
		System.out.print("final static double[] commDev = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(commDev[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(commDev[i]/filesPerAuthor.get(i) + "};");
		}
		System.out.print("final static double[] semiAvg = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(semiAvg[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(semiAvg[i]/filesPerAuthor.get(i) + "};");
		}
		System.out.print("final static double[] semiDev = {");
		for (int i=0; i<numAuthors; i++) {
			if (i != numAuthors-1)
				System.out.print(semiDev[i]/filesPerAuthor.get(i) + ", ");
			else
				System.out.println(semiDev[i]/filesPerAuthor.get(i) + "};");
		}
	}
}