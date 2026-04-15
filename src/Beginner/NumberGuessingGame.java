package Beginner;

import java.util.Random;
import java.util.Scanner;

/**
 * Console Number Guessing Game by Charles Henry M. Tinoy Jr.
 */
public class NumberGuessingGame {
    private static final int MAX_ATTEMPT = 7;

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int answer = random.nextInt(100) + 1;
        int attempts = 0;

        displayBorder(43);
        System.out.println("\t\tNUMBER GUESSING GAME");
        displayBorder(43);
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("Can you guess it?");

        while (true) {
            attempts++;

            // Displays the remaining attempts
            displayBorder(10, String.format("REMAINING ATTEMPTS: %d", MAX_ATTEMPT - attempts + 1));

            // User guess input
            System.out.print("Enter your guess: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input! Please enter a valid number: ");
                scanner.next();
            }
            int userGuess = scanner.nextInt();

            // Conditionals for guessing
            if (userGuess > answer)
                System.out.println("Lower! Try again.");
            else if (userGuess < answer)
                System.out.println("Higher! Try again.");
            else {
                System.out.println("\nCongratulations! You guessed the number!");
                System.out.println("Attempts used: " + attempts);
                break;
            }

            // Check if its game over
            if (attempts >= MAX_ATTEMPT) {
                System.out.println("\nGame Over! You've used all your attempts.");
                System.out.println("The correct number was: " + answer);
                break;
            }
        }

        scanner.close();
    }
}
