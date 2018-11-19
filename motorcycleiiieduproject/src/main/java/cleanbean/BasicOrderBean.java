package cleanbean;



public class BasicOrderBean {
	
	private String pickupDate ; 
	private String dropofDate ; 
	private String pickupStore ; 
	private String dropoffStore ;
	
	
	
	
	/**
	 * 
	 */
	public BasicOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param pickupDate
	 * @param dropofDate
	 * @param pickupStore
	 * @param dropoffStore
	 */
	public BasicOrderBean(String pickupDate, String dropofDate, String pickupStore, String dropoffStore) {
		super();
		this.pickupDate = pickupDate;
		this.dropofDate = dropofDate;
		this.pickupStore = pickupStore;
		this.dropoffStore = dropoffStore;
	}
	public String getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}
	public String getDropoffDate() {
		return dropofDate;
	}
	public void setDropoffDate(String dropofDate) {
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
