package Intermediate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The model of the Student
 * PS: If wala paka kahibalo ani goodluck nalang
 *      pero actually basic rani sya don't worry ;D
 */
class Student {
    private final int studentId;
    private String name;
    private String course;
    private int yearLevel;
    private double GPA;

    /**
     * Constructor without GPA
     * @param studentId The ID of the student
     * @param name The name of the student
     * @param course The course of the student
     * @param yearLevel The year level of the student
     */
    public Student(int studentId, String name, String course, int yearLevel) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.yearLevel = yearLevel;
        this.GPA = 0.00f;
    }

    /**
     * Constructor with GPA
     * @param studentId The ID of the student
     * @param name The name of the student
     * @param course The course of the student
     * @param yearLevel The year level of the student
     * @param GPA The GPA of the student
     */
    public Student(int studentId, String name, String course, int yearLevel, double GPA) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.yearLevel = yearLevel;
        this.GPA = GPA;
    }

    /// GETTERS
    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public int getYearLevel() { return yearLevel; }
    public double getGPA() { return GPA; }

    /// SETTERS
    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }
    public void setYearLevel(int yearLevel) { this.yearLevel = yearLevel; }
    public void setGPA(double GPA) { this.GPA = GPA; }

    /**
     * Polymorphism for "toString()" method
     * @return String
     */
    @Override
    public String toString() {
        System.out.printf("ID:\t\t\t%d\n", studentId);
        System.out.printf("Name:\t\t%s\n", name);
        System.out.printf("Course:\t\t%s\n", course);
        System.out.printf("Year:\t\t%d\n", yearLevel);

        if (GPA <= 0.00f)
            System.out.println("GPA:\t\tN.G");
        else
            System.out.printf("GPA:\t\t%.1f\n", GPA);

        return super.toString();
    }
}

/**
 * Console Student Record Manager Program by Charles Henry M. Tinoy Jr.
 */
public class StudentRecordManager {
    /// STUDENTS
    private static final ArrayList<Student> STUDENTS = new ArrayList<>();

    /**
     * Displays the border (E.g. ==========)
     * @param length The length of the border
     */
    public static void displayBorder(int length) {
        for (int i = 0; i < length; i++)
            System.out.print("=");

        System.out.println();
    }

    /**
     * Displays the border (E.g. ===== Charles Henry M. Tinoy Jr. =====)
     * @param length The length of the border
     * @param heading The heading of the border
     */
    private static void displayBorder(int length, String heading) {
        for (int i = 0; i < length; i++)
            System.out.print("=");
        System.out.printf(" %s ", heading);
        for (int i = 0; i < length; i++)
            System.out.print("=");

        System.out.println();
    }

    /**
     * Performs the add student operation
     * @param scanner The scanner buffer
     */
    private static void addStudent(Scanner scanner) {
        displayBorder(10, "ADD STUDENT");

        // Get the student ID
        System.out.print("Enter the student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, please try again: ");
            scanner.next();
            scanner.nextLine();
        }
        int studentId = scanner.nextInt();

        /*
         * Get the student with a matching student ID
         *      If no match then proceed with the rest of the input.
         */
        int finalStudentId = studentId;
        Student result = STUDENTS.stream()
                .filter(student -> student.getStudentId() == finalStudentId)
                .findFirst()
                .orElse(null);

        while (result != null) {
            System.out.printf("The ID %d already existed in the record.\n", studentId);
            System.out.println("Please input a different student ID.");
            System.out.print("Enter the student ID: ");
            scanner.nextLine();
            studentId = scanner.nextInt();

            int finalStudentIdConditional = studentId;
            result = STUDENTS.stream()
                    .filter(student -> student.getStudentId() == finalStudentIdConditional)
                    .findFirst()
                    .orElse(null);
        }

        // Clear buffer
        scanner.nextLine();

        // Get the student name
        System.out.print("Enter the student name: ");
        String studentName = scanner.nextLine();
        while (studentName.trim().isEmpty()) {
            System.out.print("Student name cannot be empty, please try again: ");
            studentName = scanner.nextLine();
        }

        // Get the student course
        System.out.print("Enter the student course: ");
        String studentCourse = scanner.nextLine();
        while (studentCourse.trim().isEmpty()) {
            System.out.print("Student course cannot be empty, please try again: ");
            studentCourse = scanner.nextLine();
        }

