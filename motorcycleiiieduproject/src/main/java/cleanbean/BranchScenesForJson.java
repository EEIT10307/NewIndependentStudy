package cleanbean;

public class BranchScenesForJson {
	private String branchName ; 
	private String spotArea;
	private String spotName; 
	private String spotAddress; 
	private String spotPhoto;
	private String spotDetail;
	
	public BranchScenesForJson(String branchName, String spotArea, String spotName, String spotAddress,
			String spotPhoto, String spotDetail) {
		super();
		this.branchName = branchName;
		this.spotArea = spotArea;
		this.spotName = spotName;
		this.spotAddress = spotAddress;
		this.spotPhoto = spotPhoto;
		this.spotDetail = spotDetail;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getSpotArea() {
		return spotArea;
	}
	public void setSpotArea(String spotArea) {
		this.spotArea = spotArea;
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
	public String getSpotDetail() {
		return spotDetail;
	}
	public void setSpotDetail(String spotDetail) {
		this.spotDetail = spotDetail;
	}
	
}
