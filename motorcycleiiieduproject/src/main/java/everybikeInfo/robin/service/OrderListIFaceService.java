package everybikeInfo.robin.service;

import java.util.List;

import cleanbean.OrderListRobin;
import cleanbean.OrderListRobinYear;
import projectbean.OrderList;

public interface OrderListIFaceService {
	List<OrderList> selectone(String OrderSerialNum);
	List<OrderList> selecOrderTotal(String branchName);
	List<OrderList> selecOrderTotalYear(String branchName);
	OrderListRobinYear OrderListForJson(List<OrderList> OrderList);
	OrderListRobinYear OrderListForJsonYear(List<OrderList> OrderList);
}
