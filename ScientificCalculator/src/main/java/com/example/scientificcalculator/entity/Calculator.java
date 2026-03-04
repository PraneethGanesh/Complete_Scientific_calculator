package com.example.scientificcalculator.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calculations")
public class Calculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double operand1;
    private Double operand2;

    @Column(nullable = false)
    private String operation;

    private Double result;

    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Long getCalulator_id() {
        return id;
    }

    public double getResult() {
        return result;
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setCalulator_id(Long calulator_id) {
        this.id = calulator_id;
    }

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
