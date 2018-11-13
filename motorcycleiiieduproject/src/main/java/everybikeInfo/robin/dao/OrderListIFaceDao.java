package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.OrderListRobin;
import cleanbean.OrderListRobinYear;
import projectbean.OrderList;
@Repository
public interface OrderListIFaceDao {
	List<OrderList> selectone(String OrderSerialNum);
	List<OrderList> selecOrderTotal(String branchName);
	List<OrderList> selecOrderTotalYear(String branchName);
	OrderListRobinYear OrderListForJson(List<OrderList> OrderList);
	OrderListRobinYear OrderListForJsonYear(List<OrderList> OrderList);
}
