package testfakebikedao;

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

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import everybikeInfo.robin.service.AcceStockIFaceService;
import everybikeInfo.robin.service.EveryBikeMileageIFaceService;
import projectbean.AcceSerialNum;
import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.IdClassBikeDetail;
import projectbean.MaintenanceDetail;
import projectbean.MemberDetail;
import projectbean.OrderList;

@Repository
public class TestDAO {

	@Autowired
	SessionFactory factory;
	@Autowired
	EveryBikeMileageIFaceService everyBikeMileageIFaceService;
	@Autowired
	AcceStockIFaceService acceStockIFaceService;

	public TestDAO() {
		System.out.println("test DAO startUP");
	}

	public void makeFakeOrderlist() throws IOException, NumberFormatException, ParseException {

		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(

				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\OrderList2.txt")));

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

//		BufferedReader bf = new BufferedReader(
//				new FileReader(new File("C:\\Users\\candylo\\Desktop\\BranchDetail.txt")));
// /Users/kuochiahao/TeamWork-workspace/fakedata/BranchDetail.txt 

//C:\\Users\\III\\Desktop\\123\\fakedata\\BranchDetail.txt
		BufferedReader bf = new BufferedReader(
				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\BranchDetail.txt")));// robin
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

//		BufferedReader bf = new BufferedReader(
//
//				new FileReader(new File("C:\\Users\\candylo\\Desktop\\BikeDetail.txt")));
// /Users/kuochiahao/TeamWork-workspace/fakedata/BikeDetail.txt 
// C:\\Users\\III\\Desktop\\fakedata\\BikeDetail.txt
//		@SuppressWarnings("resource")
//		BufferedReader motorpl = new BufferedReader(
//
//				new FileReader(new File("C:\\Users\\candylo\\Desktop\\EveryBikeInfo.txt")));
//		/Users/kuochiahao/TeamWork-workspace/fakedata/EveryBikeInfo.txt
//C:\\Users\\III\\Desktop\\fakedata\\EveryBikeInfo.txt
		BufferedReader bf = new BufferedReader(

				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\BikeDetail.txt")));
		@SuppressWarnings("resource")
		BufferedReader motorpl = new BufferedReader(

				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\EveryBikeInfo.txt")));

		String line;
		String line2;
		SimpleDateFormat sim1 = new SimpleDateFormat("yyyy/mm/dd");

		roop: while ((line = bf.readLine()) != null) {
			String[] lines = line.split(",");
			System.out.println(lines.toString());
			System.out.println("test" + lines[0]);
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
			bikeD.setFrontTire(lines[10]);
			bikeD.setFuelType(lines[11]);
			bikeD.setABS(Boolean.valueOf(lines[12]));
			bikeD.setHourPrice(Integer.valueOf(lines[13]));
			Date dar = sim1.parse(lines[14]);
			bikeD.setOnSheftTime(dar);
			bikeD.setFrontSuspension(lines[15]);
			bikeD.setRearSuspension(lines[16]);
			bikeD.setRearTire(lines[17]);
			bikeD.setHorsePower(lines[18]);
			bikeD.setTorque(lines[19]);
			bikeD.setFrontBrake(lines[20]);
			bikeD.setRearBrake(lines[21]);

			while ((line2 = motorpl.readLine()) != null) {
				String[] lines2 = line2.split(",");
				int x = 1;
				/* ====new 機車車牌==== */
				EveryBikeInfo everyBikeInfo1 = new EveryBikeInfo();
				if (line2.equals("=")) {
					System.out.println("loop");
					factory.getCurrentSession().persist(bikeD);
					continue roop;
				}
				if (lines2[1].equals("木柵")) {
					x = 2;
				}

				// 取得分店實體 （需要在選項內埋分店的ID)
				BranchDetail branchDetail1 = factory.getCurrentSession().get(BranchDetail.class, x);

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
				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\MaintenanceDetail.txt")));
		String line;
		while ((line = bf.readLine()) != null) {
			String[] lines = line.split(",");
			MaintenanceDetail maintenanceDetail = new MaintenanceDetail();
			maintenanceDetail.setMaintenanceItem(lines[0]);
			maintenanceDetail.setRequiredMileage(Double.valueOf(lines[1]));
			maintenanceDetail.setRequiredHourTodo(Double.valueOf(lines[2]));
			factory.getCurrentSession().persist(maintenanceDetail);
		}
		bf.close();
	}

	public void makeFakeBikeDescription() throws IOException, NumberFormatException, ParseException {

//		BufferedReader bf = new BufferedReader(
//				new FileReader(new File("/Users/kuochiahao/TeamWork-workspace/fakedata/description.txt")));
		BufferedReader bf = new BufferedReader(
				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\description.txt")));//ROBIN
		
		String lines = "";
		String line;
		BikeDetail bikedetail = null;
		int x = 0;

		roop: while ((line = bf.readLine()) != null) {

			if (line.equals("=")) {
				System.out.println("lines" + lines);
				bikedetail.setDescription(lines);
				factory.getCurrentSession().update(bikedetail);
				x = 0;
				lines = "";
				continue roop;
			}

			if (x == 0) {
				String[] query = line.split(",");
				bikedetail = factory.getCurrentSession().get(BikeDetail.class,
						new IdClassBikeDetail(query[0], query[1]));

				System.out.println(query[0] + query[1]);
				System.out.println(("BEAN = " + bikedetail.getIdClassBikeDetail().getBikeModel()));
				x++;
				continue roop;
			}
			lines += line;

		}
		bf.close();
	}

	public void insertEveryBikeMileage() throws IOException {
		@SuppressWarnings("resource")
		BufferedReader motorpl = new BufferedReader(

				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\EveryBikeInfo.txt")));
		String line2;
		roop: while ((line2 = motorpl.readLine()) != null) {
			if (line2.equals("=")) {
				System.out.println("loop 多的");
				continue roop;
			}
			String[] lines2 = line2.split(",");
			System.out.println("車牌:" + lines2[0]);
			everyBikeMileageIFaceService.save(lines2[0]);

		}
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

	public void insertMemberDetail() throws IOException, ParseException {
		@SuppressWarnings("resource")
		BufferedReader Member = new BufferedReader(
				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\MemberDetail.txt")));
		String line;
		SimpleDateFormat sim1 = new SimpleDateFormat("yyyy/mm/dd");
		while ((line = Member.readLine()) != null) {
			String[] lines = line.split(",");
			MemberDetail member = new MemberDetail();
			member.setAddress(lines[0]);
			member.setBirthday(sim1.parse(lines[1]));
			member.setEmail(lines[2]);
			member.setGender(lines[3]);
			member.setSigninDate(sim1.parse(lines[4]));
			member.setName(lines[5]);
			member.setPassword(lines[6]);
			member.setPhone(lines[7]);
			member.setLastLoginDate(sim1.parse(lines[8]));
			factory.getCurrentSession().persist(member);
			System.out.println(154546456);
			System.out.println("地區" + lines[0]);
		}

	}
	public void insertFakeAcceSerialNum() throws IOException, ParseException {
		@SuppressWarnings("resource")
		BufferedReader Member = new BufferedReader(
				new FileReader(new File("C:\\Users\\III\\Desktop\\123\\fakedata\\AcceSerialNum.txt")));
		String line;
		while ((line = Member.readLine()) != null) {
			String[] lines = line.split(",");
			AcceSerialNum acceSerialNum=new AcceSerialNum();
			acceSerialNum.setAcceSerialNum(lines[0]);
			acceSerialNum.setAcceType(lines[1]);
			acceStockIFaceService.insertAcceSerialNum(acceSerialNum);
		}

	}
}
