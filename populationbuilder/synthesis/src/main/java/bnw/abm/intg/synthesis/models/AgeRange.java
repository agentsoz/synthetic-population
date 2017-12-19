package bnw.abm.intg.synthesis.models;

/**
 * @author wniroshan
 * @date 18 Dec 2017
 */
public enum AgeRange {
    A0_14(0, 14), A15_24(15, 24), A25_39(25, 39), A40_54(40, 54), A55_69(55, 69), A70_84(70, 84), A85_99(85,
                                                                                                         99), A100_110(
            100,
            110);
    private int min, max;
    private boolean isEmpty = true;

    AgeRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Were any persons observed in census data in this age?
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Mark that there are persons in this age range in the census data
     *
     * @param status Whether there are persons in this age range
     */
    public void markNotEmpty(boolean status) {
        this.isEmpty = !status;
    }

    public int min() {
        return this.min;
    }

    public int max() {
        return this.max;
    }
}