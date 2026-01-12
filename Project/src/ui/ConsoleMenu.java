package ui;

import service.ExpenseService;

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
}
