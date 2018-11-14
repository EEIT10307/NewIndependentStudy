package webcrawler;

import java.net.URL;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;
import projectbean.WebCrawlerForecast;
 
@Repository
public class WebCrawlerDAO implements WebCrawlerIFaceDAO {
	@Autowired
	SessionFactory factory;

	@Override
	public int crawlWeatherData() throws Exception {
		try {
		for(int x = 1  ; x<=22;x++) {
    	if(x==2 || x==7 || x==8 || x==9 || x==10 || x==11 || x==12 || x==13 || x==15 || x==16 || x==17 || x==18 || x==19 || x==20 || x==21 || x==22) {
    		continue;
    	}//台北市=1,基隆市=3,新北市=4,桃園市=5,新竹縣=6,新竹市=14
    		
        URL url = new URL("https://www.cwb.gov.tw/rss/forecast/36_"+String.format("%02d", x)+".xml"); 
        Document xmlDoc;
		xmlDoc = Jsoup.parse(url, 3000);//使用Jsoup jar 去解析網頁
        //(要解析的文件,timeout)
        Elements title = xmlDoc.select("title"); //要解析的tag元素為title
        Elements happy = xmlDoc.select("description");  //要解析的tag元素為description
        System.out.println(title.get(0).text()); //得到title tag的內容，"中央氣象局:XX市今明天氣預報"
        System.out.println("縣市名:"+title.get(0).text().split(":")[1].substring(0, 3));
        System.out.println(happy.get(1).text()); //得到td tag的內容，"今晚至明晨多雲..."
        System.out.println("今晚至明晨的timeInterval:"+happy.get(1).text().split(" ")[0]);
        System.out.println("今晚至明晨的weatherCondition:"+happy.get(1).text().split(" ")[1]);
        System.out.println("今晚至明晨的temperatureInterval:"+happy.get(1).text().split(":")[1].substring(1,8));
        System.out.println("今晚至明晨的rainfallProbability:"+happy.get(1).text().split(" ")[7]);
        System.out.println("明日白天的timeInterval:"+happy.get(1).text().split("\n")[1].split(" ")[0]);
        System.out.println("明日白天的weatherCondition:"+happy.get(1).text().split(" ")[9]);
        System.out.println("明日白天的temperatureInterval:"+happy.get(1).text().split(":")[3].substring(1,8));
        System.out.println("明日白天的rainfallProbability:"+happy.get(1).text().split(" ")[15]);
        //注意: 因為有好多個td 我想要取得的是<td>樂</td> 是第2個td 所以填get(1)
		Session session=factory.getCurrentSession();
        WebCrawlerForecast webCrawlerForecast=new WebCrawlerForecast();
        //今晚到明晨的
        webCrawlerForecast.setCity(title.get(0).text().split(":")[1].substring(0, 3));
        webCrawlerForecast.setTemperatureInterval(happy.get(1).text().split(":")[1].substring(1,8));
        webCrawlerForecast.setWeatherCondition(happy.get(1).text().split(" ")[1]);
        webCrawlerForecast.setRainfallProbability(happy.get(1).text().split(" ")[7]);
        webCrawlerForecast.setTimeInterval(happy.get(1).text().split(" ")[0]);
		session.save(webCrawlerForecast);
		//明日白天
        WebCrawlerForecast webCrawlerForecast2=new WebCrawlerForecast();
        webCrawlerForecast2.setCity(title.get(0).text().split(":")[1].substring(0, 3));
        webCrawlerForecast2.setTemperatureInterval(happy.get(1).text().split(":")[3].substring(1,8));
        webCrawlerForecast2.setWeatherCondition(happy.get(1).text().split(" ")[9]);
        webCrawlerForecast2.setRainfallProbability(happy.get(1).text().split(" ")[15]);
        webCrawlerForecast2.setTimeInterval(happy.get(1).text().split("\n")[1].split(" ")[0]);
		session.save(webCrawlerForecast2);
    	}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<WebCrawlerForecast> showForecast() {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<WebCrawlerForecast> createQuery = builder.createQuery(WebCrawlerForecast.class);
		Root<WebCrawlerForecast> fromClass = createQuery.from(WebCrawlerForecast.class);
//		createQuery.orderBy(builder.asc(fromClass.get("licensePlate")));
		createQuery.orderBy(builder.asc(fromClass.get("forcastSerialNum")));
		createQuery.select(fromClass);
//		.where(builder.greaterThanOrEqualTo(fromClass.get("currentMileage"), fromClass.get("maintenanceItem").get("requiredMileage")));
		Query<WebCrawlerForecast> queryword = factory.getCurrentSession().createQuery(createQuery);	
//		queryword.setParameter(branchName, shopName);
		List<WebCrawlerForecast> list = queryword.getResultList();
		return list;
	}

	@Override
	public String updateForecastData() {

//		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
//		CriteriaQuery<WebCrawlerForecast> createQuery2 = builder.createQuery(WebCrawlerForecast.class);
//		Root<WebCrawlerForecast> fromClass2 = createQuery2.from(WebCrawlerForecast.class);
//		createQuery2.select(fromClass2);
//		List<WebCrawlerForecast> ForecastDataList = factory.getCurrentSession().createQuery(createQuery2).getResultList();
//		System.out.println("102還行嗎?");
		String hql="update WebCrawlerForecast set temperatureInterval=:temperatureInterval , weatherCondition=:weatherCondition , rainfallProbability=:rainfallProbability where city=:city AND timeInterval=:timeInterval"; 
//		System.out.println(8);
		Session session=factory.getCurrentSession();
		
	try {
		for(int x = 1  ; x<=22;x++) {
    	if(x==2 || x==7 || x==8 || x==9 || x==10 || x==11 || x==12 || x==13 || x==15 || x==16 || x==17 || x==18 || x==19 || x==20 || x==21 || x==22) {
    		continue;
    	}//台北市=1,基隆市=3,新北市=4,桃園市=5,新竹縣=6,新竹市=14
    		
        URL url = new URL("https://www.cwb.gov.tw/rss/forecast/36_"+String.format("%02d", x)+".xml"); 
        Document xmlDoc;
		xmlDoc = Jsoup.parse(url, 3000);//使用Jsoup jar 去解析網頁
        //(要解析的文件,timeout)
        Elements title = xmlDoc.select("title"); //要解析的tag元素為title
        Elements happy = xmlDoc.select("description");  //要解析的tag元素為description
        System.out.println(title.get(0).text()); //得到title tag的內容，"中央氣象局:XX市今明天氣預報"
        System.out.println("縣市名:"+title.get(0).text().split(":")[1].substring(0, 3));
        System.out.println(happy.get(1).text()); //得到td tag的內容，"今晚至明晨多雲..."
        System.out.println("今晚至明晨的timeInterval:"+happy.get(1).text().split(" ")[0]);
        System.out.println("今晚至明晨的weatherCondition:"+happy.get(1).text().split(" ")[1]);
        System.out.println("今晚至明晨的temperatureInterval:"+happy.get(1).text().split(":")[1].substring(1,8));
        System.out.println("今晚至明晨的rainfallProbability:"+happy.get(1).text().split(" ")[7]);
        System.out.println("明日白天的timeInterval:"+happy.get(1).text().split("\n")[1].split(" ")[0]);
        System.out.println("明日白天的weatherCondition:"+happy.get(1).text().split(" ")[9]);
        System.out.println("明日白天的temperatureInterval:"+happy.get(1).text().split(":")[3].substring(1,8));
        System.out.println("明日白天的rainfallProbability:"+happy.get(1).text().split(" ")[15]);
        //注意: 因為有好多個td 我想要取得的是<td>樂</td> 是第2個td 所以填get(1)
		Query qw = session.createSQLQuery(hql)
				.setParameter("temperatureInterval", happy.get(1).text().split(":")[1].substring(1,8))
				.setParameter("weatherCondition", happy.get(1).text().split(" ")[1])
				.setParameter("rainfallProbability", happy.get(1).text().split(" ")[7])
				.setParameter("city", title.get(0).text().split(":")[1].substring(0, 3))
				.setParameter("timeInterval", happy.get(1).text().split(" ")[0]);
		qw.executeUpdate();
		
		Query qw2 = session.createSQLQuery(hql)
				.setParameter("temperatureInterval", happy.get(1).text().split(":")[3].substring(1,8))
				.setParameter("weatherCondition", happy.get(1).text().split(" ")[9])
				.setParameter("rainfallProbability", happy.get(1).text().split(" ")[15])
				.setParameter("city", title.get(0).text().split(":")[1].substring(0, 3))
				.setParameter("timeInterval", happy.get(1).text().split("\n")[1].split(" ")[0]);
		qw2.executeUpdate();
    	}
		System.out.println("YAAAAAAAAAAAAAAAAAAAAAAAAA");
//			for (WebCrawlerForecast loop : ForecastDataList) {	
//				Query qw = session.createSQLQuery(hql)
//						.setParameter("temperatureInterval", loop.getTemperatureInterval())
//						.setParameter("weatherCondition", loop.getWeatherCondition())
//						.setParameter("rainfallProbability", loop.getRainfallProbability())
//						.setParameter("city", loop.getCity())
//						.setParameter("timeInterval", loop.getTimeInterval());
//				qw.executeUpdate();
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "成功";
	}


	
}


