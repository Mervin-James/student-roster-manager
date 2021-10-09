package tuition;

public class Student {
    private Profile profile;
    private float tuition;
    private int credits;

    public Student(String name, Major major) {
        Profile profile = new Profile(name, major);
        this.profile = profile;
    }

    public void tuitionDue() {}

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student) {
            Student student = (Student) obj;
            return student.profile.equals(this.profile);
        }
        return false;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public float getTuition() {
        return this.tuition;
    }

    public int getCredits() {
        return this.credits;
    }
}
