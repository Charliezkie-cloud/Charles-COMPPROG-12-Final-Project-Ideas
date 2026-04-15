package Beginner;

import java.util.Scanner;

/**
 * Console Student Grade Calculator Program by Charles Henry M. Tinoy Jr.
 */
public class StudentGradeCalculator {
    // COLLEGE GRADES
    private static final double COLLEGE_OUTSTANDING_GRADE = 1.50f;
    private static final double COLLEGE_VSATISFACTORY_GRADE = 2.00f;
    private static final double COLLEGE_SATISFACTORY_GRADE = 2.50f;
    private static final double COLLEGE_FAIRLY_SATISFACTORY_GRADE = 3.00f;

    // K TO 12 GRADES
    private static final double K12_OUTSTANDING_GRADE = 90.00f;

    // Very Satisfactory K to 12 grades
    private static final double K12_VSATISFACTORY_GRADE_MIN = 85.00f;

    // Satisfactory K to 12 grades
    private static final double K12_SATISFACTORY_GRADE_MIN = 80.00f;

    // Fairly Satisfactory K to 12 grades
    private static final double K12_FAIRLY_SATISFACTORY_GRADE_MIN = 75.00f;

    /**
     * Displays the border (E.g. ==========)
     * @param length The length of the border
     */
    private static void displayBorder(int length) {
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
     * Returns the number suffix
     * @param number The number to be given a suffix
     * @return The number suffix
     */
    private static String getSuffix(int number) {
        if (number % 100 >= 11 && number % 100 <= 13)
            return "th";

        return switch (number % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    /**
     * Returns the remarks
     * @param isCollege Is it a college grading system or not?
     * @param averageGrade The average grade of the subjects
     * @return The remarks of the average grade
     */
    private static String getRemarks(boolean isCollege, double averageGrade) {
        if (isCollege) {
            if (averageGrade <= COLLEGE_OUTSTANDING_GRADE) return "Outstanding";
            if (averageGrade <= COLLEGE_VSATISFACTORY_GRADE) return "Very Satisfactory";
            if (averageGrade <= COLLEGE_SATISFACTORY_GRADE) return "Satisfactory";
            if (averageGrade <= COLLEGE_FAIRLY_SATISFACTORY_GRADE) return "Fairly Satisfactory";
            return "Did Not Meet Expectations";
        }

        if (averageGrade >= K12_OUTSTANDING_GRADE) return "Outstanding";
        if (averageGrade >= K12_VSATISFACTORY_GRADE_MIN) return "Very Satisfactory";
        if (averageGrade >= K12_SATISFACTORY_GRADE_MIN) return "Satisfactory";
        if (averageGrade >= K12_FAIRLY_SATISFACTORY_GRADE_MIN) return "Fairly Satisfactory";
        return "Did Not Meet Expectations";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isCollege;

        displayBorder(31);
        System.out.println("\tSTUDENT GRADE CALCULATOR");
        displayBorder(31);
        System.out.println("COLLEGE GRADING SYSTEM\t\t[1]");
        System.out.println("K TO 12 GRADING SYSTEM\t\t[2]");
        System.out.println("EXIT PROGRAM\t\t\t\t[3]");
        displayBorder(11, "OPTIONS");

        System.out.print("Option: ");

        // Validation
        while (!scanner.hasNextInt()) {
            System.err.print("Invalid input, please try again: ");
            scanner.next();
        }
        int userOption = scanner.nextInt();

        switch (userOption) {
            case 1 -> isCollege = true;
            case 2 -> isCollege = false;
            default -> {
                scanner.close();
                return;
            }
        }

        displayBorder(10, "MAIN INPUT");

        System.out.print("Please enter the student name: ");
        scanner.nextLine(); // <----- This prevent from automatically skipping the input - Charles Tinoy
        String studentName = scanner.nextLine();

        System.out.print("Please enter the number of subjects: ");
        while (!scanner.hasNextInt()) {
            System.err.print("Invalid input, please try again: ");
            scanner.next();
        }
        int numberOfGrades = scanner.nextInt();
        while (numberOfGrades <= 0) {
            System.err.print("Enter a valid number of subjects: ");
            numberOfGrades = scanner.nextInt();
        }

        double total = 0.00f;

        displayBorder(10, "SUBJECT INPUT");
        for (int i = 1 ; i <= numberOfGrades; i++) {
            String suffix = getSuffix(i);

            System.out.printf("Enter the %d%s subject grade: ", i, suffix);
            while (!scanner.hasNextDouble()) {
                System.err.print("Invalid input, please try again: ");
                scanner.next();
            }
            double subjectGrade = scanner.nextDouble();
            while (subjectGrade > 100 || subjectGrade < 0) {
                System.err.print("Enter a valid grade (0-100): ");
                subjectGrade = scanner.nextDouble();
            }

            total += subjectGrade;
        }

        double averageGrade = total / numberOfGrades;
        String remarks = getRemarks(isCollege, averageGrade);

        displayBorder(20, "RESULT");
        System.out.printf("Student name:\t\t%s\n", studentName);
        System.out.printf("Average grade:\t\t%.2f\n", averageGrade);
        System.out.printf("Remarks:\t\t\t%s\n", remarks);
        displayBorder(20, "RESULT");

        scanner.close();
    }
}
