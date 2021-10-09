package tuition;

public class Resident extends Student {
    private static final float MAX_FINANCIAL_AID = 10000;
    private static final float FULL_TIME_TUITION_FEE = 12536;
    private static final float RATE_PER_CREDIT_HOUR = 404;
    private static final int FULL_TIME_BASE_RATE_MAX_CREDITS = 16;
    private static final int FULL_TIME_BASE_RATE_MIN_CREDITS = 12;
    private static final float PART_TIME_UNIVERSITY_FEE_MULTIPLIER = 0.8f;
    private float financialAid;
    private  float tuition;
    private  float totalPayment;
    private  Date lastPaymentDate;
    private float tuitionDue;

    public Resident() {

    }

    private boolean isFinancialAidValid(float financialAid) {
        if (financialAid > MAX_FINANCIAL_AID || financialAid < 0) {
            System.out.println("Invalid amount.");
            return false;
        }
        return true;
    }


    @Override
    public void tuitionDue() {
        int numCredits = super.getCredits();
        if (numCredits >= FULL_TIME_BASE_RATE_MAX_CREDITS) {
            tuitionDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE +
                            RATE_PER_CREDIT_HOUR * (numCredits -
                                    FULL_TIME_BASE_RATE_MAX_CREDITS);
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            tuitionDue = FULL_TIME_TUITION_FEE + UNIVERSITY_FEE;
        } else {
            tuitionDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER *
                                    UNIVERSITY_FEE;
        }
    }

}
