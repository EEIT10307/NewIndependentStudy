package maintenance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectbean.EveryBikeInfo;

@Service
@Transactional
public class MaintenanceService implements MaintenanceIFaceService {
	
	
	
	@Autowired
	MaintenanceIFaceDAO testMaintenanceDAO;
	
	@Override
	public List<String> showAllBikePlate() {
		return testMaintenanceDAO.showAllBikePlate();
	}

	@Override
	public List<EveryBikeInfo> showAllisReadyMaintenanceBike(String shopName) {
		return testMaintenanceDAO.showAllisReadyMaintenanceBike(shopName);
	}

	@Override
	public List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo) {
		return testMaintenanceDAO.forGsonConvert(finalEveryBikeInfo);
		
	}

}
