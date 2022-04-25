package com.tlc.crm.crud.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for employee name
 *
 * @author NandiniRakAS
 */
public class NameValidator implements ConstraintValidator<Name, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value.matches("[a-zA-z]{1,}");
    }
}