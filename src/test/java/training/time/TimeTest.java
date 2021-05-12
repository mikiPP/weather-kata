package training.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class TimeTest {
  

  @Test
	public void givenNullDateShouldReturnANewDate() {

		 /*------------------- When --------------------- */
			LocalDate date  = Time.getDate(null);
			LocalDate dateToCompare = LocalDate.now();

			
		 /*------------------- Then  --------------------- */
			assertEquals(dateToCompare.toString(), date.toString());
	}


	@Test
	public void givenDateShouldReturnTheDate() {

		 /*------------------- When --------------------- */

			LocalDate date  = LocalDate.of(2021,05,10);
			LocalDate dateToCompare  = Time.getDate(date);

		 /*------------------- Then  --------------------- */
			assertEquals(dateToCompare.toString(), date.toString());
	}


	@Test
	public void givenAfterSixDaysDateShouldReturnFalse() {
			/*------------------- Given --------------------- */
			long secondToMiliseconds = 1000;
			long minuteToSeconds = 60;
			long hourToMinutes = 60;
			long dayToHours = 24;
			long days = 6;

			// We add 1 because we want to check one day after the date selected.
			long daysToMiliseconds = (days + 1) *  dayToHours * hourToMinutes * minuteToSeconds * secondToMiliseconds;

			LocalDate date = LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + daysToMiliseconds);

		 /*------------------- When --------------------- */
			
			Boolean isBeforeSixdays  = Time.isDateBeforeNextDays(date,days);

		 /*------------------- Then  --------------------- */
			assertFalse(isBeforeSixdays);
	}


	@Test
	public void givenBeforeSixDaysDateShouldReturnTrue() {
		/*------------------- Given --------------------- */
			long days = 6;
			LocalDate date = LocalDate.now();

		 /*------------------- When --------------------- */
			
			Boolean isBeforeSixDays  = Time.isDateBeforeNextDays(date,days);

		 /*------------------- Then  --------------------- */
			assertTrue(isBeforeSixDays);
	}
}
