import java.util.Scanner;

/**
 * Coded By: Era Boy
 * Version: v0.1.0
 **/

public class AppInitializer {
    // Student Count Variables
    private static final int MAX_CAPACITY = 100;
    private static final int studentCount = 0;

    // Student arrays
    public static String[] studentIds = new String[MAX_CAPACITY];
    public static String[] studentNames = new String[MAX_CAPACITY];

    public static void main(String[] args) {
        // main menu method
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
        System.out.println("Check Available Seats");
    }

    // add the student details
    private static void registerNewStudent() {
        Scanner studInput = new Scanner(System.in);

        // checking the available seats in the student
        if (MAX_CAPACITY >= studentCount) {
            System.out.println("Seats are available");
        } else {
            System.out.println("Seats are not available");
        }

        System.out.print("Enter the Student id: ");
        String id = studInput.next();

    }

    // Delete the student details
    private static void deleteStudent() {
        System.out.println("Delete Student");
    }

    // Find the Student Details
    private static void findStudent() {
        System.out.println("Find Student");
    }

    //Store the student details using file
    private static void storeStudentDetails() {
        System.out.println("Store Student Details (within the file)");
    }

    // Load the student details using file
    private static void loadStudentDetails() {
        System.out.println("Load Student Details (from the file)");
    }

    // View the list of students
    private static void viewStudentList() {
        System.out.println("View the list of students");
    }

    // Exit the system
    private static void exitTheSystem() {
        Scanner exitNum = new Scanner(System.in);
        System.out.print("Do you want to the exit the system? [Y/N] >");
        String yesNo = exitNum.next();
        switch (yesNo) {
            case "y":
            case "Y":
                clearWorkingConsole();
                System.exit(0);
                break;
            case "n":
            case "N":
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
