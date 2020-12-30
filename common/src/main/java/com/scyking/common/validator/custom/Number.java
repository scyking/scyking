package com.scyking.common.validator.custom;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NumberConstraintValidator.class)
public @interface Number {
}
