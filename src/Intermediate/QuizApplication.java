package Intermediate;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Question record model
 * If wala ka kahibalo ani goodluck ;D
 */
record Question(String question, String[] choices, char answer) { }

/**
 * Console Quiz Application Program by Charles Henry M. Tinoy Jr.
 */
public class QuizApplication {
    private static final ArrayList<Question> QUESTIONS = new ArrayList<>();

    static {
        QUESTIONS.add(new Question("What is the capital of France?",
                new String[] {"A. Berlin", "B. Paris", "C. Rome", "D. Madrid"},
                'B'));
        QUESTIONS.add(new Question("Which language is used in Android development?",
                new String[] {"A. Java", "B. Python", "C. C#", "D. PHP"},
                'A'));
        QUESTIONS.add(new Question("What does CPU stand for?",
                new String[] {"A. Central Process Unit", "B. Computer Personal Unit", "C. Central Processing Unit", "D. Control Processing Unit"},
                'C'));
    }

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayBorder(34);
        System.out.println("\t\tQUIZ APPLICATION");
        displayBorder(34);

        // Get the user name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        while (name.trim().isEmpty()) {
            System.out.print("Name can't be empty, please try again: ");
            name = scanner.nextLine();
        }

        int score = 0;
        int questionNumber = 0;

        for (Question question : QUESTIONS) {
            questionNumber++;

            // Display the questions
            displayBorder(10, String.format("QUESTION %d.", questionNumber));
            System.out.println(question.question() + "\n");
            for (int i = 0; i < question.choices().length; i++)
                System.out.println(question.choices()[i]);

            while (true) {
                // Get the user answer
                System.out.print("\nYour answer: ");
                String userAnswerInput = scanner.nextLine();

                if (userAnswerInput.isEmpty())
                    continue;

                char userAnswer = userAnswerInput.toUpperCase().charAt(0);

                // Validate the answer
                if (userAnswer >= 'A' && userAnswer <= 'D') {
                    // If the answer is correct then increment the score
                    if (Character.toUpperCase(userAnswer) == question.answer())
                        score++;
                    break;
                } else {
                    System.out.println("Invalid choice, please enter A-D only.");
                }
            }
        }

        // Display result
        displayBorder(10, "RESULT");
        System.out.printf("Name: %s\n", name);
        System.out.printf("Score: %d/%d\n", score, questionNumber);

        scanner.close();
    }
}
