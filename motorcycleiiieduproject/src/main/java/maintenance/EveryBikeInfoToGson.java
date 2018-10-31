package maintenance;


public class EveryBikeInfoToGson{
	

	private String licensePlate ; 
    private String branchName ; 
    private Double totalMileage;
	private Boolean isReadyMaintenance;
	private String modelYear;
	

	public EveryBikeInfoToGson(String licensePlate, String branchName, Double totalMileage, Boolean isReadyMaintenance,
			String modelYear) {
		super();
		this.licensePlate = licensePlate;
		this.branchName = branchName;
		this.totalMileage = totalMileage;
		this.isReadyMaintenance = isReadyMaintenance;
		this.modelYear = modelYear;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Double getTotalMileage() {
		return totalMileage;
	}
	public void setTotalMileage(Double totalMileage) {
		this.totalMileage = totalMileage;
	}
	public Boolean getIsReadyMaintenance() {
		return isReadyMaintenance;
	}
	public void setIsReadyMaintenance(Boolean isReadyMaintenance) {
		this.isReadyMaintenance = isReadyMaintenance;
	}
	public String getModelYear() {
		return modelYear;
	}
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	

}
