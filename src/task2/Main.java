package task2;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

/**
 * Coded By: Era Boy
 * Version: v0.1.0
 **/

// Module Class
class Module {
    private int marks;
    private String grade;

    public Module() {
    }

    public Module(int marks) {
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    private String calculateGrade(int marks) {
        if (marks >= 80) {
            return "Distinction";
        } else if (marks >= 70) {
            return "Merit";
        } else if (marks >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }
}

// Student Class
class Student {
    private final String id;
    private final String name;
    private Module[] modules;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String id, String name, int[] marks) {
        this.id = id;
        this.name = name;
        this.modules = new Module[3];
        for (int i = 0; i < 3; i++) {
            this.modules[i] = new Module(marks[i]);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Module[] getModules() {
        return modules;
    }
}

// Main Class
public class Main {
    // Student Count Variables
    private static final int MAX_CAPACITY = 100;
    // Student Class Array
    private static final Student[] students = new Student[MAX_CAPACITY];

    // counting variable
    private static int studentCount = 0;

    // Runnable Method
    public static void main(String[] args) {
        /*Handling the exception*/
        try {
            mainMenuConsole();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please type the integer input...!");
            clearWorkingConsole();
            mainMenuConsole();
        }
    }

    // main menu commands
    private static void mainMenuConsole() throws InputMismatchException {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\tWELCOME TO STUDENT ACTIVITY MANAGEMENT SYSTEM");
        System.out.println("\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Menu List
        System.out.print("[1]: Check Available Seats\t\t\t\t\t\t\t\t");
        System.out.println("[2]: Register Student");
        System.out.print("[3]: Delete Student\t\t\t\t\t\t\t\t\t\t");
        System.out.println("[4]: Find Student");
        System.out.print("[5]: Store Student Details (within the file)\t\t\t");
        System.out.println("[6]: Load Student Details (from the file)");
        System.out.print("[7]: View the list of students\t\t\t\t\t\t\t");
        System.out.println("[8]: Add Module Marks to the students"); // Added New Item
        System.out.print("[9]:Exit \t\t\t\t");
        System.out.println();
        System.out.println();

        // menu input
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
                manageStudentMarks();
                break;
            case 9:
                exitTheSystem();
                break;

            default:
                System.out.println("Invalid Input!! Please Try again!!");
                clearWorkingConsole();
                mainMenuConsole();
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

        // checking the available seats
        int available_seats = MAX_CAPACITY - studentCount;
        System.out.print("Available Seats: " + available_seats);
        System.out.println("\n");
        clearWorkingConsole();
        mainMenuConsole();
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

        // Enter the student id
        System.out.print("Enter the Student Id: ");
        String sId = studInput.next();

        // Checking the student id exists or not
        for (Student student : students) {
            if (student != null && sId.equals(student.getId()) && sId.length() == 8) {
                System.out.println("The Student id already exists. Please try again.");
                clearWorkingConsole();
                registerNewStudent();
                return;
            }
        }

        // Enter the student name
        System.out.print("Enter the Student Name: ");
        String sName = studInput.next();

        // Adding the marks
        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter marks for Module " + (i + 1) + ": ");
            marks[i] = studInput.nextInt();
        }

        students[studentCount] = new Student(sId, sName, marks);
        studentCount++;

        System.out.print("Added Successfully. Do you want to add another student? (Y/N) : ");
        char ch = studInput.next().charAt(0);
        switch (ch) {
            case 'y':
            case 'Y':
                clearWorkingConsole();
                registerNewStudent();
                return;
            case 'n':
            case 'N':
                clearWorkingConsole();
                mainMenuConsole();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearWorkingConsole();
                mainMenuConsole();
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

        //Enter the student id
        System.out.print("Enter the Student Id: ");
        String studentId = deleteStudent.next();

        //deleting process
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null && students[i].getId().equals(studentId)) {
                students[i] = students[studentCount - 1];
                students[studentCount - 1] = null;
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
                        return;
                    default:
                        System.out.println("Invalid value...Please try again!!!");
                        clearWorkingConsole();
                        mainMenuConsole();
                        return;
                }
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

        System.out.print("Enter the Student Id: ");
        String stuId = findStudent.next();
        for (Student student : students) {
            if (student != null && stuId.equals(student.getId())) {
                System.out.println("Student Name: " + student.getName());
                Module[] modules = student.getModules();
                for (int i = 0; i < modules.length; i++) {
                    System.out.println("Module " + (i + 1) + " Marks: " + modules[i].getMarks() + ", Grade: " + modules[i].getGrade());
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
                        return;
                    default:
                        System.out.println("Invalid value...Please try again!!!");
                        clearWorkingConsole();
                        mainMenuConsole();
                }
            } else {
                System.out.println("Student is not found. Please Try Again.");
                clearWorkingConsole();
                mainMenuConsole();
            }
        }
    }

    //Store the student details using file
    private static void storeStudentDetails() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tSTORE STUDENT DETAILS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        try (PrintWriter studentWriter = new PrintWriter(new FileWriter("src/task2/student.txt"))) {
            for (int i = 0; i < studentCount; i++) {
                Student student = students[i];
                studentWriter.println(student.getId() + " - " + student.getName());
                Module[] modules = student.getModules();
                for (int j = 0; j < modules.length; j++) {
                    studentWriter.println("Module " + (j + 1) + " Marks: " + modules[j].getMarks() + ", Grade: " + modules[j].getGrade());
                }
            }
            System.out.println("All Student Details are saved successfully.");
        } catch (IOException e) {
            System.out.println("I got this error: " + e.getMessage() + " please fix it.");
        }
        clearWorkingConsole();
        mainMenuConsole();
    }

    // Load Student details
    private static void loadStudentDetails() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tLOAD STUDENT DETAILS");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        try (BufferedReader studentReader = new BufferedReader(new FileReader("src/task2/student.txt"))) {
            studentCount = 0; // Reset student count
            String line;
            while ((line = studentReader.readLine()) != null) {
                // split the lines
                String[] studentInfo = line.split(" - ");
                if (studentInfo.length < 2) {
                    System.out.println("Invalid student info format: " + line);
                    continue;
                }
                String sId = studentInfo[0];
                String sName = studentInfo[1];

                int[] marks = new int[3];
                for (int i = 0; i < 3; i++) {
                    line = studentReader.readLine();

                    if (line == null) {
                        System.out.println("Unexpected end of file while reading marks for " + sId);
                        return;
                    }
                    // Adjust the split to account for extra text
                    String[] moduleInfo = line.split(" ");
                    try {
                        marks[i] = Integer.parseInt(moduleInfo[3].replace(",", ""));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing marks from line: " + line);
                        return;
                    }
                }

                students[studentCount] = new Student(sId, sName, marks);
                studentCount++;
            }
            System.out.println("All Student Details are loaded successfully.");
        } catch (IOException e) {
            System.out.println("I got this error: " + e.getMessage() + " please fix it.");
        }
        clearWorkingConsole();
        mainMenuConsole();
    }

