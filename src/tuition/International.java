package tuition;

public class International extends NonResident {
    private static final int MAX_CREDITS = 12;
    private int credits;
    private float additionalFee;

    public International() {

    }

    private boolean isCreditsValid(int credits) {
        if (credits > MAX_CREDITS) {
            System.out.println("Invalid credit hours.");
            return false;
        }
        return true;
    }





    @Override
    public void tuitionDue() {}
}
