package bnw.abm.intg.synthesis;

import java.util.NoSuchElementException;

/**
 * @author wniroshan
 * @date 11 Jan 2018
 */
class NotEnoughPersonsException extends IndexOutOfBoundsException {
    public NotEnoughPersonsException(String msg) {
        super(msg);
    }
}

class NoSuitablePersonException extends NoSuchElementException {
    public NoSuitablePersonException(String msg) {
        super(msg);
    }
}

class NoSuitableHouseholdException extends NoSuchElementException {
    public NoSuitableHouseholdException(String msg) {
        super(msg);
    }
}
