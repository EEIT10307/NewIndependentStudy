package testbean;

import java.util.Date;

public class BikeDetailToGson {
	
	private String bikeModel ;
	private String modelYear;
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
    private Boolean aBS ; 
    private Integer hourPrice;
    private java.util.Date onSheftTime;
    
    
    
    
	/**
	 * @param bikeModel
	 * @param modelYear
	 * @param bikeBrand
	 * @param engineType
	 * @param bikeType
	 * @param plateType
	 * @param fuelTankCapacity
	 * @param seatHeight
	 * @param dryWeight
	 * @param fuelConsumption
	 * @param tire
	 * @param fuelType
	 * @param aBS
	 * @param hourPrice
	 * @param onSheftTime
	 */
	public BikeDetailToGson(String bikeModel, String modelYear, String bikeBrand, String engineType, String bikeType,
			String plateType, Double fuelTankCapacity, Double seatHeight, Double dryWeight, Double fuelConsumption,
			String tire, String fuelType, Boolean aBS, Integer hourPrice, Date onSheftTime) {
		super();
		this.bikeModel = bikeModel;
		this.modelYear = modelYear;
		this.bikeBrand = bikeBrand;
		this.engineType = engineType;
		this.bikeType = bikeType;
		this.plateType = plateType;
		this.fuelTankCapacity = fuelTankCapacity;
		this.seatHeight = seatHeight;
		this.dryWeight = dryWeight;
		this.fuelConsumption = fuelConsumption;
		this.tire = tire;
		this.fuelType = fuelType;
		this.aBS = aBS;
		this.hourPrice = hourPrice;
		this.onSheftTime = onSheftTime;
	}
	public String getBikeModel() {
		return bikeModel;
	}
	public void setBikeModel(String bikeModel) {
		this.bikeModel = bikeModel;
	}
	public String getModelYear() {
		return modelYear;
	}
	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}
	public String getBikeBrand() {
		return bikeBrand;
	}
	public void setBikeBrand(String bikeBrand) {
		this.bikeBrand = bikeBrand;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public String getBikeType() {
		return bikeType;
	}
	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}
	public String getPlateType() {
		return plateType;
	}
	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}
	public Double getFuelTankCapacity() {
		return fuelTankCapacity;
	}
	public void setFuelTankCapacity(Double fuelTankCapacity) {
		this.fuelTankCapacity = fuelTankCapacity;
	}
	public Double getSeatHeight() {
		return seatHeight;
	}
	public void setSeatHeight(Double seatHeight) {
		this.seatHeight = seatHeight;
	}
	public Double getDryWeight() {
		return dryWeight;
	}
	public void setDryWeight(Double dryWeight) {
		this.dryWeight = dryWeight;
	}
	public Double getFuelConsumption() {
		return fuelConsumption;
	}
	public void setFuelConsumption(Double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	public String getTire() {
		return tire;
	}
	public void setTire(String tire) {
		this.tire = tire;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Boolean getaBS() {
		return aBS;
	}
	public void setaBS(Boolean aBS) {
		this.aBS = aBS;
	}
	public Integer getHourPrice() {
		return hourPrice;
	}
	public void setHourPrice(Integer hourPrice) {
		this.hourPrice = hourPrice;
	}
	public java.util.Date getOnSheftTime() {
		return onSheftTime;
	}
	public void setOnSheftTime(java.util.Date onSheftTime) {
		this.onSheftTime = onSheftTime;
	}
	

}
