package webcrawler;

import java.util.List;

import projectbean.WebCrawlerForecast;

public interface WebCrawlerIFaceService {
	int crawlWeatherData() throws Exception;
	List<WebCrawlerForecast>showForecast();
	String updateForecastData();
}
