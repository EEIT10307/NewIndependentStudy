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

import cleanbean.OrderListRobin;
import cleanbean.OrderListRobinYear;
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

}
