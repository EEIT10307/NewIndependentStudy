package everybikeInfo.robin.service;

import java.util.List;

import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.MaintenanceDetail;

public interface MaintenanceDetailIFaceService {
	boolean isDup(String id);

	List<MaintenanceDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int save(BikeDetail bikeDetail);
}
