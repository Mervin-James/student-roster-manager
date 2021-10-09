package tuition;

public class Student {
    protected static final float UNIVERSITY_FEE = 3268;
    protected static final int FULL_TIME_BASE_RATE_MAX_CREDITS = 16;
    protected static final int FULL_TIME_BASE_RATE_MIN_CREDITS = 12;
    protected static final float PART_TIME_UNIVERSITY_FEE_MULTIPLIER = 0.8f;
    private Profile profile;
    private int credits;
    private float tuition;
    private float totalPayment;
    private Date lastPaymentDate;
    private float amountDue;

    public Student(String name, Major major) {
        Profile profile = new Profile(name, major);
        this.profile = profile;
    }

    public Student(String name, Major major, int credits) {
        this.profile = new Profile(name, major);
        this.credits = credits;
        this.tuition = 0;
        this.totalPayment = 0;
        this.lastPaymentDate = null;
        this.amountDue = 0;
    }

    public Student() {
    }

    public void tuitionDue() {
    }

    @Override
    public String toString() {
        return profile + ":" + credits + " credit hours" +
                ":" + "tuition due:" + amountDue + ":" + "total " +
                "payment:" + totalPayment + ":" + "last payment date:" +
                " " + (lastPaymentDate == null ?
                "--/--/--" :
                lastPaymentDate.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            return student.profile.equals(this.profile);
        }
        return false;
    }

    public int getCredits() {
        return this.credits;
    }

    public float getTuition() {
        return tuition;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setTuition(float tuition) {
        this.tuition = tuition;
    }

    public void setTotalPayment(float totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setLastPaymentDate(Date lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public void setTuitionDue(float amountDue) {
        this.amountDue = amountDue;
    }

}
