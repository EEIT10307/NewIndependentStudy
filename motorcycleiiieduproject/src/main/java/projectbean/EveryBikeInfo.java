package projectbean;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EveryBikeInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String licensePlate ; 
    private BikeDetail bikeDetail;
    private BranchDetail branchName ; 
    private Double totalMileage;
	private Boolean isReadyMaintenance;
	
	
	
	public EveryBikeInfo() {
				
	}
	
	public EveryBikeInfo(String licensePlate , Double totalMileage, Boolean isReadyMaintenance , BranchDetail branchName ) {
		super();
		this.licensePlate = licensePlate ; 
		this.totalMileage = totalMileage;
		this.isReadyMaintenance = isReadyMaintenance;	
		this.branchName = branchName ; 
	
	}

	public EveryBikeInfo(String licensePlate  ) {
		this.licensePlate = licensePlate ; 
	
	}
	
	
	@Id
//    @GeneratedValue(generator="eveid")
//    @GenericGenerator(name="eveid",strategy="assigned") 
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	public BikeDetail getBikeDetail() {
		return bikeDetail;
	}
	
	public void setBikeDetail(BikeDetail bikeDetail) {
		this.bikeDetail = bikeDetail;
	}
	

	
	@Column(nullable = false)
	public Double getTotalMileage() {
		return totalMileage;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( referencedColumnName = "branchName")
	public BranchDetail getBranchName() {
		return branchName;
	}

	public void setBranchName(BranchDetail branchName) {
		this.branchName = branchName;
	}

	public void setTotalMileage(Double totalMileage) {
		this.totalMileage = totalMileage;
	}
	@Column(nullable = false)
	public Boolean getIsReadyMaintenance() {
		return isReadyMaintenance;
	}
	public void setIsReadyMaintenance(Boolean isReadyMaintenance) {
		this.isReadyMaintenance = isReadyMaintenance;
	} 
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof EveryBikeInfo )) return false;
	        return licensePlate != null && licensePlate.equals(((EveryBikeInfo) o).licensePlate);
	    }
	    @Override
	    public int hashCode() {
	        return 31;
	    }
	
	
}
