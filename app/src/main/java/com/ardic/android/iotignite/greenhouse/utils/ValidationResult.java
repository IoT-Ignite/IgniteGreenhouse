package com.ardic.android.iotignite.greenhouse.utils;

/**
 * Created by codemania on 7/21/17.
 */

public enum ValidationResult {

    OK("Everything is OK"),
    INCORRECT_MAIL("Enter a valid email address!"),
    INVALID_NAME("Enter a valid name!"),
    INVALID_PASSWORD("Passwords must be min 6 characters."),
    REJECTED_TERM_OF_USE("To continue, you must accept the term of use"),
    PASSWORD_MISMATCH("Password and confirm password do not match.");

    private String errorMsg;

    ValidationResult(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }
}
