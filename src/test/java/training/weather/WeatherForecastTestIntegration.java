package training.weather;

import java.time.LocalDate;

import org.junit.Test;

import training.httpRequestException.HttpRequestException;

public class WeatherForecastTestIntegration {
  @Test
	public void aHundredCallsTest() throws HttpRequestException {
    WeatherForecast weatherForecast = new WeatherForecast();

    for(int i = 0; i < 100; i++){
      weatherForecast.obtainCityWeather("Madrid", LocalDate.now());
    }
	}
}
