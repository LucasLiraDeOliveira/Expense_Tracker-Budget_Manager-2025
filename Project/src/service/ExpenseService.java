package service;

import model.Expense;
import repository.ExpenseRepository;

public class ExpenseService {
    ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }



    public void addExpense(Expense expense){
        repository.add(expense);
    }
}
