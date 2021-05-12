package training.weather;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.Test;

import training.httpRequestException.HttpRequestException;

public class WeatherForecastTest {

	@Test
	public void unfinished_test() throws HttpRequestException {
		WeatherForecast weatherForecast = new WeatherForecast();
		String forecast = weatherForecast.obtainCityWeather("Madrid", LocalDate.now());
		System.out.println(forecast);
	}

	@Test
	public void givenStringWithDaysDataShouldReturnDaySelectedWeather() {
		/*------------------- Given --------------------- */
		WeatherForecast weatherForecast = new WeatherForecast();
			String daysWeatherData = "{\"consolidated_weather\":[{\"id\":4933722936705024,\"weather_state_name\":\"Showers\",\"weather_state_abbr\":\"s\",\"wind_direction_compass\":\"W\",\"created\":\"2021-05-12T18:48:56.403929Z\",\"applicable_date\":\"2021-05-12\",\"min_temp\":9.275,\"max_temp\":17.925,\"the_temp\":17.155,\"wind_speed\":9.599321371351309,\"wind_direction\":262.28816852678347,\"air_pressure\":1017.5,\"humidity\":52,\"visibility\":11.268566571224051,\"predictability\":73},{\"id\":5674358941941760,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"WSW\",\"created\":\"2021-05-12T18:49:00.470148Z\",\"applicable_date\":\"2021-05-13\",\"min_temp\":8.34,\"max_temp\":20.215,\"the_temp\":19.43,\"wind_speed\":7.215466436785175,\"wind_direction\":257.4561002122223,\"air_pressure\":1015.5,\"humidity\":46,\"visibility\":12.908054461942257,\"predictability\":71},{\"id\":6713658752106496,\"weather_state_name\":\"Light Cloud\",\"weather_state_abbr\":\"lc\",\"wind_direction_compass\":\"NW\",\"created\":\"2021-05-12T18:49:02.276341Z\",\"applicable_date\":\"2021-05-14\",\"min_temp\":10.735,\"max_temp\":22.34,\"the_temp\":19.87,\"wind_speed\":5.8915734233224635,\"wind_direction\":311.6668737000967,\"air_pressure\":1017.0,\"humidity\":36,\"visibility\":14.362684422969856,\"predictability\":70},{\"id\":4898902999302144,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"WSW\",\"created\":\"2021-05-12T18:49:05.192608Z\",\"applicable_date\":\"2021-05-15\",\"min_temp\":11.469999999999999,\"max_temp\":26.795,\"the_temp\":25.2,\"wind_speed\":5.327662919748668,\"wind_direction\":241.96636670475112,\"air_pressure\":1015.0,\"humidity\":37,\"visibility\":14.449365704286963,\"predictability\":71},{\"id\":5897770490658816,\"weather_state_name\":\"Light Cloud\",\"weather_state_abbr\":\"lc\",\"wind_direction_compass\":\"W\",\"created\":\"2021-05-12T18:49:08.446771Z\",\"applicable_date\":\"2021-05-16\",\"min_temp\":14.184999999999999,\"max_temp\":29.145,\"the_temp\":26.735,\"wind_speed\":5.236478322182454,\"wind_direction\":278.7353826983571,\"air_pressure\":1014.0,\"humidity\":36,\"visibility\":14.504667740396087,\"predictability\":70},{\"id\":4751830266413056,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"NW\",\"created\":\"2021-05-12T18:49:11.018356Z\",\"applicable_date\":\"2021-05-17\",\"min_temp\":14.575,\"max_temp\":26.45,\"the_temp\":24.1,\"wind_speed\":4.599549033643521,\"wind_direction\":304.5,\"air_pressure\":1017.0,\"humidity\":26,\"visibility\":9.999726596675416,\"predictability\":71}],\"time\":\"2021-05-12T21:33:54.626503+02:00\",\"sun_rise\":\"2021-05-12T07:01:21.878623+02:00\",\"sun_set\":\"2021-05-12T21:21:35.062419+02:00\",\"timezone_name\":\"LMT\",\"parent\":{\"title\":\"Spain\",\"location_type\":\"Country\",\"woeid\":23424950,\"latt_long\":\"39.894890,-2.988310\"},\"sources\":[{\"title\":\"BBC\",\"slug\":\"bbc\",\"url\":\"http://www.bbc.co.uk/weather/\",\"crawl_rate\":360},{\"title\":\"Forecast.io\",\"slug\":\"forecast-io\",\"url\":\"http://forecast.io/\",\"crawl_rate\":480},{\"title\":\"HAMweather\",\"slug\":\"hamweather\",\"url\":\"http://www.hamweather.com/\",\"crawl_rate\":360},{\"title\":\"Met Office\",\"slug\":\"met-office\",\"url\":\"http://www.metoffice.gov.uk/\",\"crawl_rate\":180},{\"title\":\"OpenWeatherMap\",\"slug\":\"openweathermap\",\"url\":\"http://openweathermap.org/\",\"crawl_rate\":360},{\"title\":\"Weather Underground\",\"slug\":\"wunderground\",\"url\":\"https://www.wunderground.com/?apiref=fc30dc3cd224e19b\",\"crawl_rate\":720},{\"title\":\"World Weather Online\",\"slug\":\"world-weather-online\",\"url\":\"http://www.worldweatheronline.com/\",\"crawl_rate\":360}],\"title\":\"Madrid\",\"location_type\":\"City\",\"woeid\":766273,\"latt_long\":\"40.420300,-3.705770\",\"timezone\":\"Europe/Madrid\"}";
			LocalDate date = LocalDate.of(2021,05,12);
			String resultExpected = "Showers";

		 /*------------------- When --------------------- */
			
			String result  = weatherForecast.obtainWeatherByDate(daysWeatherData,date);

		 /*------------------- Then  --------------------- */
			assertEquals(result,resultExpected);
	}

