package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.BikeDetailToGson;
import cleanbean.EveryBikeInfoToGson;
import projectbean.BikeDetail;
import projectbean.EveryBikeInfo;
@Repository
public interface EveryBikeInfoIFaceDao {
	List<EveryBikeInfo> selectModelAll(String Year);

	List<EveryBikeInfo> getAllMembers();

	public boolean getMemberOne(String LicensePlate);

	boolean checkbikeModelmodelYear(String bikeModel, String modelYear);

	BikeDetail selectbikeModelmodelYear(String bikeModel, String modelYear);

	int save(EveryBikeInfo everyBikeInfo);
	List<EveryBikeInfo> showAllEveryBikeInfo(String shopName);
	List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo);


	List<BikeDetailToGson> forGsonConvertBikeDetail(BikeDetail loop);

}
