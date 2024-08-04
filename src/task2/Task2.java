package task2;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

// Module Class
class Module {
    private final int marks;
    private final String grade;

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
    private final String studentId;
    private final String studentName;
    private Module[] modules;

    public Student(String id, String name) {
        this.studentId = id;
        this.studentName = name;
    }

    public Student(String id, String name, int[] marks) {
        this.studentId = id;
        this.studentName = name;
        this.modules = new Module[3];
        for (int i = 0; i < 3; i++) {
            this.modules[i] = new Module(marks[i]);
        }
    }

    public String getId() {
        return studentId;
    }

    public String getName() {
        return studentName;
    }

    public Module[] getModules() {
        return modules;
    }

    public void setModules(int[] marks) {
        this.modules = new Module[3];
        for (int i = 0; i < 3; i++) {
            this.modules[i] = new Module(marks[i]);
        }
    }
}

// Main Program - Student Activity Management
public class Task2 {
    // Student Seats
    private static final int MAX_STUDENTS = 100;
    // Student Class Array
    private static final Student[] studentArray = new Student[MAX_STUDENTS];

    // counting variable
    private static int countOfStudents = 0;

    // Runnable Method
    public static void main(String[] args) {
        //Handling the exception
        try {
            mainMenuConsole();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please type the integer input...!");
            clearConsole();
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
        System.out.println("[8]: Manage Students"); // Added New Item
        System.out.print("[9]:Exit \t\t\t\t");
        System.out.println();
        System.out.println();

        // menu input
        Scanner input_number = new Scanner(System.in);
        System.out.print("Enter the option to continue > ");
        int input_num = input_number.nextInt();
        switch (input_num) {
            case 1:
                checkAvailableSeats();
                break;
            case 2:
                registerStudent();
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
                clearConsole();
                mainMenuConsole();
                break;
        }
    }

    // clearing console
    private static void clearConsole() {
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
        int available_seats = MAX_STUDENTS - countOfStudents;
        System.out.print("Available Seats: " + available_seats);
        System.out.println("\n");
        clearConsole();
        mainMenuConsole();
    }

    // add the student details
    private static void registerStudent() {
        // checking the available seats in the student
        if (MAX_STUDENTS >= countOfStudents) {
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
        String studentId = studInput.next();

        // Checking the student id exists or not
        for (Student students : studentArray) {
            if (students != null && studentId.equals(students.getId()) && studentId.length() == 8) {
                System.out.println("The Student id already exists. Please try again.");
                clearConsole();
                registerStudent();
                return;
            }
        }

        // Check if student ID is 8 characters long
        if (studentId.length() != 8) {
            System.out.println("Invalid Student Id. Please ensure it is 8 characters long.");
            clearConsole();
            registerStudent();
            return;
        }

        // Enter the student name
        System.out.print("Enter the Student Name: ");
        String studentName = studInput.next();

        // add the array
        studentArray[countOfStudents] = new Student(studentId, studentName);
        countOfStudents++;

        System.out.print("Added Successfully. Do you want to add another student? (Y/N) : ");
        char ch = studInput.next().charAt(0);
        switch (ch) {
            case 'y':
            case 'Y':
                clearConsole();
                registerStudent();
                return;
            case 'n':
            case 'N':
                clearConsole();
                mainMenuConsole();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearConsole();
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
        for (int i = 0; i < countOfStudents; i++) {
            if (studentArray[i] != null && studentArray[i].getId().equals(studentId)) {
                studentArray[i] = studentArray[countOfStudents - 1];
                studentArray[countOfStudents - 1] = null;
                countOfStudents--;
                System.out.print("Deleted Successfully. Do you want to delete another student? (Y/N) : ");
                char ch = deleteStudent.next().charAt(0);
                switch (ch) {
                    case 'y':
                    case 'Y':
                        clearConsole();
                        deleteStudent();
                        return;
                    case 'n':
                    case 'N':
                        clearConsole();
                        mainMenuConsole();
                        return;
                    default:
                        System.out.println("Invalid value...Please try again!!!");
                        clearConsole();
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

        // Enter the student id
        System.out.print("Enter the Student Id: ");
        String stuId = findStudent.next();

        // Searching Part
        for (Student students : studentArray) {
            if (students != null && stuId.equals(students.getId())) {
                System.out.println("Student Name: " + students.getName());

                System.out.print("Searched Successfully. Do you want to search another student? (Y/N) : ");
                char ch = findStudent.next().charAt(0);
                switch (ch) {
                    case 'y':
                    case 'Y':
                        clearConsole();
                        findStudent();
                        return;
                    case 'n':
                    case 'N':
                        clearConsole();
                        mainMenuConsole();
                        return;
                    default:
                        System.out.println("Invalid value...Please try again!!!");
                        clearConsole();
                        mainMenuConsole();
                }
            } else {
                System.out.println("Student is not found. Please Try Again.");
                clearConsole();
                findStudent();
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

        // Store the student details
        try {
            FileWriter studentDetail = new FileWriter("src/task2/student.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(studentDetail);
            for (Student students : studentArray) {
                if (students != null) {
                    bufferedWriter.write(students.getId() + " - " + students.getName());
                    Module[] modules = students.getModules();
                    if (modules != null) {
                        for (Module module : modules) {
                            bufferedWriter.write(" - " + module.getMarks());
                        }
                    } else {
                        bufferedWriter.write("- 0 - 0 - 0"); // If no modules, write N/A
                    }
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            studentDetail.close();
            System.out.println("Student details stored successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing student details.");
        }
        clearConsole();
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
        try {
            // Create a FileReader to read the student details file
            FileReader fileReader = new FileReader("src/task2/student.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            countOfStudents = 0;

            // Read each line from the file
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into details using the hyphen as the delimiter
                System.out.println(line);
                String[] studentDetails = line.split("-");
                System.out.println(studentDetails.length);
                System.out.println(Arrays.toString(studentDetails));
                if (studentDetails.length == 5) { // Expecting 8 parts (id, name, and 6 elements for 3 marks)
                    String stu_id = studentDetails[0];
                    String stu_name = studentDetails[1];
                    int[] stu_marks = new int[3];
                    for (int i = 0; i < 3; i++) {
                        // Parse the marks from the string to integer
                        stu_marks[i] = Integer.parseInt(studentDetails[i+2].trim());
                    }
                    // Create a new Student object and add it to the studentArray
                    studentArray[countOfStudents] = new Student(stu_id, stu_name, stu_marks);
                    countOfStudents++;
                } else {
                    System.out.println("Incorrect format in the file for line: " + line);
                }
            }

            bufferedReader.close();
            fileReader.close();
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading student details.");
        }
        clearConsole();
        mainMenuConsole();
    }

    // View the list of students
    private static void viewStudentListByName() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\t\tVIEW STUDENT LIST");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        if (countOfStudents == 0) {
            System.out.println("No students are registered.");
        } else {
            // Sorting the students by name using Bubble Sort
            for (int i = 0; i < countOfStudents - 1; i++) {
                for (int j = 0; j < countOfStudents - 1 - i; j++) {
                    if (studentArray[j].getName().compareTo(studentArray[j + 1].getName()) > 0) {
                        Student tempStudent = studentArray[j];
                        studentArray[j] = studentArray[j + 1];
                        studentArray[j + 1] = tempStudent;
                    }
                }
            }

            // Display table header
            System.out.println("+---------------------------------------------+");
            System.out.printf("| %-10s | %-30s |%n", "Student ID", "Student Name");
            System.out.println("+---------------------------------------------+");

            // Display sorted students
            for (int i = 0; i < countOfStudents; i++) {
                System.out.printf("| %-10s | %-30s |%n", studentArray[i].getId(), studentArray[i].getName());
            }
            System.out.println("+---------------------------------------------+");
        }
        clearConsole();
        mainMenuConsole();
    }


    // managing student part
    private static void manageStudentMarks() {
        Scanner studInput = new Scanner(System.in);
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tMANAGE STUDENT RESULTS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // menu items
        System.out.print("[1]: Add student name\t\t\t\t\t\t\t\t");
        System.out.println("[2]: Add module marks");
        System.out.print("[3]: Go back to main menu\t\t\t\t\t\t");
        System.out.println("\n");
        System.out.print("Enter your option > ");
        int option = studInput.nextInt();

        switch (option) {
            case 1:
                addNewStudent();
                break;
            case 2:
                addModuleMarks();
                break;
            case 3:
                clearConsole();
                mainMenuConsole();
                break;
            default:
                System.out.println("Invalid Input! Please Try again.");
                clearConsole();
                manageStudentMarks();
                break;
        }
    }

    // add new student
    private static void addNewStudent() {
        Scanner studInput = new Scanner(System.in);
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\tADD NEW STUDENT");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Enter the student id
        System.out.print("Enter the Student Id: ");
        String stuId = studInput.next();

        // Checking the student id exists or not
        for (Student students : studentArray) {
            if (students != null && stuId.equals(students.getId()) && stuId.length() == 8) {
                System.out.println("The Student id already exists. Please try again.");
                clearConsole();
                addNewStudent();
                return;
            }
        }

        // Check if student ID is 8 characters long
        if (stuId.length() != 8) {
            System.out.println("Invalid Student Id. Please ensure it is 8 characters long.");
            clearConsole();
            registerStudent();
            return;
        }

        // Enter the student name
        System.out.print("Enter the Student Name: ");
        String sName = studInput.next();

        // adding the student details
        studentArray[countOfStudents] = new Student(stuId, sName);
        countOfStudents++;

        System.out.print("Added Successfully. Do you want to add another student? (Y/N) : ");
        char ch = studInput.next().charAt(0);
        switch (ch) {
            case 'y':
            case 'Y':
                clearConsole();
                addNewStudent();
                return;
            case 'n':
            case 'N':
                clearConsole();
                manageStudentMarks();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearConsole();
                mainMenuConsole();
        }
    }

    // Adding the module marks
    private static void addModuleMarks() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tADD MODULE MARKS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");

        // Enter the Student Id
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Student Id: ");
        String sId = input.next();

        // checking the equality of student id and adding the module marks
        for (Student students : studentArray) {
            if (students != null && students.getId().equals(sId)) {
                int[] stu_marks = new int[3];
                for (int i = 0; i < 3; i++) {
                    System.out.print("Enter marks for Module " + (i + 1) + ": ");
                    stu_marks[i] = input.nextInt();
                }
                students.setModules(stu_marks);

                System.out.print("Marks Added Successfully. Do you want to add marks for another student? (Y/N) : ");
                char ch = input.next().charAt(0);
                switch (ch) {
                    case 'y':
                    case 'Y':
                        clearConsole();
                        addModuleMarks();
                        return;
                    case 'n':
                    case 'N':
                        clearConsole();
                        manageStudentMarks();
                        return;
                    default:
                        System.out.println("Invalid value...Please try again!!!");
                        manageStudentMarks();
                }
            }
        }
        System.out.println("Student Id Not Exists. Try again.");
        clearConsole();
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
                clearConsole();
                System.exit(0);
                break;
            case 'n':
            case 'N':
                clearConsole();
                mainMenuConsole();
                break;
            default:
                System.out.println("Invalid Value.. Try Again..!");
                clearConsole();
                mainMenuConsole();
                break;
        }
    }
}