package com.example.scientificcalculator.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class CalculationRequestDTO {
    @NotNull
    private double operand1;

    private double operand2;

    @NotNull
    private String operation;

    private Long userId;

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public Long getUserId() {
        return userId;
    }
}
