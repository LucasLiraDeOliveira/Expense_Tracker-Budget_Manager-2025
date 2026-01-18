package ui;

import model.Category;
import model.Expense;
import model.PaymentMethod;
import service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
                case 3 -> ListingExpenseMenu();
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
        System.out.println("What's the category of this Expense?");
        Category category = pickCategory();


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


    private Category pickCategory(){
        Category category = null;

        while (category == null) {
            System.out.print("The possibilities are:\nFOOD,\nTRANSPORT,\nHOUSING,\nEDUCATION,\nHEALTH,\nINVESTMENTS," +
                    "\nSHOPPING,\nENTERTAINMENT,\nEMERGENCY");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            try {
                category = Category.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Try again.\n");
            }
        }
        return category;
    }


    private void RemoveExpense(){
        System.out.println("What is the ID of the expense that you want to remove?");
        long idNumber = scanner.nextLong();

        boolean removed = service.removeExpenseById(idNumber);

        if (removed){
            System.out.println("Expense removed.");
        } else {
            System.out.println("No expense found with that ID.");
        }
    }


    private void ListingExpenseMenu(){
        boolean running = true;

        while (running){
            System.out.println("Do you want to list your expenses by what:\n1- List by Category\n2 - List By time\n3 " +
                    "- List by amount range\n4 - List by payment method\nBack to main menu");
            int option = readOption();

            switch (option) {
                case 1 -> ListByCategoryMenu();
                case 2 -> ListByTimeMenu();
                case 3 -> ListByAmountMenu();
                case 4 -> ListByPaymentMethodMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }


    //Listing sub-menus methods:
    public void ListByCategoryMenu(){
        System.out.println("Do you want to list your expenses by which Category?");
        Category category = pickCategory();

         List<Expense> expenseList = service.ListByCategory(category);
         expenseList.forEach(System.out::println);
    }

    public void ListByTimeMenu(){
        boolean validAnswer = true;
        while (validAnswer){
            System.out.println("Do you want to see the expenses of which year?");
            int year = readOption();

            if (year > 2000 && year <= 2100){
                System.out.println("Do you want to list the expenses of:\n1 - the whole year\n2 - an semester\n3 - An" +
                        " specific month");
                int dataChoice = readOption();

                switch (dataChoice){
                    case 1:
                        System.out.println("\nAll expenses of " + year + ":");
                        service.ListByTime(1, year, 0).forEach(System.out::println);
                        validAnswer = false;
                        break;
                    case 2:
                        System.out.println("Which semester of " + year + " you want to choose?  (1  OR  2)");
                        int semester = readOption();

                        System.out.println("\nAll expenses of semester " + semester + " of " + year + ":");
                        service.ListByTime(2, year, semester).forEach(System.out::println);
                        validAnswer = false;
                        break;
                    case 3:
                        System.out.println("Which month of " + year + " you want to choose?  (1  TO  12)");
                        int month = readOption();

                        System.out.println("\nAll expenses of month " + month + " of " + year + ":");
                        service.ListByTime(3, year, month).forEach(System.out::println);
                        validAnswer = false;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid option: " + dataChoice);
                }
            } else {
                System.out.println("We're working with expenses at 21st century. Please put a valid value!");
            }
        }
    }

    public void ListByAmountMenu(){
        boolean validAnswer = true;

        while (validAnswer){
            System.out.println("Do you want to see the expenses of which year?");
            int year = readOption();

            if (year > 2000 && year <= 2100){
                System.out.println("What's the lower value of the amount range you want to list:");
                BigDecimal lowerValue = new BigDecimal(scanner.nextLine());
                System.out.println("What's the upper value of the amount range you want to list:");
                BigDecimal upperValue = new BigDecimal(scanner.nextLine());

                System.out.println("All expenses between R$" + lowerValue + " and R$" + upperValue + " are:");
                service.ListByAmount(year, lowerValue, upperValue).forEach(System.out::println);
                validAnswer = false;
            } else {
                System.out.println("We're working with expenses at 21st century. Please put a valid value!");
            }
        }
    }
}
