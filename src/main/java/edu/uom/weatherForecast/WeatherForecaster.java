package edu.uom.weatherForecast;

public class WeatherForecaster {

    public String getCurrentConditions(AdditionalClasses additionalClasses) throws WindException{
        int temp = additionalClasses.getCurrentTemp();
        String windDirection = null;
        int windSpeed = 0;
        String generalConditions = additionalClasses.getCurrentConditions();

        boolean windError = false;
        try {
            windDirection = additionalClasses.getCurrentWindDir();
            windSpeed = additionalClasses.getCurrentWindSpeed();
        } catch (WindException we){
            windDirection = we.getMessage();
            windError = true;
        }

        if(!windError){
            return "The temperature is currently "+temp+" degrees Celsius and the wind is blowing from the "+windDirection+
                    "with a strenght of "+windSpeed+" km/h. The weather is currently "+generalConditions+".";
        } else {
            return "The temperature is currently "+temp+" degrees Celsius. The weather is currently "+generalConditions+". "+windDirection;
        }
    }

    public String getForecast(AdditionalClasses additionalClasses) throws WindException{
        int maxTemp = additionalClasses.getForecastHighTemp();
        int minTemp = additionalClasses.getForecastLowTemp();
        String windDirection = null;
        int windSpeed = 0;
        String generalConditions = additionalClasses.getForecastConditions();

        boolean windError = false;
        try{
            windDirection = additionalClasses.getForecastWindDir();
            windSpeed = additionalClasses.getForecastWindSpeed();
        } catch (WindException we){
            windDirection = we.getMessage();
            windError = true;
        }

        if(!windError){
            return "The temperature will reach a high of "+maxTemp+" degrees Celsius during the day and a minimum" +
                "of "+minTemp+" degrees Celsius during the night. The wind wil blow from the "+windDirection+" with a strenght of "+windSpeed+" km/h" +
                ". The weather is likely to be "+generalConditions+".";
        } else {
            return "The temperature will reach a high of "+maxTemp+" degrees Celsius during the day and a minimum" +
                    "of "+minTemp+" degrees Celsius during the night. The weather is likely to be "+generalConditions+". " +windDirection;
        }
    }
}
