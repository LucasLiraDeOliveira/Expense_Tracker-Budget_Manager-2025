package service;

import model.Expense;
import repository.ExpenseRepository;

import java.util.Optional;

public class ExpenseService {
    ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }



    public void addExpense(Expense expense){
        repository.add(expense);
    }


    public boolean removeExpenseById(Long idNumber){
        Optional<Expense> expense = repository.findById(idNumber);

        if (expense.isPresent()){
            repository.remove(expense.get());
            return true;
        }

        return  false;
    }
}
