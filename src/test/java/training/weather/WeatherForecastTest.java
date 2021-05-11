package training.weather;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void unfinished_test() throws IOException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.getCityWeather("Madrid", new Date());
		System.out.println(forecast);
	}

	@Test
	public void givenNullDateShouldReturnANewDate() {
		WeatherForecast weatherForecast = new WeatherForecast();

		 /*------------------- When --------------------- */
			LocalDate date  = weatherForecast.getDate();
			LocalDate dateToCompare = LocalDate.now();

			
		 /*------------------- Then  --------------------- */
			assertEquals(dateToCompare.toString(), date.toString());
	}


	@Test
	public void givenDateShouldReturnTheDate() {
		WeatherForecast weatherForecast = new WeatherForecast();

		 /*------------------- When --------------------- */
			LocalDate date  = weatherForecast.getDate(new LocalDate.of(2021,05,10));
			LocalDate dateToCompare = LocalDate.of(2021,05,10);

			
		 /*------------------- Then  --------------------- */
			assertEquals(dateToCompare.toString(), date.toString());
	}
}