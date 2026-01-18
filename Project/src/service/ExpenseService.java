package service;

import model.Category;
import model.Expense;
import repository.ExpenseRepository;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
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


    public List<Expense> ListByCategory(Category category){
        return repository.findByCategory(category);
    }


    public List<Expense> ListByTime(int category, int year, int value){
        switch (category){
            case 1:
                return repository.findByYear(year).stream()
                        .sorted(Comparator.comparing(Expense::getDate))
                        .toList();
            case 2:
                if (value == 1){
                    return repository.findBySemester(year, 1, 6).stream()
                            .sorted(Comparator.comparing(Expense::getDate))
                            .toList();
                }
                if (value == 2){
                    return repository.findBySemester(year, 7, 12).stream()
                            .sorted(Comparator.comparing(Expense::getDate))
                            .toList();
                }
                throw new IllegalArgumentException("Invalid semester value: " + value);
            case 3:
                return repository.findByMonth(year, value).stream()
                        .sorted(Comparator.comparing(Expense::getDate))
                        .toList();
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
    }
}
