package everybikeInfo.robin.service;

import java.util.List;

import maintenance.EveryBikeInfoToGson;
import projectbean.EveryBikeInfo;

public interface EveryBikeInfoIFaceService {
	List<EveryBikeInfo> selectModelAll(String Year);

	List<EveryBikeInfo> getAllMembers();

	EveryBikeInfo getMember(int pk);

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(EveryBikeInfo mb);

	int save(String licensePlate, int branchName, String bikeModel, String modelYear);
	List<EveryBikeInfo> showAllEveryBikeInfo(String shopName);
	List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo);
}
