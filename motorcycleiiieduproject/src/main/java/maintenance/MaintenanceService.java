package maintenance;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;

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
	public int insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage) {
		return testMaintenanceDAO.insertNEWMaintenanceDetail(maintenanceItem, requiredMileage);
	}

	@Override
	public List<EveryBikeMileage> showEveryBikeMileagebyStore(String shopName) {
		return testMaintenanceDAO.showEveryBikeMileagebyStore(shopName);
	}

	@Override
	public List<EveryBikeMileageToGson> everyBikeMileageforGsonConvert(List<EveryBikeMileage> finalEveryBikeMileage) {
		return testMaintenanceDAO.everyBikeMileageforGsonConvert(finalEveryBikeMileage);
	}

	@Override
	public List<EveryBikeInfoToGson> everyBikeInfoforGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo) {
		// TODO Auto-generated method stub
		return testMaintenanceDAO.everyBikeInfoforGsonConvert(finalEveryBikeInfo);
	}


}