	@Test
	public void givenADateThatIsNotInTheResponseShouldReturnEmptyString() {
		/*------------------- Given --------------------- */
			WeatherForecast weatherForecast = new WeatherForecast();
			String daysWeatherData = "{\"consolidated_weather\":[{\"id\":4933722936705024,\"weather_state_name\":\"Showers\",\"weather_state_abbr\":\"s\",\"wind_direction_compass\":\"W\",\"created\":\"2021-05-12T18:48:56.403929Z\",\"applicable_date\":\"2021-05-12\",\"min_temp\":9.275,\"max_temp\":17.925,\"the_temp\":17.155,\"wind_speed\":9.599321371351309,\"wind_direction\":262.28816852678347,\"air_pressure\":1017.5,\"humidity\":52,\"visibility\":11.268566571224051,\"predictability\":73},{\"id\":5674358941941760,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"WSW\",\"created\":\"2021-05-12T18:49:00.470148Z\",\"applicable_date\":\"2021-05-13\",\"min_temp\":8.34,\"max_temp\":20.215,\"the_temp\":19.43,\"wind_speed\":7.215466436785175,\"wind_direction\":257.4561002122223,\"air_pressure\":1015.5,\"humidity\":46,\"visibility\":12.908054461942257,\"predictability\":71},{\"id\":6713658752106496,\"weather_state_name\":\"Light Cloud\",\"weather_state_abbr\":\"lc\",\"wind_direction_compass\":\"NW\",\"created\":\"2021-05-12T18:49:02.276341Z\",\"applicable_date\":\"2021-05-14\",\"min_temp\":10.735,\"max_temp\":22.34,\"the_temp\":19.87,\"wind_speed\":5.8915734233224635,\"wind_direction\":311.6668737000967,\"air_pressure\":1017.0,\"humidity\":36,\"visibility\":14.362684422969856,\"predictability\":70},{\"id\":4898902999302144,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"WSW\",\"created\":\"2021-05-12T18:49:05.192608Z\",\"applicable_date\":\"2021-05-15\",\"min_temp\":11.469999999999999,\"max_temp\":26.795,\"the_temp\":25.2,\"wind_speed\":5.327662919748668,\"wind_direction\":241.96636670475112,\"air_pressure\":1015.0,\"humidity\":37,\"visibility\":14.449365704286963,\"predictability\":71},{\"id\":5897770490658816,\"weather_state_name\":\"Light Cloud\",\"weather_state_abbr\":\"lc\",\"wind_direction_compass\":\"W\",\"created\":\"2021-05-12T18:49:08.446771Z\",\"applicable_date\":\"2021-05-16\",\"min_temp\":14.184999999999999,\"max_temp\":29.145,\"the_temp\":26.735,\"wind_speed\":5.236478322182454,\"wind_direction\":278.7353826983571,\"air_pressure\":1014.0,\"humidity\":36,\"visibility\":14.504667740396087,\"predictability\":70},{\"id\":4751830266413056,\"weather_state_name\":\"Heavy Cloud\",\"weather_state_abbr\":\"hc\",\"wind_direction_compass\":\"NW\",\"created\":\"2021-05-12T18:49:11.018356Z\",\"applicable_date\":\"2021-05-17\",\"min_temp\":14.575,\"max_temp\":26.45,\"the_temp\":24.1,\"wind_speed\":4.599549033643521,\"wind_direction\":304.5,\"air_pressure\":1017.0,\"humidity\":26,\"visibility\":9.999726596675416,\"predictability\":71}],\"time\":\"2021-05-12T21:33:54.626503+02:00\",\"sun_rise\":\"2021-05-12T07:01:21.878623+02:00\",\"sun_set\":\"2021-05-12T21:21:35.062419+02:00\",\"timezone_name\":\"LMT\",\"parent\":{\"title\":\"Spain\",\"location_type\":\"Country\",\"woeid\":23424950,\"latt_long\":\"39.894890,-2.988310\"},\"sources\":[{\"title\":\"BBC\",\"slug\":\"bbc\",\"url\":\"http://www.bbc.co.uk/weather/\",\"crawl_rate\":360},{\"title\":\"Forecast.io\",\"slug\":\"forecast-io\",\"url\":\"http://forecast.io/\",\"crawl_rate\":480},{\"title\":\"HAMweather\",\"slug\":\"hamweather\",\"url\":\"http://www.hamweather.com/\",\"crawl_rate\":360},{\"title\":\"Met Office\",\"slug\":\"met-office\",\"url\":\"http://www.metoffice.gov.uk/\",\"crawl_rate\":180},{\"title\":\"OpenWeatherMap\",\"slug\":\"openweathermap\",\"url\":\"http://openweathermap.org/\",\"crawl_rate\":360},{\"title\":\"Weather Underground\",\"slug\":\"wunderground\",\"url\":\"https://www.wunderground.com/?apiref=fc30dc3cd224e19b\",\"crawl_rate\":720},{\"title\":\"World Weather Online\",\"slug\":\"world-weather-online\",\"url\":\"http://www.worldweatheronline.com/\",\"crawl_rate\":360}],\"title\":\"Madrid\",\"location_type\":\"City\",\"woeid\":766273,\"latt_long\":\"40.420300,-3.705770\",\"timezone\":\"Europe/Madrid\"}";
			LocalDate date = LocalDate.of(2020,05,12);
			String resultExpected = "";

		 /*------------------- When --------------------- */
			
			String result  = weatherForecast.obtainWeatherByDate(daysWeatherData,date);

		 /*------------------- Then  --------------------- */
			assertEquals(result,resultExpected);
	}
}