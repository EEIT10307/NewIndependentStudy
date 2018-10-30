package testbean.robin;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.BikeDetail;
import projectbean.BikeReview;

@Repository
public interface BikeDetailIFaceDAO {
	boolean isDup(String id);

	List<BikeDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int save(BikeDetail bikeDetail);
}
