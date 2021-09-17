package com.example.test.bank.account;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.example.bank.account.Account;
import com.example.bank.account.Account.InvalidTransactionAmountException;
import com.example.bank.account.Account.OverdraftException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final String lineSeparator = System.getProperty("line.separator");

    private Account account;

    @Before
    public void setupAccount() {
        account = new Account();
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void noOutput() {
        String output = outContent.toString();
        assertEquals("", output);
    }

    @Test
    public void printHeader() {
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT  | BALANCE" }, lines);
    }

    @Test
    public void depositNothing() {
        account.deposit(0);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT  | BALANCE", "09/15/2021 | 0.00    | 0.00   ", }, lines);
    }

    @Test(expected = InvalidTransactionAmountException.class)
    public void depositNegative() {
        account.deposit(-1);
    }

    @Test
    public void depositSingle() {
        account.deposit(123);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT  | BALANCE", "09/15/2021 | 123.00  | 123.00 ", }, lines);
    }

    @Test
    public void depositMultiple() {
        account.deposit(120);
        account.deposit(334);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT  | BALANCE", "09/15/2021 | 334.00  | 454.00 ",
                "09/15/2021 | 120.00  | 120.00 ", }, lines);
    }

    @Test
    public void depositALot() {
        account.deposit(1000);
        account.deposit(100);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT  | BALANCE", "09/15/2021 | 100.00  | 1100.00",
                "09/15/2021 | 1000.00 | 1000.00", }, lines);
    }

    @Test
    public void depositALotMore() {
        account.deposit(10000);
        account.deposit(55555);
        account.deposit(55554);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT   | BALANCE  ", "09/15/2021 | 55554.00 | 121109.00",
                "09/15/2021 | 55555.00 | 65555.00 ", "09/15/2021 | 10000.00 | 10000.00 " }, lines);
    }

    @Test
    public void withdrawNothing() {
        account.withdraw(0);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT  | BALANCE", "09/15/2021 | 0.00    | 0.00   ", }, lines);
    }

    @Test
    public void withdrawEqual() {
        account.deposit(50);
        account.withdraw(50);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT  | BALANCE", "09/15/2021 | -50.00  | 0.00   ",
                "09/15/2021 | 50.00   | 50.00  ", }, lines);
    }

    @Test
    public void withdrawMultiple() {
        account.deposit(10000);
        account.withdraw(500);
        account.withdraw(99);
        account.printStatement();
        String output = outContent.toString();
        String[] lines = output.split(lineSeparator);
        assertArrayEquals(new String[] { "DATE       | AMOUNT   | BALANCE ", "09/15/2021 | -99.00   | 9401.00 ",
                "09/15/2021 | -500.00  | 9500.00 ", "09/15/2021 | 10000.00 | 10000.00", }, lines);
    }

    @Test(expected = OverdraftException.class)
    public void withdrawOverdraw() {
        account.withdraw(50);
    }

    @Test(expected = InvalidTransactionAmountException.class)
    public void withdrawNegative() {
        account.withdraw(-1);
    }

}
