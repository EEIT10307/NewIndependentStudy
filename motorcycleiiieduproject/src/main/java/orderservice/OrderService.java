package orderservice;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import orderdao.BasicOrderBean;
import orderdao.BikeDetailToGson;
import orderdao.OrderIFaceDAO;
import projectbean.BikeDetail;
import projectbean.OrderList;

@Service
@Transactional
public class OrderService implements OrderIFaceService {
	
	@Autowired
	SessionFactory factory;
	@Autowired
	OrderIFaceDAO testOrderDAO;
	
	
	/* (non-Javadoc)
	 * @see testbean.TesOrderIFaceService#showAllOrderFromShop(java.lang.String)
	 */
	@Override
	public List<OrderList>  showAllOrderFromShop(String shopName) {
		return testOrderDAO.showAllOrderFromShop(shopName);	
	}


	@Override
	public List<String> showAllBranch() {
		return testOrderDAO.showAllBranch();
	}


	@Override
	public List<String> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch)throws ParseException  {
		return testOrderDAO.compareOrderlist(customerquery, orderbranch);
	}


	@Override
	public List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist , String shopname) {
		
		return testOrderDAO.returnMotorDetailAndShowView(compareOrderlist,shopname);
	}


	@Override
	public List<BikeDetailToGson> forGsonConvert(List<BikeDetail> finalBikeDetail) {
		
		return testOrderDAO.forGsonConvert(finalBikeDetail);
	}



	
	
	
	
	

}
