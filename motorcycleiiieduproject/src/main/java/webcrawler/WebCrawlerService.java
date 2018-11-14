package webcrawler;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectbean.WebCrawlerForecast;

@Service
@Transactional
public class WebCrawlerService implements WebCrawlerIFaceService {
	
	
	
	@Autowired
	WebCrawlerIFaceDAO testWebCrawlerDAO;

	
	
	

	public WebCrawlerService() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int crawlWeatherData() throws Exception {
		return testWebCrawlerDAO.crawlWeatherData();
	}


	@Override
	public List<WebCrawlerForecast> showForecast() {
		return testWebCrawlerDAO.showForecast();
	}


	@Override
	public String updateForecastData() {
		return testWebCrawlerDAO.updateForecastData();
	}
	




}
