package orderdao;



public class BasicOrderBean {
	
	private String pickupDate ; 
	private String dropofDate ; 
	private String pickupStore ; 
	private String dropoffStore ;
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
	public String getPickupStore() {
		return pickupStore;
	}
	public void setPickupStore(String pickupStore) {
		this.pickupStore = pickupStore;
	}
	public String getDropoffStore() {
		return dropoffStore;
	}
	public void setDropoffStore(String dropoffStore) {
		this.dropoffStore = dropoffStore;
	}
	@Override
	public String toString() {
		return "TestBasicOrderBean [pickupDate=" + pickupDate + ", dropoffDate=" + dropofDate + ", pickupStore="
				+ pickupStore + ", dropoffStore=" + dropoffStore + "]";
	} 
	
	
}
