package com.lombardi.restaurant.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailConstraint {
    String message() default "Email already exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
