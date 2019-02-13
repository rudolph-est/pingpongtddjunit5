package com.bbd.pingpong;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class testFeatures {
    
    @ParameterizedTest
    @CsvSource({ "hello, 1", "world, 2", "'happy, testing', 3" })
    void testWithCsvSource(String first, int second) {
        System.out.println("Parameterized test with (String) " + first
                + " and (int) " + second);

        assertNotNull(first);
        assertNotEquals(0, second);
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    throw new IllegalArgumentException("a message");
                });
        System.out.println("Exception message was "+exception.getMessage() + " of type "+exception.getClass().getName());
        assertEquals("a message", exception.getMessage());
    }
}