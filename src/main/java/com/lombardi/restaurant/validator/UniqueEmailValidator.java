package com.lombardi.restaurant.validator;

import com.lombardi.restaurant.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements
        ConstraintValidator<UniqueEmailConstraint, String> {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    public UniqueEmailValidator() {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return customerServiceImpl.isEmailAvailable(email);
    }

    @Override
    public void initialize(UniqueEmailConstraint constraintAnnotation) {

    }
}
