package timercontroll;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class AutoBug {

	    public static void Parsingkeelung() throws Exception {
	        URL url = new URL("https://www.cwb.gov.tw/rss/forecast/36_03.xml"); 
	        Document xmlDoc =  Jsoup.parse(url, 3000); //使用Jsoup jar 去解析網頁
	        //(要解析的文件,timeout)
	        Elements title = xmlDoc.select("title"); //要解析的tag元素為title
	        Elements happy = xmlDoc.select("description");  //要解析的tag元素為description
	             
	        System.out.println("Title is "+title.get(0).text()); //得到title tag的內容
	        System.out.println("you select mood is "+happy.get(1).text()); //得到td tag的內容
	        //注意: 因為有好多個td 我想要取得的是<td>樂</td> 是第2個td 所以填get(1)
	        
	        
	 
	    }
	    
	    
	    
	    
	
}
