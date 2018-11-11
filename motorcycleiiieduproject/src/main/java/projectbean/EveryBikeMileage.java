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
public class EveryBikeMileage implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer everyBikeMileageSerialNum;
	private  EveryBikeInfo licensePlate ; 
	private  MaintenanceDetail maintenanceItem;
	private Double currentMileage ;
	
	
	
	
	public EveryBikeMileage() {

		
	}
	public EveryBikeMileage(EveryBikeInfo licensePlate, MaintenanceDetail maintenanceItem, Double currentMileage) {
		super();
		this.licensePlate = licensePlate;
		this.maintenanceItem = maintenanceItem;
		this.currentMileage = currentMileage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getEveryBikeMileageSerialNum() {
		return everyBikeMileageSerialNum;
	}
	public void setEveryBikeMileageSerialNum(Integer everyBikeMileageSerialNum) {
		this.everyBikeMileageSerialNum = everyBikeMileageSerialNum;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName  = "licensePlate" ,nullable = false)
	public EveryBikeInfo getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(EveryBikeInfo licensePlate) {
		this.licensePlate = licensePlate;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName  = "maintenanceItem" , nullable = false)
	public MaintenanceDetail getMaintenanceItem() {
		return maintenanceItem;
	}
	public void setMaintenanceItem(MaintenanceDetail maintenanceItem) {
		this.maintenanceItem = maintenanceItem;
	}
	@Column(nullable = false)
	public Double getCurrentMileage() {
		return currentMileage;
	}
	public void setCurrentMileage(Double currentMileage) {
		this.currentMileage = currentMileage;
	} 

	
	
}
