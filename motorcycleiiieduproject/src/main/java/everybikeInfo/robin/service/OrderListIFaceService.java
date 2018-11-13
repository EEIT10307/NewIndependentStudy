package everybikeInfo.robin.service;

import java.util.List;

import cleanbean.MemberDetailSelectYearForJson;
import cleanbean.OrderListRobinYear;
import projectbean.MemberDetail;
import projectbean.OrderList;

public interface OrderListIFaceService {
	List<OrderList> selectone(String OrderSerialNum);
	List<OrderList> selecOrderTotal(String branchName);
	List<OrderList> selecOrderTotalYear(String branchName);
	OrderListRobinYear OrderListForJson(List<OrderList> OrderList);
	OrderListRobinYear OrderListForJsonYear(List<OrderList> OrderList);
	List<MemberDetail> selectMemberyear();
	MemberDetailSelectYearForJson MemberDetailSelectYearForJson(List<MemberDetail> memberDetail);
}
