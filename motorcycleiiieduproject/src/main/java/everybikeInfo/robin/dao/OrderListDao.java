package everybikeInfo.robin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
