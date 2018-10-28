package testbean;

import java.text.ParseException;
import java.util.List;

import projectbean.BikeDetail;
import projectbean.OrderList;
import testcontroller.BasicOrderBean;

public interface OrderIFaceDAO {

	List<OrderList>  showAllOrderFromShop(String shopname);
	List<String> showAllBranch();
	List<String>  compareOrderlist(BasicOrderBean customerquery , List<OrderList> orderbranch)throws ParseException ;
	List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist , String shopname);
	List<BikeDetailToGson> forGsonConvert(List<BikeDetail> finalBikeDetail);
	

}