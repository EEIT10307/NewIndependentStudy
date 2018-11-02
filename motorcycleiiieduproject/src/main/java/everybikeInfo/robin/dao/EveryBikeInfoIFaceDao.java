package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import maintenance.EveryBikeInfoToGson;
import projectbean.EveryBikeInfo;
@Repository
public interface EveryBikeInfoIFaceDao {
	List<EveryBikeInfo> selectModelAll(String Year);

	List<EveryBikeInfo> getAllMembers();

	EveryBikeInfo getMember(int pk);

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(EveryBikeInfo mb);

	int save(EveryBikeInfo everyBikeInfo);
	List<EveryBikeInfo> showAllEveryBikeInfo(String shopName);
	List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo);
}
