package tuition;

public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    public Roster(Student[] roster, int size) {
        this.roster = roster;
        this.size = size;
    }

    private int find(Student student) {
        for(int i = 0; i < size; i++) {
            if(roster[i].equals(student)) {
                return i;
            }
        }
        return -1;
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
}
