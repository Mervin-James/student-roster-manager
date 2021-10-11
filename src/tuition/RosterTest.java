package tuition;

import org.junit.Test;

import static org.junit.Assert.*;

public class RosterTest {

    @Test
    public void add() {
        Student[] sList = new Student[4];
        Roster roster = new Roster(sList);

        //test case #1: adding student not in the roster should be valid.
        Student student = new Student("Robert Johnson", Major.CS);
        assertTrue(roster.add(student));

        //test case #2: adding student already in the roster should be
        // invalid.
        assertFalse(roster.add(student));

        //test case #3: adding Resident student not in the roster should be
        // valid.
        Student rStudent = new Resident("Jessica Smith", Major.CS, 15);
        assertTrue(roster.add(rStudent));

        //test case #4: adding Resident student already in the roster should
        // be invalid.
        assertFalse(roster.add(rStudent));

        //test case #5: adding Nonresident student not in the roster should
        // be valid.
        Student nrStudent = new NonResident("Kevin Ford", Major.CS, 15);
        assertTrue(roster.add(nrStudent));

        //test case #6: adding Nonresident student already in the roster
        // should be invalid.
        assertFalse(roster.add(nrStudent));

        //test case #7: adding Tristate student not in the roster should
        // be valid.
        Student tStudent = new TriState("Nate Reyes", Major.CS, 15, 4000,
                State.NY);
        assertTrue(roster.add(tStudent));

        //test case #8: adding Tristate student already in the roster should
        // be invalid.
        assertFalse(roster.add(tStudent));

        //test case #9: adding International student not in the roster should
        // be valid.
        Student iStudent = new International("Alex Jones", Major.CS, 15,
                false);
        assertTrue(roster.add(iStudent));

        //test case #10: adding International student already in the roster
        // should be invalid.
        assertFalse(roster.add(iStudent));
    }

    @Test
    public void remove() {
        Student[] sList = new Student[4];
        Roster roster = new Roster(sList);

        //test case #1: removing student in the roster should be valid.
        Student student = new Student("Robert Johnson", Major.CS);
        assertTrue(roster.add(student));
        assertTrue(roster.remove(student));

        //test case #2: removing student not in the roster should be invalid.
        assertFalse(roster.remove(student));

        //test case #3: removing Resident student in the roster should be
        // valid.
        Student rStudent = new Resident("Jessica Smith", Major.CS, 15);
        assertTrue(roster.add(rStudent));
        assertTrue(roster.remove(rStudent));

        //test case #4: removing Resident student not in the roster should be
        // invalid.
        assertFalse(roster.remove(rStudent));

        //test case #5: removing Nonresident student in the roster should be
        // valid.
        Student nrStudent = new NonResident("Kevin Ford", Major.CS, 15);
        assertTrue(roster.add(nrStudent));
        assertTrue(roster.remove(nrStudent));

        //test case #6: removing Nonresident student not in the roster should
        // be invalid.
        assertFalse(roster.remove(nrStudent));

        //test case #7: removing Tristate student in the roster should be
        // valid.
        Student tStudent = new TriState("Nate Reyes", Major.CS, 15, 4000,
                State.NY);
        assertTrue(roster.add(tStudent));
        assertTrue(roster.remove(tStudent));

        //test case #8: removing Tristate student not in the roster should be
        // invalid.
        assertFalse(roster.remove(tStudent));

        //test case #9: removing International student in the roster should
        // be valid.
        Student iStudent = new International("Alex Jones", Major.CS, 15,
                false);
        assertTrue(roster.add(iStudent));
        assertTrue(roster.remove(iStudent));

        //test case #10: removing International student not in the roster
        // should be invalid.
        assertFalse(roster.remove(iStudent));
    }
}