package maintenance;

import java.text.ParseException;
import java.util.List;

import cleanbean.EveryBikeInfoToGson;
import cleanbean.EveryBikeMileageToGson;
import cleanbean.MaintenanceHistoryToGson;
import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;
import projectbean.MaintenanceDetail;
import projectbean.MaintenanceHistory;

public interface MaintenanceIFaceDAO {
	List<String> showAllBikePlate();
	List<EveryBikeInfo>showAllisReadyMaintenanceBike(String shopName);
	List<EveryBikeMileage>showBikeMaintenancingItem(String licensePlate);//用車牌秀該車保養中的項目
	List<EveryBikeInfoToGson> everyBikeInfoforGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo);
	int insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage,Double requiredHourTodo);
	List<EveryBikeMileage>showEveryBikeMileagebyStore(String shopName);
	List<EveryBikeMileageToGson> everyBikeMileageforGsonConvert(List<EveryBikeMileage> finalEveryBikeMileage);
	List<EveryBikeMileage>showMessageIfMileageIsOver();
	List<EveryBikeMileage>showMessageIfMileageIsOverAfterComplete(String licensePlate);
	String updateBikeMileage(String licensePlate,Double increasedMileage);
	int sendMaintenance(String licensePlate);
	int completeMaintenance(String licensePlate) throws ParseException;
	List<MaintenanceHistory>showAllMaintenanceHistory();
	List<MaintenanceHistoryToGson> maintenanceHistoryforGsonConvert(List<MaintenanceHistory> finalMaintenanceHistory) throws Exception;
}
