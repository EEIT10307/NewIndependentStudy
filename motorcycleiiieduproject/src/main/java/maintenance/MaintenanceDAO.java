package maintenance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.EveryBikeInfo;
import projectbean.OrderList;

@Repository
public class MaintenanceDAO implements MaintenanceIFaceDAO {
	@Autowired
	SessionFactory factory;
	@Override
	public List<String> showAllBikePlate() {
		System.out.println("一");
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		System.out.println("二");
		CriteriaQuery<EveryBikeInfo> createQuery = buider.createQuery(EveryBikeInfo.class);
		Root<EveryBikeInfo> fromClass = createQuery.from(EveryBikeInfo.class);
		createQuery.select(fromClass);
		List<EveryBikeInfo> platelist = factory.getCurrentSession().createQuery(createQuery).getResultList();
		List<String> everyPlatelist = new ArrayList<String>();
		for (EveryBikeInfo loop : platelist) {
			everyPlatelist.add(loop.getLicensePlate());
		}
		return everyPlatelist;
	}
	@Override
	public List<EveryBikeInfo> showAllisReadyMaintenanceBike(String shopName) {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeInfo> createQuery = builder.createQuery(EveryBikeInfo.class);
		Root<EveryBikeInfo> fromClass = createQuery.from(EveryBikeInfo.class);
		ParameterExpression<String> branchName = builder.parameter(String.class);
		createQuery.select(fromClass).where(builder.and(builder.equal(fromClass.get("branchName").get("branchName"), branchName)
				,builder.equal(fromClass.get("isReadyMaintenance"), true)));
		
		Query<EveryBikeInfo> queryword = factory.getCurrentSession().createQuery(createQuery);
		
		queryword.setParameter(branchName, shopName);
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
