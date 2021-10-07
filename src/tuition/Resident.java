package tuition;

public class Resident extends Student {
    private static final float MAX_FINANCIAL_AID = 10000;
    private float financialAid;

    public Resident(float financialAid) {
        this.financialAid = financialAid;
    }

    private boolean isFinancialAidValid(float financialAid) {
        if (financialAid > MAX_FINANCIAL_AID || financialAid < 0) {
            System.out.println("Invalid Amount");
            return false;
        }
        return true;
    }







    @Override
    public void tuitionDue() {}

}