    // View the list of students
    private static void viewStudentListByName() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tVIEW STUDENT LIST");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        if (studentCount == 0) {
            System.out.println("No students are registered.");
        } else {

            // Sorting the students by name using Bubble Sort
            for (int i = 0; i < studentCount - 1; i++) {
                for (int j = 0; j < studentCount - 1 - i; j++) {
                    if (students[j].getName().compareTo(students[j + 1].getName()) > 0) {
                        Student temp = students[j];
                        students[j] = students[j + 1];
                        students[j + 1] = temp;
                    }
                }
            }

            // Display sorted students
            for (int i = 0; i < studentCount; i++) {
                System.out.println("Student ID: " + students[i].getId() + ", Student Name: " + students[i].getName());
            }
        }
        clearWorkingConsole();
        mainMenuConsole();
    }

    private static void manageStudentMarks() {
        Scanner studInput = new Scanner(System.in);
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tMANAGE STUDENT RESULTS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        System.out.print("[1]: Add student name\t\t\t\t\t\t\t\t");
        System.out.println("[2]: Add module marks");
        System.out.print("[3]: View all student details\t\t\t\t\t\t");
        System.out.println("[4]: Go back to main menu");
        System.out.println();
        System.out.println("Enter your option > ");
        int option = studInput.nextInt();

        switch (option) {
            case 1:
                addNewStudent();
                break;
            case 2:
                addModuleMarks();
                break;
            case 3:
                viewAllStudentDetails();
                break;
            case 4:
                clearWorkingConsole();
                mainMenuConsole();
                break;
            default:
                System.out.println("Invalid Input! Please Try again.");
                clearWorkingConsole();
                manageStudentMarks();
                break;
        }
    }

    private static void addNewStudent() {
        Scanner studInput = new Scanner(System.in);
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tREGISTER NEW STUDENT");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Enter the student id
        System.out.print("Enter the Student Id: ");
        String sId = studInput.next();

        // Checking the student id exists or not
        for (Student student : students) {
            if (student != null && sId.equals(student.getId()) && sId.length() == 8) {
                System.out.println("The Student id already exists. Please try again.");
                clearWorkingConsole();
                addNewStudent();
                return;
            }
        }

        // Enter the student name
        System.out.print("Enter the Student Name: ");
        String sName = studInput.next();

        students[studentCount] = new Student(sId, sName);
        studentCount++;

        System.out.print("Added Successfully. Do you want to add another student? (Y/N) : ");
        char ch = studInput.next().charAt(0);
        switch (ch) {
            case 'y':
            case 'Y':
                clearWorkingConsole();
                addNewStudent();
                return;
            case 'n':
            case 'N':
                clearWorkingConsole();
                manageStudentMarks();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearWorkingConsole();
                mainMenuConsole();
        }
    }

    private static void viewAllStudentDetails() {
        if (studentCount == 0) {
            System.out.println("No students are registered.");
        } else {
            for (int i = 0; i < studentCount; i++) {
                Student student = students[i];
                System.out.println("Student ID: " + student.getId() + ", Student Name: " + student.getName());
                Module[] modules = student.getModules();
                for (int j = 0; j < modules.length; j++) {
                    System.out.println("Module " + (j + 1) + " Marks: " + modules[j].getMarks() + ", Grade: " + modules[j].getGrade());
                }
            }
        }
        clearWorkingConsole();
        mainMenuConsole();
    }

    private static void addModuleMarks() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tADD MODULE MARKS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Student Id: ");
        String sId = input.next();

        for (Student student : students) {
            System.out.println("Student Name: " + student.getName());
            if (student.getId().equals(sId)) {
                int[] marks = new int[3];
                for (int i = 0; i < 3; i++) {
                    System.out.print("Enter marks for Module " + (i + 1) + ": ");
                    marks[i] = input.nextInt();
                }
                student = new Student(student.getId(), student.getName(), marks);
                System.out.print("Searched Successfully. Do you want to search another student? (Y/N) : ");
                char ch = input.next().charAt(0);
                switch (ch) {
                    case 'y':
                    case 'Y':
                        clearWorkingConsole();
                        findStudent();
                        return;
                    case 'n':
                    case 'N':
                        clearWorkingConsole();
                        manageStudentMarks();
                        return;
                    default:
                        System.out.println("Invalid value...Please try again!!!");
                        clearWorkingConsole();
                        mainMenuConsole();
                }
            }
        }
        System.out.println("Student Id Not Exists. Try again.");
        clearWorkingConsole();
        addModuleMarks();
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
                break;
            default:
                System.out.println("Invalid Value.. Try Again..!");
                clearWorkingConsole();
                mainMenuConsole();
                break;
        }
    }
}