package tuition;

import java.util.Scanner;

public class TuitionManager {
    private static final int MIN_CREDITS = 3;

    public void run() {
        System.out.println("Tuition Manager starts running.");
        Student[] students = new Student[4];
        Roster roster = new Roster(students);
        Scanner sc = new Scanner(System.in);
        String command = "";
        while (true) {
            String input = sc.nextLine();
            String[] splitInput = input.split(",");
            command = splitInput[0];
            if (command.equals("Q") && splitInput.length == 1) {
                break;
            } else if (command.equals("AR") || command.equals("AN") ||
                    command.equals("AI")) {
                addStudent(roster, splitInput);
            } else if (command.equals("R") && splitInput.length == 1) {
                removeStudent(roster, splitInput);
            } else if (command.equals("C")) {
                calculateAllTuition(roster);
            } else if (command.equals("T")) {
                payTuition(roster, splitInput);
            } else if (command.equals("S")) {
                setStudyAbroad(roster, splitInput);

            } else if (command.equals("F")) {

            }
            else if (command.equals("P") && splitInput.length == 1) {
                printRoster(roster);
            } else {
                System.out.println("Command '" + command + "' not " +
                        "supported!");
            }
        }
        sc.close();
        System.out.println("Tuition Manager terminated.");
    }

    private void addStudent(Roster roster, String[] splitInput) {
        if (splitInput.length < 3) {
            System.out.println("Missing data in command line.");
        }
        String command = splitInput[0];
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        if (major == null) {
            System.out.println(
                    "'" + splitInput[2] + "' is not a valid major.");
        }
        if (splitInput.length < 4) {
            System.out.println("Credit hours missing.");
        }
        int credits = Integer.parseInt(splitInput[3]);
        if (credits < 0) {
            System.out.println("Credit hours cannot be negative.");
        } else if (credits < MIN_CREDITS) {
            System.out.println("Minimum credit hours is 3.");
        }
//        else if(credits)
        switch (command) {
            case "AR":
                Resident resident = new Resident(name, major, credits);
                roster.add(resident);
                break;
            case "AN":
                NonResident nonResident =
                        new NonResident(name, major, credits);
                roster.add(nonResident);
                break;
            case "AT":
                TriState triState =
                        new TriState(name, major, credits, 1000f, State.NY);
                roster.add(triState);
                break;
            case "AI":
                International international = new International("rob",
                        Major.ME, 15, false);
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
        roster.calculateAllTuition();
        System.out.println("Calculation completed.");
    }

    private void setStudyAbroad(Roster roster, String[] splitInput) {
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        int credits = Integer.parseInt(splitInput[3]);
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            System.out.println("Couldn't find the international student.");
        } else {
            ((International) student).setStudyAbroadStatus(name, major, credits,
                    true);
        }
    }

    private void payTuition(Roster roster, String[] splitInput) {
        if (splitInput.length < 3) {
            System.out.println("Missing data in command line.");
        }
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        Student student = roster.retrieveStudent(new Student(name, major));
        if (splitInput.length < 4) {
            System.out.println("Payment amount missing.");
        }
        int payment = Integer.parseInt(splitInput[3]);
        float amountDue = student.getAmountDue();
        float totalPayment = student.getTotalPayment();
        if (payment <= 0) {
            System.out.println("Invalid amount.");
        } else if (payment > amountDue) {
            System.out.println("Amount is greater than amount due.");
        }
        if (splitInput.length < 5) {
            System.out.println("Payment date missing.");
        }
        Date paymentDate = new Date(splitInput[4]);
        if (!paymentDate.isValid()) {
            System.out.println("Payment date invalid.");
        }
        student.payTuition(payment, paymentDate);
    }

    private void printRoster(Roster roster) {
        System.out.println("* list of students in the roster **");
        System.out.println(roster.toString());
    }


}
