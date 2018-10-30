package testbean.robin;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.OrderList;
@Repository
public interface OrderListIFaceDao {
	List<OrderList> selectone(String OrderSerialNum);
}
