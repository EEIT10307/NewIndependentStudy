package maintenance;

import java.util.List;

import projectbean.EveryBikeInfo;

public interface MaintenanceIFaceDAO {
	List<String> showAllBikePlate();
	List<EveryBikeInfo>showAllisReadyMaintenanceBike(String shopName);
	List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo);
}
