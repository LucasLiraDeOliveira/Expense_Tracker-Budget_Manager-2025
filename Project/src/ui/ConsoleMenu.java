package ui;

import model.Category;
import model.Expense;
import model.PaymentMethod;
import service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleMenu {
    private ExpenseService service;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(ExpenseService service) {
        this.service = service;
    }


    public void start(){
        boolean running = true;


        while (running){
            System.out.println("\n\n--- Expense Tracker / Budget Manager ---");
            System.out.println("1 - Add an Expense\n2 - Remove an Expense\n3 - Listing Expenses\n4 - Analysing " +
                    "Expenses\n0 - To exit the APP");
            int option = readOption();


            switch (option) {
                case 1 -> AddExpense();
                case 2 -> RemoveExpense();
                case 3 -> ListingExpense();
                case 4 -> AnalysingExpense();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }


    private int readOption() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }


    private void AddExpense(){
        System.out.println("How much was the Expense:");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        System.out.println("Description of the Expense:");
        String description = scanner.nextLine().toLowerCase();


        // Category attribute part:
        scanner.nextLine(); // clear leftover newline
        Category category = null;

        while (category == null) {
            System.out.print("What's the category of this Expense? \nThe possibilities are:\nFOOD,\nTRANSPORT,\n" +
                    "HOUSING,\nEDUCATION,\nHEALTH,\nINVESTMENTS,\nSHOPPING,\nENTERTAINMENT,\nEMERGENCY");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            try {
                category = Category.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Try again.\n");
            }
        }


        // Payment Method attribute part:
        scanner.nextLine(); // clear leftover newline
        PaymentMethod paymentMethod = null;

        while (paymentMethod == null) {
            System.out.print("How did you pay? \nThe possibilities are:\nCREDIT,\nDEBIT,\nCASH,\nPIX");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            try {
                paymentMethod = PaymentMethod.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Try again.\n");
            }
        }


        System.out.println("\nWhen the Expense was made?");
        System.out.println("Year (YYYY format): ");
        int year = scanner.nextInt();

        System.out.println("Month (MM format): ");
        int month = scanner.nextInt();

        System.out.println("Day (DD format): ");
        int day = scanner.nextInt();

        LocalDate dueDate = LocalDate.of(year, month, day);

        Expense newExpense = new Expense(
                amount,
                description,
                category,
                paymentMethod,
                dueDate
        );

        service.addExpense(newExpense);
        System.out.println("Expense added!");
    }
}
