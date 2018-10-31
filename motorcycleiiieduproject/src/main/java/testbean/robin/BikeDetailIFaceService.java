package testbean.robin;

import java.util.List;

import projectbean.BikeDetail;
import projectbean.BikeReview;

public interface BikeDetailIFaceService {
	boolean isDup(String id);

	List<BikeDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int save(String licensePlate, int branchName, String bikeModel,
			String modelYear, String bikeBrand, String engineType, String bikeType, String plateType,
			Double fuelTankCapacity, Double seatHeight, Double dryWeight, Double fuelConsumption, String tire,
			String fuelType, Boolean aBS, int  hourPrice);
}
