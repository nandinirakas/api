package com.tlc.crm.crud.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * To setup error codes for exceptions
 *
 * @author NandiniRakAS
 */
public enum EmployeeErrorCodes implements ErrorCodeProvider {

    DATA_NOT_FOUND(0X34),
    DATA_INVALID(0X44);

    private final int code;

    EmployeeErrorCodes(final int code) {
        this.code = EmployeeErrorCodeGroup.GROUP.getConvertedCode(code);
    }

    /**
     * Implements ErrorCodeGroup to create custom error codes
     */
    private static class EmployeeErrorCodeGroup implements ErrorCodeGroup {

        public static final ErrorCodeGroup GROUP = new EmployeeErrorCodeGroup();

        @Override
        public int getPrefix() {
            return 0X10_0_0000;
        }
    }

    @Override
    public int getCode() {
        return code;
    }
}

