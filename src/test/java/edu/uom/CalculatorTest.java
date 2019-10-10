package edu.uom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CalculatorTest {

    Calculator calculator;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    @After
    public void teardown(){
        calculator = null;
    }

    @Test
    public void testAddition(){
        int result = calculator.add(1,2);
        assertEquals(3 , result);
    }

    @Test
    public void testSubtract(){
        int result = calculator.subtract(2, 1);
        assertEquals(1, result);
    }

    @Test
    public void testMultiply(){
        int result = calculator.multiply(2 , 2);
        assertEquals(4, result);
    }

    @Test
    public void testDivide(){
        int result = calculator.divide(4, 2);
        assertEquals(2, result);
    }

    @Test
    public void testByZero(){
        int result = calculator.divide(4, 0);
        assertEquals(-999, result);
    }
}