package edu.uom;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    StringCalculator calc;

    @Before
    public void setup(){
        calc = new StringCalculator();
    }

    @After
    public void teardown(){
        calc = null;
    }

    @Test
    public void addMethodEmptyString(){
        int result = calc.addSum("");
        assertEquals(0 , result);
    }

    @Test
    public void addMethodEmptyFirstVariable(){
        int result = calc.addSum(" ,2");
        assertEquals(2 , result);
    }

    @Test
    public void addMethodSingleDigit(){
        int result = calc.addSum("2");
        assertEquals(2, result);
    }

    @Test
    public void addMethodEmptySecondVariable(){
        int result = calc.addSum("3, ");
        assertEquals(3, result);
    }

    @Test
    public void addMethodTwoVariables(){
        int result = calc.addSum("2,3");
        assertEquals(5, result);
    }

    @Test
    public void addMethodThreeVariables(){
        int result = calc.addSum("2,3,7");
        assertEquals(12, result);
    }

    @Test
    public void addMethodFourVariables(){
        int result = calc.addSum("2,3,7,5");
        assertEquals(17, result);
    }

    @Test
    public void addMethodFourAndEmptyVariables(){
        int result = calc.addSum("8,1, ,7, ,3");
        assertEquals(19, result);
    }

    @Test
    public void addMethodFiveVariables(){
        int result = calc.addSum("2,3,7,5,3");
        assertEquals(20, result);
    }

    @Test
    public void addMethodTwoDigitValues(){
        int result = calc.addSum("10, 12, 15");
        assertEquals(37, result);
    }

    @Test
    public void addMethodWithNewLine(){
        int result = calc.addSum("2\n3");
        assertEquals(5, result);
    }

    @Test
    public void addMethodWithNewLineAndThree(){
        int result = calc.addSum("1\n2,3");
        assertEquals(6, result);
    }

    @Test
    public void addMethodThreeWithNewLine(){
        int result = calc.addSum("1\n2\n3");
        assertEquals(6, result);
    }

    @Test
    public void addMethodThreeWithNewLineAndWhitespace(){
        int result = calc.addSum(" 6\n9 \n3");
        assertEquals(18, result);
    }

    @Test
    public void addMethodFiveWithNewLine(){
        int result = calc.addSum("1\n2\n3,8 , 2");
        assertEquals(16, result);
    }
}
