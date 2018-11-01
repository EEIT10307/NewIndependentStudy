package testbean.robin;

import java.util.List;

import projectbean.OrderList;

public interface OrderListIFaceService {
	List<OrderList> selectone(String OrderSerialNum);
}
