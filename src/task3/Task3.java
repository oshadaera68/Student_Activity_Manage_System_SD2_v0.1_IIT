package task3;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    // calculate grade
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

    public void setMarks(int marks) {
        this.marks = marks;
        this.grade = calculateGrade(marks);
    }

    public String getGrade() {
        return grade;
    }
}

// Student Class
class Student {
    private final String id;
    private final String name;
    private final Module[] modules;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.modules = new Module[3];
        for (int i = 0; i < 3; i++) {
            this.modules[i] = new Module(); // Initialize with empty modules
        }
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

    // total marks
    public int getTotalMarks() {
        int total = 0;
        for (Module module : modules) {
            if (module != null) {
                total += module.getMarks();
            }
        }
        return total;
    }

    // average marks
    public double getAverageMarks() {
        return getTotalMarks() / 3.0;
    }

    // find the final grade
    public String getFinalGrade() {
        for (Module module : modules) {
            if (module.getGrade().equals("Fail")) {
                return "Fail";
            }
        }
        return "Pass";
    }
}

// Main Program - Student Activity Management
public class Task3 {
    // Student Count Variables
    private static final int MAX_CAPACITY = 100;
    // Student Class Array
    private static final Student[] students = new Student[MAX_CAPACITY];

    // counting variable
    private static int studentCount = 0;

