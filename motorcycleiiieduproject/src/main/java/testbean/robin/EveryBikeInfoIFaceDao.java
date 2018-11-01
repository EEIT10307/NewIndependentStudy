package testbean.robin;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.EveryBikeInfo;
@Repository
public interface EveryBikeInfoIFaceDao {
	boolean isDup(String id);

	List<EveryBikeInfo> getAllMembers();

	EveryBikeInfo getMember(int pk);

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(EveryBikeInfo mb);

	int save(EveryBikeInfo everyBikeInfo);
}
