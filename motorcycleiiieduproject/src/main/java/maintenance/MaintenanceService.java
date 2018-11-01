package maintenance;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;

@Service
@Transactional
public class MaintenanceService implements MaintenanceIFaceService {
	
	@Autowired
	SessionFactory factory;
	
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
	public List<EveryBikeInfoToGson> everyBikeInfoforGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo) {
		return testMaintenanceDAO.everyBikeInfoforGsonConvert(finalEveryBikeInfo);
		
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

}
