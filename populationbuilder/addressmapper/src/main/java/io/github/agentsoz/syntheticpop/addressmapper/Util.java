package io.github.agentsoz.syntheticpop.addressmapper;

import java.util.regex.Pattern;

enum SA {
    SA1,
    Sa2,
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
