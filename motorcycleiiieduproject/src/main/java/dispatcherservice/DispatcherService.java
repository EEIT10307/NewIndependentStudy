package dispatcherservice;

import java.text.ParseException;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import cleanbean.DispacherHistoryBean;
import dispatcherdao.DispatcherIFaceDao;
import projectbean.BikeDetail;
import projectbean.OrderList;

@Service
@Transactional
public class DispatcherService implements DispatcherIFaceService {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	DispatcherIFaceDao dispatcherIFaceDao;

	@Override
	public List<OrderList> showAllOrderFromShop(List<String> allbranch ) {
		// TODO Auto-generated method stub
		return dispatcherIFaceDao.showAllOrderFromShop(allbranch);
	}

	@Override
	public List<OrderList> showAllOrderFromAnotherShop(List<String> shopname, String pickupdate) throws ParseException {
		// TODO Auto-generated method stub
		return dispatcherIFaceDao.showAllOrderFromAnotherShop(shopname, pickupdate);
	}

	@Override
	public List<OrderList> checkAllOrderFromABShop(List<OrderList> orderbranch, String pickupdate)
			throws ParseException {
		// TODO Auto-generated method stub
		return dispatcherIFaceDao.checkAllOrderFromABShop(orderbranch, pickupdate);
	}

	@Override
	public List<String> showAllBranch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderList> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch)
			throws ParseException {
		// TODO Auto-generated method stub
		return dispatcherIFaceDao.compareOrderlist(customerquery, orderbranch);
	}

	@Override
	public List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist, List<String> shopname,
			List<OrderList> orderABbranch, List<OrderList> orderFromAnotherbranch) {
		// TODO Auto-generated method stub
		return dispatcherIFaceDao.returnMotorDetailAndShowView(compareOrderlist, shopname, orderABbranch, orderFromAnotherbranch);
	}

	@Override
	public List<BikeDetailToGsonHaoUse> forGsonConvert(List<BikeDetail> finalBikeDetail) {
		// TODO Auto-generated method stub
		return dispatcherIFaceDao.forGsonConvert(finalBikeDetail);
	}

	@Override
	public List<OrderList> checkDispatcherHistory(DispacherHistoryBean dispacherHistoryBean) {
		// TODO Auto-generated method stub
		return dispatcherIFaceDao.checkDispatcherHistory(dispacherHistoryBean);
	}
	
	

}
