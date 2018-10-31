package testbean.robin.ch02;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.IdClassBikeDetail;
import testbean.robin.BikeDetailIFaceDao;
import testbean.robin.BikeDetailIFaceService;

@Service
@Transactional
public class BikeDetailService implements BikeDetailIFaceService {
	@Autowired
	BikeDetailIFaceDao bikeDetailIFaceDAO;

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
	public int save(String licensePlate, int branchName, String bikeModel, String modelYear, String bikeBrand,
			String engineType, String bikeType, String plateType, Double fuelTankCapacity, Double seatHeight,
			Double dryWeight, Double fuelConsumption, String tire, String fuelType, Boolean aBS, int hourPrice) {

		Session session = factory.getCurrentSession();
		BranchDetail branchDetail=session.get(BranchDetail.class, branchName);//分店 需要分店的流水號 加入機車 建構子
		EveryBikeInfo everyBikeInfo = new EveryBikeInfo(licensePlate, 0.0, false,branchDetail);// 機車個別資訊 新增
		IdClassBikeDetail idClassBikeDetail = new IdClassBikeDetail(bikeModel, modelYear);// 型號跟年份 複合主鍵
		Date now = new Date();
		
		BikeDetail bikeDetail = new BikeDetail(idClassBikeDetail,bikeBrand,engineType,bikeType, plateType,fuelTankCapacity,seatHeight,
				dryWeight,fuelConsumption,tire,fuelType,aBS, hourPrice,now);//機車 詳細資訊新增
		bikeDetail.addEveryBikeInfo(everyBikeInfo);
		return bikeDetailIFaceDAO.save(bikeDetail);
	}

}
