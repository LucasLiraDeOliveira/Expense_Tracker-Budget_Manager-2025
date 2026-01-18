package repository;

import model.Category;
import model.Expense;
import model.PaymentMethod;

import java.math.BigDecimal;
import java.util.*;

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

    public List<Expense> findByCategory(int year, Category category){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .filter(e -> e.getCategory() == category)
                .toList();
    }

    public List<Expense> findByYear(int year){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .toList();
    }

    public List<Expense> findBySemester(int year, int semesterBegin, int semesterEnd){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .filter(e -> e.getDate().getMonthValue() >= semesterBegin && e.getDate().getMonthValue() <= semesterEnd)
                .toList();
    }

    public List<Expense> findByMonth(int year, int month){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .filter(e -> e.getDate().getMonthValue() == month)
                .toList();
    }

    public List<Expense> findByAmount(int year, BigDecimal lowerValue, BigDecimal upperValue){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .filter(e -> e.getAmount().compareTo(lowerValue) >= 0 && e.getAmount().compareTo(upperValue) <= 0)
                .toList();
    }

    public List<Expense> findByPaymentMethod(int year, PaymentMethod paymentMethod){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .filter(e -> e.getPaymentMethod() == paymentMethod)
                .toList();
    }

    public List<Expense> findAll(int year){
        return expenseSet.stream()
                .filter(e -> e.getDate().getYear() == year)
                .toList();
    }
}
