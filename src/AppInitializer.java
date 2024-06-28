import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Coded By: Era Boy
 * Version: v0.1.0
 **/

public class AppInitializer {
    // Student Count Variables
    private static final int MAX_CAPACITY = 100;
    // Student 2D array
    public static String[][] students = new String[MAX_CAPACITY][2];

    // counting variable
    private static int studentCount = 0;

    // Runnable Method
    public static void main(String[] args) {
        mainMenuConsole();

        /*Handling the exception*/
        try {
            mainMenuInput();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please type the integer input...!");
            clearWorkingConsole();
            mainMenuConsole();
            mainMenuInput();
        }
    }

    // main menu
    private static void mainMenuConsole() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\tWELCOME TO STUDENT ACTIVITY MANAGEMENT SYSTEM");
        System.out.println("\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        System.out.print("[1]: Check Available Seats\t\t\t\t\t\t\t\t");
        System.out.println("[2]: Register Student");
        System.out.print("[3]: Delete Student\t\t\t\t\t\t\t\t\t\t");
        System.out.println("[4]: Find Student");
        System.out.print("[5]: Store Student Details (within the file)\t\t\t");
        System.out.println("[6]: Load Student Details (from the file)");
        System.out.print("[7]: View the list of students\t\t\t\t\t\t\t");
        System.out.println("[8]: Exit");
        System.out.println();
    }

    // clearing console
    private static void clearWorkingConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // main menu input method
    private static void mainMenuInput() throws InputMismatchException {
        Scanner input_number = new Scanner(System.in);
        // Handling the exception
        System.out.print("Enter the option to continue > ");
        int inputted_num = input_number.nextInt();
        switch (inputted_num) {
            case 1:
                checkAvailableSeats();
                break;
            case 2:
                registerNewStudent();
                break;
            case 3:
                deleteStudent();
                break;
            case 4:
                findStudent();
                break;
            case 5:
                storeStudentDetails();
                break;
            case 6:
                loadStudentDetails();
                break;
            case 7:
                viewStudentList();
                break;
            case 8:
                exitTheSystem();
                break;

            default:
                System.out.println("Invalid Input!! Please Try again!!");
                clearWorkingConsole();
                mainMenuConsole();
                mainMenuInput();
                break;
        }
    }

