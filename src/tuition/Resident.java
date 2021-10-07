package tuition;

public class Resident extends Student {
    private static final float MAX_FINANCIAL_AID = 10000;
    private float financialAid;
    private float tuition;

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
    public void tuitionDue() {}

}
