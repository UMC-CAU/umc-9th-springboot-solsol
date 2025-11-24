package com.example.umc9th.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<ValidPage, Integer> {
    
    @Override
    public void initialize(ValidPage constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        if (page == null) {
            return false;
        }
        return page >= 1;
    }
}

