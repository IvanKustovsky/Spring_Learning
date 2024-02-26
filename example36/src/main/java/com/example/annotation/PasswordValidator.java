package com.example.annotation;

import com.example.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented  // Optional
@Constraint(validatedBy = PasswordStrengthValidator.class) //mention where is written business logic related to this custom validation
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidator {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default "Please choose a strong password!";

}
