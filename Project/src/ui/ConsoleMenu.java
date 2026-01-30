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

        System.out.println("Do you want to add some expenses examples to manipulate the app a little? [YES - NO]");
        if (scanner.next().equalsIgnoreCase("yes")){
            preFillList();
            System.out.println("Pre-filling 24 example tasks into the List!");
        } else {
            System.out.println("Okok! List of Tasks starting empty");
        }

        while (running){
            System.out.println("\n\n--- Expense Tracker / Budget Manager ---");
            System.out.println("1 - Add an Expense\n2 - Remove an Expense\n3 - Listing Expenses\n4 - Analysing " +
                    "Expenses\n0 - To exit the APP");
            int option = readOption();

            switch (option) {
                case 1 -> AddExpense();
                case 2 -> RemoveExpense();
                case 3 -> ListingExpenseMenu();
                case 4 -> AnalysingExpenseMenu();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }



    public void preFillList() {
        // Transport examples:
        service.addExpense(new Expense(new BigDecimal("120.0"), "interstate bus ticket", Category.TRANSPORT,
                PaymentMethod.CREDIT, LocalDate.of(2024, 2, 17)));
        service.addExpense(new Expense(new BigDecimal("100.0"), "Gas for the week", Category.TRANSPORT,
                PaymentMethod.CREDIT, LocalDate.of(2024, 4, 13)));
        service.addExpense(new Expense(new BigDecimal("20.0"), "weekly bus transportation to work", Category.TRANSPORT,
                PaymentMethod.PIX, LocalDate.of(2024, 1, 3)));
        service.addExpense(new Expense(new BigDecimal("20.0"), "weekly bus transportation to work", Category.TRANSPORT,
                PaymentMethod.CASH, LocalDate.of(2024, 7, 9)));
        service.addExpense(new Expense(new BigDecimal("20.0"), "weekly bus transportation to work", Category.TRANSPORT,
                PaymentMethod.DEBIT, LocalDate.of(2024, 9, 7)));
        service.addExpense(new Expense(new BigDecimal("22.5"), "weekly bus transportation to work", Category.TRANSPORT,
                PaymentMethod.PIX, LocalDate.of(2025, 1, 7)));

        //Food examples:
        service.addExpense(new Expense(new BigDecimal("250.0"), "Diner with family at Fogo de Chão", Category.FOOD,
                PaymentMethod.CREDIT, LocalDate.of(2024, 2, 14)));
        service.addExpense(new Expense(new BigDecimal("124.8"), "weekly groceries", Category.FOOD,
                PaymentMethod.PIX, LocalDate.of(2024, 3, 3)));
        service.addExpense(new Expense(new BigDecimal("144.6"), "weekly groceries", Category.FOOD,
                PaymentMethod.CREDIT, LocalDate.of(2024, 4, 20)));
        service.addExpense(new Expense(new BigDecimal("104.2"), "weekly groceries", Category.FOOD,
                PaymentMethod.DEBIT, LocalDate.of(2024, 8, 15)));
        service.addExpense(new Expense(new BigDecimal("154.0"), "weekly groceries", Category.FOOD,
                PaymentMethod.PIX, LocalDate.of(2025, 1, 2)));
        service.addExpense(new Expense(new BigDecimal("33.8"), "uber eats midnight snack", Category.FOOD,
                PaymentMethod.PIX, LocalDate.of(2024, 11, 12)));

        // Housing examples:
        service.addExpense(new Expense(new BigDecimal("220.5"), "House rent", Category.HOUSING,
                PaymentMethod.CREDIT, LocalDate.of(2024, 1, 3)));
        service.addExpense(new Expense(new BigDecimal("220.5"), "House rent", Category.HOUSING,
                PaymentMethod.CREDIT, LocalDate.of(2024, 3, 3)));
        service.addExpense(new Expense(new BigDecimal("252.5"), "House rent", Category.HOUSING,
                PaymentMethod.CREDIT, LocalDate.of(2025, 1, 3)));
        service.addExpense(new Expense(new BigDecimal("80.5"), "Fixing the kitchen sink", Category.HOUSING,
                PaymentMethod.CREDIT, LocalDate.of(2024, 2, 26)));

        // Other examples:
        service.addExpense(new Expense(new BigDecimal("475.7"), "car crash", Category.EMERGENCY,
                PaymentMethod.CREDIT, LocalDate.of(2024, 7, 7)));
        service.addExpense(new Expense(new BigDecimal("55.3"), "Kid's medicine", Category.HEALTH,
                PaymentMethod.CREDIT, LocalDate.of(2024, 9, 17)));
        service.addExpense(new Expense(new BigDecimal("85.0"), "Gym membership", Category.HEALTH,
                PaymentMethod.CREDIT, LocalDate.of(2024, 1, 14)));
        service.addExpense(new Expense(new BigDecimal("255.0"), "acquired a Java Course", Category.EDUCATION,
                PaymentMethod.CREDIT, LocalDate.of(2024, 3, 12)));
        service.addExpense(new Expense(new BigDecimal("125.7"), "Date with a friend", Category.ENTERTAINMENT,
                PaymentMethod.CREDIT, LocalDate.of(2024, 11, 24)));
        service.addExpense(new Expense(new BigDecimal("45.4"), "Brought new Levi's trouser", Category.SHOPPING,
                PaymentMethod.CREDIT, LocalDate.of(2024, 4, 5)));
        service.addExpense(new Expense(new BigDecimal("475.7"), "Brought my first america's Treasury bill",
                Category.INVESTMENTS,PaymentMethod.DEBIT, LocalDate.of(2025, 8, 11)));
        service.addExpense(new Expense(new BigDecimal("475.7"), "first step into building my money emercency reserve",
                Category.INVESTMENTS, PaymentMethod.PIX, LocalDate.of(2025, 8, 11)));
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
        System.out.println("\nHow much was the Expense:");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        System.out.println("\nDescription of the Expense:");
        String description = scanner.nextLine().toLowerCase();


        // Category attribute part:
        System.out.println("\nWhat's the category of this Expense?");
        Category category = pickCategory();


        // Payment Method attribute part:
        System.out.println("\nHow did you pay?");
        PaymentMethod paymentMethod = pickPaymentMethod();



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
            System.out.println("The possibilities are:\nFOOD,\nTRANSPORT,\nHOUSING,\nEDUCATION," +
                    "\nHEALTH,\nINVESTMENTS,\nSHOPPING,\nENTERTAINMENT,\nEMERGENCY");
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


    private PaymentMethod pickPaymentMethod(){
        PaymentMethod paymentMethod = null;

        while (paymentMethod == null) {
            System.out.println("The possibilities are:\nCREDIT,\nDEBIT,\nCASH,\nPIX");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            try {
                paymentMethod = PaymentMethod.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Try again.\n");
            }
        }
        return paymentMethod;
    }


    private void RemoveExpense(){
        System.out.println("\n\nWhat is the ID of the expense that you want to remove?");
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
        boolean hasYear = false;
        int year = 0;

        while (running){
            if (!hasYear){
                validationOfYear();
                hasYear = true;
            }

            System.out.println("\nDo you want to list your expenses by what:\n1- List by Category\n2 - List By time\n3 " +
                    "- List by amount range\n4 - List by payment method\n5 - List all expenses of a year\n6 - Change " +
                    "the year of the analysis\n0 - Back to main menu");
            int option = readOption();

            switch (option) {
                case 1 -> ListByCategoryMenu(year);
                case 2 -> ListByTimeMenu(year);
                case 3 -> ListByAmountMenu(year);
                case 4 -> ListByPaymentMethodMenu(year);
                case 5 -> ListAllExpensesMenu(year);
                case 6 -> hasYear = false;
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }


    private int validationOfYear(){
        boolean validYear = false;
        int year = 0;

        while (!validYear){
            System.out.println("\nDo you want to list the expenses of which year?");
            year = readOption();

            if (year > 2000 && year <= 2100) {
                validYear = true;
            } else {
                System.out.println("\nWe're working with expenses at 21st century. Please put a " +
                        "valid value!");
            }
        }

        return year;
    }


    //Listing sub-menus methods:
    public void ListByCategoryMenu(int year){
        System.out.println("\nDo you want to list your expenses by which Category?");
        Category category = pickCategory();

         List<Expense> expenseList = service.ListByCategory(year, category);
         expenseList.forEach(System.out::println);
    }

    public void ListByTimeMenu(int year){
        System.out.println("\nDo you want to list the expenses of:\n1 - the whole year\n2 - an semester\n3 - An" +
                " specific month");
        int dataChoice = readOption();

        switch (dataChoice){
            case 1:
                System.out.println("\nAll expenses of " + year + ":");
                service.ListByTime(1, year, 0).forEach(System.out::println);
                break;
            case 2:
                System.out.println("\nWhich semester of " + year + " you want to choose?  (1  OR  2)");
                int semester = readOption();

                System.out.println("\nAll expenses of semester " + semester + " of " + year + ":");
                service.ListByTime(2, year, semester).forEach(System.out::println);
                break;
            case 3:
                System.out.println("\nWhich month of " + year + " you want to choose?  (1  TO  12)");
                int month = readOption();

                System.out.println("\nAll expenses of month " + month + " of " + year + ":");
                service.ListByTime(3, year, month).forEach(System.out::println);
                break;
            default:
                throw new IllegalArgumentException("Invalid option: " + dataChoice);
        }
    }

    public void ListByAmountMenu(int year){
        System.out.println("\nWhat's the lower value of the amount range you want to list:");
        BigDecimal lowerValue = new BigDecimal(scanner.nextLine());
        System.out.println("What's the upper value of the amount range you want to list:");
        BigDecimal upperValue = new BigDecimal(scanner.nextLine());

        System.out.println("\nAll expenses between R$" + lowerValue + " and R$" + upperValue + " are:");
        service.ListByAmount(year, lowerValue, upperValue).forEach(System.out::println);
    }

    public void ListByPaymentMethodMenu(int year){
        System.out.println("\nDo you want a list of expenses based on which Payment method:");
        PaymentMethod paymentMethod = pickPaymentMethod();

        System.out.println("\nThis are all the expenses that with " + paymentMethod + " in " + year + " :");
        service.ListByPaymentMethod(year, paymentMethod).forEach(System.out::println);
    }

    public void ListAllExpensesMenu(int year){
        System.out.println("\nThis are all the expenses of " + year + " :");
        service.ListAllExpenses(year).forEach(System.out::println);
    }


    private void AnalysingExpenseMenu(){
        boolean running = true;
        boolean hasYear = false;
        int year = 0;

        while (running){
            if (!hasYear){
                validationOfYear();
                hasYear = true;
            }

            System.out.println("\nDo you want to look into your expenses based on:\n1- A parameter that spent the " +
                    "most/the least\n2 - The somatory that some parameter\n3 - Average amount of expenses\n4 - Change " +
                    "the year of the analysis\n0 - Back to main menu");
            int option = readOption();

            switch (option) {
                case 1 -> AnalyseByMostAndLeastMenu(year);
                case 2 -> AnalyseBySomatoryMenu(year);
                case 3 -> AnalyseByAverageMenu(year);
                case 4 -> hasYear = false;
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }
}
