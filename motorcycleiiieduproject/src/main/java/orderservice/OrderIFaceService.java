package orderservice;

import java.text.ParseException;
import java.util.List;

import orderdao.BasicOrderBean;
import orderdao.BikeDetailToGson;
import projectbean.BikeDetail;
import projectbean.OrderList;

public interface OrderIFaceService {

	List<OrderList>  showAllOrderFromShop(String shopName);
	List<String> showAllBranch();
	List<String> compareOrderlist(BasicOrderBean customerquery , List<OrderList> orderbranch)throws ParseException ;
	List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist , String shopname);
	List<BikeDetailToGson> forGsonConvert(List<BikeDetail> finalBikeDetail);
	
	
	
}