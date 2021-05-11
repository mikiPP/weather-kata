package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

	private final long DAYS = 6;
	private String format = "yyyy-MM-dd";
	private String path = "https://www.metaweather.com/api/location/";

	public String getCityWeather(String city, LocalDate datetime) throws IOException {
		LocalDate date = getDate(datetime); 
		String weather = "";

		if (!isDateBeforeNextDays(date, DAYS)){
			return "";
		}
	
		HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
		HttpRequest req = rf
			.buildGetRequest(new GenericUrl(this.path + "search/?query=" + city));
		String r = req.execute().parseAsString();
		JSONArray array = new JSONArray(r);
		String woe = array.getJSONObject(0).get("woeid").toString();
		rf = new NetHttpTransport().createRequestFactory();
		req = rf.buildGetRequest(new GenericUrl(this.path + woe));
		r = req.execute().parseAsString();
		JSONArray results = new JSONObject(r).getJSONArray("consolidated_weather");
		for (int i = 0; i < results.length(); i++) {
			if (date.format(DateTimeFormatter.ofPattern(this.format))
				.equals(results.getJSONObject(i).get("applicable_date").toString())) {
				weather =results.getJSONObject(i).get("weather_state_name").toString();
				break;
			}
		}

		return weather;
	}

	/**
	 * if the date exists returns the given date if not returns a brand new date.
	 * 
	 * @param  date
	 * @return LocalDate
	 */
	public LocalDate getDate(LocalDate date) {
		return date != null ? date : LocalDate.now();
	}

	/**
	 * Given a date and a number of days returns if the date is before than today and the days selected
	 * 
	 * @param  date the date that will be checked if is before.
	 * @param days the number of days after today that will be compared.
	 * @return a boolean that will be true if the date is before today plus the days selected and false if not.
	 */
	public boolean isDateBeforeNextDays(LocalDate date, long days) {
		long secondToMiliseconds = 1000;
		long minuteToSeconds = 60;
		long hourToMinutes = 60;
		long dayToHours = 24;

		long daysToMiliseconds = days *  dayToHours * hourToMinutes * minuteToSeconds * secondToMiliseconds;
		long milisecondsFromEpoch = daysToMiliseconds + LocalDate.now().toEpochDay();

		LocalDate dateToCompare = LocalDate.ofEpochDay(milisecondsFromEpoch);

		return date.isBefore(dateToCompare);
	}
}