    // check the available seats in the array
    private static void checkAvailableSeats() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tCHECKING AVAILABLE SEATS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        int available_seats = MAX_CAPACITY - studentCount;
        System.out.print("Available Seats: " + available_seats);
        System.out.println("\n");
        clearWorkingConsole();
        mainMenuConsole();
        mainMenuInput();
    }

    // add the student details
    private static void registerNewStudent() {
        // checking the available seats in the student
        if (MAX_CAPACITY >= studentCount) {
            System.out.println("Seats are available");
        } else {
            System.out.println("Seats are not available. Please try to the removing the one or more students.");
        }

        Scanner studInput = new Scanner(System.in);
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tREGISTER NEW STUDENT");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // store values in the array
        int indexValues = nextIdValues(students);

        //  main loop
        for (int i = indexValues; i < students.length; i++) {
            System.out.print("Enter the Student Id: ");
            String sId = studInput.next();

            //  check the existing the student id
            boolean studentIdFound = false;
            for (String[] student : students) {
                if (sId.equals(student[0])) {
                    System.out.println("The Student id was Already Exists. Please Try again.");
                    studentIdFound = true;
                    break;
                }
            }

            // if hadn't any exist student id found, this block executed and allow to add the students name.
            if (!studentIdFound) {
                students[i][0] = sId;
                System.out.print("Enter the Student Name: ");
                String sName = studInput.next();
                students[i][1] = sName;
                studentCount++;

                // Handling the exception
                System.out.println();
                System.out.print("Added Successfully! Do you want to add another student? [Y/N]: ");
                char yesNo = studInput.next().charAt(0);
                switch (yesNo) {
                    case 'y':
                    case 'Y':
                        clearWorkingConsole();
                        registerNewStudent();
                        break;
                    case 'n':
                    case 'N':
                        clearWorkingConsole();
                        mainMenuConsole();
                        mainMenuInput();
                        break;
                    default:
                        System.out.println("Invalid Value.. Try Again..!");
                        clearWorkingConsole();
                        mainMenuConsole();
                        mainMenuInput();
                        break;
                }
            }
        }
    }

    // Delete the student details
    private static void deleteStudent() {
        Scanner deleteStudent = new Scanner(System.in);
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\t\tDELETE STUDENT");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        while (true) {
            System.out.print("Enter the Student Id: ");
            String studentId = deleteStudent.next();

            boolean studentIdExists = false;
            int studentIndex = -1;

            for (int i = 0; i < students.length; i++) {
                if (studentId.equals(students[i][0])) {
                    studentIdExists = true;
                    studentIndex = i;
                    break;
                }
            }

            if (!studentIdExists) {
                System.out.println("Student Id Not Exists. Try again.");
                continue;
            }

            // Deleting the student
            String[][] tempStudents = new String[students.length - 1][2];

            for (int i = 0, j = 0; i < students.length; i++) {
                if (i != studentIndex) {
                    tempStudents[j][0] = students[i][0];
                    tempStudents[j][1] = students[i][1];
                    j++;
                }
            }

            students = tempStudents;
            studentCount--;

            System.out.print("Deleted Successfully. Do you want to delete another student? (Y/N) : ");
            char ch = deleteStudent.next().charAt(0);
            switch (ch) {
                case 'y':
                case 'Y':
                    clearWorkingConsole();
                    deleteStudent();
                    return;
                case 'n':
                case 'N':
                    clearWorkingConsole();
                    mainMenuConsole();
                    mainMenuInput();
                    return;
                default:
                    System.out.println("Invalid value...Please try again!!!");
                    clearWorkingConsole();
                    mainMenuConsole();
                    mainMenuInput();
                    return;
            }
        }
    }

    // Find the Student Details
    private static void findStudent() {
        Scanner findStudent = new Scanner(System.in);
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\t\tFIND STUDENT");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        boolean validStudentId = false;

        // check the valid student id
        while (!validStudentId) {
            System.out.print("Enter the Student Id: ");
            String stuId = findStudent.next();
            boolean studentFound = false;

            // inner loop - showing data in the student id
            for (int i = 0; i < students.length; i++) {
                if (stuId.equals(students[i][0])) {
                    System.out.println("Student Name: " + students[i][1]);
                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                System.out.println("Student is not found. Please Try Again.");
            } else {
                validStudentId = true;
            }
        }

        System.out.print("Searched Successfully. Do you want to search another student? (Y/N) : ");
        char ch = findStudent.next().charAt(0);
        switch (ch) {
            case 'y':
            case 'Y':
                clearWorkingConsole();
                findStudent();
                return;
            case 'n':
            case 'N':
                clearWorkingConsole();
                mainMenuConsole();
                mainMenuInput();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearWorkingConsole();
                mainMenuConsole();
                mainMenuInput();
        }
    }

    //Store the student details using file
    private static void storeStudentDetails() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tSTORE STUDENT DETAILS");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        //writing the data in the file
        try (PrintWriter studentWriter = new PrintWriter(new FileWriter("student.txt"))) {
            for (int i = 0; i < studentCount; i++) {
                studentWriter.println(students[i][0] + " - " + students[i][1]);
            }
            System.out.println("All Student Details are saved successfully.");
        } catch (IOException e) {
            System.out.println("I got this error: " + e.getMessage() + "please fix it.");
        }
        clearWorkingConsole();
        mainMenuConsole();
        mainMenuInput();
    }

    // Load the student details using file
    private static void loadStudentDetails() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tLOAD STUDENT DETAILS");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
    }

    // View the list of students
    private static void viewStudentList() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tVIEW STUDENT LIST");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
    }

    // Exit the system
    private static void exitTheSystem() {
        Scanner exitNum = new Scanner(System.in);
        System.out.print("Do you want to the exit the system? [Y/N] >");
        char yesNo = exitNum.next().charAt(0);
        switch (yesNo) {
            case 'y':
            case 'Y':
                clearWorkingConsole();
                System.exit(0);
                break;
            case 'n':
            case 'N':
                clearWorkingConsole();
                mainMenuConsole();
                mainMenuInput();
                break;
            default:
                System.out.println("Invalid Value.. Try Again..!");
                clearWorkingConsole();
                mainMenuConsole();
                mainMenuInput();
                break;
        }
    }

    // store values in the array
    public static int nextIdValues(String[][] students) {
        int array = students.length;
        for (int i = 0; i < students.length; i++) {
            if (students[i][0] == null) {
                array = i;
                break;
            }
        }
        return array;
    }
}