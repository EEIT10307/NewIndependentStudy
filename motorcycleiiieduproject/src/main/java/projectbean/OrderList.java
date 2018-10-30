package projectbean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderList {

	private String orderSerialNum;
	private String phone ; 
	private String bikeModel;
	private EveryBikeInfo licensePlate ;  // 多對一
	private java.util.Date pickupDate ; 
	private java.util.Date dropoffDate ; 
	private Double totalDiscount ; 
	private Integer bikePrice; 
	private String accessoriesAmount ; // 此欄位儲存json格式 k:v , k:v , ...
	private Integer accessoriesTotalPrice ; 
	private Integer orderTotalPrice ; 
	private java.util.Date orderTime ; 
	private String pickupStore ; 
	private String dropoffStore ; 
	private String discountName ; // 多對一
	private String orderStatus ;
	private boolean is_member ; 
	private boolean payOrNot; 

	
	public boolean isPayOrNot() {
		return payOrNot;
	}
	public void setPayOrNot(boolean payOrNot) {
		this.payOrNot = payOrNot;
	}
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	public String getOrderSerialNum() {
		return orderSerialNum;
	}
	public void setOrderSerialNum(String orderSerialNum) {
		this.orderSerialNum = orderSerialNum;
	}
	@Column(nullable = false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(nullable = false)
	public String getBikeModel() {
		return bikeModel;
	}
	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName = "licensePlate" , nullable = false)
	public EveryBikeInfo getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(EveryBikeInfo licensePlate) {
		this.licensePlate = licensePlate;
	}
	@Column(nullable = false)
	public java.util.Date getPickupDate() {
		return pickupDate;
	}
	public void setPickupDate(java.util.Date pickupDate) {
		this.pickupDate = pickupDate;
	}
	@Column(nullable = false)
	public java.util.Date getDropoffDate() {
		return dropoffDate;
	}
	public void setDropoffDate(java.util.Date dropoffDate) {
		this.dropoffDate = dropoffDate;
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	@Column(nullable = false)
	public Integer getBikePrice() {
		return bikePrice;
	}
	public void setBikePrice(Integer bikePrice) {
		this.bikePrice = bikePrice;
	}
	public String getAccessoriesAmount() {
		return accessoriesAmount;
	}
	public void setAccessoriesAmount(String accessoriesAmount) {
		this.accessoriesAmount = accessoriesAmount;
	}
	public Integer getAccessoriesTotalPrice() {
		return accessoriesTotalPrice;
	}
	public void setAccessoriesTotalPrice(Integer accessoriesTotalPrice) {
		this.accessoriesTotalPrice = accessoriesTotalPrice;
	}
	@Column(nullable = false)
	public Integer getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(Integer orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	@Column(nullable = false)
	public java.util.Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}
	@Column(nullable = false)
	public String getPickupStore() {
		return pickupStore;
	}
	public void setPickupStore(String pickupStore) {
		this.pickupStore = pickupStore;
	}
	@Column(nullable = false)
	public String getDropoffStore() {
		return dropoffStore;
	}
	public void setDropoffStore(String dropoffStore) {
		this.dropoffStore = dropoffStore;
	}

	public String getDiscountName() {
		return discountName;
	}
	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}
	@Column(nullable = false)
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Column(nullable = false)
	public boolean isIs_member() {
		return is_member;
	}
	public void setIs_member(boolean is_member) {
		this.is_member = is_member;
	} 
	
	
	
}
