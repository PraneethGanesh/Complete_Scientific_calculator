package com.example.scientificcalculator.dto;

import java.time.LocalDateTime;

public class CalculationResponseDTO {
    private Long id;
    private Double operand1;
    private Double operand2;
    private String operation;
    private Double result;
    private LocalDateTime timestamp;

    public CalculationResponseDTO() {}

    public CalculationResponseDTO(Long id, Double operand1, Double operand2,
                                  String operation, Double result,
                                  LocalDateTime timestamp) {
        this.id = id;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public Double getOperand1() {
        return operand1;
    }

    public Double getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public Double getResult() {
        return result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOperand1(Double operand1) {
        this.operand1 = operand1;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setOperand2(Double operand2) {
        this.operand2 = operand2;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
