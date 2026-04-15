package Beginner;

import java.util.Scanner;

/**
 * Console Simple Calculator Program by Charles Henry M. Tinoy Jr.
 */
public class SimpleCalculator {
    /**
     * Displays the border (E.g. ==========)
     * @param length The length of the border
     */
    private static void displayBorder(int length) {
        for (int i = 0; i < length; i++)
            System.out.print("=");
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayBorder(35);
            System.out.println("\t\tSIMPLE CALCULATOR");
            displayBorder(35);

            // Get the first operand
            System.out.print("Enter the first operand: ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input, please try again: ");
                scanner.next();
            }
            double firstOperand = scanner.nextDouble();

            // Get the second operand
            System.out.print("Enter the second operand: ");
            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input, please try again: ");
                scanner.next();
            }
            double secondOperand = scanner.nextDouble();

            while (true) {
                // Get the operator
                System.out.print("Choose an operator: ");
                char operator = scanner.next().charAt(0);

                /*
                 * Check if the operator is divide and the second operand is equal to 0
                 * If the condition is true then display the error
                 */
                if (operator == '/' && secondOperand == 0) {
                    System.out.println("Error: You can't divide a number by 0, please try again.");
                    continue;
                }

                /*
                 * Check if the operator is valid
                 * If the condition is true then display the error and show the operator input again
                 */
                if ("+-*/".indexOf(operator) == -1) {
                    System.out.println("Error: Please choose a valid operator, please try again.");
                    continue;
                }

                // Perform the calculation based on the given operator
                double result = switch (operator) {
                    case '+' -> firstOperand + secondOperand;
                    case '-' -> firstOperand - secondOperand;
                    case '*' -> firstOperand * secondOperand;
                    default -> firstOperand / secondOperand;
                };

                System.out.printf("%.2f %s %.2f is equal to %.2f\n",
                        firstOperand, operator, secondOperand, result);

                break;
            }

            /*
             * Ask the user if he/she will still want to continue the program
             * If the condition is false then exit the program
             */
            System.out.print("\nDo you want to continue? (y/n): ");
            char choice = scanner.next().toLowerCase().charAt(0);

            if (choice != 'y')
                break;
        }

        scanner.close();
    }
}

