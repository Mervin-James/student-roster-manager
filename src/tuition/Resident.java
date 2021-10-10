package tuition;

public class Resident extends Student {
    private static final float MAX_FINANCIAL_AID = 10000;
    private static final float FULL_TIME_TUITION_FEE = 12536;
    private static final float RATE_PER_CREDIT_HOUR = 404;
    private float financialAid;
//    private float amountDue;

    public Resident(String name, Major major, int credits) {
        super(name, major, credits);
    }

    public boolean isFinancialAidValid(float financialAid) {
        if (financialAid > MAX_FINANCIAL_AID || financialAid < 0) {
            System.out.println("Invalid amount.");
            return false;
        }
        return true;
    }

    @Override
    public void tuitionDue() {
        int numCredits = super.getCredits();
        float amountDue = 0;
        if (numCredits >= FULL_TIME_BASE_RATE_MAX_CREDITS) {
            amountDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE +
                            RATE_PER_CREDIT_HOUR * (numCredits -
                                    FULL_TIME_BASE_RATE_MAX_CREDITS) -
                            financialAid;
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            amountDue = FULL_TIME_TUITION_FEE + UNIVERSITY_FEE - financialAid;
        } else {
            amountDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER *
                                    UNIVERSITY_FEE - financialAid;
        }
        super.setAmountDue(amountDue);
    }

    @Override
    public String toString() {
        return super.toString() + ":resident";
    }

    public float getFinancialAid() {
        return this.financialAid;
    }

    public void setFinancialAid(float financialAid) {
        this.financialAid = financialAid;
    }

}
