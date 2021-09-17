package com.example.test.stringAdd;

import static org.junit.Assert.assertEquals;

import com.example.add.Add;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringAddTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void blankInput() {
        assertEquals(0, Add.add(""));
    }

    @Test
    public void singleInput() {
        assertEquals(1, Add.add("1"));
    }

    @Test
    public void multipleInput() {
        assertEquals(3, Add.add("1,2"));
    }

    @Test
    public void unknownMultipleInput() {
        assertEquals(63, Add.add("1,2,10,20,30"));
    }

    @Test
    public void newlineDelimiter() {
        assertEquals(3, Add.add("1\n2"));
    }

    @Test
    public void mixedDelimiters() {
        assertEquals(6, Add.add("1,2\n3"));
    }

    @Test(expected = RuntimeException.class)
    public void trailingCommaDelimiter() {
        Add.add("1,2,");
    }

    @Test(expected = RuntimeException.class)
    public void trailingNewlineDelimiter() {
        Add.add("1,2\n");
    }

    @Test
    public void customDelimiter() {
        assertEquals(4, Add.add("//;\n1;3"));
        assertEquals(6, Add.add("//|\n1|2|3"));
    }

    @Test
    public void customerLongDelimiter() {
        assertEquals(7, Add.add("//sep\n2sep5"));
    }

    @Test(expected = RuntimeException.class)
    public void trailingCustomDelimiter() {
        assertEquals(4, Add.add("//+\n1+3+"));
    }

    @Test
    public void invalidMixedDelimiter() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("'|' expected but ',' found at position 3.");
        Add.add("//|\n1|2,3");
    }

    @Test
    public void negativeNumber() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Negative number(s) not allowed: -2");
        Add.add("1,-2");
    }

    @Test
    public void negativeNumbers() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Negative number(s) not allowed: -4, -9");
        Add.add("2,-4,-9");
    }

    @Test
    public void multipleErrors() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Negative number(s) not allowed: -3\n'|' expected but ',' found at position 3.");
        Add.add("//|\n1|2,-3");
    }

    @Test
    public void over1000() {
        assertEquals(2, Add.add("2,1001"));
    }

}
