package task1;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Coded By: Era Boy
 * Version: v0.1.0
 **/

public class Task1 {
    // Student Count Variables
    private static final int MAX_CAPACITY = 100;
    // 2D array to store student details
    public static String[][] studentData = new String[MAX_CAPACITY][2];

    // Counter for number of students
    private static int currentStudentCount = 0;

    // Main executable method
    public static void main(String[] args) {
        /* Handle potential input exceptions */
        try {
            displayMainMenu();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            clearWorkingConsole();
            displayMainMenu();
        }
    }

    // main menu
    public static void displayMainMenu() throws InputMismatchException {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\tWELCOME TO STUDENT ACTIVITY MANAGEMENT SYSTEM");
        System.out.println("\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Menu Items
        System.out.print("[1]: Check Available Seats\t\t\t\t\t\t\t\t");
        System.out.println("[2]: Register Student");
        System.out.print("[3]: Delete Student\t\t\t\t\t\t\t\t\t\t");
        System.out.println("[4]: Find Student");
        System.out.print("[5]: Store Student Details (within the file)\t\t\t");
        System.out.println("[6]: Load Student Details (from the file)");
        System.out.print("[7]: View the list of students\t\t\t\t\t\t\t");
        System.out.println("[8]: Exit");
        System.out.println();

        // User Input
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
                viewStudentListByName();
                break;
            case 8:
                exitTheSystem();
                break;

            default:
                System.out.println("Invalid Input!! Please Try again!!");
                clearWorkingConsole();
                displayMainMenu();
                break;
        }
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

    // check the available seats in the array
    private static void checkAvailableSeats() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tCHECKING AVAILABLE SEATS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Calculate available seats
        int availableSeats = MAX_CAPACITY - currentStudentCount;
        System.out.print("Seats Available: " + availableSeats);
        System.out.println("\n");
        clearWorkingConsole();
        displayMainMenu();
    }

    // add the student details
    private static void registerNewStudent() {
        // checking the available seats in the student
        if (MAX_CAPACITY >= currentStudentCount) {
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

        // Find the next available index
        int nextIndex = nextIdValues(studentData);

        // Main loop
        for (int i = nextIndex; i < studentData.length; i++) {
            System.out.print("Enter Student Id: ");
            String studentId = studInput.next();

            // Check for existing student id
            boolean idExists = false;
            for (String[] student : studentData) {
                if (studentId.equals(student[0])) {
                    System.out.println("Student ID already exists. Please try again.");
                    idExists = true;
                    break;
                }
            }

            // If id does not exist, add student
            if (!idExists) {
                studentData[i][0] = studentId;
                System.out.print("Enter Student Name: ");
                String studentName = studInput.next();
                studentData[i][1] = studentName;
                currentStudentCount++;

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
                        displayMainMenu();
                        break;
                    default:
                        System.out.println("Invalid Value.. Try Again..!");
                        clearWorkingConsole();
                        displayMainMenu();
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
            // Enter the Student Id
            System.out.print("Enter Student Id: ");
            String studentId = deleteStudent.next();

            boolean idFound = false;
            int studentIndex = -1;

            // Search for student by ID
            for (int i = 0; i < studentData.length; i++) {
                if (studentId.equals(studentData[i][0])) {
                    idFound = true;
                    studentIndex = i;
                    break;
                }
            }

            // If student ID not found
            if (!idFound) {
                System.out.println("Student ID not found. Try again.");
                continue;
            }

            // Remove student from array
            String[][] tempData = new String[studentData.length - 1][2];
            for (int i = 0, j = 0; i < studentData.length; i++) {
                if (i != studentIndex) {
                    tempData[j][0] = studentData[i][0];
                    tempData[j][1] = studentData[i][1];
                    j++;
                }
            }

            // Update student data array and count
            studentData = tempData;
            currentStudentCount--;

            // Asking the user if they want to delete another student
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
                    displayMainMenu();
                    return;
                default:
                    System.out.println("Invalid value...Please try again!!!");
                    clearWorkingConsole();
                    displayMainMenu();
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

        boolean validId = false;

        // Loop to search student by ID
        while (!validId) {
            System.out.print("Enter Student Id: ");
            String studentId = findStudent.next();
            boolean studentFound = false;

            // Search for student
            for (String[] student : studentData) {
                if (studentId.equals(student[0])) {
                    System.out.println("Student Name: " + student[1]);
                    studentFound = true;
                    break;
                }
            }

            // If student not found
            if (!studentFound) {
                System.out.println("Student not found. Please try again.");
            } else {
                validId = true;
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
                displayMainMenu();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearWorkingConsole();
                displayMainMenu();
        }
    }

    // Store the student details using file
    private static void storeStudentDetails() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tSTORE STUDENT DETAILS");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Writing the data in the file
        try (FileWriter fileWriter = new FileWriter("src/task1/student-details.txt")) {
            // Iterate through the students array and write each student's details to the file
            for (int i = 0; i < currentStudentCount; i++) {
                fileWriter.write(studentData[i][0] + " - " + studentData[i][1] + "\n"); // write to the file
            }
            System.out.println("All Student Details are saved successfully.");
        } catch (IOException e) {
            System.out.println("I got this error: " + e.getMessage());
        }

        System.out.println("\n");
        clearWorkingConsole();
        displayMainMenu();
    }


    // Load the student details using file
    private static void loadStudentDetails() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tLOAD STUDENT DETAILS");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Loading the data from the file
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/task1/student-details.txt"));
            // Iterate through the students array and read each student's details from the file
            for (int i = 0; i < currentStudentCount; i++) {
                studentData[i][0] = reader.readLine(); // read student ID from the file
                studentData[i][1] = reader.readLine(); // read student name from the file
            }
            // Print a success message after loading all student details
            System.out.println("Student details loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading student details: " + e.getMessage());
        } finally {
            // Close the reader if it was opened
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing reader: " + e.getMessage());
                }
            }
        }

