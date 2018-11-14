package cleanbean;



public class EveryBikeMileageToGson {
	private Integer everyBikeMileageSerialNum;
	private String licensePlate ; 
	private String maintenanceItem;
	private Double currentMileage ;
	private Double requiredMileage;
	private String branchName;
	private Double requiredHourToDo;
	private Double totalMileage;
	public EveryBikeMileageToGson(Integer everyBikeMileageSerialNum, String licensePlate, String maintenanceItem,
			Double currentMileage,Double requiredMileage,String branchName,Double requiredHourToDo,Double totalMileage) {
		super();
		this.everyBikeMileageSerialNum = everyBikeMileageSerialNum;
		this.licensePlate = licensePlate;
		this.maintenanceItem = maintenanceItem;
		this.currentMileage = currentMileage;
		this.requiredMileage=requiredMileage;
		this.branchName=branchName;
		this.requiredHourToDo=requiredHourToDo;
		this.totalMileage=totalMileage;
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
	public Double getRequiredMileage() {
		return requiredMileage;
	}
	public void setRequiredMileage(Double requiredMileage) {
		this.requiredMileage = requiredMileage;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Double getRequiredHourToDo() {
		return requiredHourToDo;
	}
	public void setRequiredHourToDo(Double requiredHourToDo) {
		this.requiredHourToDo = requiredHourToDo;

	}
	public Double getTotalMileage() {
		return totalMileage;
	}
	public void setTotalMileage(Double totalMileage) {
		this.totalMileage = totalMileage;
	}
	

	
	
	
}
