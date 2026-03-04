package com.example.scientificcalculator.entity;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String user_name;
    @Column(nullable = false,unique = true)
    private String userEmail;
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Calculator> calculator;
    public Long getUser_id() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_email() {
        return userEmail;
    }

    public void setUser_id(Long user_id) {
        this.id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_email(String user_email) {
        this.userEmail = user_email;
    }

    public List<Calculator> getCalculator() {
        return calculator;
    }

    public void setCalculator(List<Calculator> calculator) {
        this.calculator = calculator;
    }
}
