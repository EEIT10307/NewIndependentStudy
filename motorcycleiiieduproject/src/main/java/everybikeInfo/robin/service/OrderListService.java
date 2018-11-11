package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import everybikeInfo.robin.dao.OrderListIFaceDao;
import projectbean.OrderList;
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
