package orderdao;

import java.text.ParseException;
import java.util.List;

import projectbean.AcceStock;
import projectbean.BikeDetail;
import projectbean.Discount;
import projectbean.OrderList;

public interface OrderIFaceDAO {

	List<OrderList> showAllOrderFromShop(String shopname);
	
	List<OrderList> showAllOrderFromAnotherShop(String shopname , String pickupdate) throws ParseException;
	
	List<OrderList>  checkAllOrderFromABShop(List<OrderList> orderbranch , String pickupdate) throws ParseException;

	List<String> showAllBranch();

	List<OrderList> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch) throws ParseException;

	List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist, String shopname ,List<OrderList> orderABbranch , 
			List<OrderList> orderFromAnotherbranch );

	List<BikeDetailToGson> forGsonConvert(List<BikeDetail> finalBikeDetail);

	List<Discount> getDiscount();

	List<AcceStock> compareAcceStock(BasicOrderBean customerquery, List<OrderList> orderABbranch ,List<OrderList> orderFromAnotherbranch) throws ParseException;

	List<AcceStock> showAllAcceFromShop(String shopname);
	
	OrderList  convertToOrderList(OrderListToGson customorder) throws ParseException;
	
	void addOrderToDatabase(OrderList convertOrder , String customorderOld , List<OrderList> orderbranch , 
			BasicOrderBean customerquery , List<OrderList> orderABbranch  ,  List<OrderList> orderFromAnotherbranch) throws ParseException;
	
	List<AcceStock> afterCompareAccAndABshopAndAnother (List<AcceStock> afterCompareAcc , List<OrderList> acceUseOrderABbranch , List<OrderList>  acceUseOrderFromAnotherbranch) ; 
}