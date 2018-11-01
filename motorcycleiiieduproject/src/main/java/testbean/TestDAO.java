package testbean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.IdClassBikeDetail;
import projectbean.MaintenanceDetail;
import projectbean.OrderList;
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

		TestHibernateBean testbea = new TestHibernateBean();
		testbea.setAge(11);
		testbea.setName("阿P");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date par = sdf.parse("2018-11-11 12:12:12");
		testbea.setDate(par);
		session.save(testbea);

		System.out.println(session.toString());

	}

	public void testHibernateBean() throws ParseException {
		System.out.println("test bean go ");
		Session session = factory.getCurrentSession();

		WebInformationForManager testSpring = new WebInformationForManager();
		testSpring.setWebContent("hiSpring");
		testSpring.setWebElements("HI PROJECT");

		session.save(testSpring);

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

	public void makeFakeOrderlist() throws IOException, NumberFormatException, ParseException {

		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(

				new FileReader(new File("C:\\Users\\III\\Desktop\\fakedata\\OrderList.txt")));

		String line;
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		while ((line = bf.readLine()) != null) {
			String[] lines = line.split(",");

			OrderList odlistnew = new OrderList(lines[0], lines[1], lines[2], sim.parse(lines[4]), sim.parse(lines[5]),
					Double.valueOf(lines[6]), Integer.valueOf(lines[7]), lines[8], Integer.valueOf(lines[9]),
					Integer.valueOf(lines[10]), sim.parse(lines[11]), lines[12], lines[13], lines[14], lines[15],
					Boolean.valueOf(lines[16]), Boolean.valueOf(lines[17]));

			odlistnew.setLicensePlate(factory.getCurrentSession().get(EveryBikeInfo.class, lines[3]));

			factory.getCurrentSession().persist(odlistnew);

		}

	}

	public void makeFakeBranchDetail() throws IOException, NumberFormatException, ParseException {

		BufferedReader bf = new BufferedReader(
				new FileReader(new File("C:\\Users\\III\\Desktop\\fakedata\\BranchDetail.txt")));

		String line;
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");
		while ((line = bf.readLine()) != null) {
			String[] lines = line.split(",");
			BranchDetail branchDetail = new BranchDetail();
			branchDetail.setBranchName(lines[0]);
			branchDetail.setBranchArea(lines[1]);
			branchDetail.setBranchCounty(lines[2]);
			branchDetail.setBranchAddress(lines[3]);
			branchDetail.setBranchPhone(lines[4]);
			branchDetail.setOpeningDay(sim.parse(lines[5]));
			factory.getCurrentSession().persist(branchDetail);

		}
		bf.close();
	}

	public void makeFakeBikedetail_EveryBikeInfor() throws IOException, NumberFormatException, ParseException {

		BufferedReader bf = new BufferedReader(

				new FileReader(new File("C:\\Users\\III\\Desktop\\fakedata\\BikeDetail.txt")));

		@SuppressWarnings("resource")
		BufferedReader motorpl = new BufferedReader(

				new FileReader(new File("C:\\Users\\III\\Desktop\\fakedata\\EveryBikeInfo.txt")));

		String line;
		String line2;
		SimpleDateFormat sim1 = new SimpleDateFormat("yyyy/mm/dd");

		roop: while ((line = bf.readLine()) != null) {
			String[] lines = line.split(",");

			/* ====new 上架新商品 ==== */
			BikeDetail bikeD = new BikeDetail();
			// 複合主鍵 設定主鍵值
			IdClassBikeDetail idc = new IdClassBikeDetail(lines[0], lines[1]);
			bikeD.setIdClassBikeDetail(idc);
			bikeD.setBikeBrand(lines[2]);
			bikeD.setEngineType(lines[3]);
			bikeD.setBikeType(lines[4]);
			bikeD.setPlateType(lines[5]);
			bikeD.setFuelTankCapacity(Double.valueOf(lines[6]));
			bikeD.setSeatHeight(Double.valueOf(lines[7]));
			bikeD.setDryWeight(Double.valueOf(lines[8]));
			bikeD.setFuelConsumption(Double.valueOf(lines[9]));
			bikeD.setTire(lines[10]);
			bikeD.setFuelType(lines[11]);
			bikeD.setABS(Boolean.valueOf(lines[12]));
			bikeD.setHourPrice(Integer.valueOf(lines[13]));
			Date dar = sim1.parse(lines[14]);
			bikeD.setOnSheftTime(dar);

			while ((line2 = motorpl.readLine()) != null) {
				/* ====new 機車車牌==== */
				EveryBikeInfo everyBikeInfo1 = new EveryBikeInfo();
				// 取得分店實體 （需要在選項內埋分店的ID)
				BranchDetail branchDetail1 = factory.getCurrentSession().get(BranchDetail.class, 1);
				String[] lines2 = line2.split(",");

				if (line2.equals("=")) {
					System.out.println("loop");
					factory.getCurrentSession().persist(bikeD);
					continue roop;
				}

				everyBikeInfo1.setLicensePlate(lines2[0]);
				everyBikeInfo1.setBranchName(branchDetail1);
				everyBikeInfo1.setTotalMileage(Double.valueOf(lines2[2]));
				everyBikeInfo1.setIsReadyMaintenance(Boolean.valueOf(lines2[3]));
				// 將車牌加入list
				bikeD.addEveryBikeInfo(everyBikeInfo1);

			}

			factory.getCurrentSession().persist(bikeD);

		}

		bf.close();
	}

	public void makeFakeMaintenanceDetail() throws IOException, NumberFormatException, ParseException {

		BufferedReader bf = new BufferedReader(
				new FileReader(new File("C:\\Users\\III\\Desktop\\fakedata\\MaintenanceDetail.txt")));
		String line;
		while ((line = bf.readLine()) != null) {
			String[] lines = line.split(",");
			MaintenanceDetail maintenanceDetail = new MaintenanceDetail();
			maintenanceDetail.setMaintenanceItem(lines[0]);
			maintenanceDetail.setRequiredMileage(Double.valueOf(lines[1]));
			factory.getCurrentSession().persist(maintenanceDetail);
		}
		bf.close();
	}

	public void createCriteria() {

		// factory.getCurrentSession().createCriteria(OrderList.class);
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> from = createQuery.from(OrderList.class);
		ParameterExpression<String> par = buider.parameter(String.class);
		createQuery.select(from).where(buider.equal(from.get("bikeModel"), par));
		Query<OrderList> queryword = factory.getCurrentSession().createQuery(createQuery);
		queryword.setParameter(par, "R3");
		List<OrderList> list = queryword.getResultList();

		for (OrderList loop : list) {
			System.out.println(loop);
		}

	}

}
