
/**
 * @author gabriel smith (ges7506@rit.edu)
 * 
 * 	Uses the Crawler to collect data on any number of
 * 	files, then compares that data all the data accumulated
 * 	using the Learner.
 * 
 * 	Usage:
 * 		java Classifier file1 file2 file3...
 */
public class Classifier {
	// Data collected from the Learner.
	final static String[] name = 	{"Jane Austen", "Charles Dickens", "Mark Twain"};
	final static double[] wordAvg = {4.162747875959744, 4.017217311099657, 3.849720644938886};
	final static double[] wordDev = {2.5260060368736723, 2.376525144017592, 2.3597498812118336};
	final static double[] sentAvg = {22.574949432070646, 19.1618794423442, 20.51765838379448};
	final static double[] sentDev = {19.175252859586077, 17.537102965261674, 17.97646947111961};
	final static double[] commAvg = {1.5800425738517483, 1.765414895968856, 1.279229106105216};
	final static double[] commDev = {1.9677957464784877, 2.043013682771205, 1.6994317868629765};
	final static double[] semiAvg = {0.2765547331233484, 0.16788286346324324, 0.2141831255371435};
	final static double[] semiDev = {0.5740523257256137, 0.4956551212988431, 0.5536890191187963};
	static double doc_wordAvg, doc_wordDev, doc_sentAvg, doc_sentDev,
					doc_commAvg, doc_commDev, doc_semiAvg, doc_semiDev;
		
	/**
	 * Instantiates all variables and begins classification
	 * 
	 * @param args	array of files to Classify
	 */
	public static void main(String args[]) {
		for (String file : args) {
			Crawler crawler = new Crawler(file);
			crawler.crawl();
			doc_wordAvg = crawler.getAverage(crawler.WORDS);
			doc_wordDev = crawler.getStdDev(crawler.WORDS);
			doc_sentAvg = crawler.getAverage(crawler.SENTENCES);
			doc_sentDev = crawler.getStdDev(crawler.SENTENCES);
			doc_commAvg = crawler.getAverage(crawler.COMMAS);
			doc_commDev = crawler.getStdDev(crawler.COMMAS);
			doc_semiAvg = crawler.getAverage(crawler.SEMICOLONS);
			doc_semiDev = crawler.getStdDev(crawler.SEMICOLONS);
			System.out.println("Estimated author: " + classify());
			crawler.close();
		}
	}
	
	/**
	 *	Calculates the max "score" towards each author, which
	 *	totals the percent errors from each attribute.
	 * 
	 * @return	name of the author best fitting the document
	 */
	private static String classify(){
		double[] score = new double[name.length];
		for (int i=0; i<score.length; i++){
			score[i] += percentError(doc_wordAvg, wordAvg[i]);
			score[i] += percentError(doc_wordDev, wordDev[i]);
			score[i] += percentError(doc_sentAvg, sentAvg[i]);
			score[i] += percentError(doc_sentDev, sentDev[i]);
			score[i] += percentError(doc_commAvg, commAvg[i]);
			score[i] += percentError(doc_commDev, commDev[i]);
			score[i] += percentError(doc_semiAvg, semiAvg[i]);
			score[i] += percentError(doc_semiDev, semiDev[i]);
		}
		int maxIndex = 0;
		for (int i=0; i<score.length; i++) {
			if (score[i] > score[maxIndex]) {
				maxIndex=i;
			}
		}
		return name[maxIndex];
	}
	
	/**
	 *	Calculates the percent error from known
	 *	value and test value.
	 * 
	 * @param estimate	test value
	 * @param actual	known value
	 * @return		percent error between values
	 */
	private static double percentError(double estimate, double actual) {
		return 1-Math.abs((estimate-actual)/actual);
	}
}