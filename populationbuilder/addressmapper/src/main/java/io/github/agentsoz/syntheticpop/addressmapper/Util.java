package io.github.agentsoz.syntheticpop.addressmapper;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2019 by its authors. See AUTHORS file.
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

import java.util.regex.Pattern;

enum SA {
    SA1,
    SA2,
    SA3,
    SA4;
}

/**
 * @author wniroshan 01 May 2018
 */
public class Util {

    static Pattern getSACodePattern(String saType) {
        Pattern p = null;
        switch (saType) {
            case "SA1":
                p = Pattern.compile("^\\d+");
                break;
            case "SA2":
                p = Pattern.compile("^\\d{9}");
                break;
            case "SA3":
                p = Pattern.compile("^\\d{5}");
                break;
            case "SA4":
                p = Pattern.compile("^\\d{3}");
                break;
            default:
                p = null;
                break;
        }
        return p;

    }
}
