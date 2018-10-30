package projectbean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class BikeReview {
	
	private Integer bikeReviewSerialNum ; //流水號
	private OrderList orderSerialNum ; //多對一 對order 訂單流水
	private MemberDetail email ; //會員信箱
	private String reviewContent ; //評價內容
	private Double satisfacation ;//滿意度
	private java.util.Date reviewTime ; //評價時間
	private String bikeModel ; //型號
	
	
	public BikeReview() {
	
	}
	public BikeReview(OrderList orderSerialNum, MemberDetail email, String reviewContent,
			Double satisfacation, Date reviewTime, String bikeModel) {
		super();
		
		this.orderSerialNum = orderSerialNum;
		this.email = email;
		this.reviewContent = reviewContent;
		this.satisfacation = satisfacation;
		this.reviewTime = reviewTime;
		this.bikeModel = bikeModel;
	}
	@Column(nullable = false)
	public String getBikeModel() {
		return bikeModel;
	}
	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBikeReviewSerialNum() {
		return bikeReviewSerialNum;
	}
	public void setBikeReviewSerialNum(Integer bikeReviewSerialNum) {
		this.bikeReviewSerialNum = bikeReviewSerialNum;
	}
	@OneToOne
	@JoinColumn(referencedColumnName = "orderSerialNum" , nullable = false)
	public OrderList getOrderSerialNum() {
		return orderSerialNum;
	}
	public void setOrderSerialNum(OrderList orderSerialNum) {
		this.orderSerialNum = orderSerialNum;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName="email" , nullable = false)
	public MemberDetail getEmail() {
		return email;
	}
	public void setEmail(MemberDetail email) {
		this.email = email;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	@Column(nullable = false)
	public Double getSatisfacation() {
		return satisfacation;
	}
	public void setSatisfacation(Double satisfacation) {
		this.satisfacation = satisfacation;
	}
	@Column(nullable = false)
	public java.util.Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(java.util.Date reviewTime) {
		this.reviewTime = reviewTime;
	} 
	

}
