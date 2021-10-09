package tuition;

import java.util.Scanner;

public class TuitionManager {
    public void run() {
        System.out.println("Tuition Manager starts running.");
        Student[] students = new Student[4];
        Roster roster = new Roster(students, 0);
        Scanner sc = new Scanner(System.in);
        String command = "";
        while (true) {
            String input = sc.nextLine();
            String[] splitInput = input.split(",");
            command = splitInput[0];
            if (command.equals("Q") && splitInput.length == 1) {
                break;
            } else if (command.equals("AR") && splitInput.length == 4) {
                addStudent(roster, splitInput);
            } else if (command.equals("AN") && splitInput.length == 3) {

            } else if (command.equals("AT") && splitInput.length == 3) {

            } else if (command.equals("AI") && splitInput.length == 3) {

            } else if (command.equals("R") && splitInput.length == 1) {
                removeStudent(roster, splitInput);
            } else if (command.equals("C") && splitInput.length == 1) {
                calculateAllTuition(roster);
            } else if (command.equals("T") && splitInput.length == 1) {
                payTuition(roster, splitInput);
            } else if (command.equals("P") && splitInput.length == 1) {
                printRoster(roster);
            } else {
                System.out.println("Command " + command + " not " +
                        "supported!");
            }
        }
        sc.close();
        System.out.println("Tuition Manager terminated.");
    }

    private void addStudent(Roster roster, String[] splitInput) {
        String command = splitInput[0];
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        int credits = Integer.parseInt(splitInput[3]);
        switch (command) {
            case "AR":
//                Resident resident = new Resident("bob", Major.BA, 14,1000f,
//                        1000f, 1000f, new Date("01/01/2021"), 500f);
                Resident resident = new Resident(name, major, credits);
                roster.add(resident);
                break;
            case "AN":
                NonResident nonResident = new NonResident("bob", Major.BA, 14,
                        1000f, 1000f, new Date("01/01/2021"), 500f);
                roster.add(nonResident);
                break;
            case "AT":
                TriState triState = new TriState();
                roster.add(triState);
                break;
            case "AI":
                International international = new International();
                roster.add(international);
                break;
        }
        System.out.println("Student added.");
    }

    private Major checkMajor(String stringMajor) {
        stringMajor = stringMajor.toUpperCase();
        if (stringMajor.equals("CS")) {
            return Major.CS;
        } else if (stringMajor.equals("IT")) {
            return Major.IT;
        } else if (stringMajor.equals("BA")) {
            return Major.BA;
        } else if (stringMajor.equals("EE")) {
            return Major.EE;
        } else if (stringMajor.equals("ME")) {
            return Major.ME;
        } else {
            return null;
        }
    }

    private void removeStudent(Roster roster, String[] splitInput) {
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        Student student = new Student(name, major);
        roster.remove(student);
    }

    private void calculateAllTuition(Roster roster) {
        float totalTuition = roster.calculateAllTuition();
        System.out.println("Calculation completed.");
    }

    private void payTuition(Roster roster, String[] splitInput) {
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        int payment = Integer.parseInt(splitInput[3]);
        Date paymentDate = new Date(splitInput[4]);
        Student student = new Student(name, major);

        //TODO: Figure out how to payTuition w/o passing in payment as
        // parameter to roster.payTuition(student);
    }

    private void printRoster(Roster roster) {
        System.out.println("* list of students in the roster **");
        System.out.println(roster.toString());
    }


}
