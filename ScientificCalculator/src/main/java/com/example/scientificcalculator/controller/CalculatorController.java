package com.example.scientificcalculator.controller;

import com.example.scientificcalculator.dto.CalculationRequestDTO;
import com.example.scientificcalculator.dto.CalculationResponseDTO;
import com.example.scientificcalculator.service.CalculatorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculations")
@CrossOrigin(origins = "http://localhost:3000")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping
    public ResponseEntity<CalculationResponseDTO> calculate(
            @RequestBody CalculationRequestDTO requestDTO) {

        CalculationResponseDTO response = calculatorService.calculate(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<Page<CalculationResponseDTO>> history(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("timestamp").descending());
        Page<CalculationResponseDTO> history = calculatorService.getHistory(userId, pageable);
        return ResponseEntity.ok(history);
    }
}