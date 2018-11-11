package orderservice;

import java.text.ParseException;
import java.util.List;
import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGson;
import cleanbean.BikeDetailToGsonHaoUse;
import orderdao.OrderListToGson;
import projectbean.AcceStock;
import projectbean.BikeDetail;
import projectbean.Discount;
import projectbean.OrderList;

public interface OrderIFaceService {

	List<OrderList> showAllOrderFromShop(String shopName);

	List<OrderList> showAllOrderFromAnotherShop(String shopname , String pickupdate) throws ParseException;
	
	List<OrderList> checkAllOrderFromABShop(List<OrderList> orderbranch , String pickupdate) throws ParseException;

	List<String> showAllBranch();

	List<OrderList> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch) throws ParseException;

	List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist, String shopname , List<OrderList> orderABbranch ,
			List<OrderList> orderFromAnotherbranch );

	List<BikeDetailToGsonHaoUse> forGsonConvert(List<BikeDetail> finalBikeDetail);

	List<Discount> getDiscount();

	List<AcceStock> compareAcceStock(BasicOrderBean customerquery , List<OrderList> orderABbranch ,List<OrderList> orderFromAnotherbranch ) throws ParseException;

	OrderList convertToOrderList(OrderListToGson customorder) throws ParseException;

	void addOrderToDatabase(OrderList convertOrder, String customorderOld, List<OrderList> orderbranch,
			BasicOrderBean customerquery ,  List<OrderList> orderABbranch  ,  List<OrderList> orderFromAnotherbranch) throws ParseException;


}