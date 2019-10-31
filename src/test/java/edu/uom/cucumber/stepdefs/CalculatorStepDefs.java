package edu.uom.cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.uom.Calculator;

import static org.junit.Assert.assertEquals;

public class CalculatorStepDefs {

    Calculator calculator;
    int result;

    @Given("I am using my calculator")
    public void i_am_using_my_calculator(){
        calculator = new Calculator();
    }

    @When("I add two positive numbers")
    public void i_add_two_positive_numbers(){
        result = calculator.add(5, 2);
    }

    @Then("The result should be the sum of the two numbers")
    public void the_result_should_be_the_sum_of_the_two_numbers(){
        assertEquals(7, result);
    }

    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2){
        result = calculator.add(int1, int2);
    }

    @Then("The result should be {int}")
    public void the_result_should_be(Integer expectedResult) {
        assertEquals(expectedResult.intValue(), result);
    }

    @When("I subtract two positive numbers")
    public void i_subtract_two_positive_numbers(){
        result = calculator.subtract(5, 2);
    }

    @Then("The result should be the subtraction of the number")
    public void the_result_should_be_the_subtraction_of_the_number(){ assertEquals(3, result);}

    @When("I subtract {int} from {int}")
    public void i_subtract_from(Integer int1, Integer int2) { result = calculator.subtract(int1, int2);}
}