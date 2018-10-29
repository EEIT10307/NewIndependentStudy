package projectbean;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class IdClassBikeDetail implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String bikeModel ;
	private String modelYear;
	
	
	
	public IdClassBikeDetail() {
		super();
	
	}
	
	public IdClassBikeDetail(String bikeModel, String modelYear) {
		super();
		this.bikeModel = bikeModel;
		this.modelYear = modelYear;
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
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	/**
	 * 
	 */
	
}
