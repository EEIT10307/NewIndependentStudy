package cleanbean;

public class FinOrderBean {
	
	private String orderstatus ; 
	private String ordersernum ;
	private String newmileage ;
	
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getOrdersernum() {
		return ordersernum;
	}
	public void setOrdersernum(String ordersernum) {
		this.ordersernum = ordersernum;
	}
	
	
	public String getNewmileage() {
		return newmileage;
	}
	public void setNewmileage(String newmileage) {
		this.newmileage = newmileage;
	}
	@Override
	public String toString() {
		return "Dispatcher [orderstatus=" + orderstatus + ", ordersernum=" + ordersernum + ", newmileage=" + newmileage
				+ "]";
	}

	

}
