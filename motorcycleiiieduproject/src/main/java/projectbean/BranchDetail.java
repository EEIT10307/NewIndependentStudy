package projectbean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"branchName"} ) })
public class BranchDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer branchSerialNum;
	private String branchName;
	private String branchArea;
	private String branchCounty; 
	private String branchAddress; 
	private String branchPhone; 

	private java.util.Date openingDay;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBranchSerialNum() {
		return branchSerialNum;
	}
	public void setBranchSerialNum(Integer branchSerialNum) {
		this.branchSerialNum = branchSerialNum;
	}
	@Column(nullable = false)
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(nullable = false)
	public String getBranchArea() {
		return branchArea;
	}
	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}
	@Column(nullable = false)
	public String getBranchCounty() {
		return branchCounty;
	}
	public void setBranchCounty(String branchCounty) {
		this.branchCounty = branchCounty;
	}
	@Column(nullable = false)
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	@Column(nullable = false)
	public String getBranchPhone() {
		return branchPhone;
	}
	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}
	@Column(nullable = false)
	public java.util.Date getOpeningDay() {
		return openingDay;
	}
	public void setOpeningDay(java.util.Date openingDay) {
		this.openingDay = openingDay;
	}
	
	
	
}
