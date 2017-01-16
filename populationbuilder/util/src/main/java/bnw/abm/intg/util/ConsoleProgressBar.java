package bnw.abm.intg.util;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class ConsoleProgressBar {

	public static void updateProgress(String prefix, double progressPercentage, String suffix) {
	    final int width = 40; // progress bar width in chars
	    
	    int i = 0;
	    String delim ="",gap ="";
	    
	    for (; i <= (int)(progressPercentage*width); i++) {
	      delim +=".";
	    }

	    for (; i < width; i++) {
	      gap+=" ";
	    }
	    System.out.print("\r"+prefix+"["+delim+gap+"] "+suffix);
	  }
}