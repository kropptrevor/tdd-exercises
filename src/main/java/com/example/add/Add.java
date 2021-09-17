package com.example.add;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Add {

    public static int add(String numbers) {
        if ("".equals(numbers)) {
            return 0;
        }
        String delimiter = ",|\n";
        String customDelimiter = null;
        boolean hasCustomDelimiter = numbers.matches("//[^\n]+\n.*");
        if (hasCustomDelimiter) {
            int newlineIndex = numbers.indexOf('\n');
            customDelimiter = numbers.substring(2, newlineIndex);
            delimiter = Pattern.quote(customDelimiter);
            numbers = numbers.substring(newlineIndex + 1);
        }
        if (numbers.substring(numbers.length() - 1).matches(delimiter)) {
            throw new RuntimeException("trailing delimiter");
        }
        List<String> errorMessages = new ArrayList<>();
        if (customDelimiter != null) {
            errorMessages.addAll(checkCustomDelimiterParsingErrors(numbers, customDelimiter));
        }
        String[] values = numbers.split(delimiter + "|[^-\\d]");
        List<Integer> negatives = Stream.of(values).filter((val) -> val != null && !val.equals(""))
                .map(Integer::parseInt).filter((num) -> num < 0).collect(Collectors.toList());
        if (negatives.size() > 0) {
            List<String> negativeStrings = negatives.stream().map((num) -> num.toString()).collect(Collectors.toList());
            errorMessages.add(0,
                    "Negative number(s) not allowed: " + String.join(", ", negativeStrings.toArray(new String[] {})));
        }
        if (errorMessages.size() > 0) {
            String[] messages = errorMessages.toArray(new String[] {});
            throw new RuntimeException(String.join("\n", messages));
        }
        Integer result = Stream.of(values).map(Integer::parseInt).filter((num) -> num <= 1000).reduce(0,
                (a, b) -> a + b);
        return result;
    }

    private static List<String> checkCustomDelimiterParsingErrors(String numbers, String customDelimiter) {
        List<String> errorMessages = new ArrayList<>();
        boolean isPreviousDelimiter = true;
        for (int i = 0; i < numbers.length(); i++) {
            boolean startsWithDelimiter = numbers.substring(i).startsWith(customDelimiter);
            String token = numbers.substring(i, i + 1);
            if (startsWithDelimiter && isPreviousDelimiter) {
                errorMessages
                        .add(String.format("'%s' expected but '%s' found at position %d.", customDelimiter, token, i));
            } else if (startsWithDelimiter) {
                isPreviousDelimiter = true;
                i += customDelimiter.length() - 1;
                continue;
            }
            if (token.matches("\\d")) {
                isPreviousDelimiter = false;
                continue;
            }
            errorMessages.add(String.format("'%s' expected but '%s' found at position %d.", customDelimiter, token, i));
        }
        return errorMessages;
    }

}
