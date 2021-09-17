package com.example.validator.password;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

    private List<String> errMsgs;

    public boolean validate(String password) {
        errMsgs = new ArrayList<>();
        if (password == null || password.length() < 8) {
            errMsgs.add("Password must be at least 8 characters");
        }
        char[] characters = password.toCharArray();
        int numbers = 0;
        boolean hasCapital = false;
        boolean hasSpecial = false;
        for (char c : characters) {
            if (Character.isDigit(c)) {
                numbers++;
            }
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (!Character.isLetter(c) && !Character.isDigit(c)) {
                hasSpecial = true;
            }
        }
        if (numbers < 2) {
            errMsgs.add("The password must contain at least 2 numbers");
        }
        if (!hasCapital) {
            errMsgs.add("Password must contain at least one capital letter");
        }
        if (!hasSpecial) {
            errMsgs.add("Password must contain at least one special character");
        }
        return errMsgs.size() == 0;
    }

    public String getErrorMessage() {
        return String.join("\n", errMsgs.toArray(new String[] {}));
    }

}
