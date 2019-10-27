package edu.uom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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

    @Test
    public void testTimedMessageMorning() {
        // Setup
        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
        // Telling mockito that when one thing is passed the it will return a particular value
        Mockito.when(timeProvider.getTimeSegment()).thenReturn(TimeProvider.Morning);

        // Exercise
        String msg = helloWorld.getTimedMessage(timeProvider);

        // Verify
        assertEquals("Hello World!! Good Morning!", msg);

        // Teardown
        timeProvider = null;
    }

    @Test
    public void testTimedMessageAfternoon() {
        // Setup
        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
        // Telling mockito that when one thing is passed the it will return a particular value
        Mockito.when(timeProvider.getTimeSegment()).thenReturn(TimeProvider.Afternoon);

        // Exercise
        String msg = helloWorld.getTimedMessage(timeProvider);

        // Verify
        assertEquals("Hello World!! Good Afternoon!", msg);

        // Teardown
        timeProvider = null;
    }

    @Test
    public void testTimedMessageEvening() {
        // Setup
        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
        // Telling mockito that when one thing is passed the it will return a particular value
        Mockito.when(timeProvider.getTimeSegment()).thenReturn(TimeProvider.Evening);

        // Exercise
        String msg = helloWorld.getTimedMessage(timeProvider);

        // Verify
        assertEquals("Hello World!! Good Evening!", msg);

        // Teardown
        timeProvider = null;
    }

    @Test
    public void testTimeMessageUnknownTime(){
        // Setup
        TimeProvider timeProvider = Mockito.mock(TimeProvider.class);
        // Tell Mockito what to do
        Mockito.when(timeProvider.getTimeSegment()).thenReturn(12);

        // Exercise
        String msg = helloWorld.getTimedMessage(timeProvider);

        // Verify
        assertEquals("Hello World!!", msg);

        // Teardown
        timeProvider = null;
    }
}
