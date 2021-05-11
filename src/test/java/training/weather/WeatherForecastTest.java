package training.weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
		/*------------------- Given --------------------- */
			WeatherForecast weatherForecast = new WeatherForecast();

		 /*------------------- When --------------------- */
			LocalDate date  = weatherForecast.getDate();
			LocalDate dateToCompare = LocalDate.now();

			
		 /*------------------- Then  --------------------- */
			assertEquals(dateToCompare.toString(), date.toString());
	}


	@Test
	public void givenDateShouldReturnTheDate() {
		/*------------------- Given --------------------- */
			WeatherForecast weatherForecast = new WeatherForecast();

		 /*------------------- When --------------------- */

			LocalDate date  = LocalDate.of(2021,05,10);
			LocalDate dateToCompare  = weatherForecast.getDate(date);

		 /*------------------- Then  --------------------- */
			assertEquals(dateToCompare.toString(), date.toString());
	}


	@Test
	public void givenAfterSixDaysDateShouldReturnFalse() {
			/*------------------- Given --------------------- */
		
			WeatherForecast weatherForecast = new WeatherForecast();
			int days = 6;
			LocalDate date = LocalDate.of(2021,05,10);
			LocalDate secondDate  = LocalDate.of(2021,05,18);

		 /*------------------- When --------------------- */
			
			Boolean isBeforeSixdays  = weatherForecast.isDateBeforeDays(date,secondDate,days);

		 /*------------------- Then  --------------------- */
			assertFalse(isBeforeSixdays);
	}


	@Test
	public void givenBeforeSixDaysDateShouldReturnTrue() {
		/*------------------- Given --------------------- */

			WeatherForecast weatherForecast = new WeatherForecast();
			int days = 6;
			LocalDate date = LocalDate.of(2021,05,10);
			LocalDate secondDate  = LocalDate.of(2021,05,12);

		 /*------------------- When --------------------- */
			
			Boolean isBeforeSixdays  = weatherForecast.isDateBeforeDays(date,secondDate,days);

		 /*------------------- Then  --------------------- */
			assertTrue(isBeforeSixdays);
	}
}