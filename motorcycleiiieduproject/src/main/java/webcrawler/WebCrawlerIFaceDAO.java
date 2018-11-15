package webcrawler;

import java.util.List;

import projectbean.WebCrawlerForecast;

public interface WebCrawlerIFaceDAO {
	int crawlWeatherData() throws Exception;
	List<WebCrawlerForecast>showForecast();
	String updateForecastData();
}
