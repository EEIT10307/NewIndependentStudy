package cleanbean;



public class ManagerDispatcherBean {
	
	private String pickupDate ; 
	private String dropofDate ; 
	private String neededbranchName ; 

	
	
	
	/**
	 * 
	 */
	public ManagerDispatcherBean() {
		super();
		// TODO Auto-generated constructor stub
	}




	@Override
	public String toString() {
		return "BasicOrderBean2 [pickupDate=" + pickupDate + ", dropofDate=" + dropofDate + ", neededbranchName="
				+ neededbranchName + "]";
	}




	public String getPickupDate() {
		return pickupDate;
	}




	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}




	public String getDropofDate() {
		return dropofDate;
	}




	public void setDropofDate(String dropofDate) {
		this.dropofDate = dropofDate;
	}




	public String getNeededbranchName() {
		return neededbranchName;
	}




	public void setNeededbranchName(String neededbranchName) {
		this.neededbranchName = neededbranchName;
	}
	
	
}
