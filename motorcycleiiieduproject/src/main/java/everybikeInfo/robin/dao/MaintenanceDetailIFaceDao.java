package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.MaintenanceDetail;
@Repository
public interface MaintenanceDetailIFaceDao {
	boolean isDup(String id);

	List<MaintenanceDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int save(BikeDetail bikeDetail);
}
