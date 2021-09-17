package com.example.fizzbuzz;

public class FizzBuzz {

    public static String fizzbuzz(int i) {
        if (i <= 0) {
            return Integer.toString(i);
        }
        if (i % 15 == 0) {
            return "FizzBuzz";
        }
        if (i % 3 == 0) {
            return "Fizz";
        }
        if (i % 5 == 0) {
            return "Buzz";
        }
        return Integer.toString(i);
    }

}
