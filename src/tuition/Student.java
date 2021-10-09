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
    private float tuitionDue;

    public Student(String name, Major major) {
        Profile profile = new Profile(name, major);
        this.profile = profile;
    }

    public Student(String name, Major major, int credits, float tuition,
                   float totalPayment,
                   Date lastPaymentDate, float tuitionDue) {
        this.profile = new Profile(name, major);
        this.credits = credits;
        this.tuition = tuition;
        this.totalPayment = totalPayment;
        this.lastPaymentDate = lastPaymentDate;
        this.tuitionDue = tuitionDue;
    }

    public Student() {
    }

    public void tuitionDue() {
    }

    @Override
    public String toString() {
        return profile + ":" + credits + " credit hours" +
                ":" + "tuition due:" + tuitionDue + ":" + "total " +
                "payment:" + totalPayment + ":" + "last payment date:" +
                " " + lastPaymentDate;
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

}
