package com.tlc.crm.crud.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * Gives validation for phoneNumber
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER,ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
@Documented
public @interface PhoneNumber {
    
    String message() default "must be a valid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}