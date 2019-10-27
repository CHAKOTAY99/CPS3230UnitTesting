package edu.uom.weatherForecast;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;

public class WeatherForecasterTest {
    WeatherForecaster weatherForecaster;
    AdditionalClasses additionalClasses;

    @Before
    public void setup(){
        weatherForecaster = new WeatherForecaster();
        additionalClasses = Mockito.mock(AdditionalClasses.class);
    }

    @After
    public void teardown(){
        weatherForecaster = null;
        additionalClasses = null;
    }

    @Test
    public void testCurrentConditions(){
        //Setup
        Mockito.when(additionalClasses.getCurrentTemp()).thenReturn(10);
        Mockito.when(additionalClasses.getCurrentWindDir()).thenReturn("north");
        Mockito.when(additionalClasses.getCurrentWindSpeed()).thenReturn(15);
        Mockito.when(additionalClasses.getCurrentConditions()).thenReturn("fair");

        // Exercise
        String msg = weatherForecaster.getCurrentConditions(additionalClasses);

        // Verify
        assertEquals("The temperature is currently 10 degrees Celsius and the wind is blowing from the north" +
                "with a strenght of 15 km/h. The weather is currently fair.", msg);
    }

    @Test
    public void testCurrentForecast(){

        //Setup
        Mockito.when(additionalClasses.getForecastHighTemp()).thenReturn(28);
        Mockito.when(additionalClasses.getForecastLowTemp()).thenReturn(21);
        Mockito.when(additionalClasses.getForecastWindDir()).thenReturn("east");
        Mockito.when(additionalClasses.getForecastWindSpeed()).thenReturn(15);
        Mockito.when(additionalClasses.getForecastConditions()).thenReturn("good");

        // Exercise
        String msg = weatherForecaster.getForecast(additionalClasses);

        // Verify
        assertEquals("The temperature will reach a high of 28 degrees Celsius during the day and a minimum" +
                "of 21 degrees Celsius during the night. The wind wil blow from the east with a strenght of 15 km/h" +
                ". The weather is likely to be good.", msg);
    }

    @Test
    public void testCurrentConditionsWindOffline(){

        //Setup
        Mockito.when(additionalClasses.getCurrentTemp()).thenReturn(10);
        Mockito.when(additionalClasses.getCurrentWindDir()).thenThrow(new WindException());
        Mockito.when(additionalClasses.getCurrentWindSpeed()).thenThrow(new WindException());
        Mockito.when(additionalClasses.getCurrentConditions()).thenReturn("fair");

        // Exercise
        String msg = weatherForecaster.getCurrentConditions(additionalClasses);

        // Verify
        assertEquals("The temperature is currently 10 degrees Celsius. The weather is currently fair. UNAVAILABLE", msg);
    }

    @Test
    public void getForecastWindOffline(){

        //Setup
        Mockito.when(additionalClasses.getForecastHighTemp()).thenReturn(28);
        Mockito.when(additionalClasses.getForecastLowTemp()).thenReturn(21);
        Mockito.when(additionalClasses.getForecastWindDir()).thenThrow(new WindException());
        Mockito.when(additionalClasses.getForecastWindSpeed()).thenThrow(new WindException());
        Mockito.when(additionalClasses.getForecastConditions()).thenReturn("good");

        // Exercise
        String msg = weatherForecaster.getForecast(additionalClasses);

        // Verify
        assertEquals("The temperature will reach a high of 28 degrees Celsius during the day and a minimum" +
                "of 21 degrees Celsius during the night. The weather is likely to be good. UNAVAILABLE", msg);
    }
}
