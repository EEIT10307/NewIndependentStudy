package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import everybikeInfo.robin.dao.MaintenanceDetailDao;
import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.MaintenanceDetail;
@Service
@Transactional
public class MaintenanceDetailService implements MaintenanceDetailIFaceService {
	@Autowired
	MaintenanceDetailDao maintenanceDetailDao;
	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return maintenanceDetailDao.isDup(id);
	}

	@Override
	public List<MaintenanceDetail> getAllMembers() {
		// TODO Auto-generated method stub
		return maintenanceDetailDao.getAllMembers();
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return maintenanceDetailDao.deleteMember(bikeModel, modelYear);
	}

	@Override
	public int updateMember(BikeReview mb) {
		// TODO Auto-generated method stub
		return maintenanceDetailDao.updateMember(mb);
	}

	@Override
	public int save(BikeDetail bikeDetail) {
		// TODO Auto-generated method stub
		return maintenanceDetailDao.save(bikeDetail);
	}

}
