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
		String forecast = weatherForecast.getCityWeather("Madrid", LocalDate.now());
		System.out.println(forecast);
	}

	@Test
	public void givenNullDateShouldReturnANewDate() {
		/*------------------- Given --------------------- */
			WeatherForecast weatherForecast = new WeatherForecast();

		 /*------------------- When --------------------- */
			LocalDate date  = weatherForecast.getDate(null);
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
			long secondToMiliseconds = 1000;
			long minuteToSeconds = 60;
			long hourToMinutes = 60;
			long dayToHours = 24;
			long days = 6;

			// We add 1 because we want to check one day after the date selected.
			long daysToMiliseconds = (days + 1) *  dayToHours * hourToMinutes * minuteToSeconds * secondToMiliseconds;

			LocalDate date = LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + daysToMiliseconds);

		 /*------------------- When --------------------- */
			
			Boolean isBeforeSixdays  = weatherForecast.isDateBeforeNextDays(date,days);

		 /*------------------- Then  --------------------- */
			assertFalse(isBeforeSixdays);
	}


	@Test
	public void givenBeforeSixDaysDateShouldReturnTrue() {
		/*------------------- Given --------------------- */

			WeatherForecast weatherForecast = new WeatherForecast();
			long days = 6;
			LocalDate date = LocalDate.now();

		 /*------------------- When --------------------- */
			
			Boolean isBeforeSixDays  = weatherForecast.isDateBeforeNextDays(date,days);

		 /*------------------- Then  --------------------- */
			assertTrue(isBeforeSixDays);
	}
}