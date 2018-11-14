package webcrawlercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import projectbean.WebCrawlerForecast;
import webcrawler.WebCrawlerIFaceService;

 
@Controller
public class WebCrawlerController {
	@Autowired
	WebCrawlerIFaceService webCrawlerIFaceService;
	@Autowired
	Gson gson;
	
	@RequestMapping(value = "/crawlWeatherData", method = RequestMethod.GET,produces="text/html;charset=UTF-8") // 爬氣象局資料
	public @ResponseBody String crawlWeatherData() throws Exception{
		System.out.println("執行爬爬爬crawlWeatherData");
		webCrawlerIFaceService.crawlWeatherData();
		return "爬到資料羅!!!";
	}
	
	@GetMapping(value = "/showForecast",produces = "application/JSON; charset = UTF-8") // 秀氣象局資料
	public @ResponseBody String showForecast() throws Exception{
		System.out.println("執行SHOW爬爬爬showForecast");
//		webCrawlerIFaceService.showForecast();
//		return "秀一波爬的資料羅!!!";
		
		try {		
			List<WebCrawlerForecast> showAllForecast = webCrawlerIFaceService.showForecast();
			String forGsonConvert=gson.toJson(showAllForecast);
			return forGsonConvert;
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}	
	}
	//@Scheduled(fixedDelay = 15000)
	@RequestMapping(value = "/updateForecastData", method = RequestMethod.GET,produces="text/html;charset=UTF-8") // 爬氣象局資料
	public @ResponseBody String updateForecastData() throws Exception{
		System.out.println("更新爬爬爬updateForecastData");
		webCrawlerIFaceService.updateForecastData();
		return "更新爬爬爬資料羅!!!";
	}
}
