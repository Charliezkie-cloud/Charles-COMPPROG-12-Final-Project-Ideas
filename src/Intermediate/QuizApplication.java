package Intermediate;

import java.util.ArrayList;
import java.util.Collections;
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
    private static final double PASSING_PERCENTAGE = 0.75;
    private static final ArrayList<Question> QUESTIONS = new ArrayList<>();

    /*
     * Load questions
     */
    static {
        QUESTIONS.add(new Question("What does CPU stand for?",
                new String[] {"A. Central Process Unit", "B. Computer Personal Unit", "C. Central Processing Unit", "D. Control Processing Unit"},
                'C'));

        QUESTIONS.add(new Question("What does RAM stand for?",
                new String[] {"A. Random Access Memory", "B. Read Access Memory", "C. Run Access Memory", "D. Rapid Access Module"},
                'A'));

        QUESTIONS.add(new Question("Which language is used in Android development?",
                new String[] {"A. Java", "B. Python", "C. C#", "D. PHP"},
                'A'));

        QUESTIONS.add(new Question("Which company developed Java?",
                new String[] {"A. Microsoft", "B. Apple", "C. Sun Microsystems", "D. Google"},
                'C'));

        QUESTIONS.add(new Question("What does HTML stand for?",
                new String[] {"A. Hyper Trainer Marking Language", "B. Hyper Text Markup Language", "C. Hyper Text Marketing Language", "D. High Text Markup Language"},
                'B'));

        QUESTIONS.add(new Question("Which symbol is used for single-line comments in Java?",
                new String[] {"A. //", "B. /* */", "C. <!-- -->", "D. #"},
                'A'));

        QUESTIONS.add(new Question("Which data type is used to store whole numbers in Java?",
                new String[] {"A. double", "B. String", "C. int", "D. boolean"},
                'C'));

        QUESTIONS.add(new Question("What does IDE stand for?",
                new String[] {"A. Integrated Development Environment", "B. Internal Development Engine", "C. Interface Design Editor", "D. Integrated Design Engine"},
                'A'));

        QUESTIONS.add(new Question("Which device is used to input data into a computer?",
                new String[] {"A. Monitor", "B. Keyboard", "C. Printer", "D. Speaker"},
                'B'));

        QUESTIONS.add(new Question("What is the main function of an operating system?",
                new String[] {"A. Run games", "B. Manage hardware and software", "C. Edit videos", "D. Browse internet"},
                'B'));

        QUESTIONS.add(new Question("Which storage device is the fastest?",
                new String[] {"A. HDD", "B. SSD", "C. USB", "D. CD"},
                'B'));

        QUESTIONS.add(new Question("What does HTTP stand for?",
                new String[] {"A. Hyper Text Transfer Protocol", "B. High Transfer Text Protocol", "C. Hyper Transfer Text Process", "D. Hyper Tool Transfer Protocol"},
                'A'));

        QUESTIONS.add(new Question("Which programming language is known for web development?",
                new String[] {"A. JavaScript", "B. C", "C. Assembly", "D. COBOL"},
                'A'));

        QUESTIONS.add(new Question("What does GUI stand for?",
                new String[] {"A. Graphical User Interface", "B. General User Input", "C. Graphic Utility Interface", "D. General Utility Input"},
                'A'));

        QUESTIONS.add(new Question("Which part stores permanent data?",
                new String[] {"A. RAM", "B. CPU", "C. Hard Drive", "D. Cache"},
                'C'));

        QUESTIONS.add(new Question("Which of the following is an example of an operating system?",
                new String[] {"A. Microsoft Word", "B. Windows", "C. Google Chrome", "D. Photoshop"},
                'B'));

        QUESTIONS.add(new Question("What does URL stand for?",
                new String[] {"A. Uniform Resource Locator", "B. Universal Resource Link", "C. Uniform Reference Link", "D. Universal Reference Locator"},
                'A'));

        QUESTIONS.add(new Question("Which component is responsible for graphics processing?",
                new String[] {"A. CPU", "B. GPU", "C. RAM", "D. SSD"},
                'B'));

        QUESTIONS.add(new Question("Which language is primarily used for structuring web pages?",
                new String[] {"A. CSS", "B. HTML", "C. JavaScript", "D. Python"},
                'B'));

        QUESTIONS.add(new Question("What does LAN stand for?",
                new String[] {"A. Local Area Network", "B. Large Area Network", "C. Logical Access Network", "D. Linked Area Network"},
                'A'));
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

        // Shuffles the questions randomly
        Collections.shuffle(QUESTIONS);

        int score = 0;
        int questionNumber = 0;
        int skippedItems = 0;

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

                if (userAnswerInput.isEmpty()) {
                    System.out.println("Question skipped (no answer provided).");
                    skippedItems++;
                    break;
                }

                char userAnswer = userAnswerInput.toUpperCase().charAt(0);

                // Validate the answer
                if (userAnswer >= 'A' && userAnswer <= 'D') {
                    /*
                     * If the answer is correct then increment the score
                     * And display if it's correct or not.
                     */
                    if (Character.toUpperCase(userAnswer) == question.answer()) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.printf("Wrong! Correct answer: %c    \n", question.answer());
                    }

                    break;
                } else {
                    System.out.println("Invalid choice, please enter A-D only.");
                }
            }
        }

        int passingScore = (int) Math.round(PASSING_PERCENTAGE * QUESTIONS.size());
        int incorrectItems = QUESTIONS.size() - score - skippedItems;

        // Display result
        displayBorder(10, "RESULT");
        System.out.printf("Name: %s\n", name);
        System.out.printf("Score: %d/%d\n", score, questionNumber);
        System.out.printf("Incorrect items: %d\n", incorrectItems);
        System.out.printf("Skipped items: %d\n", skippedItems);
        System.out.printf("Status: %s\n",  score >= passingScore ? "PASSED" : "FAILED");

        scanner.close();
    }
}
