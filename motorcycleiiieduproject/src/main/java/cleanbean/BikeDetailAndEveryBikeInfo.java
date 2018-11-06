package cleanbean;

import java.io.Serializable;

public class BikeDetailAndEveryBikeInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String licensePlate;// 車牌
	private Integer branchName;// 分店
	private String bikeModel;// 型號
	private String modelYear;// 年分
	private String bikeBrand;// 廠牌
	private String engineType;// CC數
	private String bikeType;// 車種
	private String plateType;// 紅黃白牌
	private Double fuelTankCapacity;// 油箱容量
	private Double seatHeight;// 座高
	private Double dryWeight;// 乾重
	private Double fuelConsumption;// 油耗
	private String tire;// 輪胎
	private String fuelType;// 使用燃料
	private Boolean aBS;// 煞車
	private Integer hourPrice;// 每小時價格

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getBranchName() {
		return branchName;
	}

	public void setBranchName(Integer branchName) {
		this.branchName = branchName;
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

}
