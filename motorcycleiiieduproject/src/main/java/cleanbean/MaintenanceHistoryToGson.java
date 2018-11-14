package cleanbean;

import java.util.Date;

import projectbean.EveryBikeInfo;

public class MaintenanceHistoryToGson{
	private Integer maintenanceHistorySerialNum ;
	private String licensePlate;
	private String historyMaintenanceItem ; 
	private String maintenanceDate ; 
	private Double totalMileage;
	public MaintenanceHistoryToGson(Integer maintenanceHistorySerialNum, String licensePlate,
			String historyMaintenanceItem, String maintenanceDate, Double totalMileage) {
		super();
		this.maintenanceHistorySerialNum = maintenanceHistorySerialNum;
		this.licensePlate = licensePlate;
		this.historyMaintenanceItem = historyMaintenanceItem;
		this.maintenanceDate = maintenanceDate;
		this.totalMileage = totalMileage;
	}
	public Integer getMaintenanceHistorySerialNum() {
		return maintenanceHistorySerialNum;
	}
	public void setMaintenanceHistorySerialNum(Integer maintenanceHistorySerialNum) {
		this.maintenanceHistorySerialNum = maintenanceHistorySerialNum;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getHistoryMaintenanceItem() {
		return historyMaintenanceItem;
	}
	public void setHistoryMaintenanceItem(String historyMaintenanceItem) {
		this.historyMaintenanceItem = historyMaintenanceItem;
	}
	public String getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public Double getTotalMileage() {
		return totalMileage;
	}
	public void setTotalMileage(Double totalMileage) {
		this.totalMileage = totalMileage;
	}


}
