package com.ardic.android.iotignite.greenhouse.utils;

import android.text.TextUtils;

import com.ardic.android.iotignite.greenhouse.Constants;

/**
 * Created by codemania on 7/19/17.
 */

public class ValidationUtils {

    private static ValidationResult result = ValidationResult.OK;


    private ValidationUtils() {

    }

    private static void checkMail(String email) {
        if (TextUtils.isEmpty(email) || !email.matches(Constants.PATTERN_EMAIL)) {
            result = ValidationResult.INCORRECT_MAIL;
        }
    }

    private static void checkName(String fName, String lName) {
        if (TextUtils.isEmpty(fName) || TextUtils.isEmpty(lName)) {
            result = ValidationResult.INVALID_NAME;
        }
    }

    private static void checkPasswordEquality(String password, String confirmPassword) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || !confirmPassword.equals(password)) {
            result = ValidationResult.PASSWORD_MISMATCH;
        }
    }

    private static void checkPasswordCredentials(String password) {
        if (password == null || password.length() < 6) {
            result = ValidationResult.INVALID_PASSWORD;
        }
    }

    private static void checkTermOfUse(boolean isChecked) {
        if (!isChecked) {
            result = ValidationResult.REJECTED_TERM_OF_USE;
        }
    }

    public static ValidationResult checkSignUpCredentials(String email, String fName, String lName,
                                                          String password, String confirmPassword,
                                                          boolean isChecked) {

        result = ValidationResult.OK;
        checkTermOfUse(isChecked);
        checkPasswordCredentials(password);
        checkPasswordEquality(password, confirmPassword);
        checkMail(email);
        checkName(fName, lName);
        return result;
    }

    public static ValidationResult checkLoginCredentials(String email, String password) {

        result = ValidationResult.OK;
        checkMail(email);
        checkPasswordCredentials(password);
        return result;

    }


}
