package branchdetaildao;

import projectbean.BranchDetail;


public class BranchScenesToGson {
	
	private Integer branchDetailSerialNum;
	private BranchDetail branchName ; 
	private String spotName; 
	private String spotAddress; 
	private String spotPhoto;

	public BranchScenesToGson(Integer branchDetailSerialNum, BranchDetail branchName, String spotName,
			String spotAddress, String spotPhoto) {
		 super();
		 this.setBranchDetailSerialNum(branchDetailSerialNum);
		 this.setBranchName(branchName);
		 this.setSpotName(spotName);
		 this.setSpotAddress(spotAddress);
		 this.setSpotPhoto(spotPhoto);
	}

	public Integer getBranchDetailSerialNum() {
		return branchDetailSerialNum;
	}

	public void setBranchDetailSerialNum(Integer branchDetailSerialNum) {
		this.branchDetailSerialNum = branchDetailSerialNum;
	}

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
