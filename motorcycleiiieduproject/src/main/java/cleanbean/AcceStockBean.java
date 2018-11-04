package cleanbean;

//robin 新增配件
public class AcceStockBean {
	private String acceName;
	private String branchName;
	private Integer acceNum;
	private String acceType;
	private Integer acceePrice;
	public AcceStockBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AcceStockBean(String acceName, String branchName, Integer acceNum, String acceType,
			Integer acceePrice) {
		super();
		this.acceName = acceName;
		this.branchName = branchName;
		this.acceNum = acceNum;
		this.acceType = acceType;
		this.acceePrice = acceePrice;
	}
	public String getAcceName() {
		return acceName;
	}
	public String getBranchName() {
		return branchName;
	}
	public Integer getAcceNum() {
		return acceNum;
	}
	public String getAcceType() {
		return acceType;
	}
	public Integer getAcceePrice() {
		return acceePrice;
	}
	public void setAcceName(String acceName) {
		this.acceName = acceName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setAcceNum(Integer acceNum) {
		this.acceNum = acceNum;
	}
	public void setAcceType(String acceType) {
		this.acceType = acceType;
	}
	public void setAcceePrice(Integer acceePrice) {
		this.acceePrice = acceePrice;
	}
	@Override
	public String toString() {
		
		return "AcceStockBean [acceName:"+acceName+",branchName:"+branchName+",acceNum:"+acceNum+",acceType:"+acceType+",acceePrice:"+acceePrice+"";
	}
	
	
}
