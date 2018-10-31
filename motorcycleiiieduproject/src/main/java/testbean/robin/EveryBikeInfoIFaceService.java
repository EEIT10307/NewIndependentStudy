package testbean.robin;

import java.util.List;

import projectbean.EveryBikeInfo;

public interface EveryBikeInfoIFaceService {
	boolean isDup(String id);

	List<EveryBikeInfo> getAllMembers();

	EveryBikeInfo getMember(int pk);

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(EveryBikeInfo mb);

	int save(EveryBikeInfo everyBikeInfo);
}
