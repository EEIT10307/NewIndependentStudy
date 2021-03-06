package maintenance;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cleanbean.EveryBikeInfoToGson;
import cleanbean.EveryBikeMileageToGson;
import cleanbean.MaintenanceHistoryToGson;
import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;
import projectbean.MaintenanceHistory;

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
	public int insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage,Double requiredHourTodo) {
		return testMaintenanceDAO.insertNEWMaintenanceDetail(maintenanceItem, requiredMileage,requiredHourTodo);
	}

	@Override
	public List<EveryBikeMileage> showEveryBikeMileagebyStore(String shopName) {
		return testMaintenanceDAO.showEveryBikeMileagebyStore(shopName);
	}
	
	@Override
	public List<EveryBikeInfo> showEveryBikeBasicInfobyStore(String shopName) {

		return testMaintenanceDAO.showEveryBikeBasicInfobyStore(shopName);
	}
	
	@Override
	public List<EveryBikeMileage> showEveryMaintenanceItembyPlate(String licensePlate) {
		return testMaintenanceDAO.showEveryMaintenanceItembyPlate(licensePlate);
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

	@Override
	public List<EveryBikeMileage> showMessageIfMileageIsOver() {
		return testMaintenanceDAO.showMessageIfMileageIsOver();
	}
	@Override
	public List<EveryBikeMileage> showMessageIfMileageIsOverAfterComplete(String licensePlate) {
		return testMaintenanceDAO.showMessageIfMileageIsOverAfterComplete(licensePlate);
	}

	@Override
	public String updateBikeMileage(String licensePlate,Double increasedMileage ) {
		return testMaintenanceDAO.updateBikeMileage(licensePlate, increasedMileage);
	}

	@Override
	public int sendMaintenance(String licensePlate) {
		return testMaintenanceDAO.sendMaintenance(licensePlate);
	}

	@Override
	public List<EveryBikeMileage> showBikeMaintenancingItem(String licensePlate) {
		return testMaintenanceDAO.showBikeMaintenancingItem(licensePlate);
	}

	@Override
	public int completeMaintenance(String licensePlate) throws ParseException {
		return testMaintenanceDAO.completeMaintenance(licensePlate);
	}

	@Override
	public List<MaintenanceHistory> showAllMaintenanceHistory() {
		return testMaintenanceDAO.showAllMaintenanceHistory();
	}

	@Override
	public List<MaintenanceHistoryToGson> maintenanceHistoryforGsonConvert(List<MaintenanceHistory> finalMaintenanceHistory) throws Exception {

		return testMaintenanceDAO.maintenanceHistoryforGsonConvert(finalMaintenanceHistory);
	}

	@Override
	public boolean selectEveryBikeInfo(String licensePlate) {
		// TODO Auto-generated method stub
		return testMaintenanceDAO.selectEveryBikeInfo(licensePlate);
	}






}
