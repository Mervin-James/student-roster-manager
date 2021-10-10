package tuition;

/**
 * A Class that defines an International student by name, major, and credits.
 * This class contains a getter method for retrieving the attribute
 * describing the amount of tuition that is due, as well as a setter method
 * for the student's study abroad status. The International class also has
 * methods for calculating the Student's amount of tuition due and for
 * generating a String representation of this Student's information.
 */
public class International extends NonResident {
    private static final int STUDY_ABROAD_MAX_CREDITS = 12;
    private static final float ADDITIONAL_FEE = 2650;
    private boolean isStudyAbroad;
    private float amountDue;

    /**
     * Constructs an International object by their name, major, and credits.
     *
     * @param name          the name of the student.
     * @param major         the major of study the student is pursuing.
     * @param credits       the number of credits the student is attempting.
     * @param isStudyAbroad the study abroad status of the student.
     */
    public International(String name, Major major, int credits,
                         boolean isStudyAbroad) {
        super(name, major, credits);
        isStudyAbroad = false;
    }

    /**
     * Setter method for the study abroad status of this Student.
     *
     * @param name          the name of the student.
     * @param major
     * @param credits
     * @param isStudyAbroad
     */
    public void setStudyAbroadStatus(boolean isStudyAbroad) {
        this.isStudyAbroad = isStudyAbroad;
        if (this.getCredits() > STUDY_ABROAD_MAX_CREDITS) {
            this.setCredits(STUDY_ABROAD_MAX_CREDITS);
        }
        this.setTuition(0);
        this.setTotalPayment(0);
        this.setLastPaymentDate(null);
        System.out.println("Tuition updated.");
    }

    @Override
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
