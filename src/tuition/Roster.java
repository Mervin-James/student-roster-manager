package tuition;

public class Roster {
    private static final int NOT_FOUND = -1;
    private Student[] roster;
    private int size; //keep track of the number of students in the roster

    public Roster(Student[] roster) {
        this.roster = roster;
        this.size = 0;
    }

    private int find(Student student) {
        for (int i = 0; i < size; i++) {
            if (roster[i].equals(student)) {
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
        if (find(student) != -1) {
            System.out.println("Student is already in the roster.");
            return false;
        }
        roster[size++] = student;
        if (size == roster.length) {
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
        for (int i = 0; i < size; i++) {
            if (i == studentIndex) {
                isSkipped = true;
            }
            if (isSkipped) {
                newRoster[i] = roster[i + 1];
                continue;
            }
            newRoster[i] = roster[i];
        }
        size--;
        roster = newRoster;
        return true;
    }

    public void calculateAllTuition() {
        for (int i = 0; i < size; i++) {
            roster[i].tuitionDue();
        }
    }

    public Student retrieveStudent(Student student) {
        int studentIndex = find(student);
        if (studentIndex == NOT_FOUND) {
            return null;
        }
        return roster[studentIndex];
    }

    public void sortByName() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (roster[j].getProfile()
                        .compareTo(roster[minIndex].getProfile()) ==
                        -1) {
                    minIndex = j;
                }
            }
            Student tempStudent = roster[minIndex];
            roster[minIndex] = roster[i];
            roster[i] = tempStudent;
        }
    }

    public void sortByPayment() {
        if (size == 0) {
            System.out.println("Student roster is empty!");
            return;
        }
        int numStudents = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getLastPaymentDate() != null) {
                numStudents++;
            }
        }
        Student[] madePayments = new Student[numStudents];
        Student[] didNotMakePayments = new Student[size - numStudents];
        int k = 0;
        int l = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i].getLastPaymentDate() != null) {
                madePayments[k++] = roster[i];
            } else {
                didNotMakePayments[l++] = roster[i];
            }
        }
        for (int i = 0; i < numStudents - 1; i++) {
            int minIndex = i;
            if (madePayments[i].getLastPaymentDate() != null) {
                for (int j = i + 1; j < numStudents; j++) {
                    if (madePayments[j].getLastPaymentDate()
                        .compareTo(madePayments[minIndex].getLastPaymentDate()) ==
                        -1) {
                    minIndex = j;
                    }
                }
                Student tempStudent = madePayments[minIndex];
                madePayments[minIndex] = madePayments[i];
                madePayments[i] = tempStudent;
            }
        }
        int j = 0;
        for (int i = 0; i < size - numStudents; i++) {
            roster[j++] = didNotMakePayments[i];
        }
        for (int i = 0; i < numStudents; i++) {
            roster[j++] = madePayments[i];
        }
        System.out.println("* list of students made payments ordered by payment date **");
        for (int i = 0; i < numStudents; i++) {
            System.out.println(madePayments[i].toString());
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "Student roster is empty!";
        }
        String rosterList = "* list of students in the roster **\n";
        for (int i = 0; i < size; i++) {
            rosterList += roster[i].toString() + "\n";
        }
        rosterList += "* end of roster **";
        return rosterList;
    }
}
