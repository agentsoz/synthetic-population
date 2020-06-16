package io.github.agentsoz.syntheticpop.util;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2020 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

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
