package com.example.scientificcalculator.service;

import com.example.scientificcalculator.dto.CalculationRequestDTO;
import com.example.scientificcalculator.dto.CalculationResponseDTO;
import com.example.scientificcalculator.entity.Calculator;
import com.example.scientificcalculator.entity.User;
import com.example.scientificcalculator.repository.CalculationRepository;
import com.example.scientificcalculator.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CalculatorService {

    private final CalculationRepository calculationRepository;
    private final UserRepository userRepository;

    public CalculatorService(CalculationRepository calculationRepository,
                             UserRepository userRepository) {
        this.calculationRepository = calculationRepository;
        this.userRepository = userRepository;
    }

    public CalculationResponseDTO calculate(CalculationRequestDTO dto) {

        Double result;

        Double a = dto.getOperand1();
        Double b = dto.getOperand2();

        switch (dto.getOperation().toUpperCase()) {
            case "ADD" -> result = a + b;
            case "SUB" -> result = a - b;
            case "MUL" -> result = a * b;
            case "DIV" -> {
                if (b == null || b == 0.0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                result = a / b;
            }
            case "SIN" -> result = Math.sin(a);
            case "COS" -> result = Math.cos(a);
            case "TAN" -> result = Math.tan(a);
            case "SQRT" -> {
                if (a < 0) throw new ArithmeticException("Cannot sqrt negative number");
                result = Math.sqrt(a);
            }
            default -> throw new IllegalArgumentException("Unknown operation: " + dto.getOperation());
        }

        var calculation = new Calculator();
        calculation.setOperand1(a);
        calculation.setOperand2(b);
        calculation.setOperation(dto.getOperation());
        calculation.setResult(result);
        calculation.setTimestamp(LocalDateTime.now());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            calculation.setUser(user);
        }

        var saved = calculationRepository.save(calculation);

        return new CalculationResponseDTO(
                saved.getCalulator_id(),
                saved.getOperand1(),
                saved.getOperand2(),
                saved.getOperation(),
                saved.getResult(),
                saved.getTimestamp()
        );
    }

    public Page<CalculationResponseDTO> getHistory(Long userId, Pageable pageable) {
        Page<Calculator> page = calculationRepository.findByUserId(userId, pageable);
        return page.map(c -> new CalculationResponseDTO(
                c.getCalulator_id(), c.getOperand1(), c.getOperand2(),
                c.getOperation(), c.getResult(), c.getTimestamp()
        ));
    }
}