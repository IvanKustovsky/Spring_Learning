package com.example.validations;

import com.example.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;
    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(value).
                getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).
                getPropertyValue(fieldMatch);
        if(fieldValue != null){
            if(fieldValue.toString().startsWith("$2a")){ // "$2a" indicates that this is hash value, so we don't need to
                // compare this field with confirmPwd field. This validation happens again after successful registration,
                // because of JPA tries to store record into DB.
                return true;
            } else {
                return fieldValue.equals(fieldMatchValue);
            }
        } else {
            return fieldMatchValue == null;
        }
    }
}
