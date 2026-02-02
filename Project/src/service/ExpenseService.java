package service;

import model.Category;
import model.Expense;
import model.PaymentMethod;
import repository.ExpenseRepository;

import java.math.BigDecimal;
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



    // Listing methods:
    public List<Expense> ListByCategory(int year, Category category){
        return repository.findByCategory(year, category).stream()
                .sorted(Comparator.comparing(Expense::getCategory))
                .toList();
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

    public  List<Expense> ListByAmount(int year, BigDecimal lowerValue, BigDecimal upperValue){
        return repository.findByAmount(year, lowerValue, upperValue).stream()
                .sorted(Comparator.comparing(Expense::getAmount))
                .toList();
    }

    public List<Expense> ListByPaymentMethod(int year, PaymentMethod paymentMethod){
        return repository.findByPaymentMethod(year, paymentMethod).stream()
                .sorted(Comparator.comparing(Expense::getPaymentMethod))
                .toList();
    }

    public List<Expense> ListAllExpenses(int year){
        return repository.findAll(year).stream()
                .sorted(Comparator.comparing(Expense::getPaymentMethod))
                .toList();
    }



    // Analysing methods:
    public BigDecimal totalAmountOfMonth(int year, int month){
        return repository.findByMonth(year, month).stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalAmountOfYear(int year){
        return repository.findByYear(year).stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalAmountOfCategory(int year, Category category){
        return repository.findByCategory(year, category).stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal totalAmountOfPaymentMethod(int year, PaymentMethod paymentMethod){
        return repository.findByPaymentMethod(year, paymentMethod).stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
