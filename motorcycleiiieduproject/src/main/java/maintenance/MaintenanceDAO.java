package maintenance;

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

import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;
import projectbean.MaintenanceDetail;

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
	@Override
	public int insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage) {
		Session session=factory.getCurrentSession();
		
		System.out.println("一");
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		System.out.println("二");
		CriteriaQuery<EveryBikeInfo> createQuery = buider.createQuery(EveryBikeInfo.class);
		Root<EveryBikeInfo> fromClass = createQuery.from(EveryBikeInfo.class);
		createQuery.select(fromClass);
		List<EveryBikeInfo> platelist = factory.getCurrentSession().createQuery(createQuery).getResultList();
	
		MaintenanceDetail maintenanceDetail=new MaintenanceDetail();
		maintenanceDetail.setMaintenanceItem(maintenanceItem);
		maintenanceDetail.setRequiredMileage(requiredMileage);

		session.save(maintenanceDetail);
	
		int insertCount=0;
		for (EveryBikeInfo loop : platelist) {
			EveryBikeMileage everyBikeMileage=new EveryBikeMileage();
			everyBikeMileage.setMaintenanceItem(maintenanceDetail);//?????
			everyBikeMileage.setCurrentMileage(0.0);
			everyBikeMileage.setLicensePlate(loop);//?????
			System.out.println(loop.getLicensePlate());
			insertCount+=1;
			session.persist(everyBikeMileage);
		}
		System.out.println("insertCount:"+insertCount);
		return insertCount;
	}

}
