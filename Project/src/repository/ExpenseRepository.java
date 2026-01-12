package repository;

import model.Category;
import model.Expense;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpenseRepository {
    Set<Expense> expenseSet = new HashSet<>();

    public void add(Expense expense){
        expenseSet.add(expense);
    }

    public void remove(Expense expense){
        expenseSet.remove(expense);
    }

    public List<Expense> findAll(){
        return List.copyOf(expenseSet);
    }
}
