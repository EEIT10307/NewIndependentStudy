package everybikeInfo.robin.dao;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.BikeDetailToGson;
import everybikeInfo.robin.service.BikeDetailIFaceService;
import everybikeInfo.robin.service.EveryBikeInfoIFaceService;
import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.IdClassBikeDetail;

@Repository
public class BikeDetailDao implements BikeDetailIFaceDao {
	@Autowired
	SessionFactory Factory;
	@Autowired
	BikeDetailIFaceService bikeDetailIFaceService;
	@Autowired
	EveryBikeInfoIFaceService everyBikeInfoIFaceService;

	@Override
	public boolean isDup(String id) {
		Session session = Factory.getCurrentSession();
		boolean dup = false;
		String hgl = "FROM BikeDetail WHERE id=:id";
		List<BikeDetail> list = session.createQuery(hgl).setParameter("id", id).getResultList();
		if (list.size() > 0) {
			dup = true;
		}
		return dup;

	}

	@Override
	public int merge(BikeDetail bikeDetail) {
		Session session = Factory.getCurrentSession();

		int updateCount = 0;

		session.merge(bikeDetail);

		updateCount = 1;

		return updateCount;
	}

	@Override
	public List<BikeDetail> getAllMembers() {
		Session session = Factory.getCurrentSession();

		List<BikeDetail> allMembers = null;
		String hgl = "FROM BikeDetail";

		allMembers = session.createQuery(hgl).getResultList();

		return allMembers;

	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {

		Session session = Factory.getCurrentSession();
		int updateCount = 0;
		// 複合主鍵(bikeModel型號,modelYear年分) 一次輸入兩個
		IdClassBikeDetail idClassBikeDetail = new IdClassBikeDetail(bikeModel, modelYear);
		session.delete(idClassBikeDetail);
		updateCount++;

		return updateCount;
	}

	@Override
	public int updateBikeDetai(BikeDetailToGson bikeDetailToGson) {
		Session session = Factory.getCurrentSession();

		System.out.println(bikeDetailToGson.getBikeType());
		 BikeDetail op = everyBikeInfoIFaceService.selectbikeModelmodelYear(bikeDetailToGson.getBikeModel(), bikeDetailToGson.getModelYear());
		 Date QQ = op.getOnSheftTime();
		IdClassBikeDetail idClassBikeDetailnew = new IdClassBikeDetail(bikeDetailToGson.getBikeModel(),
				bikeDetailToGson.getModelYear());
		BikeDetail bike = new BikeDetail(idClassBikeDetailnew, bikeDetailToGson.getBikeBrand(),
				bikeDetailToGson.getEngineType(), bikeDetailToGson.getBikeType(), bikeDetailToGson.getPlateType(),
				bikeDetailToGson.getFuelTankCapacity(), bikeDetailToGson.getSeatHeight(),
				bikeDetailToGson.getDryWeight(), bikeDetailToGson.getFuelConsumption(), bikeDetailToGson.getTire(),
				bikeDetailToGson.getFuelType(), bikeDetailToGson.getaBS(), bikeDetailToGson.getHourPrice(),QQ);

//		BikeDetail bikeDetail=new BikeDetail(bikeDetailToGson.getBikeBrand()
//											,bikeDetailToGson.getEngineType(),bikeDetailToGson.getBikeType()
//											,bikeDetailToGson.getPlateType(),bikeDetailToGson.getFuelTankCapacity(),bikeDetailToGson.getSeatHeight()
//											,bikeDetailToGson.getDryWeight(),bikeDetailToGson.getFuelConsumption(),bikeDetailToGson.getTire()
//											,bikeDetailToGson.getFuelType(),bikeDetailToGson.getaBS(),bikeDetailToGson.getHourPrice());
//		String hql = "UPDATE BikeDetail set engineType=:engineType,bikeType=:bikeType,plateType=:plateType,fuelTankCapacity=:fuelTankCapacity,seatHeight=:seatHeight,dryWeight=:dryWeight,fuelConsumption=:fuelConsumption,tire=:tire,fuelType=:fuelType,ABS=:aBS,hourPrice=:hourPrice where bikeModel=:bikeModel and modelYear=:modelYear";
		int updateCount = 0;
		System.out.println(1);
//		Query ta = session.createQuery(hql).setParameter("engineType", bikeDetailToGson.getEngineType())
//				.setParameter("bikeType", bikeDetailToGson.getBikeType())
//				.setParameter("plateType", bikeDetailToGson.getPlateType())
//				.setParameter("fuelTankCapacity", bikeDetailToGson.getFuelTankCapacity())
//				.setParameter("seatHeight", bikeDetailToGson.getSeatHeight())
//				.setParameter("dryWeight", bikeDetailToGson.getDryWeight())
//				.setParameter("fuelConsumption", bikeDetailToGson.getFuelConsumption())
//				.setParameter("tire", bikeDetailToGson.getTire())
//				.setParameter("fuelType", bikeDetailToGson.getFuelType()).setParameter("aBS", bikeDetailToGson.getaBS())
//				.setParameter("hourPrice", bikeDetailToGson.getHourPrice())
//				.setParameter("bikeModel", bikeDetailToGson.getModelYear())
//				.setParameter("modelYear", bikeDetailToGson.getModelYear());

		// ta.executeUpdate();
		session.merge(bike);
		System.out.println(2);
		updateCount++;
		System.out.println(3);

		return updateCount;
	}

	@Override
	public int updateMember(BikeReview mb) {
		// TODO Auto-generated method stub
		return 0;

	}

}
