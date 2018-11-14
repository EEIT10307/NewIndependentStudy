package timercontroll;

import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import webcrawler.WebCrawlerDAO;


@Component
public class TimerPicker extends TimerTask  {

	//讓方法類別AutoWired
	@Autowired
	AutoBug autoBug;
	@Autowired
	UpdateForecastData updateForecastData;
	
	
	static  Timer timer = new Timer();
	@PostConstruct
	public void init(){
		System.out.println("TimeMachine init method called");	
		   timer.schedule(new TimerPicker(), 0 , 10000);  //參數毫秒
	}
	@PreDestroy
	public void destory(){
		System.out.println("TimeMachine destroy method called");
		timer.cancel();
	}
	
	//把需要伺服器自動執行的方法寫在裡面
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		System.out.println("Hi Im Spring AI ");
		   try {
	//		   autoBug.Parsingkeelung();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
	
	

