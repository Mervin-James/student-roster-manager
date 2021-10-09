package tuition;

public class International extends NonResident {
    private static final int STUDY_ABROAD_MAX_CREDITS = 12;
    protected static final float FULL_TIME_TUITION_FEE = 29737;
    protected static final float RATE_PER_CREDIT_HOUR = 966;
    private boolean isStudyAbroad = false;
    private float amountDue;

    public International(String name, Major major, int credits,
                         boolean isStudyAbroad) {
        super(name, major, credits);
        isStudyAbroad = false;
    }

//    private boolean isCreditsValid(int credits) {
//        if (credits > STUDY_ABROAD_MAX_CREDITS) {
//            System.out.println("Invalid credit hours.");
//            return false;
//        }
//        return true;
//    }

    private void setStudyAbroadStatus(String name,
                                      Major major,
                                      int credits, boolean isStudyAbroad) {
        isStudyAbroad = true;
        if (credits > 12) {
            this.setCredits(12);
        }
        this.setTotalPayment(0);
        this.setLastPaymentDate(null);
        System.out.println("Tuition updated.");
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
        return super.toString() + (isStudyAbroad ? ":non-resident" +
                ":international:study abroad" : "non-resident:international");
    }

    public float getTuitionDue() {
        return this.amountDue;
    }
}
