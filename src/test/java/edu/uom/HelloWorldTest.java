package edu.uom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HelloWorldTest {

    HelloWorld helloWorld;

    @Before
    public void setup(){
        helloWorld = new HelloWorld();
    }

    @After
    public void teardown(){
        helloWorld = null;
    }

    @Test
    public void testStandardMessage() {

        //Exercise
        String msg = helloWorld.getMessage();
        //Verify
        assertEquals( "Hello World!!", msg);

    }

    @Test
    public void testMessageWithName() {

        //Exercise
        String msg = helloWorld.getMessage("Ahmed");
        //Verify
        assertEquals( "Hello Ahmed!", msg);

    }

    @Test
    public void testMessageWithNullName() {

        //Exercise
        String msg = helloWorld.getMessage(null);
        //Verify
        assertEquals( "Hello World!!", msg);

    }
}
