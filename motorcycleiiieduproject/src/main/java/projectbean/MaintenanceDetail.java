package projectbean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class MaintenanceDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maintenanceItem ; 
	private Double requiredMileage ;
	@Id

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
