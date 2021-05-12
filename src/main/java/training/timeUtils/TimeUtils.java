package training.timeUtils;

import java.time.LocalDate;

public class TimeUtils {

	public static final long SECOND_TO_MILISECONDS = 1000;
	public static final long MINUTE_TO_SECONDS = 60;
	public static final long HOUR_TO_MINUTES = 60;
	public static final long DAY_TO_HOURS = 24;

	private TimeUtils() {

	}

	/**
	 * if the date exists returns the given date if not returns a brand new date.
	 * 
	 * @param date
	 * @return a localDate object
	 */
	public static LocalDate getDate(final LocalDate date) {
		return date != null ? date : LocalDate.now();
	}

	/**
	 * Given a date and a number of days returns if the date is before than today
	 * and the days selected
	 * 
	 * @param date the date that will be checked if is before.
	 * @param days the number of days after today that will be compared.
	 * @return a boolean that will be true if the date is before today plus the days
	 *         selected and false if not.
	 */
	public static boolean isDateBeforeNextDays(LocalDate date, long days) {

		long daysToMiliseconds = days * DAY_TO_HOURS * HOUR_TO_MINUTES * MINUTE_TO_SECONDS * SECOND_TO_MILISECONDS;
		long milisecondsFromEpoch = daysToMiliseconds + LocalDate.now().toEpochDay();

		LocalDate dateToCompare = LocalDate.ofEpochDay(milisecondsFromEpoch);

		return date.isBefore(dateToCompare);
	}
}
