package maintenance;



public class EveryBikeMileageToGson {
	private Integer everyBikeMileageSerialNum;
	private  String licensePlate ; 
	private  String maintenanceItem;
	private Double currentMileage ;
	public EveryBikeMileageToGson(Integer everyBikeMileageSerialNum, String licensePlate, String maintenanceItem,
			Double currentMileage) {
		super();
		this.everyBikeMileageSerialNum = everyBikeMileageSerialNum;
		this.licensePlate = licensePlate;
		this.maintenanceItem = maintenanceItem;
		this.currentMileage = currentMileage;
	}
	public Integer getEveryBikeMileageSerialNum() {
		return everyBikeMileageSerialNum;
	}
	public void setEveryBikeMileageSerialNum(Integer everyBikeMileageSerialNum) {
		this.everyBikeMileageSerialNum = everyBikeMileageSerialNum;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getMaintenanceItem() {
		return maintenanceItem;
	}
	public void setMaintenanceItem(String maintenanceItem) {
		this.maintenanceItem = maintenanceItem;
	}
	public Double getCurrentMileage() {
		return currentMileage;
	}
	public void setCurrentMileage(Double currentMileage) {
		this.currentMileage = currentMileage;
	}
	

	
	
	
}
