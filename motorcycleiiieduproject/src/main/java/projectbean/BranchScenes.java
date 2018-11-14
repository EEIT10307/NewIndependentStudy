package projectbean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BranchScenes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer branchDetailSerialNum;
	private BranchDetail branchName ; 
	private String spotArea;
	private String spotName; 
	private String spotAddress; 
	private String spotPhoto;
	private String spotDetail;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBranchDetailSerialNum() {
		return branchDetailSerialNum;
	}
	public void setBranchDetailSerialNum(Integer branchDetailSerialNum) {
		this.branchDetailSerialNum = branchDetailSerialNum;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName  = "branchName" , nullable = true)

	public BranchDetail getBranchName() {
		return branchName;
	}
	public void setBranchName(BranchDetail branchName) {
		this.branchName = branchName;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getSpotAddress() {
		return spotAddress;
	}
	public void setSpotAddress(String spotAddress) {
		this.spotAddress = spotAddress;
	}
	@Column(columnDefinition="varchar(max)")
	public String getSpotPhoto() {
		return spotPhoto;
	}
	public void setSpotPhoto(String spotPhoto) {
		this.spotPhoto = spotPhoto;
	}
	@Column(columnDefinition="varchar(max)")
	public String getSpotDetail() {
		return spotDetail;
	}
	public void setSpotDetail(String spotDetail) {
		this.spotDetail = spotDetail;
	}
	public String getSpotArea() {
		return spotArea;
	}
	public void setSpotArea(String spotArea) {
		this.spotArea = spotArea;
	}
}
