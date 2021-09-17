package com.example.test.fizzbuzz;

import static org.junit.Assert.assertEquals;

import com.example.fizzbuzz.FizzBuzz;

import org.junit.Test;

public class FizzBuzzTest {

    @Test
    public void baseCase() {
        assertEquals("0", FizzBuzz.fizzbuzz(0));
    }

    @Test
    public void one() {
        assertEquals("1", FizzBuzz.fizzbuzz(1));
    }

    @Test
    public void two() {
        assertEquals("2", FizzBuzz.fizzbuzz(2));
    }

    @Test
    public void three() {
        assertEquals("Fizz", FizzBuzz.fizzbuzz(3));
    }

    @Test
    public void four() {
        assertEquals("4", FizzBuzz.fizzbuzz(4));
    }

    @Test
    public void five() {
        assertEquals("Buzz", FizzBuzz.fizzbuzz(5));
    }

    @Test
    public void six() {
        assertEquals("Fizz", FizzBuzz.fizzbuzz(6));
    }

    @Test
    public void ten() {
        assertEquals("Buzz", FizzBuzz.fizzbuzz(10));
    }

    @Test
    public void fizzBuzz() {
        assertEquals("FizzBuzz", FizzBuzz.fizzbuzz(15));
        assertEquals("FizzBuzz", FizzBuzz.fizzbuzz(30));
    }

}
