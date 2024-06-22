import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Coded By: Era Boy
 * Version: v0.1.0
 **/

public class AppInitializer {
    // Student Count Variables
    private static final int MAX_CAPACITY = 100; // const variables
    // Student arrays
    public static String[] studentIds = new String[MAX_CAPACITY];
    public static String[] studentNames = new String[MAX_CAPACITY];
    private static int studentCount = 0;

    // Runnable Method
    public static void main(String[] args) {
        mainMenuConsole();
        mainMenuInput();
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
    private static void mainMenuInput() {
        Scanner input_number = new Scanner(System.in);
        // Handling the exception
        try {
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
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please type the integer input...!");
            clearWorkingConsole();
            mainMenuConsole();
            mainMenuInput();
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
        System.out.println();
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
        int indexValues = nextIdValues(studentIds);

        //  main loop
        for (int i = indexValues; i < studentIds.length; i++) {
            System.out.print("Enter the Student Id: ");
            String sId = studInput.next();

            //  check the existing the student id
            boolean studentIdFound = false;
            for (String studentId : studentIds) {
                if (sId.equals(studentId)) {
                    System.out.println("The Student id was Already Exists. Please Try again.");
                    studentIdFound = true;
                    break;
                }
            }

            // if hadn't any exist student id found, this block executed and allow to add the students name.
            if (!studentIdFound) {
                studentIds[i] = sId;
                System.out.print("Enter the Student Name: ");
                String sName = studInput.next();
                studentNames[i] = sName;
                studentCount++;

                // Handling the exception
                try {
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
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please type the integer input...!");
                    clearWorkingConsole();
                    mainMenuConsole();
                    mainMenuInput();
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

            for (int i = 0; i < studentIds.length; i++) {
                if (studentId.equals(studentIds[i])) {
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
            String[] tempStudentId = new String[studentIds.length - 1];
            String[] tempStudentName = new String[studentNames.length - 1];

            for (int i = 0, j = 0; i < studentIds.length; i++) {
                if (i != studentIndex) {
                    tempStudentId[j] = studentIds[i];
                    tempStudentName[j] = studentNames[i];
                    j++;
                }
            }

            studentIds = tempStudentId;
            studentNames = tempStudentName;
            studentCount--;

            try {
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
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please type the integer input...!");
                clearWorkingConsole();
                mainMenuConsole();
                mainMenuInput();
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
            for (int i = 0; i < studentIds.length; i++) {
                if (stuId.equals(studentIds[i])) {
                    System.out.println("Student Name: " + studentNames[i]);
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

        try {
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
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please type the integer input...!");
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
        try {
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
        } catch (InputMismatchException ex) {
            System.out.println("Please type a valid input..!!");
            clearWorkingConsole();
            mainMenuConsole();
            mainMenuInput();
        }
    }

    // store values in the array
    public static int nextIdValues(String[] id) {
        int array = id.length;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == null) {
                array = i;
                break;
            }
        }
        return array;
    }
}
