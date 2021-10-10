package tuition;

public class Profile {
    private String name;
    private Major major;

    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            return (profile.name.equals(this.name) &&
                    profile.major == this.major);
        }
        return false;
    }

    public int compareTo(Profile profile) {
        if (this.name.equals(profile.name)) {
            return 0;
        } else if (this.name.compareTo(profile.name) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return name + ":" + major;
    }
}