        // Get the student year
        System.out.print("Enter the student year: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, please try again: ");
            scanner.next();
            scanner.nextLine();
        }
        int studentYear = scanner.nextInt();

        // Clear buffer
        scanner.nextLine();

        // Get the student GPA
        System.out.print("Enter the student GPA (optional): ");
        String studentGPAInput = scanner.nextLine();

        double studentGPA = -1;

        /*
         * Check if the studentGPAInput is empty
         *      If it's not empty then proceed to parse the given value to double
         */
        if (!studentGPAInput.trim().isEmpty()) {
            while (true) {
                try {
                    studentGPA = Double.parseDouble(studentGPAInput);
                    break;
                } catch (NumberFormatException ex) {
                    System.out.print("Invalid input, please try again: ");
                    studentGPAInput = scanner.nextLine();
                }
            }
        }

        /*
         * If no GPA then create a new student with no GPA
         *      else create one with GPA
         */
        Student newStudent;
        if (studentGPA != -1)
            newStudent = new Student(studentId, studentName, studentCourse, studentYear, studentGPA);
        else
            newStudent = new Student(studentId, studentName, studentCourse, studentYear);

        STUDENTS.add(newStudent);

        System.out.printf("%s has successfully added to the record.\n", studentName);
    }

    /**
     * Display all students
     */
    private static void displayAllStudents() {
        displayBorder(13, "STUDENTS RECORD");

        // If students empty then print the message
        if (STUDENTS.isEmpty()) {
            System.out.println("Looks like you haven't add any student yet.");
            displayBorder(43);
            return;
        }

        for (Student student : STUDENTS) {
            student.toString();
            displayBorder(43);
        }
    }

    /**
     * Performs the student search by ID
     * @param scanner Scanner buffer
     */
    private static void searchStudentById(Scanner scanner) {
        // Get the student ID
        System.out.print("Enter the student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, please try again: ");
            scanner.next();
            scanner.nextLine();
        }
        int studentId = scanner.nextInt();

        /*
         * Get the student based on the given student ID
         * If someone matched then return the student and display
         */
        Student result = STUDENTS.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst()
                .orElse(null);

        if (result != null) {
            displayBorder(10, "RESULT");
            result.toString();
            return;
        }

        System.out.printf("Unable to find student with ID %d\n", studentId);
    }

    /**
     * Performs the student search by name
     * @param scanner Scanner buffer
     */
    private static void searchStudentByName(Scanner scanner) {
        // Clear buffer
        scanner.nextLine();

        // Get the student name
        System.out.print("Enter the student name: ");
        String studentName = scanner.nextLine();
        while (studentName.trim().isEmpty()) {
            System.out.print("Student name cannot be empty, please try again: ");
            studentName = scanner.nextLine();
        }

        /*
         * Get the student based on the given student name
         * If someone matched then return the student and display
         */
        String finalStudentName = studentName;
        Student result = STUDENTS.stream()
                .filter(student -> student.getName().equalsIgnoreCase(finalStudentName))
                .findFirst()
                .orElse(null);

        if (result != null) {
            displayBorder(10, "RESULT");
            result.toString();
            return;
        }

        System.out.printf("Unable to find student named %s\n", studentName);
    }

    /**
     * Search the student by choosen option
     * @param scanner Scanner buffer
     */
    private static void searchStudent(Scanner scanner) {
        displayBorder(10, "SEARCH STUDENT(s)");

        while (true) {
            System.out.println("Search by ID\t\t\t\t\t\t[1]");
            System.out.println("Search by Name\t\t\t\t\t\t[2]");
            System.out.println("Go back\t\t\t\t\t\t\t\t[3]");
            displayBorder(15, "OPTIONS");

            // Get the user option
            System.out.print("Option: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input, please try again: ");
                scanner.next();
                scanner.nextLine();
            }
            int option = scanner.nextInt();

            if (option == 1) searchStudentById(scanner);
            else if (option == 2) searchStudentByName(scanner);
            else if (option == 3) break;
            else System.out.println("Invalid option, please try again.");

            displayBorder(10, "SEARCH STUDENT(s)");
        }
    }

    /**
     * Updates the student by ID
     * @param scanner Scanner buffer
     */
    private static void updateStudent(Scanner scanner) {
        displayBorder(10, "UPDATE STUDENT");

        // Get the student
        System.out.print("Enter the student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, please try again: ");
            scanner.next();
            scanner.nextLine();
        }
        int studentId = scanner.nextInt();

        // Get the student by its ID
        Student result = STUDENTS.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst()
                .orElse(null);

        // If nothing match then cancel the operation
        if (result == null) {
            System.out.printf("Unable to find a student with ID %d\n", studentId);
            return;
        }

        // Otherwise perform the update
        displayBorder(10, "CURRENT STUDENT INFORMATION");
        result.toString();
        displayBorder(49);
        System.out.println("Press the \"Enter\" key to skip an input.");

        // Clear buffer
        scanner.nextLine();

        // Get student new name
        System.out.print("Enter the new name: ");
        String studentName = scanner.nextLine();

        // Get student new course
        System.out.print("Enter the new course: ");
        String studentCourse = scanner.nextLine();

        // Get student new year level
        System.out.print("Enter the new year level: ");
        String yearLevelInput = scanner.nextLine();
        int studentYearLevel = result.getYearLevel();
        while (true) {
            if (yearLevelInput.isEmpty()) break;
            try {
                studentYearLevel = Integer.parseInt(yearLevelInput);
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input, please try again: ");
                yearLevelInput = scanner.nextLine();
            }
        }

        // Get student new GPA
        System.out.print("Enter the new GPA: ");
        String gpaInput = scanner.nextLine();
        double studentGPA = result.getGPA();
        while (true) {
            if (gpaInput.isEmpty()) break;
            try {
                studentGPA = Double.parseDouble(gpaInput);
                break;
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input, please try again: ");
                gpaInput = scanner.nextLine();
            }
        }

        if (!studentName.trim().isEmpty())
            result.setName(studentName);
        if (!studentCourse.trim().isEmpty())
            result.setCourse(studentCourse);

        result.setYearLevel(studentYearLevel);
        result.setGPA(studentGPA);

        System.out.printf("%s record has successfully been updated.\n", result.getName());
    }

    /**
     * Deletes the student by ID
     * @param scanner Scanner buffer
     */
    private static void deleteStudent(Scanner scanner) {
        displayBorder(10, "DELETE STUDENT");

        // Get the student ID
        System.out.print("Enter the student ID: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, please try again: ");
            scanner.next();
            scanner.nextLine();
        }
        int studentId = scanner.nextInt();

        // Get the student by ID
        Student result = STUDENTS.stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst()
                .orElse(null);

        // If not match then close the operation
        if (result == null) {
            System.out.printf("Unable to find student with ID %d\n", studentId);
            return;
        }

        // Clear buffer
        scanner.nextLine();

        /*
         * Otherwise show the confirmation input
         * And delete the student
         */
        System.out.printf("Are you sure you want to delete %s from the record?\n", result.getName());
        System.out.print("Confirm deletion [y/n]: ");
        String confirmationInput = scanner.nextLine();

        char confirmation = confirmationInput.toLowerCase().toCharArray()[0];
        while (true) {
            if (confirmation == 'y') {
                STUDENTS.remove(result);
                System.out.printf("%s has successfully been deleted from the record.\n", result.getName());
                break;
            } else if (confirmation == 'n') {
                System.out.println("Deletion cancelled.");
                break;
            } else {
                System.out.println("Invalid option, please try again.");
                confirmation = scanner.nextLine().toCharArray()[0];
            }
        }
    }

    /**
     * Debug type shit- ni charles
     */
    private static void debugTypeShit() {
        displayBorder(10, "DEBUG");
        for (Student student : STUDENTS)
            student.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayBorder(29);
        System.out.println("\tSTUDENT RECORD MANAGER");

        do {
            displayBorder(10, "OPTIONS");
            System.out.println("Add Student\t\t\t\t\t[1]");
            System.out.println("View All Students\t\t\t[2]");
            System.out.println("Search Student\t\t\t\t[3]");
            System.out.println("Update Student\t\t\t\t[4]");
            System.out.println("Delete Student\t\t\t\t[5]");
            System.out.println("Exit\t\t\t\t\t\t[6]");
            displayBorder(10, "OPTIONS");

            // Get the user option
            System.out.print("Option: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input, please try again: ");
                scanner.nextLine();
            }
            int userOption = scanner.nextInt();

            if (userOption == 1) addStudent(scanner);
            else if (userOption == 2) displayAllStudents();
            else if (userOption == 3) searchStudent(scanner);
            else if (userOption == 4) updateStudent(scanner);
            else if (userOption == 5) deleteStudent(scanner);
            else if (userOption == 6) break;
            else System.out.println("Invalid option, please try again.");

            // Debug
            // debugTypeShit();
        } while (true);

        scanner.close();
    }
}
