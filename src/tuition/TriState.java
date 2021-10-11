package tuition;

public class TriState extends NonResident {
    private float discount;
    private State state;

    public TriState(String name, Major major, int credits, float discount,
                    State state) {
        super(name, major, credits);
        this.discount = discount;
        this.state = state;
    }

    @Override
    public void tuitionDue() {
        int numCredits = super.getCredits();
        float amountDue = 0;
        if (numCredits >= FULL_TIME_BASE_RATE_MAX_CREDITS) {
            amountDue =
                    FULL_TIME_TUITION_FEE + UNIVERSITY_FEE +
                            RATE_PER_CREDIT_HOUR * (numCredits -
                                    FULL_TIME_BASE_RATE_MAX_CREDITS) - discount;
        } else if (numCredits >= FULL_TIME_BASE_RATE_MIN_CREDITS) {
            amountDue = FULL_TIME_TUITION_FEE + UNIVERSITY_FEE - discount;
        } else {
            amountDue =
                    RATE_PER_CREDIT_HOUR * numCredits +
                            PART_TIME_UNIVERSITY_FEE_MULTIPLIER * UNIVERSITY_FEE;
        }
        super.setAmountDue(amountDue - this.getTotalPayment());
    }

    @Override
    public String toString() {
        return super.toString() + "(tri-state):" + state;
    }

}
