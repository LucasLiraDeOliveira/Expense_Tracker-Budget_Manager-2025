package service;

import model.Category;
import model.Expense;
import model.PaymentMethod;
import repository.ExpenseRepository;

import java.math.BigDecimal;
import java.util.*;

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
        List<Expense> monthlyExpenses = repository.findByMonth(year, month);

        if (monthlyExpenses.isEmpty()){
            return BigDecimal.ZERO;
        }else {
            return monthlyExpenses.stream()
                    .map(Expense::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
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

    public int mostLeastMonth(int year, int mostLeastChoice){
        BigDecimal[] mostLeast = new BigDecimal[12];
        Arrays.fill(mostLeast, BigDecimal.ZERO);
        BigDecimal auxMonth = BigDecimal.ZERO;
        int chosenMonth = 0;
        boolean firstValue = true;

        for (int i = 0; i < mostLeast.length; i++) {
            mostLeast[i] = totalAmountOfMonth(year, i+1);

            if (mostLeastChoice == 1){
                if (auxMonth.compareTo(mostLeast[i]) < 0 && mostLeast[i].compareTo(BigDecimal.ZERO) > 0){
                    auxMonth = mostLeast[i];
                    chosenMonth = i+1;
                }
            } else if (mostLeastChoice == 2){
                if (firstValue){
                    if (mostLeast[i].compareTo(BigDecimal.ZERO) > 0){
                        auxMonth = mostLeast[i];
                        chosenMonth = i+1;
                        firstValue = false;
                    }
                } else {
                    if (auxMonth.compareTo(mostLeast[i]) > 0 && mostLeast[i].compareTo(BigDecimal.ZERO) > 0){
                        auxMonth = mostLeast[i];
                        chosenMonth = i + 1;
                    }
                }
            }
        }

        return chosenMonth;
    }
}
