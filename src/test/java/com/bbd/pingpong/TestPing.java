package com.bbd.pingpong;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPing {
    @Test
    public void testNumber1() {
        assertEquals(1,RomanConverter.convert("I"));
    }

    @Test
    public void testNumber2() {
        assertEquals(2,RomanConverter.convert("II"));
    }

    @Test
    public void testNumber5() { assertEquals( 5, RomanConverter.convert( "V"));}

    @ParameterizedTest
    @CsvSource({ "i, 1", "ii, 2", "v, 5" })
    public void whenStringIsLowerCaseTheConversionShouldStillWork(String s, int i){
        assertEquals(i,RomanConverter.convert(s));
    }

    @ParameterizedTest
    @CsvSource({ "x, 10", "X, 10"})
    public void testNumber10UpperAndLowerCase(String s, int i){
        assertEquals(i, RomanConverter.convert(s));
    }

    @Test
    public void whenInputContainsInvalidCharactersExpectAnException(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> RomanConverter.convert("abcde"));
        assertEquals("Invalid input", e.getMessage());
    }

    @Test
    public void testNumber6() { assertEquals( 6, RomanConverter.convert("VI"));}

    @Test
    public void testSmallerBeforeLargerNumberDoesSubtraction(){
        assertEquals(4,RomanConverter.convert("iv"));
    }

    @Test
    public void whenInputContainsOneInvalidCharacterExpectAnException(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> RomanConverter.convert("VLN"));
        assertEquals("Invalid input", e.getMessage());
    }

    @Test
    public void testNumber1100() { assertEquals( 1050, RomanConverter.convert("ML"));}

    @Test
    public void testNumber3000() { assertEquals( 3000, RomanConverter.convert("MMM"));}


    @Test
    public void whenInvalidDuplicateInputIsReceivedExpectAnException() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> RomanConverter.convert("XiXiX"));
        assertEquals("Invalid input duplicate subtraction", e.getMessage());
    }
}