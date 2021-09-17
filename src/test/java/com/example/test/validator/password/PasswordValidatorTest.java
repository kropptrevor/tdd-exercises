package com.example.test.validator.password;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.validator.password.PasswordValidator;

import org.junit.Before;
import org.junit.Test;

public class PasswordValidatorTest {

    @Before
    public void setup() {
        pv = new PasswordValidator();
    }

    private PasswordValidator pv;

    @Test
    public void emptyInput() {
        boolean res = pv.validate("A#12");
        String errorMessage = pv.getErrorMessage();
        assertFalse(res);
        assertEquals("Password must be at least 8 characters", errorMessage);
    }

    @Test
    public void shortInput() {
        boolean res = pv.validate("$Hort12");
        String errorMessage = pv.getErrorMessage();
        assertFalse(res);
        assertEquals("Password must be at least 8 characters", errorMessage);
    }

    @Test
    public void minimumMinusOne() {
        boolean res = pv.validate("A#23456");
        String errorMessage = pv.getErrorMessage();
        assertFalse(res);
        assertEquals("Password must be at least 8 characters", errorMessage);
    }

    @Test
    public void atLeastTwoNumbers() {
        boolean res = pv.validate("Abcdefgh!j");
        String errorMessage = pv.getErrorMessage();
        assertFalse(res);
        assertEquals("The password must contain at least 2 numbers", errorMessage);
    }

    @Test
    public void multipleErrors() {
        boolean res = pv.validate("Ap@ss");
        assertFalse(res);
        String errorMessage = pv.getErrorMessage();
        assertEquals("Password must be at least 8 characters\nThe password must contain at least 2 numbers",
                errorMessage);
    }

    @Test
    public void capitalLetter() {
        boolean res = pv.validate("@password123");
        assertFalse(res);
        String errorMessage = pv.getErrorMessage();
        assertEquals("Password must contain at least one capital letter", errorMessage);
    }

    @Test
    public void specialCharacter() {
        boolean res = pv.validate("Apassword123");
        assertFalse(res);
        String errorMessage = pv.getErrorMessage();
        assertEquals("Password must contain at least one special character", errorMessage);
    }

    @Test
    public void validPassword() {
        boolean res = pv.validate("Apassword#123");
        assertTrue(res);
        String errorMessage = pv.getErrorMessage();
        assertEquals("", errorMessage);
    }

    @Test
    public void reuseValidator() {
        pv.validate("Apassword#123");
        boolean res = pv.validate("Apassword#123");
        String errorMessage = pv.getErrorMessage();
        assertTrue(res);
        assertEquals("", errorMessage);
        res = pv.validate("");
        errorMessage = pv.getErrorMessage();
        assertFalse(res);
        assertEquals(
                "Password must be at least 8 characters\nThe password must contain at least 2 numbers\nPassword must contain at least one capital letter\nPassword must contain at least one special character",
                errorMessage);
    }

}
