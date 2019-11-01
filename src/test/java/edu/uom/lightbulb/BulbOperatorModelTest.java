package edu.uom.lightbulb;

import edu.uom.lightbulb.enums.BulbOperatorStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BulbOperatorModelTest implements FsmModel {
    //Linking the SUT
    private BulbOperator systemUnderTest = new BulbOperator();

    //State Variables
    private BulbOperatorStates modelBulb = BulbOperatorStates.OFF;
    private boolean lowBrightness = false, mediumBrightness = false, highBrightness = false;

    //Method implementations
    public BulbOperatorStates getState() {
        return modelBulb;
    }

    public void reset(final boolean var1) {
        if (var1) {
            systemUnderTest = new BulbOperator();
        }
        modelBulb = BulbOperatorStates.OFF;
        lowBrightness = false;
        mediumBrightness = false;
        highBrightness = false;
    }

    //Transitions incl. guards
    public boolean turnOnBulbGuard() {
        return getState().equals(BulbOperatorStates.OFF);
    }
    public @Action void turnOnBulb() {
        //Updating SUT
        systemUnderTest.turnOn();

        //Updating model
        modelBulb = BulbOperatorStates.MEDIUM_BRIGHTNESS;
        mediumBrightness = true;

        //Checking correspondence between model and SUT.
        assertEquals("The SUT's bulb does not match the model's bulb after turning on the bulb", mediumBrightness, systemUnderTest.isMediumBrightness());
        assertEquals("SUT has multiple brightness values at once.", lowBrightness || highBrightness, systemUnderTest.onlyOneStateTrue());
    }

    public boolean turnOffBulbGuard() {
        return !getState().equals(BulbOperatorStates.OFF);
    }
    public @Action void turnOffBulb() {
        //Updating SUT
        systemUnderTest.turnOff();

        //Updating model
        modelBulb = BulbOperatorStates.OFF;
        lowBrightness = false;
        highBrightness = false;
        mediumBrightness = false;

        //Checking correspondence between model and SUT.
        assertEquals("The SUT's bulb does not match the model's bulb after turning off the bulb", lowBrightness || mediumBrightness || highBrightness, systemUnderTest.isTurnedOn());
    }

    public boolean decreaseBulbBrightnessGuard() {
        return getState().equals(BulbOperatorStates.HIGH_BRIGHTNESS) || getState().equals(BulbOperatorStates.MEDIUM_BRIGHTNESS);
    }
    public @Action void decreaseBulbBrightness() {
        //Updating SUT
        systemUnderTest.decreaseBrightness();

        //Updating model
        if (getState().equals(BulbOperatorStates.HIGH_BRIGHTNESS)) {
            modelBulb = BulbOperatorStates.MEDIUM_BRIGHTNESS;
            highBrightness = false;
            mediumBrightness = true;

            //Checking correspondence between model and SUT.
            assertEquals("The SUT's bulb does not match the model's bulb after decreasing the bulb's brightness", mediumBrightness, systemUnderTest.isMediumBrightness());
            assertEquals("SUT has multiple brightness values at once.", lowBrightness || highBrightness, systemUnderTest.onlyOneStateTrue());
        } else {
            modelBulb = BulbOperatorStates.LOW_BRIGHTNESS;
            mediumBrightness = false;
            lowBrightness = true;

            //Checking correspondence between model and SUT.
            assertEquals("The SUT's bulb does not match the model's bulb after decreasing the bulb's brightness", lowBrightness, systemUnderTest.isLowBrightness());
            assertEquals("SUT has multiple brightness values at once.", mediumBrightness || highBrightness, systemUnderTest.onlyOneStateTrue());
        }
    }

    public boolean increaseBulbBrightnessGuard() {
        return getState().equals(BulbOperatorStates.LOW_BRIGHTNESS) || getState().equals(BulbOperatorStates.MEDIUM_BRIGHTNESS);
    }
    public @Action void increaseBulbBrightness() {
        //Updating SUT
        systemUnderTest.increaseBrightness();

        //Updating model
        if (getState().equals(BulbOperatorStates.LOW_BRIGHTNESS)) {
            modelBulb = BulbOperatorStates.MEDIUM_BRIGHTNESS;
            lowBrightness = false;
            mediumBrightness = true;

            //Checking correspondence between model and SUT.
            assertEquals("The SUT's bulb does not match the model's bulb after increasing the bulb's brightness", mediumBrightness, systemUnderTest.isMediumBrightness());
            assertEquals("SUT has multiple brightness values at once.", lowBrightness || highBrightness, systemUnderTest.onlyOneStateTrue());
        } else {
            modelBulb = BulbOperatorStates.HIGH_BRIGHTNESS;
            mediumBrightness = false;
            highBrightness = true;

            //Checking correspondence between model and SUT.
            assertEquals("The SUT's bulb does not match the model's bulb after increasing the bulb's brightness", highBrightness, systemUnderTest.isHighBrightness());
            assertEquals("SUT has multiple brightness values at once.", lowBrightness || mediumBrightness, systemUnderTest.onlyOneStateTrue());
        }
    }

    //Test runner
    @Test
    public void BulbOperatorModelRunner() {
        final GreedyTester tester = new GreedyTester(new BulbOperatorModelTest()); //Creates a test generator that can generate random walks. A greedy random walk gives preference to transitions that have never been taken before. Once all transitions out of a state have been taken, it behaves the same as a random walk.
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(500); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
}