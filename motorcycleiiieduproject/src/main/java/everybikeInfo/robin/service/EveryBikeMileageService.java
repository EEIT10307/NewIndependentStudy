package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import everybikeInfo.robin.dao.EveryBikeMileageIFaceDao;
import projectbean.BikeReview;
import projectbean.EveryBikeMileage;
import projectbean.MaintenanceDetail;

@Service
@Transactional
public class EveryBikeMileageService implements EveryBikeMileageIFaceService {
	@Autowired
	EveryBikeMileageIFaceDao everyBikeMileageIFaceDao;

	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MaintenanceDetail> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EveryBikeMileage> selectallname(String branchname0){
		return everyBikeMileageIFaceDao.selectallname(branchname0);
	}



	
	

	@Override
	public int save(String everyBikeMileage) {
		// TODO Auto-generated method stub
		return everyBikeMileageIFaceDao.save(everyBikeMileage);
	}

}
