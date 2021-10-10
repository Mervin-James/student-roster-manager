package tuition;

import javax.swing.text.SimpleAttributeSet;
import java.util.Scanner;

public class TuitionManager {
    private static final int MIN_CREDITS = 3;
    private static final int MAX_CREDITS = 24;
    private static final float MAX_FINANCIAL_AID = 10000;
    private static final int MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND = 3;
    private static final int MIN_NUM_ARGUMENTS_FOR_CREDIT_HOURS = 4;
    private static final int MIN_NUM_ARGUMENTS_FOR_PAYMENT_AMOUNT = 4;
    private static final int MIN_NUM_ARGUMENTS_FOR_PAYMENT_DATE = 5;

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
            if (command.equals("Q")) {
                break;
            } else if (command.equals("AR") || command.equals("AN") ||
                    command.equals("AI")) {
                addStudent(roster, splitInput);
            } else if (command.equals("R")) {
                removeStudent(roster, splitInput);
            } else if (command.equals("C")) {
                calculateAllTuition(roster);
            } else if (command.equals("T")) {
                payTuition(roster, splitInput);
            } else if (command.equals("S")) {
                setStudyAbroad(roster, splitInput);
            } else if (command.equals("F")) {
                setStudentFinancialAid(roster,splitInput);
            } else if (command.equals("P")) {
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
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
            return;
        }
        String command = splitInput[0];
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        if (major == null) {
            System.out.println(
                    "'" + splitInput[2] + "' is not a valid major.");
            return;
        }
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_CREDIT_HOURS) {
            System.out.println("Credit hours missing.");
            return;
        }
        int credits = Integer.parseInt(splitInput[3]);
        if (credits < 0) {
            System.out.println("Credit hours cannot be negative.");
            return;
        } else if (credits < MIN_CREDITS) {
            System.out.println("Minimum credit hours is 3.");
            return;
        } else if(credits > MAX_CREDITS) {
            System.out.println("Credit hours exceed the maximum 24.");
            return;
        }
        switch (command) {
            case "AR":
                Resident resident = new Resident(name, major, credits);
                roster.add(resident);
                break;
            case "AN":
                NonResident nonResident = new NonResident(name, major,
                        credits);
                roster.add(nonResident);
                break;
            case "AT":
                State state = checkState(splitInput[4]);
                float discount = 0;
                if (state == State.NY) {
                    discount = 4000;
                } else if (state == State.CT) {
                    discount = 5000;
                }
                TriState triState = new TriState(name, major, credits,
                        discount, state);
                roster.add(triState);
                break;
            case "AI":
                International international = new International(name, major,
                        credits, false);
                roster.add(international);
                break;
        }
        System.out.println("Student added.");
    }

    private Major checkMajor(String stringMajor) {
        stringMajor = stringMajor.toUpperCase();
        return switch (stringMajor) {
            case "CS" -> Major.CS;
            case "IT" -> Major.IT;
            case "BA" -> Major.BA;
            case "EE" -> Major.EE;
            case "ME" -> Major.ME;
            default -> null;
        };
    }

    private State checkState(String stringState) {
        stringState = stringState.toUpperCase();
        return switch (stringState) {
            case "NY" -> State.NY;
            case "CT" -> State.CT;
            default -> null;
        };
    }

    private void removeStudent(Roster roster, String[] splitInput) {
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
        }
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        Student student = new Student(name, major);
        boolean isStudentRemoved = roster.remove(student);
        if(!isStudentRemoved) {
            System.out.println("Student is not in the roster.");
        } else {
            System.out.println("Student removed from the roster.");
        }
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
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
            return;
        }
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_PAYMENT_AMOUNT) {
            System.out.println("Payment amount missing.");
            return;
        }
        float payment = Float.parseFloat(splitInput[3]);
        if (payment <= 0) {
            System.out.println("Invalid amount.");
            return;
        }
        Student student = roster.retrieveStudent(new Student(name, major));
        if(student == null) {
            System.out.println("Student not in the roster.");
            return;
        }
        float amountDue = student.getAmountDue();
        float totalPayment = student.getTotalPayment();
        if (payment > amountDue) {
            System.out.println("Amount is greater than amount due.");
            return;
        }
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_PAYMENT_DATE) {
            System.out.println("Payment date missing.");
            return;
        }
        Date paymentDate = new Date(splitInput[4]);
        if (!paymentDate.isValid()) {
            System.out.println("Payment date invalid.");
            return;
        }
        student.payTuition(payment, paymentDate);
    }

    private void setStudentFinancialAid(Roster roster, String[] splitInput) {
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_VALID_COMMAND) {
            System.out.println("Missing data in command line.");
            return;
        }
        String name = splitInput[1];
        Major major = checkMajor(splitInput[2]);
        if (splitInput.length < MIN_NUM_ARGUMENTS_FOR_PAYMENT_AMOUNT) {
            System.out.println("Missing the amount.");
            return;
        }
        float aidAmount = Float.parseFloat(splitInput[3]);
        Student student = roster.retrieveStudent(new Student(name, major));
        if (student == null) {
            System.out.println("Student not in roster.");
            return;
        } else if (student.getCredits() < 12) {
            System.out.println("Parttime student doesn't qualify for the " +
                    "award.");
            return;
        } else if (!(student instanceof Resident)) {
            System.out.println("Not a resident student.");
            return;
        }
        Resident resident = (Resident) student;
        if (resident.getFinancialAid() != 0) {
            System.out.println("Awarded once already.");
            return;
        }
        if (resident.isFinancialAidValid(aidAmount)) {
            resident.setFinancialAid(aidAmount);
            System.out.println("Tuition updated.");
        }
    }

    private void printRoster(Roster roster) {
        System.out.println(roster.toString());
    }


}
