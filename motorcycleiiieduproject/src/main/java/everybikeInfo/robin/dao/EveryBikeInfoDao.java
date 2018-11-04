package everybikeInfo.robin.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.BikeDetailToGson;
import maintenance.EveryBikeInfoToGson;
import projectbean.BikeDetail;
import projectbean.EveryBikeInfo;

@Repository
public class EveryBikeInfoDao implements EveryBikeInfoIFaceDao {
	@Autowired
	SessionFactory Factory;

	@Override
	public List<EveryBikeInfo> selectModelAll(String bikeDetail_bikeModel) {
		Session session = Factory.getCurrentSession();
		String hgl = "FROM BikeDetail WHERE bikedetail0_.modelYear=:bikeDetail_bikeModel";
		@SuppressWarnings("unchecked")
		List<EveryBikeInfo> list = session.createQuery(hgl).setParameter("bikeDetail_bikeModel", bikeDetail_bikeModel)
				.getResultList();

		return list;
	}

	@Override
	public List<EveryBikeInfo> getAllMembers() {
		Session session = Factory.getCurrentSession();
		String hql = "From EveryBikeInfo";

		List<EveryBikeInfo> allMembers = null;
		allMembers = session.createQuery(hql).getResultList();

		return allMembers;
	}

	@Override
	public boolean getMemberOne(String LicensePlate) {
		Session session = Factory.getCurrentSession();
		boolean exist = false;

		String hql = "FROM EveryBikeInfo WHERE licensePlate=:licensePlate";
		try {
			EveryBikeInfo usera = (EveryBikeInfo) session.createQuery(hql).setParameter("licensePlate", LicensePlate)
					.getSingleResult();
			exist = true;
		} catch (NoResultException ex) {
			
		}
		return exist;
	}

	@Override
	public boolean checkbikeModelmodelYear(String bikeModel, String modelYear) {
		Session session = Factory.getCurrentSession();
		boolean exist = false;
		System.out.println(052550.0);
		String hql = "FROM BikeDetail WHERE bikeModel=:bikeModel and modelYear=:modelYear";
		try {
			@SuppressWarnings("unchecked")
			BikeDetail usera = (BikeDetail) session.createQuery(hql).setParameter("bikeModel", bikeModel)
					.setParameter("modelYear", modelYear).getSingleResult();
			exist = true;
		} catch (NoResultException ex) {
			
		}
		return exist;
	}

	@Override//BikeDetail
	public BikeDetail selectbikeModelmodelYear(String bikeModel, String modelYear) {
		Session session = Factory.getCurrentSession();
		String hql = "FROM BikeDetail WHERE bikeModel=:bikeModel and modelYear=:modelYear";
	
			@SuppressWarnings("unchecked")
			BikeDetail usera = (BikeDetail) session.createQuery(hql).setParameter("bikeModel", bikeModel)
					.setParameter("modelYear", modelYear).getSingleResult();
	
	
		
		
		return usera;
	}

	@Override
	public int save(EveryBikeInfo everyBikeInfo) {
		Session session = Factory.getCurrentSession();

		int upadte = 0;
		upadte++;
		session.save(everyBikeInfo);

		return upadte;
	}

	@Override
	public List<EveryBikeInfo> showAllEveryBikeInfo(String shopName) {
		// 建立查詢物件
		CriteriaBuilder builder = Factory.getCurrentSession().getCriteriaBuilder();
		// 查詢結果的型態
		CriteriaQuery<EveryBikeInfo> createQuery = builder.createQuery(EveryBikeInfo.class);
		// 查詢目標
		Root<EveryBikeInfo> fromClass = createQuery.from(EveryBikeInfo.class);
		// 定義查詢型態
		ParameterExpression<String> bikeModel = builder.parameter(String.class);
		// select * from everbikeinfo.class where
		// everbikeinfo.class.BranchName.branchName = 傳入店名
		createQuery.select(fromClass)
				.where(builder.equal(fromClass.get("bikeDetail").get("idClassBikeDetail").get("bikeModel"), bikeModel));
		// 查詢物件
		Query<EveryBikeInfo> queryword = Factory.getCurrentSession().createQuery(createQuery);
		// 定義參數
		queryword.setParameter(bikeModel, shopName);
		// 取出結果
		List<EveryBikeInfo> list = queryword.getResultList();

//		for (OrderList loop : list) {
//			System.out.println("showAllOrderFromShop ="+loop.getBikeModel()+":"+loop.getPickupDate()+"=>"+loop.getDropoffDate());
//		}
		return list;

	}

	@Override
	public List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo) {
		ArrayList<EveryBikeInfoToGson> everyBikeInfoToGson = new ArrayList<EveryBikeInfoToGson>();
		for (EveryBikeInfo loop : finalEveryBikeInfo) {
			everyBikeInfoToGson.add(new EveryBikeInfoToGson(loop.getLicensePlate(),
					loop.getBranchName().getBranchName(), loop.getTotalMileage(), loop.getIsReadyMaintenance(),
					loop.getBikeDetail().getIdClassBikeDetail().getModelYear()));
		}
		return everyBikeInfoToGson;
	}
	
	@Override
	public List<BikeDetailToGson> forGsonConvertBikeDetail(BikeDetail loop) {
		ArrayList<BikeDetailToGson> everyBikeInfoToGson = new ArrayList<BikeDetailToGson>();
		
			everyBikeInfoToGson.add(new BikeDetailToGson(loop.getIdClassBikeDetail().getBikeModel(),loop.getIdClassBikeDetail().getModelYear()
					,loop.getBikeBrand(),loop.getEngineType(),loop.getBikeType(),loop.getPlateType(),loop.getFuelTankCapacity(),loop.getSeatHeight()
					,loop.getDryWeight(),loop.getFuelConsumption(),loop.getTire(),loop.getFuelType(),loop.getABS(),loop.getHourPrice()));
	
		return everyBikeInfoToGson;
	}


}
