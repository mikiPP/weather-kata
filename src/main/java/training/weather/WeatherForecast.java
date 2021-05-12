package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;

import training.time.Time;

public class WeatherForecast {

	private long days = 6;
	private String url = "https://www.metaweather.com/api/location/";
	private String format = "yyyy-MM-dd";
	private HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();

	public String getCityWeather(String city, LocalDate datetime) throws Exception {
		LocalDate date = Time.getDate(datetime); 

		if (!Time.isDateBeforeNextDays(date, days)){
			return "";
		}

		try {
			String woeid = getWoeid(city);
			String weatherDataResponse = makeHttpCallToApi(woeid);
			return getDaySelectedWeather(weatherDataResponse,datetime);

		}	catch (IOException e) {
			throw new Exception("Error getting data: " + e.getMessage());
		}
	
	}

	private String getWoeid(String city) throws IOException {
		String cityQueryResponse = makeHttpCallToApi("search/?query=" + city);
		JSONArray array = new JSONArray(cityQueryResponse);
		return array.getJSONObject(0).get("woeid").toString();
	}


	private String makeHttpCallToApi(String path) throws IOException {
		GenericUrl uri = new GenericUrl(path != null ? url + path : url);
		HttpRequest request = requestFactory.buildGetRequest(uri);
		return request.execute().parseAsString();
	}

	public String getDaySelectedWeather(String weatherDataResponse,LocalDate date) {

		JSONArray results = new JSONObject(weatherDataResponse).getJSONArray("consolidated_weather");

		Optional<String> list = StreamSupport.stream(results.spliterator(), false)
			.map(JSONObject.class::cast)
			.filter(dayData -> dayData.get("applicable_date").toString().equals(date.format(DateTimeFormatter.ofPattern(this.format))))
			.map(daySelected -> daySelected.get("weather_state_name").toString())
			.findFirst();

		return list.isPresent() ? list.get() : "";
	}
}
