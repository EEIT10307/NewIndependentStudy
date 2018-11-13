package projectbean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class WebInformationForManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String webElements ; //存id
	private String webContent ; //存id內容
	private String webSrc;
	
	
	@Id
    @GeneratedValue(generator="webid")
    @GenericGenerator(name="webid",strategy="assigned") 
	public String getWebElements() {
		return webElements;
	}
	public void setWebElements(String webElements) {
		this.webElements = webElements;
	}
	@Column(columnDefinition="varchar(max)")
	public String getWebContent() {
		return webContent;
	}
	public void setWebContent(String webContent) {
		this.webContent = webContent;
	}
	
	public String getWebSrc() {
		return webSrc;
	}
	public void setWebSrc(String webSrc) {
		this.webSrc = webSrc;
	}
	
   

}
