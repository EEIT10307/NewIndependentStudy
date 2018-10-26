package projectbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"bikeBrand"} ) })
public class BikeDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IdClassBikeDetail IdClassBikeDetail ;
	private String bikeBrand ; 
	private String engineType ; 
	private String bikeType;
	private String plateType ; 
	private Double fuelTankCapacity ; 
	private Double seatHeight;
	private Double dryWeight;
	private Double fuelConsumption;
	private String tire ; 
    private String fuelType ; 
    private Boolean ABS ; 
    private Integer hourPrice;
    private java.util.Date onSheftTime;

    private List<EveryBikeInfo> everyBikeInfos = new ArrayList<>();

 



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
		return IdClassBikeDetail;
	}
	
	public void setIdClassBikeDetail(IdClassBikeDetail idClassBikeDetail) {
		IdClassBikeDetail = idClassBikeDetail;
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
	public String getTire() {
		return tire;
	}
	public void setTire(String tire) {
		this.tire = tire;
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
		return ABS;
	}
	public void setABS(Boolean aBS) {
		ABS = aBS;
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
	
	
}
