package service;

import repository.ExpenseRepository;

public class ExpenseService {
    ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }
}
