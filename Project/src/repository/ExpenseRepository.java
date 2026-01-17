package repository;

import model.Category;
import model.Expense;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ExpenseRepository {
    Set<Expense> expenseSet = new HashSet<>();

    public void add(Expense expense){
        expenseSet.add(expense);
    }

    public void remove(Expense expense){
        expenseSet.remove(expense);
    }

    public Optional<Expense> findById(long idNumber){
        return expenseSet.stream()
                .filter(e -> e.getId() == idNumber)
                .findFirst();
    }

    public List<Expense> findAll(){
        return List.copyOf(expenseSet);
    }

    public List<Expense> findByCategory(Category category){
        return expenseSet.stream()
                .filter(e -> e.getCategory() == category)
                .toList();
    }

    public List<Expense> findByYear(int year){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .toList();
    }
}
