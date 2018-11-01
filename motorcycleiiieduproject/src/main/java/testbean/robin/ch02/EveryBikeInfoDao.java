package testbean.robin.ch02;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import maintenance.EveryBikeInfoToGson;
import projectbean.EveryBikeInfo;
import testbean.robin.EveryBikeInfoIFaceDao;

@Repository
public class EveryBikeInfoDao implements EveryBikeInfoIFaceDao {
	@Autowired
	SessionFactory Factory;

	@Override
	public List<EveryBikeInfo> selectModelAll(String bikeDetail_bikeModel) {
		Session session=Factory.getCurrentSession();
		String hgl = "FROM BikeDetail WHERE bikedetail0_.modelYear=:bikeDetail_bikeModel";
		@SuppressWarnings("unchecked")
		List<EveryBikeInfo> list = session.createQuery(hgl).setParameter("bikeDetail_bikeModel", bikeDetail_bikeModel).getResultList();
		
		return list;
	}

	@Override
	public List<EveryBikeInfo> getAllMembers() {
		Session session=Factory.getCurrentSession();
		String hql = "From EveryBikeInfo";
		
		List<EveryBikeInfo> allMembers = null;
		allMembers=session.createQuery(hql).getResultList();
		
		return allMembers;
	}

	@Override
	public  EveryBikeInfo getMember(int pk) {
		
		return null ;
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(EveryBikeInfo mb) {
		// TODO Auto-generated method stub
		return 0; 
	}

	@Override
	public int save(EveryBikeInfo everyBikeInfo) {
		Session session=Factory.getCurrentSession();
		int updateCount = 0;

		session.save(everyBikeInfo);

		updateCount = 1;

		return updateCount;
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
		createQuery.select(fromClass).where(builder.equal(fromClass.get("bikeDetail").get("idClassBikeDetail").get("bikeModel"), bikeModel));
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
		ArrayList<EveryBikeInfoToGson> everyBikeInfoToGson=new ArrayList<EveryBikeInfoToGson>();
		for(EveryBikeInfo loop:finalEveryBikeInfo) {
			everyBikeInfoToGson.add(new EveryBikeInfoToGson(loop.getLicensePlate(),loop.getBranchName().getBranchName(),
					loop.getTotalMileage(),loop.getIsReadyMaintenance(),loop.getBikeDetail().getIdClassBikeDetail().getModelYear()));
		}
		return everyBikeInfoToGson;
	}


	

}
