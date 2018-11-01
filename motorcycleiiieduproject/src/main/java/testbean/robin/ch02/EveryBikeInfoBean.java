package testbean.robin.ch02;

public class EveryBikeInfoBean {
	private String everyBikeInfoModel;

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
