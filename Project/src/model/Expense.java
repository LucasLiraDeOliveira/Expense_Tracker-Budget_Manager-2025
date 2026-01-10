package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private static long counter = 1;

    private Long id;
    private BigDecimal amount;
    private String description;
    private Category category;
    private PaymentMethod paymentMethod;
    private LocalDate date;


    public Expense(BigDecimal amount, String description, Category category, PaymentMethod paymentMethod,
                   LocalDate date) {
        this.id = counter++;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.paymentMethod = paymentMethod;
        this.date = date;
    }



    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", paymentMethod=" + paymentMethod +
                ", date=" + date +
                '}';
    }
}
