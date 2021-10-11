package tuition;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    @Test
    public void add() {
        Student[] sList = new Student[4];
        Roster roster = new Roster(sList);

        Student student = new Student("Robert Johnson", Major.CS);
        Student rStudent = new Resident("Jessica Smith", Major.CS, 15);
        Student nrStudent = new NonResident("Kevin Ford", Major.CS, 15);
        Student tStudent = new TriState("Nate Reyes", Major.CS, 15);
        Student iStudent = new International("Nate Reyes", Major.CS, 15);

        roster.add(student1);

    }

    @Test
    public void remove() {

    }
}