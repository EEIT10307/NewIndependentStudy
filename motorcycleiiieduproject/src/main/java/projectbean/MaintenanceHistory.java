package projectbean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MaintenanceHistory implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer maintenanceHistorySerialNum ;
	private  EveryBikeInfo licensePlate;
	private String historyMaintenanceItem ; 
	private java.util.Date maintenanceDate ; 
	private Double totalMileage;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getMaintenanceHistorySerialNum() {
		return maintenanceHistorySerialNum;
	}
	public void setMaintenanceHistorySerialNum(Integer maintenanceHistorySerialNum) {
		this.maintenanceHistorySerialNum = maintenanceHistorySerialNum;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName  = "licensePlate" , nullable = false)
	public EveryBikeInfo getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(EveryBikeInfo licensePlate) {
		this.licensePlate = licensePlate;
	}
	@Column(nullable = false)
	public String getHistoryMaintenanceItem() {
		return historyMaintenanceItem;
	}
	public void setHistoryMaintenanceItem(String historyMaintenanceItem) {
		this.historyMaintenanceItem = historyMaintenanceItem;
	}
	@Column(nullable = false)
	public java.util.Date getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(java.util.Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	@Column(nullable = false)
	public Double getTotalMileage() {
		return totalMileage;
	}
	public void setTotalMileage(Double totalMileage) {
		this.totalMileage = totalMileage;
	}
	
	

}
