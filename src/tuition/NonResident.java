package tuition;

public class NonResident extends Student {
    protected static final float FULL_TIME_TUITION_FEE = 29737;
    protected static final float RATE_PER_CREDIT_HOUR = 966;
    private float amountDue;

    public NonResident(String name, Major major, int credits) {
        super(name, major, credits);
    }

    public void tuitionDue() {
        int numCredits = super.getCredits();
        if (numCredits >= FULL_TIME_BASE_RATE_MAX_CREDITS) {
            amountDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE +
                            RATE_PER_CREDIT_HOUR * (numCredits -
                                    FULL_TIME_BASE_RATE_MAX_CREDITS);
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            amountDue = FULL_TIME_TUITION_FEE + UNIVERSITY_FEE;
        } else {
            amountDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER *
                                    UNIVERSITY_FEE;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ":non-resident";
    }

    public float getTuitionDue() {
        return this.amountDue;
    }



}
