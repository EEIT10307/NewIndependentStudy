package maintenance;

import java.util.List;


import cleanbean.EveryBikeInfoToGson;
import cleanbean.EveryBikeMileageToGson;
import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;

public interface MaintenanceIFaceService {
	List<String> showAllBikePlate();
	List<EveryBikeInfo> showAllisReadyMaintenanceBike(String shopName);

	List<EveryBikeInfoToGson> everyBikeInfoforGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo);
	int insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage);
	List<EveryBikeMileage>showEveryBikeMileagebyStore(String shopName);
	List<EveryBikeMileageToGson> everyBikeMileageforGsonConvert(List<EveryBikeMileage> finalEveryBikeMileage);



}
