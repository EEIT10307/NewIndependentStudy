package cleanbean;

public class BikeDetailToGson {
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
	
	public BikeDetailToGson() {}
	
	
	
	
	public BikeDetailToGson(String bikeModel, String modelYear, String bikeBrand, String engineType, String bikeType,
			String plateType, Double fuelTankCapacity, Double seatHeight, Double dryWeight, Double fuelConsumption,
			String tire, String fuelType, Boolean aBS, Integer hourPrice) {
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
	@Override
	public String toString() {
		return "BikeDetailToGson [bikeModel=" + bikeModel +",modelYear="+modelYear+",bikeBrand="+bikeBrand+",engineType="+engineType+",bikeType="+bikeType+""
				+ ",plateType="+plateType+",fuelTankCapacity="+fuelTankCapacity+",seatHeight="+seatHeight+",dryWeight="+dryWeight+""
				+ ",fuelConsumption="+fuelConsumption+",tire="+tire+",fuelType="+fuelType+",abs="+aBS+",hourPrice="+hourPrice+"]";
	}
}
