package tuition;

public class Student {
    protected static final float UNIVERSITY_FEE = 3268;
    private final Profile profile;
    private int credits;
//    private final float tuition;
//    private final float totalPayment;
//    private final Date lastPaymentDate;
//    private float tuitionDue;

    public Student(String name, Major major) {
        Profile profile = new Profile(name, major);
        this.profile = profile;
//        this.tuition = 0;
//        this.totalPayment = 0;
        //TODO: See if this is valid
//        this.lastPaymentDate = new Date("--/--/--");    //empty date
//        this.tuitionDue = 0;
    }

    public void tuitionDue() {
    }

    @Override
    public String toString() {
        return profile.toString() + ":" + credits + " credit hours" +
                ":" + "tuition due:" + tuitionDue + ":" + "total " +
                "payment:" +totalPayment + ":" + "last payment date: " +
                lastPaymentDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            return student.profile.equals(this.profile);
        }
        return false;
    }

//    public Profile getProfile() {
//        return this.profile;
//    }
    public int getCredits() {
//        return this.credits;
    }

    public float getTuition() {
        return tuition;
    }

//    public float getTotalPayment() {
//        return this.totalPayment;
//    }
//
//    public Date getLastPaymentDate() {
//        return this.lastPaymentDate;
//    }

    public boolean calculateTuitionDue() {
        tuitionDue = tuition - totalPayment;
        return true;
    }

}
