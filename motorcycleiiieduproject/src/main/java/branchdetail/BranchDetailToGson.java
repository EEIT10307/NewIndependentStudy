package branchdetail;

import java.util.Date;

public class BranchDetailToGson {
	
	private Integer branchSerialNum;
	private String branchName;
	private String branchArea ;
	private String branchCounty ; 
	private String branchAddress ; 
	private String branchPhone ; 
	private java.util.Date openingDay;
	
	public BranchDetailToGson(Integer branchSerialNum, String branchName, String branchArea, String branchCounty,
			String branchAddress, String branchPhone, Date openingDay) {
		super();
		this.branchSerialNum = branchSerialNum;
		this.branchName = branchName;
		this.branchArea = branchArea;
		this.branchCounty = branchCounty;
		this.branchAddress = branchAddress;
		this.branchPhone = branchPhone;
		this.openingDay = openingDay;
	}
	
	public Integer getBranchSerialNum() {
		return branchSerialNum;
	}
	public void setBranchSerialNum(Integer branchSerialNum) {
		this.branchSerialNum = branchSerialNum;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchArea() {
		return branchArea;
	}

	public void setBranchArea(String branchArea) {
		this.branchArea = branchArea;
	}

	public String getBranchCounty() {
		return branchCounty;
	}

	public void setBranchCounty(String branchCounty) {
		this.branchCounty = branchCounty;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchPhone() {
		return branchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

	public java.util.Date getOpeningDay() {
		return openingDay;
	}

	public void setOpeningDay(java.util.Date openingDay) {
		this.openingDay = openingDay;
	}

}
