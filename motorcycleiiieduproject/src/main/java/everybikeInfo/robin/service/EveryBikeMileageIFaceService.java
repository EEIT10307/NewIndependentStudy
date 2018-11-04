package everybikeInfo.robin.service;

import java.util.List;

import projectbean.EveryBikeMileage;
import projectbean.MaintenanceDetail;

public interface EveryBikeMileageIFaceService {
	boolean isDup(String id);

	List<MaintenanceDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	List<EveryBikeMileage> selectallname(String branchname0);

	int save(String everyBikeMileage);
}
