package everybikeInfo.robin.service;

import java.util.List;

import cleanbean.BikeDetailToGson;
import projectbean.BikeDetail;
import projectbean.EveryBikeInfo;

public interface EveryBikeInfoIFaceService {
	List<EveryBikeInfo> selectModelAll(String Year);

	List<EveryBikeInfo> getAllMembers();

	public boolean getMemberOne(String LicensePlate);

	BikeDetail selectbikeModelmodelYear(String bikeModel, String modelYear);

	boolean checkbikeModelmodelYear(String bikeModel, String modelYear);

	int save(String licensePlate, int branchName, String bikeModel, String modelYear);
	List<EveryBikeInfo> showAllEveryBikeInfo(String shopName);
//	List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo);
	List<BikeDetailToGson> forGsonConvertBikeDetail(BikeDetail loop);
}
