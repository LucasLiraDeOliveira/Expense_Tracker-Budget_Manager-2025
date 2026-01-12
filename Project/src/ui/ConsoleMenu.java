package ui;

import service.ExpenseService;

public class ConsoleMenu {
    private ExpenseService service;

    public ConsoleMenu(ExpenseService service) {
        this.service = service;
    }
}
