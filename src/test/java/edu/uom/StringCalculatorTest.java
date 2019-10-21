package edu.uom;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    StringCalculator calc;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup(){
        calc = new StringCalculator();
    }

    @After
    public void teardown(){
        calc = null;
    }

    @Test
    public void addMethodEmptyString() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("");
        assertEquals(0 , result);
    }

    @Test
    public void addMethodEmptyFirstVariable() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum(" ,2");
        assertEquals(2 , result);
    }

    @Test
    public void addMethodSingleDigit() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("2");
        assertEquals(2, result);
    }

    @Test
    public void addMethodEmptySecondVariable() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("3, ");
        assertEquals(3, result);
    }

    @Test
    public void addMethodTwoVariables() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("2,3");
        assertEquals(5, result);
    }

    @Test
    public void addMethodThreeVariables() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("2,3,7");
        assertEquals(12, result);
    }

    @Test
    public void addMethodFourVariables() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("2,3,7,5");
        assertEquals(17, result);
    }

    @Test
    public void addMethodFourAndEmptyVariables() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("8,1, ,7, ,3");
        assertEquals(19, result);
    }

    @Test
    public void addMethodFiveVariables() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("2,3,7,5,3");
        assertEquals(20, result);
    }

    @Test
    public void addMethodTwoDigitValues() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("10, 12, 15");
        assertEquals(37, result);
    }

    @Test
    public void addMethodThreeDigitValues() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("100 , 10, 12, 15 \n 200");
        assertEquals(337, result);
    }

    @Test
    public void addMethodWithNewLine() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("2\n3");
        assertEquals(5, result);
    }

    @Test
    public void addMethodWithNewLineAndThree() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("1\n2,3");
        assertEquals(6, result);
    }

    @Test
    public void addMethodThreeWithNewLine() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("1\n2\n3");
        assertEquals(6, result);
    }

    @Test
    public void addMethodThreeWithNewLineAndWhitespace() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum(" 6\n9 \n3");
        assertEquals(18, result);
    }

    @Test
    public void addMethodFiveWithNewLine() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("1\n2\n3,8 , 2");
        assertEquals(16, result);
    }

    @Test
    public void testAdditionalDelimiters() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("//;\n1;2;3");
        assertEquals(6, result);
    }

    @Test
    public void testAdditionalDelimitersV2() throws StringCalculator.NegativeNumberException {
        int result = calc.addSum("//$\n1$2$3");
        assertEquals(6, result);
    }

    @Test
    public void rejectNegativeNumbers_throwException() throws StringCalculator.NegativeNumberException{
        int result = calc.addSum("-2,3");
        assertThrows(StringCalculator.NegativeNumberException, () -> result, "Expect something");
    }

    @Test
    public void rejectOneNegativeNumber_throwException() throws StringCalculator.NegativeNumberException{
        try {
            calc.addSum("1,2,-3,4,5,-6,7,8,-9");
        }
        catch (StringCalculator.NegativeNumberException ne) {
            //Verify
            assertEquals("Negatives not allowed: [-3, -6, -9]", ne.getMessage());
        }
    }
}