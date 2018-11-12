package cleanbean;

import projectbean.EveryBikeInfo;

public class OrderListToGson {
	
	private String orderSerialNum;
	private String phone ; 
	private String bikeModel;
	private String licensePlate ;  // 多對一
	private String pickupDate ; 
	private String dropoffDate ; 
	private Double totalDiscount ; 
	private Integer bikePrice; 
	private String accessoriesAmount ; // 此欄位儲存json格式 k:v , k:v , ...
	private Integer accessoriesTotalPrice ; 
	private Integer orderTotalPrice ; 
	private String orderTime ; 
	private String pickupStore ; 
	private String dropoffStore ; 
	private String discountName ; // 多對一
	private String orderStatus ;
	private boolean is_member ; 
	private boolean payOrNot;
	
	
	
	
	
	/**
	 * 
	 */
	public OrderListToGson() {
	
		// TODO Auto-generated constructor stub
	}





	/**
	 * @param orderSerialNum
	 * @param phone
	 * @param bikeModel
	 * @param licensePlate
	 * @param pickupDate
	 * @param dropoffDate
	 * @param totalDiscount
	 * @param bikePrice
	 * @param accessoriesAmount
	 * @param accessoriesTotalPrice
	 * @param orderTotalPrice
	 * @param orderTime
	 * @param pickupStore
	 * @param dropoffStore
	 * @param discountName
	 * @param orderStatus
	 * @param is_member
	 * @param payOrNot
	 */
	public OrderListToGson(String orderSerialNum, String phone, String bikeModel, String licensePlate,
			String pickupDate, String dropoffDate, Double totalDiscount, Integer bikePrice, String accessoriesAmount,
			Integer accessoriesTotalPrice, Integer orderTotalPrice, String orderTime, String pickupStore,
			String dropoffStore, String discountName, String orderStatus, boolean is_member, boolean payOrNot) {
		super();
		this.orderSerialNum = orderSerialNum;
		this.phone = phone;
		this.bikeModel = bikeModel;
		this.licensePlate = licensePlate;
		this.pickupDate = pickupDate;
		this.dropoffDate = dropoffDate;
		this.totalDiscount = totalDiscount;
		this.bikePrice = bikePrice;
		this.accessoriesAmount = accessoriesAmount;
		this.accessoriesTotalPrice = accessoriesTotalPrice;
		this.orderTotalPrice = orderTotalPrice;
		this.orderTime = orderTime;
		this.pickupStore = pickupStore;
		this.dropoffStore = dropoffStore;
		this.discountName = discountName;
		this.orderStatus = orderStatus;
		this.is_member = is_member;
		this.payOrNot = payOrNot;
	}





	/**
	 * @param orderSerialNum
	 * @param phone
	 * @param bikeModel
	 * @param pickupDate
	 * @param dropoffDate
	 * @param totalDiscount
	 * @param bikePrice
	 * @param accessoriesAmount
	 * @param accessoriesTotalPrice
	 * @param orderTotalPrice
	 * @param orderTime
	 * @param pickupStore
	 * @param dropoffStore
	 * @param discountName
	 * @param orderStatus
	 * @param is_member
	 * @param payOrNot
	 */
	public OrderListToGson(String orderSerialNum, String phone, String bikeModel, String pickupDate, String dropoffDate,
			Double totalDiscount, Integer bikePrice, String accessoriesAmount, Integer accessoriesTotalPrice,
			Integer orderTotalPrice, String orderTime, String pickupStore, String dropoffStore, String discountName,
			String orderStatus, boolean is_member, boolean payOrNot) {
		super();
		this.orderSerialNum = orderSerialNum;
		this.phone = phone;
		this.bikeModel = bikeModel;
		this.pickupDate = pickupDate;
		this.dropoffDate = dropoffDate;
		this.totalDiscount = totalDiscount;
		this.bikePrice = bikePrice;
		this.accessoriesAmount = accessoriesAmount;
		this.accessoriesTotalPrice = accessoriesTotalPrice;
		this.orderTotalPrice = orderTotalPrice;
		this.orderTime = orderTime;
		this.pickupStore = pickupStore;
		this.dropoffStore = dropoffStore;
		this.discountName = discountName;
		this.orderStatus = orderStatus;
		this.is_member = is_member;
		this.payOrNot = payOrNot;
	}





	public String getOrderSerialNum() {
		return orderSerialNum;
	}





	public void setOrderSerialNum(String orderSerialNum) {
		this.orderSerialNum = orderSerialNum;
	}





	public String getPhone() {
		return phone;
	}





	public void setPhone(String phone) {
		this.phone = phone;
	}





	public String getBikeModel() {
		return bikeModel;
	}





	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}





	public String getPickupDate() {
		return pickupDate;
	}





	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}





	public String getDropoffDate() {
		return dropoffDate;
	}





	public void setDropoffDate(String dropoffDate) {
		this.dropoffDate = dropoffDate;
	}





	public Double getTotalDiscount() {
		return totalDiscount;
	}





	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}





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





	public Integer getOrderTotalPrice() {
		return orderTotalPrice;
	}





	public void setOrderTotalPrice(Integer orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}





	public String getOrderTime() {
		return orderTime;
	}





	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
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





	public String getDiscountName() {
		return discountName;
	}





	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}





	public String getOrderStatus() {
		return orderStatus;
	}





	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}





	public boolean isIs_member() {
		return is_member;
	}





	public void setIs_member(boolean is_member) {
		this.is_member = is_member;
	}





	public boolean isPayOrNot() {
		return payOrNot;
	}





	public void setPayOrNot(boolean payOrNot) {
		this.payOrNot = payOrNot;
	}
	
	
	
	
}
