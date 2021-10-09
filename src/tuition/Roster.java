package tuition;

public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster
    private static final int NOT_FOUND = -1;

    public Roster(Student[] roster) {
        this.roster = roster;
        this.size = 0;
    }

    private int find(Student student) {
        for(int i = 0; i < size; i++) {
            if(roster[i].equals(student)) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {
        Student[] newRoster = new Student[size + 4];
        for (int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }

    public boolean add(Student student) {
        if(find(student) != -1) {
            return false;
        }
        roster[size++] = student;
        if(size == roster.length) {
            grow();
        }
        return true;
    }

    public boolean remove(Student student) {
        int studentIndex = find(student);
        if (studentIndex == -1) {
            return false;
        }
        Student[] newRoster = new Student[size];
        boolean isSkipped = false;  //marks when the deleted student has
        // been skipped
        for(int i = 0; i < size; i++) {
            if(i == studentIndex) {
                isSkipped = true;
            }
            if(isSkipped) {
                newRoster[i] = roster[i+1];
                continue;
            }
            newRoster[i] = roster[i];
        }
        size--;
        roster = newRoster;
        return true;
    }

    public void calculateAllTuition() {
        for(int i=0; i<size; i++) {
            roster[i].tuitionDue();
        }
    }

    public Student retrieveStudent(Student student) {
        int studentIndex = find(student);
        if(studentIndex == NOT_FOUND) {
            return null;
        }
        return roster[studentIndex];
    }

    @Override
    public String toString() {
        for(int i=0; i<size; i++) {
            return roster[i].toString();
        }
        return "";
    }
}