        System.out.println("\n");
        clearWorkingConsole();
        displayMainMenu();
    }

    // View the list of students
    private static void viewStudentListByName() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tVIEW STUDENT LIST BY NAME");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Checking if there are any students registered
        if (currentStudentCount == 0) {
            System.out.println("No students are registered.");
            clearWorkingConsole();
            registerNewStudent();
        } else {
            // If there are students, sort them by name using bubble sort
            for (int i = 0; i < currentStudentCount - 1; i++) {
                for (int j = 0; j < currentStudentCount - 1 - i; j++) {
                    if (studentData[j][1] != null && studentData[j + 1][1] != null) {
                        // Compare adjacent students' names and swap if needed
                        if (studentData[j][1].compareToIgnoreCase(studentData[j + 1][1]) > 0) {
                            String[] temp = studentData[j];
                            studentData[j] = studentData[j + 1];
                            studentData[j + 1] = temp;
                        }
                    }
                }
            }

            // Display the sorted list of students in a tabular format
            System.out.println("+----------------------+----------------------+");
            System.out.printf("| %-20s | %-20s |\n", "Student ID", "Name");
            System.out.println("+----------------------+----------------------+");

            for (int i = 0; i < currentStudentCount; i++) {
                if (studentData[i][0] != null && studentData[i][1] != null) {
                    // Capitalize the first letter of each name
                    String studentName = studentData[i][1].substring(0, 1).toUpperCase() + studentData[i][1].substring(1).toLowerCase();
                    System.out.printf("| %-20s | %-20s |\n", studentData[i][0], studentName);
                }
            }

            System.out.println("+----------------------+----------------------+");
        }
        clearWorkingConsole();
        displayMainMenu();
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
                displayMainMenu();
                break;
            default:
                System.out.println("Invalid Value.. Try Again..!");
                clearWorkingConsole();
                displayMainMenu();
                break;
        }
    }

    // Get the next available index for storing values in the students array
    public static int nextIdValues(String[][] students) {
        int array = students.length;

        // Iterate through the students array to find the first null value
        for (int i = 0; i < students.length; i++) {
            if (students[i][0] == null) {
                array = i;
                break;
            }
        }
        return array;
    }
}