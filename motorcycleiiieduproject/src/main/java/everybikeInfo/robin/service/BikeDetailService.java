package everybikeInfo.robin.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cleanbean.BikeDetailAndEveryBikeInfo;
import cleanbean.BikeDetailToGson;
import cleanbean.QaBean;
import cleanbean.QaBeanToJson;
import everybikeInfo.robin.dao.BikeDetailIFaceDao;
import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.IdClassBikeDetail;
import projectbean.QAndA;

@Service
@Transactional
public class BikeDetailService implements BikeDetailIFaceService {
	@Autowired
	BikeDetailIFaceDao bikeDetailIFaceDAO;
	@Autowired
	EveryBikeMileageIFaceService everyBikeMileageIFaceService;
	@Autowired
	SessionFactory factory;

	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.isDup(id);
	}

	@Override
	public List<BikeDetail> getAllMembers() {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.getAllMembers();
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.deleteMember(bikeModel, modelYear);
	}

	@Override
	public int updateMember(BikeReview mb) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.updateMember(mb);
	}

	@Override
	public int save(BikeDetailAndEveryBikeInfo[] bikeDetailAndEveryBikeInfo) {

		Session session = factory.getCurrentSession();

		String licensePlate;
		Integer branchName;
		String bikeModel;
		String modelYear;
		String bikeBrand;
		String engineType;
		String bikeType;
		String plateType;
		Double fuelTankCapacity;
		Double dryWeight;
		Double seatHeight;
		Double fuelConsumption;
		String tire;
		String fuelType;
		Boolean aBS;
		Integer hourPrice;
		
		String frontSuspension;
		String rearSuspension;
		String rearTire;
		String horsePower;
		String torque;
		String frontBrake;
		String rearBrake;
		int count = 0;
		count++;

		for (BikeDetailAndEveryBikeInfo root : bikeDetailAndEveryBikeInfo) {
			
			licensePlate = root.getLicensePlate();
			branchName = root.getBranchName();
			bikeModel = root.getBikeModel();
			modelYear = root.getModelYear();
			bikeBrand = root.getBikeBrand();
			engineType = root.getEngineType();
			bikeType = root.getBikeType();
			plateType = root.getPlateType();
			fuelTankCapacity = root.getFuelTankCapacity();
			seatHeight = root.getSeatHeight();
			dryWeight = root.getDryWeight();
			fuelConsumption = root.getFuelConsumption();
			tire = root.getTire();
			fuelType = root.getFuelType();
			aBS = root.getaBS();
			hourPrice = root.getHourPrice();
			
			frontSuspension=root.getFrontSuspension();
			rearSuspension=root.getRearSuspension();
			rearTire=root.getRearTire();
			horsePower=root.getHorsePower();
			torque=root.getTorque();
			frontBrake=root.getFrontBrake();
			rearBrake=root.getRearBrake();

//			BranchDetail branchDetail = session.get(BranchDetail.class, branchName);// 分店 需要分店的流水號 加入機車 建構子
//			EveryBikeInfo everyBikeInfo = new EveryBikeInfo(licensePlate, 0.0, false, branchDetail);// 機車個別資訊 新增
			IdClassBikeDetail idClassBikeDetail = new IdClassBikeDetail(bikeModel, modelYear);// 型號跟年份 複合主鍵
			Date now = new Date();
			BikeDetail bikeDetail = new BikeDetail(idClassBikeDetail, bikeBrand, engineType, bikeType, plateType,
					fuelTankCapacity, seatHeight, dryWeight, fuelConsumption, tire, fuelType, aBS, hourPrice, now,frontSuspension,rearSuspension,
					rearTire,horsePower,torque,frontBrake,rearBrake);// 機車
																													// 詳細資訊新增
//			bikeDetail.addEveryBikeInfo(everyBikeInfo);
		
			return bikeDetailIFaceDAO.merge(bikeDetail);
			
		}
		return count;

	}

	@Override
	public int updateBikeDetai(BikeDetailToGson bikeDetailToGson) {

		return bikeDetailIFaceDAO.updateBikeDetai(bikeDetailToGson);

	}

	@Override
	public int insertQA(QaBean qaBean) {
		return bikeDetailIFaceDAO.insertQA(qaBean);
	}

	@Override
	public List<QAndA> selectQA(String BikeModel, String ModelYear) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.selectQAwhere(BikeModel, ModelYear);
	}

	@Override
	public List<QaBeanToJson> QaBeanToJson(List<QAndA> QAndA) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.QaBeanToJson(QAndA);
	}

	@Override
	public int updateQA(int qAndASerialNum, String ans, String ansquction) {

		return bikeDetailIFaceDAO.updateQA(qAndASerialNum, ans, ansquction);
	}

}
