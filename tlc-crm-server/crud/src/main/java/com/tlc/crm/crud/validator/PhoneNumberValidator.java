package com.tlc.crm.crud.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validation for phoneNumber
 *
 * @author NandiniRakAS
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return (value.matches("[6-9][0-9]{9}"));
    }
}
