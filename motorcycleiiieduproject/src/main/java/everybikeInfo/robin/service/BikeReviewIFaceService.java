package everybikeInfo.robin.service;

import java.util.Date;
import java.util.List;

import cleanbean.BikeReviewForJson;
import projectbean.BikeReview;

public interface BikeReviewIFaceService {
	boolean isDup(String id);

	List<BikeReview> getAllMembers();

	BikeReview getMember(int pk);

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int save(String orderSerialNum, Integer member, String reviewContent, Double satisfacation, Date reviewTime);
	List<BikeReview> selectBikeReview(String bikeModel, String modelYear);
	List<BikeReviewForJson> BikeReviewForJson(List<BikeReview> BikeReview);

}
