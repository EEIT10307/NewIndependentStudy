package maintenance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.EveryBikeInfoToGson;
import cleanbean.EveryBikeMileageToGson;
import cleanbean.MaintenanceHistoryToGson;
import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;
import projectbean.MaintenanceDetail;
import projectbean.MaintenanceHistory;
 
@Repository
public class MaintenanceDAO implements MaintenanceIFaceDAO {
	@Autowired
	SessionFactory factory;
	@Override
	public List<String> showAllBikePlate() {
		System.out.println("一");
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		System.out.println("二");
		CriteriaQuery<EveryBikeInfo> createQuery = builder.createQuery(EveryBikeInfo.class);
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
		return list;
	}
	@Override
	public List<EveryBikeInfoToGson> everyBikeInfoforGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo) {
		ArrayList<EveryBikeInfoToGson> everyBikeInfoToGson=new ArrayList<EveryBikeInfoToGson>();
		for(EveryBikeInfo loop:finalEveryBikeInfo) {
			everyBikeInfoToGson.add(new EveryBikeInfoToGson(loop.getLicensePlate(),loop.getBranchName().getBranchName(),
			loop.getTotalMileage(),loop.getIsReadyMaintenance(),loop.getBikeDetail().getIdClassBikeDetail().getModelYear()));
		}
		return everyBikeInfoToGson;
	}

	@Override
	public int insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage,Double requiredHourTodo ) {
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
		maintenanceDetail.setRequiredHourTodo(requiredHourTodo);
		session.save(maintenanceDetail);
	
		int insertCount=0;
		for (EveryBikeInfo loop : platelist) {
			EveryBikeMileage everyBikeMileage=new EveryBikeMileage();
			everyBikeMileage.setMaintenanceItem(maintenanceDetail);
			everyBikeMileage.setCurrentMileage(0.0);
			everyBikeMileage.setLicensePlate(loop);
			
			System.out.println(loop.getLicensePlate());
			insertCount+=1;
			session.persist(everyBikeMileage);
		}
		System.out.println("insertCount:"+insertCount);
		return insertCount;
	}

	@Override
	public List<EveryBikeMileage> showEveryBikeMileagebyStore(String shopName) {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeMileage> createQuery = builder.createQuery(EveryBikeMileage.class);
		Root<EveryBikeMileage> fromClass = createQuery.from(EveryBikeMileage.class);
		createQuery.orderBy(builder.asc(fromClass.get("licensePlate")));
		ParameterExpression<String> branchName = builder.parameter(String.class);
		createQuery.select(fromClass).where(builder.equal(fromClass.get("licensePlate").get("branchName").get("branchName"), branchName));
		Query<EveryBikeMileage> queryword = factory.getCurrentSession().createQuery(createQuery);
		
		queryword.setParameter(branchName, shopName);
		List<EveryBikeMileage> list = queryword.getResultList();
		System.out.println("List的值為"+list);
		return list;
	}
	
	@Override
	public List<EveryBikeInfo> showEveryBikeBasicInfobyStore(String shopName) {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeInfo> createQuery = builder.createQuery(EveryBikeInfo.class);
		Root<EveryBikeInfo> fromClass = createQuery.from(EveryBikeInfo.class);
		createQuery.orderBy(builder.asc(fromClass.get("licensePlate")));
		createQuery.select(fromClass).where(builder.equal(fromClass.get("branchName").get("branchName"), shopName));
		Query<EveryBikeInfo> queryword = factory.getCurrentSession().createQuery(createQuery);	
		List<EveryBikeInfo> list = queryword.getResultList();
		System.out.println("List的值為"+list);
		return list;
	}
	
	@Override
	public List<EveryBikeMileage> showEveryMaintenanceItembyPlate(String licensePlate) {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeMileage> createQuery = builder.createQuery(EveryBikeMileage.class);
		Root<EveryBikeMileage> fromClass = createQuery.from(EveryBikeMileage.class);
		createQuery.orderBy(builder.asc(fromClass.get("maintenanceItem")));
		createQuery.select(fromClass).where(builder.equal(fromClass.get("licensePlate").get("licensePlate"), licensePlate));
		Query<EveryBikeMileage> queryword = factory.getCurrentSession().createQuery(createQuery);	
		List<EveryBikeMileage> list = queryword.getResultList();
		System.out.println("List的值為"+list);
		return list;
	}

	@Override
	public List<EveryBikeMileageToGson> everyBikeMileageforGsonConvert(List<EveryBikeMileage> finalEveryBikeMileage) {
		ArrayList<EveryBikeMileageToGson> everyBikeMileageToGson=new ArrayList<EveryBikeMileageToGson>();
		for(EveryBikeMileage loop:finalEveryBikeMileage) {
			System.out.println(loop.getEveryBikeMileageSerialNum());
			System.out.println(loop.getLicensePlate().getLicensePlate());
			System.out.println(loop.getMaintenanceItem().getMaintenanceItem());
			System.out.println(loop.getCurrentMileage());
			System.out.println(loop.getMaintenanceItem().getRequiredMileage());
			System.out.println(loop.getLicensePlate().getBranchName().getBranchName());
			System.out.println(loop.getMaintenanceItem().getRequiredHourTodo());
			System.out.println(loop.getLicensePlate().getTotalMileage());
			everyBikeMileageToGson.add(new EveryBikeMileageToGson(
					loop.getEveryBikeMileageSerialNum(),
					loop.getLicensePlate().getLicensePlate(),
					loop.getMaintenanceItem().getMaintenanceItem(),
					loop.getCurrentMileage(),
					loop.getMaintenanceItem().getRequiredMileage(),
					loop.getLicensePlate().getBranchName().getBranchName(),
					loop.getMaintenanceItem().getRequiredHourTodo(),
					loop.getLicensePlate().getTotalMileage()));
		}
		return everyBikeMileageToGson;
		
		}
	@Override
	public List<EveryBikeMileage> showMessageIfMileageIsOver() {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeMileage> createQuery = builder.createQuery(EveryBikeMileage.class);
		Root<EveryBikeMileage> fromClass = createQuery.from(EveryBikeMileage.class);
		createQuery.orderBy(builder.asc(fromClass.get("licensePlate")));
		createQuery.select(fromClass)
		.where(builder.greaterThanOrEqualTo(fromClass.get("currentMileage"), fromClass.get("maintenanceItem").get("requiredMileage")));
		Query<EveryBikeMileage> queryword = factory.getCurrentSession().createQuery(createQuery);	
//		queryword.setParameter(branchName, shopName);
		List<EveryBikeMileage> list = queryword.getResultList();
		return list;
	}
	
	@Override
	public List<EveryBikeMileage> showMessageIfMileageIsOverAfterComplete(String licensePlate) {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeMileage> createQuery = builder.createQuery(EveryBikeMileage.class);
		Root<EveryBikeMileage> fromClass = createQuery.from(EveryBikeMileage.class);
		createQuery.orderBy(builder.asc(fromClass.get("licensePlate")));//???
		createQuery.select(fromClass).where(builder.equal(fromClass.get("licensePlate").get("licensePlate"), licensePlate)); 
		Query<EveryBikeMileage> queryword = factory.getCurrentSession().createQuery(createQuery);	
//		queryword.setParameter(branchName, shopName);
		List<EveryBikeMileage> list = queryword.getResultList();
		return list;
	}
	@Override
	public String updateBikeMileage(String licensePlate,Double increasedMileage) {
		 CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();	 
		 CriteriaUpdate<EveryBikeInfo> createUpdate=builder.createCriteriaUpdate(EveryBikeInfo.class);
		 Root<EveryBikeInfo> fromClass = createUpdate.from(EveryBikeInfo.class);
		 Session session=factory.getCurrentSession();
		 EveryBikeInfo bike = session.get(EveryBikeInfo.class, licensePlate);
		 System.out.println("車牌車牌:"+bike.getLicensePlate()+"總里程數"+bike.getTotalMileage());
    	 createUpdate.set("totalMileage", bike.getTotalMileage()+increasedMileage).where(builder.equal(fromClass.get("licensePlate"), licensePlate));
		 factory.getCurrentSession().createQuery(createUpdate).executeUpdate();
/////////////上面先增加該車牌總里程數,下面增加該車牌對應的各項保養里程/////////////
		 CriteriaQuery<EveryBikeMileage> createQuery2 = builder.createQuery(EveryBikeMileage.class);
		 Root<EveryBikeMileage> fromClass2 = createQuery2.from(EveryBikeMileage.class);
		 createQuery2.select(fromClass2).where(builder.equal(fromClass2.get("licensePlate").get("licensePlate"), licensePlate)); 
		 List<EveryBikeMileage> maintenanceItemList = factory.getCurrentSession().createQuery(createQuery2).getResultList();
		 System.out.println("166還行嗎?");
//	//↓同時Update增加的總里程到對應車牌的EveryBikeMileage的每一項
//		 CriteriaUpdate<EveryBikeMileage> createUpdate2=builder.createCriteriaUpdate(EveryBikeMileage.class);
//		 fromClass2=createUpdate2.from(EveryBikeMileage.class);
//	
//		 	for (EveryBikeMileage loop : maintenanceItemList) {	
//		 		System.out.println("171行保養項目:"+loop.getMaintenanceItem().getMaintenanceItem());
////		     	 createUpdate2.set("currentMileage", loop.getCurrentMileage()+increasedMileage).where(builder.equal(fromClass2.get("licensePlate").get("licensePlate"), licensePlate));
////		     	 createUpdate2.set("currentMileage", loop.getCurrentMileage()+increasedMileage).where(builder.equal(fromClass2.get("maintenanceItem").get("maintenanceItem"), loop.getMaintenanceItem()));
//		     	 createUpdate2.set("currentMileage", loop.getCurrentMileage()+increasedMileage).where(builder.and(builder.equal(fromClass2.get("maintenanceItem").get("maintenanceItem"), loop.getMaintenanceItem().getMaintenanceItem())
//							,builder.equal(fromClass2.get("licensePlate").get("licensePlate"), licensePlate)));
//		     	 																			   
//		     	 factory.getCurrentSession().createQuery(createUpdate2).executeUpdate();
//				 System.out.println("成功增加里程數"+increasedMileage+"公里至項目"+loop.getMaintenanceItem());
//			}
		 System.out.println(9);
		 String hql="update EveryBikeMileage set currentMileage=:currentMileage where licensePlate=:licensePlate";
		 System.out.println(8);
			for (EveryBikeMileage loop : maintenanceItemList) {	
				EveryBikeInfo car = session.get(EveryBikeInfo.class, licensePlate);
				Query qw = session.createQuery(hql).setParameter("currentMileage", loop.getCurrentMileage()+increasedMileage).setParameter("licensePlate", car);
				qw.executeUpdate();
			}
	//ROBIN寫的HQL	
			
       return "成功";
	}
	@Override
	public int sendMaintenance(String licensePlate) {
		 Session session=factory.getCurrentSession();
		 String hql="update EveryBikeInfo set isReadyMaintenance=:isReadyMaintenance where licensePlate=:licensePlate";
		 Query qw = session.createQuery(hql).setParameter("isReadyMaintenance", true).setParameter("licensePlate", licensePlate);
		 qw.executeUpdate();
			return 0;
	}
	@Override
	public List<EveryBikeMileage> showBikeMaintenancingItem(String licensePlate) {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<EveryBikeMileage> createQuery = builder.createQuery(EveryBikeMileage.class);
		Root<EveryBikeMileage> fromClass = createQuery.from(EveryBikeMileage.class);
		createQuery.select(fromClass).where(builder.and(builder.equal(fromClass.get("licensePlate").get("licensePlate"), licensePlate)
				,builder.greaterThanOrEqualTo(fromClass.get("currentMileage"),fromClass.get("maintenanceItem").get("requiredMileage"))));
		Query<EveryBikeMileage> queryword = factory.getCurrentSession().createQuery(createQuery);	
		List<EveryBikeMileage> list = queryword.getResultList();
		return list;
	}

	@Override
	public int completeMaintenance(String licensePlate) throws ParseException {
		 CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		 CriteriaQuery<EveryBikeMileage> createQuery = builder.createQuery(EveryBikeMileage.class);
		 Root<EveryBikeMileage> fromClass = createQuery.from(EveryBikeMileage.class);
		 createQuery.select(fromClass).where(builder.and(builder.equal(fromClass.get("licensePlate").get("licensePlate"), licensePlate)
				,builder.greaterThanOrEqualTo(fromClass.get("currentMileage"),fromClass.get("maintenanceItem").get("requiredMileage"))));
		 List<EveryBikeMileage> maintenanceItemList = factory.getCurrentSession().createQuery(createQuery).getResultList();
		
		 Session session=factory.getCurrentSession();
		 String hql="update EveryBikeInfo set isReadyMaintenance=:isReadyMaintenance where licensePlate=:licensePlate";
		 Query qw = session.createQuery(hql).setParameter("isReadyMaintenance", false).setParameter("licensePlate", licensePlate);
		 qw.executeUpdate();
		 System.out.println("執行hql之前227還行嗎?");	
		 String hql2="update EveryBikeMileage set EveryBikeMileage.currentMileage=0 from EveryBikeMileage join MaintenanceDetail on EveryBikeMileage.maintenanceItem_maintenanceItem =MaintenanceDetail.maintenanceItem WHERE EveryBikeMileage.licensePlate_licensePlate=:licensePlate and EveryBikeMileage.currentMileage >= MaintenanceDetail.requiredMileage";
		 System.out.println("執行完hql了229還行嗎?");
		 EveryBikeInfo car = session.get(EveryBikeInfo.class, licensePlate);
		 Query qw2 = session.createSQLQuery(hql2).setParameter("licensePlate", car);
		 qw2.executeUpdate();
		 
		 System.out.println("執行新增MaintenanceHistory!!!!!!");

		 Date now=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm");
		 System.out.println("現在時間:"+sdf.format(now));
		 for(EveryBikeMileage loop : maintenanceItemList) {
			 System.out.println("靠杯喔");
			 MaintenanceHistory maintenanceHistory=new MaintenanceHistory();
			 maintenanceHistory.setLicensePlate(car);
			 maintenanceHistory.setMaintenanceDate(sdf.parse(sdf.format(now)));
//			 maintenanceHistory.setMaintenanceDate(now);			 
			 maintenanceHistory.setHistoryMaintenanceItem(loop.getMaintenanceItem().getMaintenanceItem());
			 maintenanceHistory.setTotalMileage(car.getTotalMileage());
			 session.save(maintenanceHistory);
			 System.out.println("新增保養歷史紀錄成功"+loop.getMaintenanceItem().getMaintenanceItem());
		 }

		 return 0;
	}
	@Override
	public List<MaintenanceHistory> showAllMaintenanceHistory() {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<MaintenanceHistory> createQuery = builder.createQuery(MaintenanceHistory.class);
		Root<MaintenanceHistory> fromClass = createQuery.from(MaintenanceHistory.class);
//		createQuery.orderBy(builder.asc(fromClass.get("licensePlate").get("licensePlate")));
		createQuery.select(fromClass);
//		.where(builder.greaterThanOrEqualTo(fromClass.get("currentMileage"), fromClass.get("maintenanceItem").get("requiredMileage")));
		List<MaintenanceHistory> list = factory.getCurrentSession().createQuery(createQuery).getResultList();
		return list;

		
		
	}
	@Override
	public List<MaintenanceHistoryToGson> maintenanceHistoryforGsonConvert(List<MaintenanceHistory> finalMaintenanceHistory) throws Exception {		
			ArrayList<MaintenanceHistoryToGson> maintenanceHistoryToGson=new ArrayList<MaintenanceHistoryToGson>();
//			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm");
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//			sdf.parse(sdf.format(now));
			for(MaintenanceHistory loop:finalMaintenanceHistory) {
				maintenanceHistoryToGson.add(new MaintenanceHistoryToGson(
//						SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm");
//						loop.getEveryBikeMileageSerialNum(),
						loop.getMaintenanceHistorySerialNum(),
						loop.getLicensePlate().getLicensePlate(),
						loop.getHistoryMaintenanceItem(),
//						sdf.parse(sdf.format(loop.getMaintenanceDate())),//?????????????
						df.format(loop.getMaintenanceDate()),//?????????????
						loop.getTotalMileage()));
			}
			return maintenanceHistoryToGson;
			
	}


	
}