    // Runnable Method
    public static void main(String[] args) {
        //Handling the exception
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
        System.out.println("[8]: Manage Students"); // Added New Item
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

        // Check if student ID is 8 characters long
        if (sId.length() != 8) {
            System.out.println("Invalid Student Id. Please ensure it is 8 characters long.");
            clearWorkingConsole();
            registerNewStudent();
            return;
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
        System.out.print("\t\t\t\t\t\t\t\t\tSTORE STUDENT DETAILS");
        System.out.println("\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        try {
            FileWriter fileWriter = new FileWriter("src/task3/student.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Student student : students) {
                if (student != null) {
                    bufferedWriter.write(student.getId() + " - " + student.getName());
                    for (Module module : student.getModules()) {
                        bufferedWriter.write(" , " + module.getMarks());
                    }
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Student details stored successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing student details.");
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
        try {
            FileReader fileReader = new FileReader("src/task3/student.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            studentCount = 0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] details = line.split("-");
                String id = details[0].trim();
                String name = details[1].trim();

                if (details.length > 2) {
                    // File contains marks
                    int[] marks = new int[3];
                    for (int i = 0; i < 3 && i + 2 < details.length; i++) {
                        marks[i] = Integer.parseInt(details[i + 2].trim());
                    }
                    students[studentCount] = new Student(id, name, marks);
                } else {
                    // File only contains ID and name
                    students[studentCount] = new Student(id, name);
                }
                studentCount++;
            }
            bufferedReader.close();
            fileReader.close();
            System.out.println("Student details loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading student details.");
        } catch (NumberFormatException e) {
            System.out.println("Error parsing marks. Ensure all marks are valid integers.");
        }
        clearWorkingConsole();
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

            // Display table header
            System.out.println("+---------------------------------------------+");
            System.out.printf("| %-10s | %-30s |%n", "Student ID", "Student Name");
            System.out.println("+---------------------------------------------+");

            // Display sorted students
            for (int i = 0; i < studentCount; i++) {
                System.out.printf("| %-10s | %-30s |%n", students[i].getId(), students[i].getName());
            }
            System.out.println("+---------------------------------------------+");
        }
        clearWorkingConsole();
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
        System.out.print("[3]: Generate the summary\t\t\t\t\t\t\t");
        System.out.println("[4]: Generate the complete report");
        System.out.print("[5]: Go back to main menu\t\t\t\t\t\t");
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
                summaryGenerator();
                break;
            case 4:
                comReportGenerator();
                break;
            case 5:
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

    private static void comReportGenerator() {
        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\tCOMPLETE REPORT");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("Student ID: " + students[i].getId());
            System.out.println("Student Name: " + students[i].getName());
            for (int j = 0; j < 3; j++) {
                System.out.println("Module " + (j + 1) + " Marks: " + students[i].getModules()[j].getMarks());
                System.out.println("Module " + (j + 1) + " Grade: " + students[i].getModules()[j].getGrade());
            }
            System.out.println("Total Marks: " + students[i].getTotalMarks());
            System.out.println("Average Marks: " + students[i].getAverageMarks());
            System.out.println("Final Grade: " + students[i].getFinalGrade());
            System.out.println();
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Generated Successfully. Do you want to go to the menu? (Y/N) : ");
        char ch = input.next().charAt(0);
        switch (ch) {
            case 'y':
            case 'Y':
                clearWorkingConsole();
                manageStudentMarks();
                return;
            case 'n':
            case 'N':
                clearWorkingConsole();
                comReportGenerator();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearWorkingConsole();
                mainMenuConsole();
        }
    }

    private static void summaryGenerator() {
        int totalStudents = 0;
        int totalMarks = 0;
        int highestMarks = Integer.MIN_VALUE;
        int lowestMarks = Integer.MAX_VALUE;
        double averageMarks;

        // Count valid students and calculate total marks, highest and lowest marks
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null) { // Check for non-null student
                int studentTotalMarks = students[i].getTotalMarks();
                System.out.println("Student " + (i + 1) + " Marks: " + studentTotalMarks); // Debug print

                totalMarks += studentTotalMarks;
                totalStudents++; // Increment the count of valid students
                if (studentTotalMarks > highestMarks) {
                    highestMarks = studentTotalMarks;
                }
                if (studentTotalMarks < lowestMarks) {
                    lowestMarks = studentTotalMarks;
                }
            }
        }

        // Handle the case where there are no valid students
        if (totalStudents == 0) {
            System.out.println("No valid student data available.");
            return;
        }

        averageMarks = (double) totalMarks / totalStudents;

        // Sort students by total marks using bubble sort
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j] != null && students[j + 1] != null && students[j].getTotalMarks() > students[j + 1].getTotalMarks()) {
                    // Swap students[j] and students[j + 1]
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        System.out.print("\n");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.print("|");
        System.out.print("\t\t\t\t\t\t\t\t\t\tSUMMARY");
        System.out.println("\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------------------+");
        System.out.println("Total Students: " + totalStudents);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Marks: " + averageMarks);
        System.out.println("Highest Marks: " + highestMarks);
        System.out.println("Lowest Marks: " + lowestMarks);
        System.out.println("\nSorted Students by Marks:");
        for (int i = 0; i < studentCount; i++) {
            if (students[i] != null) {
                System.out.println("Student " + (i + 1) + " Marks: " + students[i].getTotalMarks());
            }
        }
        System.out.println();


        Scanner input = new Scanner(System.in);
        System.out.print("Generated Successfully. Do you want to go to the menu? (Y/N) : ");
        char ch = input.next().charAt(0);
        switch (ch) {
            case 'y':
            case 'Y':
                clearWorkingConsole();
                manageStudentMarks();
                return;
            case 'n':
            case 'N':
                clearWorkingConsole();
                summaryGenerator();
                return;
            default:
                System.out.println("Invalid value...Please try again!!!");
                clearWorkingConsole();
                mainMenuConsole();
        }
    }

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

    // Adding the module marks
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

        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            if (student != null && student.getId().equals(sId)) {
                Module[] modules = student.getModules();
                for (int j = 0; j < 3; j++) {
                    System.out.print("Enter marks for Module " + (j + 1) + ": ");
                    int marks = input.nextInt();
                    modules[j].setMarks(marks);
                }
                System.out.print("Marks Added Successfully. Do you want to add marks for another student? (Y/N) : ");
                char ch = input.next().charAt(0);
                switch (ch) {
                    case 'y':
                    case 'Y':
                        clearWorkingConsole();
                        addModuleMarks();
                        return;
                    case 'n':
                    case 'N':
                        clearWorkingConsole();
                        manageStudentMarks();
                        return;
                    default:
                        System.out.println("Invalid value...Please try again!!!");
                        manageStudentMarks();
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