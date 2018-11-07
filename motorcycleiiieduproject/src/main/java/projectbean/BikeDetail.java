package projectbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class BikeDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IdClassBikeDetail idClassBikeDetail ;
	private String bikeBrand ; 
	private String engineType ; 
	private String bikeType;
	private String plateType ; 
	private Double fuelTankCapacity ; 
	private Double seatHeight;
	private Double dryWeight;
	private Double fuelConsumption;
	private String frontTire ; 
    private String fuelType ; 
    private Boolean aBS ; 
    private Integer hourPrice;
    private java.util.Date onSheftTime;
    private String frontSuspension ; 
    private String rearSuspension ;
    private String rearTire ;
    private String horsePower ; 
    private String torque ;
    private String frontBrake;
    private String rearBrake;

    private List<EveryBikeInfo> everyBikeInfos = new ArrayList<EveryBikeInfo>();

 



 
	public BikeDetail(IdClassBikeDetail idClassBikeDetail) {
	
		this.idClassBikeDetail = idClassBikeDetail;
	}


	public BikeDetail(IdClassBikeDetail idClassBikeDetail, String bikeBrand, String engineType, String bikeType,
			String plateType, Double fuelTankCapacity, Double seatHeight, Double dryWeight, Double fuelConsumption,
			String frontTire, String fuelType, Boolean aBS, Integer hourPrice, Date onSheftTime, String frontSuspension,
			String rearSuspension, String rearTire, String horsePower, String torque, String frontBrake,
			String rearBrake, List<EveryBikeInfo> everyBikeInfos) {
		super();
		this.idClassBikeDetail = idClassBikeDetail;
		this.bikeBrand = bikeBrand;
		this.engineType = engineType;
		this.bikeType = bikeType;
		this.plateType = plateType;
		this.fuelTankCapacity = fuelTankCapacity;
		this.seatHeight = seatHeight;
		this.dryWeight = dryWeight;
		this.fuelConsumption = fuelConsumption;
		this.frontTire = frontTire;
		this.fuelType = fuelType;
		this.aBS = aBS;
		this.hourPrice = hourPrice;
		this.onSheftTime = onSheftTime;
		this.frontSuspension = frontSuspension;
		this.rearSuspension = rearSuspension;
		this.rearTire = rearTire;
		this.horsePower = horsePower;
		this.torque = torque;
		this.frontBrake = frontBrake;
		this.rearBrake = rearBrake;
		this.everyBikeInfos = everyBikeInfos;
	}


	public BikeDetail() {
		
	}

	public BikeDetail(IdClassBikeDetail idClassBikeDetail, String bikeBrand, String engineType, String bikeType,
			String plateType, Double fuelTankCapacity, Double seatHeight, Double dryWeight, Double fuelConsumption,
			String frontTire, String fuelType, Boolean aBS, Integer hourPrice, Date onSheftTime) {
		super();
		this.idClassBikeDetail = idClassBikeDetail;
		this.bikeBrand = bikeBrand;
		this.engineType = engineType;
		this.bikeType = bikeType;
		this.plateType = plateType;
		this.fuelTankCapacity = fuelTankCapacity;
		this.seatHeight = seatHeight;
		this.dryWeight = dryWeight;
		this.fuelConsumption = fuelConsumption;
		this.frontTire = frontTire;
		this.fuelType = fuelType;
		this.aBS = aBS;
		this.hourPrice = hourPrice;
		this.onSheftTime = onSheftTime;
	}

	
	public BikeDetail(IdClassBikeDetail idClassBikeDetail,String bikeBrand, String engineType, String bikeType,
			String plateType, Double fuelTankCapacity, Double seatHeight, Double dryWeight, Double fuelConsumption,
			String frontTire, String fuelType, Boolean aBS, Integer hourPrice) {
		super();
		this.idClassBikeDetail = idClassBikeDetail;
		this.bikeBrand = bikeBrand;
		this.engineType = engineType;
		this.bikeType = bikeType;
		this.plateType = plateType;
		this.fuelTankCapacity = fuelTankCapacity;
		this.seatHeight = seatHeight;
		this.dryWeight = dryWeight;
		this.fuelConsumption = fuelConsumption;
		this.frontTire = frontTire;
		this.fuelType = fuelType;
		this.aBS = aBS;
		this.hourPrice = hourPrice;
	
	}
	

	


	public void addEveryBikeInfo(EveryBikeInfo everyBikeInfo) {
    	everyBikeInfos.add(everyBikeInfo);
    	everyBikeInfo.setBikeDetail(this);
    }
 
    public void removeEveryBikeInfo(EveryBikeInfo everyBikeInfo) {
    	everyBikeInfos.remove(everyBikeInfo);
    	everyBikeInfo.setBikeDetail(this);
    }
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true )
	public List<EveryBikeInfo> getEveryBikeInfos() {
		return everyBikeInfos;
	}

	public void setEveryBikeInfos(List<EveryBikeInfo> everyBikeInfo) {
		this.everyBikeInfos = everyBikeInfo;
	}

	
    @EmbeddedId
	public IdClassBikeDetail getIdClassBikeDetail() {
		return idClassBikeDetail;
	}
	
	public void setIdClassBikeDetail(IdClassBikeDetail idClassBikeDetail) {
		this.idClassBikeDetail = idClassBikeDetail;
	}

	@Column(nullable = false)
	public String getBikeBrand() {
		return bikeBrand;
	}

	public void setBikeBrand(String bikeBrand) {
		this.bikeBrand = bikeBrand;
	}
	

	@Column(nullable = false)
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	@Column(nullable = false)
	public String getBikeType() {
		return bikeType;
	}
	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}
	@Column(nullable = false)
	public String getPlateType() {
		return plateType;
	}
	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}
	@Column(nullable = false)
	public Double getFuelTankCapacity() {
		return fuelTankCapacity;
	}
	public void setFuelTankCapacity(Double fuelTankCapacity) {
		this.fuelTankCapacity = fuelTankCapacity;
	}
	@Column(nullable = false)
	public Double getSeatHeight() {
		return seatHeight;
	}
	public void setSeatHeight(Double seatHeight) {
		this.seatHeight = seatHeight;
	}
	@Column(nullable = false)
	public Double getDryWeight() {
		return dryWeight;
	}
	public void setDryWeight(Double dryWeight) {
		this.dryWeight = dryWeight;
	}

	@Column(nullable = false)
	public Double getFuelConsumption() {
		return fuelConsumption;
	}
	public void setFuelConsumption(Double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	@Column(nullable = false)
	public String getFrontTire() {
		return frontTire;
	}
	public void setFrontTire(String frontTire) {
		this.frontTire = frontTire;
	}
	@Column(nullable = false)
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	@Column(nullable = false)
	public Boolean getABS() {
		return aBS;
	}
	public void setABS(Boolean aBS) {
		this.aBS = aBS;

	}
	@Column(nullable = false)
	public Integer getHourPrice() {
		return hourPrice;
	}
	public void setHourPrice(Integer hourPrice) {
		this.hourPrice = hourPrice;
	}
	@Column(nullable = false)
	public java.util.Date getOnSheftTime() {
		return onSheftTime;
	}
	public void setOnSheftTime(java.util.Date onSheftTime) {
		this.onSheftTime = onSheftTime;
	}
	
	public Boolean getaBS() {
		return aBS;
	}
	
	public void setaBS(Boolean aBS) {
		this.aBS = aBS;
	}
	
	public String getFrontSuspension() {
		return frontSuspension;
	}
	
	public void setFrontSuspension(String frontSuspension) {
		this.frontSuspension = frontSuspension;
	}
	
	public String getRearSuspension() {
		return rearSuspension;
	}
	
	public void setRearSuspension(String rearSuspension) {
		this.rearSuspension = rearSuspension;
	}
	
	public String getRearTire() {
		return rearTire;
	}
	
	public void setRearTire(String rearTire) {
		this.rearTire = rearTire;
	}
	
	public String getHorsePower() {
		return horsePower;
	}
	
	public void setHorsePower(String horsePower) {
		this.horsePower = horsePower;
	}
	
	public String getTorque() {
		return torque;
	}
	
	public void setTorque(String torque) {
		this.torque = torque;
	}
	
	public String getFrontBrake() {
		return frontBrake;
	}
	
	public void setFrontBrake(String frontBrake) {
		this.frontBrake = frontBrake;
	}
	
	public String getRearBrake() {
		return rearBrake;
	}
	
	public void setRearBrake(String rearBrake) {
		this.rearBrake = rearBrake;
	}
	
}
