package edu.uom.weatherForecast;

public interface AdditionalClasses {

    // Thermometer - measures/forecasts temperature
    public int getCurrentTemp();
    public int getForecastHighTemp();
    public int getForecastLowTemp();

    // WindMeter = measures/forecasts wind
    public int getCurrentWindSpeed();
    public String getCurrentWindDir();
    public int getForecastWindSpeed();
    public String getForecastWindDir();

    // Barometer - measures/forecasts conditions; sunny,rainy,etc
    public String getCurrentConditions();
    public String getForecastConditions();
}
