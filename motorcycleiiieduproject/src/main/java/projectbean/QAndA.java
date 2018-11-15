package projectbean;



import java.io.Serializable;
import java.util.Date;

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
	private String  questioner; //回答者姓名
	private String questionCotent; //回答內容
	 private BikeDetail bikeDetail;//型號和年份
	private String administratorID; //管理員ID
	private String answerContent; //回答內容
	private java.util.Date questionDate ; //問時間
	private java.util.Date answerTime ;//回答時間
	
	
	public QAndA() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public QAndA(String questioner, String questionCotent, BikeDetail bikeDetail,Date questionDate) {
		super();
		this.questioner = questioner;
		this.questionCotent = questionCotent;
		this.bikeDetail = bikeDetail;
	
		this.questionDate = questionDate;
	}



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
