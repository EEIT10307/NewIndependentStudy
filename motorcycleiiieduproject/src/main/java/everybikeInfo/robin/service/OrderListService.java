package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cleanbean.OrderListRobin;
import cleanbean.OrderListRobinYear;
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

	@Override
	public List<OrderList> selecOrderTotal(String branchName) {
		// TODO Auto-generated method stub
		return orderListIFaceDao.selecOrderTotal(branchName);
	}

	@Override
	public OrderListRobinYear OrderListForJson(List<OrderList> OrderList) {
		// TODO Auto-generated method stub
		return orderListIFaceDao.OrderListForJson(OrderList);
	}

	@Override
	public List<OrderList> selecOrderTotalYear(String branchName) {
		// TODO Auto-generated method stub
		return orderListIFaceDao.selecOrderTotalYear(branchName);
	}

	@Override
	public OrderListRobinYear OrderListForJsonYear(List<OrderList> OrderList) {
		// TODO Auto-generated method stub
		return orderListIFaceDao.OrderListForJsonYear(OrderList);
	}

}
