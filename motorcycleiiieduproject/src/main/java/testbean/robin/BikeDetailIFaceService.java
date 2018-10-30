package testbean.robin;

import java.util.List;

import projectbean.BikeDetail;
import projectbean.BikeReview;

public interface BikeDetailIFaceService {
	boolean isDup(String id);

	List<BikeDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int save(BikeDetail bikeDetail);
}
