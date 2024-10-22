package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testy jednostkowe dla klasy Calculator.
 */
public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        // Test dodawania dwóch liczb
        assertEquals(5, calculator.add(2, 3), "2 + 3 powinno być równe 5");
        assertEquals(-1, calculator.add(-2, 1), "-2 + 1 powinno być równe -1");
    }

    @Test
    public void testMultiply() {
        // Test mnożenia dwóch liczb
        assertEquals(6, calculator.multiply(2, 3), "2 * 3 powinno być równe 6");
        assertEquals(0, calculator.multiply(0, 5), "0 * 5 powinno być równe 0");
        assertEquals(-6, calculator.multiply(-2, 3), "-2 * 3 powinno być równe -6");
    }
}
