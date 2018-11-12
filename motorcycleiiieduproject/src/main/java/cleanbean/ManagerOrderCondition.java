package cleanbean;

import java.io.Serializable;

public class ManagerOrderCondition  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	

}
