package cleanbean;

public class ShowManagerChangeOrderStatus {
	
	private String orderstatus ; 
	private String ordersernum ;
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
	@Override
	public String toString() {
		return "ShowManagerChangeOrderStatus [orderstatus=" + orderstatus + ", ordersernum=" + ordersernum + "]";
	}
	
	

}
