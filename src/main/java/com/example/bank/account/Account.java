package com.example.bank.account;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final List<Integer> transactions = new ArrayList<>();
    private final List<Integer> runningTotal = new ArrayList<>();

    private final String numberSuffix = ".00";

    private int balance = 0;

    public void deposit(int amount) {
        if (amount < 0) {
            throw new InvalidTransactionAmountException();
        }
        balance += amount;
        transactions.add(amount);
        runningTotal.add(balance);
    }

    public void withdraw(int amount) {
        if (amount < 0) {
            throw new InvalidTransactionAmountException();
        }
        if (balance - amount < 0) {
            throw new OverdraftException();
        }
        balance -= amount;
        transactions.add(-1 * amount);
        runningTotal.add(balance);
    }

    private String generatePrintString(int amount, int padding) {
        String amountString = Integer.toString(amount) + numberSuffix;
        int amountPadding = padding - amountString.length();
        String amountPaddingString = " ".repeat(amountPadding);
        return amountString + amountPaddingString;
    }

    private String generatePrintString(String message, int padding) {
        int amountPadding = padding - message.length();
        String amountPaddingString = " ".repeat(amountPadding);
        return message + amountPaddingString;
    }

    private int getMaxLengthFromList(List<Integer> values) {
        return values.stream().map((n) -> Integer.toString(n)).map(String::length).max(Integer::compare).get()
                + numberSuffix.length();
    }

    private int getPadding(int maxLength) {
        int padding = Math.max(7, maxLength);
        return padding;
    }

    public void printStatement() {
        if (transactions.size() == 0) {
            System.out.println("DATE       | AMOUNT  | BALANCE");
            return;
        }
        List<String> messages = new ArrayList<>();
        int amountPadding = getPadding(getMaxLengthFromList(transactions));
        int totalPadding = getPadding(getMaxLengthFromList(runningTotal));
        for (int i = 0; i < transactions.size(); i++) {
            int amount = transactions.get(i);
            int total = runningTotal.get(i);
            String message = "09/15/2021 | " + generatePrintString(amount, amountPadding) + " | "
                    + generatePrintString(total, totalPadding);
            messages.add(message);
        }
        System.out.println("DATE       | " + generatePrintString("AMOUNT", amountPadding) + " | "
                + generatePrintString("BALANCE", totalPadding) + "");
        for (int i = messages.size() - 1; i >= 0; i--) {
            System.out.println(messages.get(i));
        }
    }

    public class InvalidTransactionAmountException extends RuntimeException {
    }

    public class OverdraftException extends RuntimeException {
    }
}
