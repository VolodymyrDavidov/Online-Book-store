package com.example.demo.service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PatternValidator implements ConstraintValidator<PasswordValidator, String> {
    private static final String PATTERN = "^[A-Za-z\\d]{6,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && password.matches(PATTERN);
    }
}
