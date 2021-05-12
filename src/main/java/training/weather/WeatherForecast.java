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

	private long days = 6;
	private String path = "https://www.metaweather.com/api/location/";
	private String format = "yyyy-MM-dd";

	public String getCityWeather(String city, LocalDate datetime) throws IOException {
		LocalDate date = Time.getDate(datetime); 
		String weather = "";

		if (!Time.isDateBeforeNextDays(date, days)){
			return "";
		}
	
		HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
		HttpRequest request = requestFactory
			.buildGetRequest(new GenericUrl(this.path + "search/?query=" + city));
		String response = request.execute().parseAsString();
		JSONArray array = new JSONArray(response);
		String woe = array.getJSONObject(0).get("woeid").toString();
		request = requestFactory.buildGetRequest(new GenericUrl(this.path + woe));
		response = request.execute().parseAsString();
		JSONArray results = new JSONObject(response).getJSONArray("consolidated_weather");
		for (int i = 0; i < results.length(); i++) {
			if (date.format(DateTimeFormatter.ofPattern(this.format))
				.equals(results.getJSONObject(i).get("applicable_date").toString())) {
				weather =results.getJSONObject(i).get("weather_state_name").toString();
				break;
			}
		}
		// return results.stream

		return weather;
	}
}
