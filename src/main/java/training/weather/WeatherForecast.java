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

import training.httpRequestException.HttpRequestException;
import training.timeUtils.TimeUtils;

public class WeatherForecast {

	private static final long DAYS = 6;
	private static final String URL = "https://www.metaweather.com/api/location/";
	private static final String HTTP_ERROR_MESSAGE = "Error fetching data";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final HttpRequestFactory REQUEST_FACTORY = new NetHttpTransport().createRequestFactory();

	public String obtainCityWeather(final String city, final LocalDate datetime) throws HttpRequestException {
		LocalDate date = TimeUtils.getDate(datetime);

		if (!TimeUtils.isDateBeforeNextDays(date, DAYS)) {
			return "";
		}

		try {
			String woeid = obtainWoeid(city);
			String weatherDataResponse = makeHttpCallToApi(woeid);
			return obtainWeatherByDate(weatherDataResponse, datetime);

		} catch (HttpRequestException e) {
			return "SERVICE DOWN, try it again later";
		}

	}

	public String obtainWeatherByDate(final String weatherDataResponse, final LocalDate date) {

		JSONArray results = new JSONObject(weatherDataResponse).getJSONArray("consolidated_weather");

		Optional<String> daySelectedWeather = StreamSupport.stream(results.spliterator(), false).map(JSONObject.class::cast)
				.filter(dayData -> dayData.get("applicable_date").toString()
						.equals(date.format(DateTimeFormatter.ofPattern(DATE_FORMAT))))
				.map(daySelected -> daySelected.get("weather_state_name").toString()).findFirst();

		return daySelectedWeather.isPresent() ? daySelectedWeather.get() : "";
	}

	private String obtainWoeid(final String city) throws HttpRequestException {

		String cityQueryResponse = makeHttpCallToApi("search/?query=" + city);
		JSONArray responseAsArray = new JSONArray(cityQueryResponse);
		return responseAsArray.getJSONObject(0).get("woeid").toString();
	}

	private String makeHttpCallToApi(final String path) throws HttpRequestException {
		try {

			GenericUrl uri = new GenericUrl(path != null ? URL + path : URL);
			HttpRequest request = REQUEST_FACTORY.buildGetRequest(uri);
			return request.execute().parseAsString();

		} catch (IOException e) {
			throw new HttpRequestException(HTTP_ERROR_MESSAGE + e.getMessage());
		}

	}
}
