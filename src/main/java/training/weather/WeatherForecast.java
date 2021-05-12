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

import training.time.Time;

public class WeatherForecast {

	private final long DAYS = 6;
	private String format = "yyyy-MM-dd";
	private String path = "https://www.metaweather.com/api/location/";

	public String getCityWeather(String city, LocalDate datetime) throws IOException {
		LocalDate date = Time.getDate(datetime); 
		String weather = "";

		if (!Time.isDateBeforeNextDays(date, DAYS)){
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
}
