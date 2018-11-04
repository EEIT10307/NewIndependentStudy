package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.BikeDetailToGson;
import projectbean.BikeDetail;
import projectbean.BikeReview;

@Repository
public interface BikeDetailIFaceDao {
	boolean isDup(String id);

	List<BikeDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int merge(BikeDetail bikeDetail);

	int updateBikeDetai(BikeDetailToGson bikeDetailToGson);
}
