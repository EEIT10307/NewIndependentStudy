package cleanbean;

public class DispacherHistoryBean {

	private String pickupstore ; 
	private String dropoffstore ; 
	private String orderstatus ;
	public String getPickupstore() {
		return pickupstore;
	}
	public void setPickupstore(String pickupstore) {
		this.pickupstore = pickupstore;
	}
	public String getDropoffstore() {
		return dropoffstore;
	}
	public void setDropoffstore(String dropoffstore) {
		this.dropoffstore = dropoffstore;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	@Override
	public String toString() {
		return "DispacherHistoryBean [pickupstore=" + pickupstore + ", dropoffstore=" + dropoffstore + ", orderstatus="
				+ orderstatus + "]";
	} 
	
	
}
