package webcrawler;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TTTTTTTTT {

	public static void main(String[] args) throws IOException {
		
		 URL url = new URL("https://tw.yahoo.com"); 
	        Document xmlDoc;
			xmlDoc = Jsoup.parse(url, 3000);//使用Jsoup jar 去解析網頁
	        //(要解析的文件,timeout)
	        Elements title = xmlDoc.select("span"); //要解析的tag元素為title
	        Elements happy = xmlDoc.select("description");  //要解析的tag元素為description
	        System.out.println(title); //得到title tag的內容，"中央氣象局:XX市今明天氣預報"
//	        System.out.println("縣市名:"+title.get(0).text().split(":")[1].substring(0, 3));
//	        System.out.println(happy.get(1).text()); //得到td tag的內容，"今晚至明晨多雲..."
//	        System.out.println("今晚至明晨的timeInterval:"+happy.get(1).text().split(" ")[0]);
//	        System.out.println("今晚至明晨的weatherCondition:"+happy.get(1).text().split(" ")[1]);
//	        System.out.println("今晚至明晨的temperatureInterval:"+happy.get(1).text().split(":")[1].substring(1,8));
//	        System.out.println("今晚至明晨的rainfallProbability:"+happy.get(1).text().split(" ")[7]);
//	        System.out.println("明日白天的timeInterval:"+happy.get(1).text().split("\n")[1].split(" ")[0]);
//	        System.out.println("明日白天的weatherCondition:"+happy.get(1).text().split(" ")[9]);
//	        System.out.println("明日白天的temperatureInterval:"+happy.get(1).text().split(":")[3].substring(1,8));
//	        System.out.println("明日白天的rainfallProbability:"+happy.get(1).text().split(" ")[15]);

	}

}
