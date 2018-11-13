package dispatcherdao;

import java.text.ParseException;
import java.util.List;

import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import projectbean.BikeDetail;
import projectbean.OrderList;

public interface DispatcherIFaceDao {

	
	List<OrderList> showAllOrderFromShop(List<String> allbranch );

	List<OrderList> showAllOrderFromAnotherShop(List<String> shopname , String pickupdate) throws ParseException;
	
	List<OrderList> checkAllOrderFromABShop(List<OrderList> orderbranch , String pickupdate) throws ParseException;

	List<String> showAllBranch();

	List<OrderList> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch) throws ParseException;

	List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist, List<String> shopname , List<OrderList> orderABbranch ,
			List<OrderList> orderFromAnotherbranch );

	List<BikeDetailToGsonHaoUse> forGsonConvert(List<BikeDetail> finalBikeDetail);
	
}
