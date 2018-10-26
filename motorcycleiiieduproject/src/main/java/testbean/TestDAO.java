package testbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.IdClassBikeDetail;
import projectbean.WebInformationForManager;

@Repository
public class TestDAO {

	@Autowired
	 SessionFactory factory;
	
	
	
	public TestDAO() {
		System.out.println("test DAO startUP");
	}

	public void testFactory() throws ParseException {
		
		  Session session = factory.getCurrentSession();
		  
		  
		    TestHibernateBean testbea = new TestHibernateBean() ; 
		    testbea.setAge(11);
		    testbea.setName("阿P");
	
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		          
		        Date par = sdf.parse("2018-11-11 12:12:12") ; 
		    testbea.setDate(par);
		    session.save(testbea) ; 
		  
		  System.out.println(session.toString());
		
	}
	
	
	public void testHibernateBean() throws ParseException {
		
		
		System.out.println("test bean go ");
		  Session session = factory.getCurrentSession();
		  
		  
		  WebInformationForManager testSpring = new WebInformationForManager() ; 
		  testSpring.setWebContent("hiSpring");
		  testSpring.setWebElements("HI PROJECT");
		    
		    session.save(testSpring) ; 
		  
		  System.out.println(session.toString());
		  
		  
		  /* ====new 機車車牌==== */
			EveryBikeInfo everyBikeInfo1 = new EveryBikeInfo();
		
			// 取得分店實體 （需要在選項內埋分店的ID)
			BranchDetail branchDetail = session.get(BranchDetail.class, 2);
			everyBikeInfo1.setBranchName(branchDetail);
			everyBikeInfo1.setLicensePlate("hel111s");
			everyBikeInfo1.setTotalMileage(3.4);
			everyBikeInfo1.setIsReadyMaintenance(false);
			/* ====new 上架新商品 ==== */
			BikeDetail bikeD = new BikeDetail();
			// 複合主鍵 設定主鍵值
			IdClassBikeDetail idc = new IdClassBikeDetail("GHw22wI", "1988");
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-mm-dd");
			Date dar = sim.parse("1933-02-18");
			// 將複合主鍵類別放到屬性
			bikeD.setIdClassBikeDetail(idc);
			bikeD.setABS(true);
			bikeD.setBikeBrand("YAHAN2");
			bikeD.setBikeType("spory222");
			bikeD.setDryWeight(23.3);
			bikeD.setEngineType("qwqwq");
			bikeD.setFuelConsumption(23.3);
			bikeD.setFuelTankCapacity(45.5);
			bikeD.setFuelType("97");
			bikeD.setHourPrice(45);
			bikeD.setOnSheftTime(dar);
			bikeD.setPlateType("GOGO");
			bikeD.setSeatHeight(3.4);
			bikeD.setTire("PULIA");

			// 將車牌加入list
			bikeD.addEveryBikeInfo(everyBikeInfo1);
			bikeD.addEveryBikeInfo(new EveryBikeInfo("irrQQQW", 3.4d, false, branchDetail));

		
				session.persist(bikeD);
			
		  
				System.out.println("test bean down ");
		
	}
	

	
	
}
