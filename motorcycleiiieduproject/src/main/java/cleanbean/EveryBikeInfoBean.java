package cleanbean;

public class EveryBikeInfoBean {
	private String everyBikeInfoModel;
//有事問robin  
	public String getEveryBikeInfoModel() {
		return everyBikeInfoModel;
	}

	public void setEveryBikeInfoModel(String everyBikeInfoModel) {
		this.everyBikeInfoModel = everyBikeInfoModel;
	}

	@Override
	public String toString() {
		return "EveryBikeInfoBean [everyBikeInfoModel=" + everyBikeInfoModel + "]";
	}

}
