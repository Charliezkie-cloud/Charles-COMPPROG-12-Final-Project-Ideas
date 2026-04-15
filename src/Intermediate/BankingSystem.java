package Intermediate;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class BankingSystem {
    // Currency formatting
    private static final NumberFormat US_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);

    // Account states
    private static String ACCOUNT_NAME = "";
    private static BigDecimal CURRENT_BALANCE = BigDecimal.ZERO;

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
     * Displays your current balance
     */
    private static void displayCurrentBalance() {
        displayBorder(23);
        System.out.printf("Account Name:\t\t%s\n", ACCOUNT_NAME);
        System.out.printf("Current Balance:\t%s\n", US_FORMAT.format(CURRENT_BALANCE));
    }

    /**
     * Performs the deposit operation
     * @param scanner The scanner buffer
     */
    private static void performDeposit(Scanner scanner) {
        displayBorder(15, "DEPOSIT");

        // Get the amount to deposit
        System.out.print("Enter the amount to deposit: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input, please try again: ");
            scanner.next();
        }
        double amountToDeposit = scanner.nextDouble();

        // Validation for negative input
        while (amountToDeposit <= 0) {
            System.out.print("Amount must be greater than 0. Please try again: ");

            while (!scanner.hasNextDouble()) {
                System.out.print("Invalid input, please try again: ");
                scanner.next();
            }
            amountToDeposit = scanner.nextDouble();
        }

        CURRENT_BALANCE = CURRENT_BALANCE.add(BigDecimal.valueOf(amountToDeposit));

        System.out.printf("You have successfully deposited %s\n", US_FORMAT.format(amountToDeposit));
     }

    /**
     * Performs the withdrawal operation
     * @param scanner The scanner buffer
     */
     private static void performWithdraw(Scanner scanner) {
         displayBorder(15, "WITHDRAW");

         while (true) {
             // Get the amount to withdraw
             System.out.print("Enter the amount to withdraw: ");
             while (!scanner.hasNextDouble()) {
                 System.out.print("Invalid input, please try again: ");
                 scanner.next();
             }
             double amountToWithdraw = scanner.nextDouble();

             /**
              * Check if the new current balance is not negative
              * If it's not negative then perform the substraction of the current balance
              */
             BigDecimal convertedWithdrawAmount = BigDecimal.valueOf(amountToWithdraw);
             if (CURRENT_BALANCE.compareTo(convertedWithdrawAmount) >= 0) {
                 System.out.printf("You have successfully withdrawn %s from your account.\n", US_FORMAT.format(amountToWithdraw));
                 System.out.printf("Current balance:\t%s\n", US_FORMAT.format(CURRENT_BALANCE));

                 CURRENT_BALANCE = CURRENT_BALANCE.subtract(convertedWithdrawAmount);
                 System.out.printf("New balance:\t\t%s\n", US_FORMAT.format(CURRENT_BALANCE));
                 return;
             }

             System.out.println("Insufficient balance, please try again.");
         }
     }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayBorder(47);
        System.out.println("\t\tWelcome to Charlie Bank System");
        displayBorder(47);

        // Get the account name
        System.out.println("Before we proceed with your bank account,\nyou must enter your account name.");
        System.out.print("Enter your account name: ");
        ACCOUNT_NAME = scanner.nextLine();
        while (ACCOUNT_NAME.trim().isEmpty()) {
            System.out.print("Account name cannot be empty, please try again: ");
            ACCOUNT_NAME = scanner.nextLine();
        }

        while (true) {
            displayBorder(7, "OPTIONS");
            System.out.println("Check Balance\t\t[1]");
            System.out.println("Deposit\t\t\t\t[2]");
            System.out.println("Withdraw\t\t\t[3]");
            System.out.println("Exit\t\t\t\t[4]");
            displayBorder(7, "OPTIONS");

            // Get the user option input
            System.out.print("Option: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input, please try again: ");
                scanner.next();
            }
            int option = scanner.nextInt();

            // Option statements
            if (option == 1) {
                displayCurrentBalance();
            } else if (option == 2) {
                performDeposit(scanner);
            } else if (option == 3) {
                performWithdraw(scanner);
            } else if (option == 4) {
                break;
            } else {
                System.out.print("Invalid option, please try again: ");
                scanner.next();
            }
        }

        scanner.close();
    }
}
