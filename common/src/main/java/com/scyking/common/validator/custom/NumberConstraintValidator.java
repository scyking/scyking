package com.scyking.common.validator.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author scyking
 * @description
 **/
public class NumberConstraintValidator implements ConstraintValidator<Number, Object> {

    @Override
    public void initialize(Number constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        // 不校验空值
        if (o == null) {
            return true;
        }
        if (o instanceof java.lang.Number) {
            return true;
        }
        return false;
    }
}
