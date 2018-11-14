package projectbean;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class WebCrawlerForecast implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer forcastSerialNum;
	private String city;
	private String timeInterval;
	private String weatherCondition;
	private String temperatureInterval;
	private String rainfallProbability;
	
	public WebCrawlerForecast() {

	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getForcastSerialNum() {
		return forcastSerialNum;
	}

	public void setForcastSerialNum(Integer forcastSerialNum) {
		this.forcastSerialNum = forcastSerialNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}

	public String getWeatherCondition() {
		return weatherCondition;
	}

	public void setWeatherCondition(String weatherCondition) {
		this.weatherCondition = weatherCondition;
	}

	public String getTemperatureInterval() {
		return temperatureInterval;
	}

	public void setTemperatureInterval(String temperatureInterval) {
		this.temperatureInterval = temperatureInterval;
	}



	public String getRainfallProbability() {
		return rainfallProbability;
	}
	public void setRainfallProbability(String rainfallProbability) {
		this.rainfallProbability = rainfallProbability;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
   

}
