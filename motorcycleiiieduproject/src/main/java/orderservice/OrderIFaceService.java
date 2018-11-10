package orderservice;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import cleanbean.ManagerOrderCondition;
import cleanbean.OrderListToGson;
import cleanbean.ShowManagerChangeOrderStatus;
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

	List<OrderListToGson>  convertOrderListToGson(List<OrderList>  customorder) throws ParseException;
	
	void addOrderToDatabase(OrderList convertOrder, String customorderOld, List<OrderList> orderbranch,
			BasicOrderBean customerquery ,  List<OrderList> orderABbranch  ,  List<OrderList> orderFromAnotherbranch) throws ParseException;

	List<OrderList>    showMemberAndNonMemberDetail(String showMemberAndNonMemberphnoe);
	
	List<OrderListToGson>  convertOrderListToGsonWithPlate(List<OrderList>  customorder) throws ParseException;
	
	List<OrderList>    showManagerSearchDetail(ManagerOrderCondition managerOrderCondition) ; 
	
	  void   showManagerChangeOrderStatus( ShowManagerChangeOrderStatus showManagerChangeOrderStatus)  ; 
	
	
}