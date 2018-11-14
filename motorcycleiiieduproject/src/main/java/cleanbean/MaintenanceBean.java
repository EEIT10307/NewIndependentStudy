package cleanbean;

public class MaintenanceBean {
	private String maintenanceStore;
	private String licensePlate;
	private Double increasedMileage;
	public String getMaintenanceStore() {
		return maintenanceStore;
	}

	public void setMaintenanceStore(String maintenanceStore) {
		this.maintenanceStore = maintenanceStore;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Double getIncreasedMileage() {
		return increasedMileage;
	}

	public void setIncreasedMileage(Double increasedMileage) {
		this.increasedMileage = increasedMileage;
	}

	@Override
	public String toString() {
		return "MaintenanceBean [maintenanceStore=" + maintenanceStore + ", licensePlate=" + licensePlate
				+ ", increasedMileage=" + increasedMileage + "]";
	}

}
