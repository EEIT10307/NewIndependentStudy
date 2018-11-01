package testbean.robin.ch01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectbean.OrderList;
import testbean.robin.OrderListIFaceDao;
import testbean.robin.OrderListIFaceService;
@Service
@Transactional
public class OrderListService implements OrderListIFaceService {
	@Autowired
	OrderListIFaceDao orderListIFaceDao;

	@Override
	public List<OrderList> selectone(String OrderSerialNum) {
		 
		return orderListIFaceDao.selectone(OrderSerialNum);
	}

}
