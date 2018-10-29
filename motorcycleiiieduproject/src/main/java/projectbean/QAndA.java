package projectbean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class QAndA implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer qAndASerialNum ; 
	private String  questioner; 
	private String questionCotent; 
	 private BikeDetail bikeDetail;
	private String administratorID; 
	private String answerContent; 
	private java.util.Date questionDate ; 
	private java.util.Date answerTime ;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getQAndASerialNum() {
		return qAndASerialNum;
	}
	public void setQAndASerialNum(Integer qAndASerialNum) {
		this.qAndASerialNum = qAndASerialNum;
	}
	@Column(nullable = false)
	public String getQuestioner() {
		return questioner;
	}
	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}
	@Column(nullable = false , columnDefinition="varchar(MAX)")
	public String getQuestionCotent() {
		return questionCotent;
	}
	public void setQuestionCotent(String questionCotent) {
		this.questionCotent = questionCotent;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	public BikeDetail getBikeDetail() {
		return bikeDetail;
	}
	
	public void setBikeDetail(BikeDetail bikeDetail) {
		this.bikeDetail = bikeDetail;
	}
	
	public String getAdministratorID() {
		return administratorID;
	}
	public void setAdministratorID(String administratorID) {
		this.administratorID = administratorID;
	}
	@Column(columnDefinition="varchar(MAX)")
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	@Column(nullable = false)
	public java.util.Date getQuestionDate() {
		return questionDate;
	}
	public void setQuestionDate(java.util.Date questionDate) {
		this.questionDate = questionDate;
	}
	public java.util.Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(java.util.Date answerTime) {
		this.answerTime = answerTime;
	} 
	
	
	

}
