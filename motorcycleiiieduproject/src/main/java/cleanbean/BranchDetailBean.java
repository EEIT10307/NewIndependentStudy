package cleanbean;

public class BranchDetailBean {
	private String branchName;
	private String branchArea;
	private String branchCounty; 
	private String branchAddress; 
	private String branchPhone; 
	
	public BranchDetailBean() {
		super();
	}
	public BranchDetailBean(String branchName, String branchArea, String branchCounty,
			String branchAddress, String branchPhone) {
		super();
		this.branchName = branchName;
		this.branchArea = branchArea;
		this.branchCounty = branchCounty;
		this.branchAddress = branchAddress;
		this.branchPhone = branchPhone;
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
	
	@Override
	public String toString() {
		return "BranchDetailBean ["+"branchName:"+branchName+",+ branchArea:"
				+branchArea+",branchCounty:"+branchCounty+",branchAddress:"+branchAddress+
				",branchPhone:"+branchPhone+"]";
	}
}
