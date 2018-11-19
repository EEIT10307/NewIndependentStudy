package everybikeInfo.robin.dao;

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
public class EveryBikeMileageDao implements EveryBikeMileageIFaceDao {
	@Autowired
	SessionFactory Factory;

	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EveryBikeMileage> getAllMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override//查詢分店 所有資訊
	public  List<EveryBikeMileage> selectallname(String branchname0) {
		Session session = Factory.getCurrentSession();

		CriteriaBuilder buider = Factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeMileage> createQuery = buider.createQuery(EveryBikeMileage.class);
		Root<EveryBikeMileage> fromClass = createQuery.from(EveryBikeMileage.class);
		ParameterExpression<String> checkshopname = buider.parameter(String.class);
		createQuery.select(fromClass).where(buider.equal(fromClass.get("licensePlate").get("branchName").get("branchName"), checkshopname));
		
		Query<EveryBikeMileage> platelist = Factory.getCurrentSession().createQuery(createQuery);
		platelist.setParameter(checkshopname, branchname0);
		List<EveryBikeMileage> bikeDetaillist = platelist.getResultList();
	
		
//		for (EveryBikeMileage loop : bikeDetaillist) {
//			
//	System.out.println("車牌:"+loop.getLicensePlate().getLicensePlate());
//	System.out.println("墓碑里程:"+loop.getCurrentMileage());
//	System.out.println("項目:"+loop.getMaintenanceItem().getMaintenanceItem());
//	System.out.println("需求里程:"+loop.getMaintenanceItem().getRequiredMileage());
//		}
		
		return bikeDetaillist;
	}

	@Override // 多筆車牌 同步新增 保養項目
	public int save(String everyBikeMileage) {
		Session session = Factory.getCurrentSession();

		CriteriaBuilder buider = Factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<MaintenanceDetail> createQuery = buider.createQuery(MaintenanceDetail.class);
		Root<MaintenanceDetail> fromClass = createQuery.from(MaintenanceDetail.class);
		createQuery.select(fromClass);
		List<MaintenanceDetail> platelist = Factory.getCurrentSession().createQuery(createQuery).getResultList();
		int insertCount=0;
		EveryBikeInfo everyBikeInfo=new EveryBikeInfo();
		everyBikeInfo.setLicensePlate(everyBikeMileage);
		for (MaintenanceDetail loop : platelist) {
		
			EveryBikeMileage everyBike=new EveryBikeMileage(everyBikeInfo,loop,0.0);
			
			session.save(everyBike);
		}
		
		return insertCount;
	}

}
