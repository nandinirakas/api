package com.tlc.crm.crud.validator;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.crud.model.Employee;
import com.tlc.crm.crud.status.EmployeeErrorCodes;
import com.tlc.validator.ModelValidator;
import com.tlc.validator.ValidatorAccess;

/**
 * Validation class which validates employee details
 *
 * @author NandiniRakAS
 */
public class EmployeeValidation {

    private static final ModelValidator MODEL_VALIDATOR = ValidatorAccess.get();

    /**
     * Validates the inputs
     *
     * @param employee
     * @param groups
     */
    public static void validate(final Employee employee, final Class... groups) {
        if (!MODEL_VALIDATOR.isValid(employee, groups)) {
            throw ErrorCode.get(EmployeeErrorCodes.DATA_INVALID);
        }
    }
}
