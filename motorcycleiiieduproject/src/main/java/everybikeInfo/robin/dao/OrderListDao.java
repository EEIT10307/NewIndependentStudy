package everybikeInfo.robin.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.MemberDetailSelectYearForJson;
import cleanbean.OrderListRobinYear;
import projectbean.MemberDetail;
import projectbean.OrderList;

@Repository
public class OrderListDao implements OrderListIFaceDao {
	@Autowired
	SessionFactory Factory;

	@Override
	public List<OrderList> selectone(String OrderSerialNum) {
		Session session = Factory.getCurrentSession();
		String hgl = "FROM OrderList WHERE OrderSerialNum=:OrderSerialNum";

		return session.createQuery(hgl).setParameter("OrderSerialNum", OrderSerialNum).getResultList();
	}

	@Override
	public List<OrderList> selecOrderTotal(String branchName) {
		Session session = Factory.getCurrentSession();
		SimpleDateFormat Format = new SimpleDateFormat("yyyy");
		Date year = new Date();
		String YEAR = Format.format(year);
		int intyear = Integer.parseInt(YEAR);
		String hgl = "select pickupStore,sum(orderTotalPrice),month(orderTime) from OrderList where YEAR(orderTime)=:year and pickupStore=:pickupStore group by pickupStore,month(orderTime) order by pickupStore";
		return session.createQuery(hgl).setParameter("year", intyear).setParameter("pickupStore", branchName).list();
	}

	@Override
	public OrderListRobinYear OrderListForJson(List<OrderList> OrderList) {
		OrderListRobinYear or = new OrderListRobinYear();

		Iterator iterator = OrderList.iterator();

		while (iterator.hasNext()) {
			Object[] obj = (Object[]) iterator.next();

			if (obj[2] == (Object) 1) {
				or.setOne((long) obj[1]);
			}
			if (obj[2] == (Object) 2) {
				or.setTwo((long) obj[1]);
			}

			if (obj[2] == (Object) 2) {
				or.setThree((long) obj[1]);
			}
			if (obj[2] == (Object) 4) {
				or.setFour((long) obj[1]);
			}
			if (obj[2] == (Object) 5) {
				or.setFive((long) obj[1]);
			}
			if (obj[2] == (Object) 6) {
				or.setSix((long) obj[1]);
			}
			if (obj[2] == (Object) 7) {
				or.setSeven((long) obj[1]);
			}
			if (obj[2] == (Object) 8) {
				or.setEight((long) obj[1]);
			}
			if (obj[2] == (Object) 9) {
				or.setNine((long) obj[1]);
			}
			if (obj[2] == (Object) 10) {
				or.setTen((long) obj[1]);
			}
			if (obj[2] == (Object) 11) {
				or.setEleven((long) obj[1]);
			}
			if (obj[2] == (Object) 11) {
				or.setTwelve((long) obj[1]);
			}
		}

		return or;
	}

	@Override
	public List<OrderList> selecOrderTotalYear(String branchName) {
		Session session = Factory.getCurrentSession();
		SimpleDateFormat Format = new SimpleDateFormat("yyyy");
		Date year = new Date();
		String YEAR = Format.format(year);
		System.out.println(YEAR);
		int intyear = Integer.parseInt(YEAR);
		String hgl = "select pickupStore,sum(orderTotalPrice),ROW_NUMBER()OVER(order by YEAR(orderTime)desc) as s from OrderList where pickupStore=:pickupStore1 group by pickupStore,YEAR(orderTime)";
		return session.createSQLQuery(hgl).setParameter("pickupStore1", branchName).list();

	}

	@Override
	public OrderListRobinYear OrderListForJsonYear(List<OrderList> OrderList) {
		OrderListRobinYear or = new OrderListRobinYear();

		Iterator iterator = OrderList.iterator();

		while (iterator.hasNext()) {

			Object[] obj = (Object[]) iterator.next();
//				System.out.println(obj[0] + ",順序1" + obj[1] + "，" + obj[2]);
			BigInteger one = BigInteger.valueOf(1);
			BigInteger two = BigInteger.valueOf(2);
			BigInteger three = BigInteger.valueOf(3);
			BigInteger four = BigInteger.valueOf(4);
			BigInteger five = BigInteger.valueOf(5);
			BigInteger six = BigInteger.valueOf(6);
			BigInteger seven = BigInteger.valueOf(7);
			BigInteger eight = BigInteger.valueOf(8);
			BigInteger nine = BigInteger.valueOf(9);
			BigInteger ten = BigInteger.valueOf(10);
		

			if (obj[2] == one) {
				or.setOne((Integer) obj[1]);
			}
			if (obj[2] == two) {
				or.setTwo((Integer)obj[1]);
			}
				
			
			if (obj[2] == three) {
				or.setThree((Integer)obj[1]);
			}
			if (obj[2] == four) {
				or.setFour((Integer)obj[1]);
			}
			if (obj[2] == five) {
				or.setFive((Integer)obj[1]);
			}
			if (obj[2] == six) {
				or.setSix((Integer)obj[1]);
			}
			if (obj[2] == seven) {
				or.setSeven((Integer)obj[1]);
			}
			if (obj[2] == eight) {
				or.setEight((Integer)obj[1]);
			}
			if (obj[2] == nine) {
				or.setNine((Integer)obj[1]);
			}
			if (obj[2] == ten) {
				or.setTen((Integer)obj[1]);
			}

		}

		return or;
	}

	@Override
	public List<MemberDetail> selectMemberyear() {
		Session session = Factory.getCurrentSession();
		String hgl = "select (select count(*) from MemberDetail where year(birthday) <='1990')as a,(select count(*)   from MemberDetail where (year(birthday)  between '1988' and '1999'))as b,(select count(*) from MemberDetail where (year(birthday)  between '1978' and '1988'))as c,(select count(*) from MemberDetail where (year(birthday)  between '1968' and '1978'))as d,(select count(*) from MemberDetail where (year(birthday)  between '1958' and '1968'))as e,(select count(*) from MemberDetail where (year(birthday)  <= '1958'))as f,(select count(*) from MemberDetail where gender='女')as girl,(select count(*) from MemberDetail where gender='男')as boy from MemberDetail";
		return session.createSQLQuery(hgl).list();

	}

	@Override
	public MemberDetailSelectYearForJson MemberDetailSelectYearForJson(List<MemberDetail> memberDetail) {
		MemberDetailSelectYearForJson memberDetailSelectYearForJson=new MemberDetailSelectYearForJson();
					Iterator iterator = memberDetail.iterator();
					
						Object[] obj = (Object[]) iterator.next();
//						System.out.println("20歲以下"+obj[0]+",20-30歲"+obj[1]+",31-40歲"+obj[2]+",41-50歲"+obj[3]+",51-60歲"+obj[4]+",60歲以上"+obj[5]);
						memberDetailSelectYearForJson.setOne((Integer)obj[0]);
						memberDetailSelectYearForJson.setTwo((Integer)obj[1]);
						memberDetailSelectYearForJson.setThree((Integer)obj[2]);
						memberDetailSelectYearForJson.setFour((Integer)obj[3]);
						memberDetailSelectYearForJson.setFive((Integer)obj[4]);
						memberDetailSelectYearForJson.setSix((Integer)obj[5]);
						memberDetailSelectYearForJson.setGirl((Integer)obj[6]);
						memberDetailSelectYearForJson.setBoy((Integer)obj[7]);
		return memberDetailSelectYearForJson;
	}

}
