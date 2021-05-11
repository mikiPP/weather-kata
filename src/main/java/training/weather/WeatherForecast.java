package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {

	public String getCityWeather(String city, LocalDate datetime) throws IOException {
		// LocalDate date = getDate(datetime); 


		// if (datetime.before(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 6)))) {
		// 	HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
		// 	HttpRequest req = rf
		// 		.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/search/?query=" + city));
		// 	String r = req.execute().parseAsString();
		// 	JSONArray array = new JSONArray(r);
		// 	String woe = array.getJSONObject(0).get("woeid").toString();
		// 	rf = new NetHttpTransport().createRequestFactory();
		// 	req = rf.buildGetRequest(new GenericUrl("https://www.metaweather.com/api/location/" + woe));
		// 	r = req.execute().parseAsString();
		// 	JSONArray results = new JSONObject(r).getJSONArray("consolidated_weather");
		// 	for (int i = 0; i < results.length(); i++) {
		// 		if (new SimpleDateFormat("yyyy-MM-dd").format(datetime)
		// 			.equals(results.getJSONObject(i).get("applicable_date").toString())) {
		// 			return results.getJSONObject(i).get("weather_state_name").toString();
		// 		}
		// 	}
		// }
		return "";
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
}
