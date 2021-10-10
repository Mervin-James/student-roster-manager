package tuition;

/**
 * A Class that defines an International students by name, major, and credits.
 *
 */
public class International extends NonResident {
    private static final int STUDY_ABROAD_MAX_CREDITS = 12;
    private static final float ADDITIONAL_FEE = 2650;
    private boolean isStudyAbroad = false;
    private float amountDue;

    public International(String name, Major major, int credits,
                         boolean isStudyAbroad) {
        super(name, major, credits);
        isStudyAbroad = false;
    }

    public void setStudyAbroadStatus(String name, Major major, int credits,
                                      boolean isStudyAbroad) {
        isStudyAbroad = true;
        if (credits > STUDY_ABROAD_MAX_CREDITS) {
            this.setCredits(STUDY_ABROAD_MAX_CREDITS);
        }
        this.setTuition(0);
        this.setTotalPayment(0);
        this.setLastPaymentDate(null);
        System.out.println("Tuition updated.");
    }

    public void tuitionDue() {
        int numCredits = super.getCredits();
        if (isStudyAbroad) {
            amountDue = UNIVERSITY_FEE + ADDITIONAL_FEE;
            return;
        }
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
