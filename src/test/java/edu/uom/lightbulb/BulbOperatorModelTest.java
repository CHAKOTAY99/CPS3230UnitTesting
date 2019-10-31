package edu.uom.lightbulb;

import edu.uom.lightbulb.enums.BulbOperatorStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

import static org.junit.Assert.assertEquals;

public class BulbOperatorModelTest implements FsmModel {

    // Link the system under test
    private  BulbOperator systemUnderTest = new BulbOperator();

    // State Variables
    private BulbOperatorStates modelBulb = BulbOperatorStates.OFF;
    private boolean lowBrightness = false, mediumBrightness = false, highBrightness = false;

    // Method implementations
    public BulbOperatorStates getState(){
        return modelBulb;
    }

    public void reset (final boolean var1) {
        if (var1) {
            systemUnderTest = new BulbOperator();
        }
        modelBulb = BulbOperatorStates.OFF;
        lowBrightness = false;
        mediumBrightness = false;
        highBrightness = false;
    }

    // Transactions including guards
    public boolean turnOnBulbGuard(){
        return getState().equals(BulbOperatorStates.OFF);
    }

    public @Action void turnOnBulb(){
        // Updating System Under Test
        systemUnderTest.turnOn();

        // Updating Model
        modelBulb = BulbOperatorStates.MEDIUM_BRIGHTNESS;
        mediumBrightness = true;

        // Checking the correspondence between the model and the system under test
        assertEquals("The SUT's bulb does not match the model's bulb after turing on the bulb", mediumBrightness, systemUnderTest.isMediumBrightness());

        // Additional safety measure test - was not required
        assertEquals("SUT has multiple brightness values at once.", lowBrightness || highBrightness, systemUnderTest.onlyOneStateTrue());
    }

    public boolean turnOffBulbGuard(){
        return !getState().equals(BulbOperatorStates.OFF);
    }

    public @Action void turnOffBulb() {
        // Updating System Under Test
        systemUnderTest.turnOff();

        // Updating Model
        modelBulb = BulbOperatorStates.OFF;
        lowBrightness = false;
        highBrightness = false;
        mediumBrightness = false;

        // Checking the correspondence between the model and the system under test
        assertEquals("The SUT's bulb does not match the model's bulb after tuning off the bulb", lowBrightness || mediumBrightness || highBrightness,
                systemUnderTest.isTurnedOn());
    }

    public boolean decreaseBulbBrightnessGuard(){
        return getState().equals(BulbOperatorStates.HIGH_BRIGHTNESS) || getState().equals(BulbOperatorStates.MEDIUM_BRIGHTNESS);
    }

    public @Action void decreaseBulbBrightness() {
        // Updating SUT
        systemUnderTest.decreaseBrightness();

        //Checking correspondence between model and SUT.
        assertEquals("The SUT's bulb does not match the model's bulb after decreasing the bulb's brightness", mediumBrightness, systemUnderTest.isMediumBrightness());
        assertEquals("SUT has multiple brightness values at once.", lowBrightness || highBrightness, systemUnderTest.onlyOneStateTrue());
    } else
    {
        modelBulb = BulbOperatorStates.LOW_BRIGHTNESS;
        mediumBrightness = false;
        lowBrightness = true;

        //Checking correspondence between model and SUT.
        assertEquals("The SUT's bulb does not match the model's bulb after decreasing the bulb's brightness", lowBrightness, systemUnderTest.isLowBrightness());
        assertEquals("SUT has multiple brightness values at once.", mediumBrightness || highBrightness, systemUnderTest.onlyOneStateTrue());
    }

    public boolean increaseBulbBrightnessGuard() {
        return getState().equals(BulbOperatorStates.LOW_BRIGHTNESS) || getState().equals(BulbOperatorStates.MEDIUM_BRIGHTNESS);
    }

    public @Action void increaseBulbBrightness(){
        // Updating SUT
        systemUnderTest.increaseBrightness();
    }
}
