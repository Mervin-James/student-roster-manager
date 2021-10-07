package tuition;

import java.util.Locale;
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
                Resident resident = new Resident();
                roster.add(resident);
                break;
            case "AN":
                NonResident nonResident = new NonResident();
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
        } else if (stringMajor.equals("EE"))  {
            return Major.EE;
        } else if (stringMajor.equals("ME")) {
            return Major.ME;
        } else {
            return null;
        }
    }


}
