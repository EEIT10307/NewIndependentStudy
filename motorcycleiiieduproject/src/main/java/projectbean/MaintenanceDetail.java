package projectbean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MaintenanceDetail {
	
	private String maintenanceItem ; 
	private Double requiredMileage ;
	@Id
    @GeneratedValue(generator="mainid")
    @GenericGenerator(name="mainid",strategy="assigned") 
	public String getMaintenanceItem() {
		return maintenanceItem;
	}
	public void setMaintenanceItem(String maintenanceItem) {
		this.maintenanceItem = maintenanceItem;
	}
	@Column(nullable = false)
	public Double getRequiredMileage() {
		return requiredMileage;
	}
	public void setRequiredMileage(Double requiredMileage) {
		this.requiredMileage = requiredMileage;
	} 
	
	

}
