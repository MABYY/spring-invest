package com.investmentsportal.portal.entities.dtos;

import com.investmentsportal.portal.validation.Lowercase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<Lowercase,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("VALUE " + value);
        if(value == null){
            return true;
        }
        return value.equals(value.toLowerCase());
    }
}

