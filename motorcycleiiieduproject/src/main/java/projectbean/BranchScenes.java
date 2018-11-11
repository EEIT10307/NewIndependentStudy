package projectbean;


import java.io.Serializable;

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
	private String spotName; 
	private String spotAddress; 
	private String spotPhoto;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBranchDetailSerialNum() {
		return branchDetailSerialNum;
	}
	public void setBranchDetailSerialNum(Integer branchDetailSerialNum) {
		this.branchDetailSerialNum = branchDetailSerialNum;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName  = "branchName" , nullable = false)


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
	public String getSpotPhoto() {
		return spotPhoto;
	}
	public void setSpotPhoto(String spotPhoto) {
		this.spotPhoto = spotPhoto;
	}
	
	
	
}
