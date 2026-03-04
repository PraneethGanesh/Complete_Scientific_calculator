package com.example.scientificcalculator.repository;
import com.example.scientificcalculator.entity.Calculator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculator, Long> {

    Page<Calculator> findByUserId(Long userId, Pageable pageable);
}