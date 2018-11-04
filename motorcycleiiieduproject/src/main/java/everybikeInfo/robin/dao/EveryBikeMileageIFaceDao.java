package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.BikeReview;
import projectbean.EveryBikeMileage;
import projectbean.MaintenanceDetail;
@Repository
public interface EveryBikeMileageIFaceDao {
	boolean isDup(String id);

	List<EveryBikeMileage> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	List<EveryBikeMileage> selectallname(String branchname0);

	int save(String everyBikeMileage);
}
