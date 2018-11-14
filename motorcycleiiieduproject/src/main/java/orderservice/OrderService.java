package orderservice;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import cleanbean.FinOrderBean;
import cleanbean.ManagerOrderCondition;
import cleanbean.OrderListToGson;
import cleanbean.ShowManagerChangeOrderStatus;
import orderdao.OrderIFaceDAO;
import projectbean.AcceStock;
import projectbean.BikeDetail;
import projectbean.Discount;
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
	public List<OrderList> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch)throws ParseException  {
		return testOrderDAO.compareOrderlist(customerquery, orderbranch);
	}


	@Override
	public List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist , String shopname , 
			List<OrderList> orderABbranch , List<OrderList> orderFromAnotherbranch) {
		
		return testOrderDAO.returnMotorDetailAndShowView(compareOrderlist,shopname , orderABbranch , orderFromAnotherbranch );
	}


	@Override
	public List<BikeDetailToGsonHaoUse> forGsonConvert(List<BikeDetail> finalBikeDetail) {
		
		return testOrderDAO.forGsonConvert(finalBikeDetail);
	}


	@Override
	public List<Discount> getDiscount() {
		
		return testOrderDAO.getDiscount();
	}


	@Override
	public List<AcceStock> compareAcceStock(BasicOrderBean customerquery ,  List<OrderList> orderABbranch ,List<OrderList> orderFromAnotherbranch) throws ParseException {
		
		return testOrderDAO.compareAcceStock(customerquery, orderABbranch , orderFromAnotherbranch);
	}


	@Override
	public OrderList convertToOrderList(OrderListToGson customorder) throws ParseException {
		
		return testOrderDAO.convertToOrderList(customorder);
	}


	@Override
	public void addOrderToDatabase(OrderList convertOrder , String customorderOld , List<OrderList> orderbranch , BasicOrderBean customerquery 
			,  List<OrderList> orderABbranch  ,  List<OrderList> orderFromAnotherbranch) throws ParseException {
		testOrderDAO.addOrderToDatabase(convertOrder , customorderOld , orderbranch , customerquery ,  orderABbranch  ,   orderFromAnotherbranch);
		
	}


	@Override
	public List<OrderList> checkAllOrderFromABShop(List<OrderList> orderbranch , String pickupdate) throws ParseException {
		
		return testOrderDAO.checkAllOrderFromABShop(orderbranch , pickupdate);
	}


	@Override
	public List<OrderList> showAllOrderFromAnotherShop(String shopname , String pickupdate) throws ParseException {
		// TODO Auto-generated method stub
		return testOrderDAO.showAllOrderFromAnotherShop(shopname , pickupdate);

	}


	@Override
	public List<OrderList> showMemberAndNonMemberDetail(String showMemberAndNonMemberphnoe) {
		
		return testOrderDAO.showMemberAndNonMemberDetail(showMemberAndNonMemberphnoe);
	}


	@Override
	public List<OrderListToGson> convertOrderListToGson(List<OrderList> customorder) throws ParseException {
		// TODO Auto-generated method stub
		return testOrderDAO.convertOrderListToGson(customorder);
	}


	@Override
	public List<OrderList> showManagerSearchDetail(ManagerOrderCondition managerOrderCondition) {
		// TODO Auto-generated method stub
		return testOrderDAO.showManagerSearchDetail(managerOrderCondition);
	}


	@Override
	public List<OrderListToGson> convertOrderListToGsonWithPlate(List<OrderList> customorder) throws ParseException {
		// TODO Auto-generated method stub
		return testOrderDAO.convertOrderListToGsonWithPlate(customorder);
	}


	@Override
	public void showManagerChangeOrderStatus(ShowManagerChangeOrderStatus showManagerChangeOrderStatus) {
		// TODO Auto-generated method stub
		testOrderDAO.showManagerChangeOrderStatus(showManagerChangeOrderStatus) ; 
		
	}


	@Override
	public void showManagerFinishedOrder(FinOrderBean finOrderBean) {
		// TODO Auto-generated method stub
		testOrderDAO.showManagerFinishedOrder(finOrderBean);
	}


	@Override
	public void showManagerFinishedDiapatcher(ShowManagerChangeOrderStatus showManagerChangeOrderStatus) {
		// TODO Auto-generated method stub
		testOrderDAO.showManagerFinishedDiapatcher(showManagerChangeOrderStatus) ; 
	}



	
	
	
	
	

}
