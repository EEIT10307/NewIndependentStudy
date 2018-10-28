package testbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.OrderList;
import testcontroller.BasicOrderBean;

@Repository
public class OrderDAO implements OrderIFaceDAO {

	@Autowired
	SessionFactory factory;

	// 查詢該店訂單總數

	@Override
	public List<OrderList> showAllOrderFromShop(String shopname) {

		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		ParameterExpression<String> pickupStore = buider.parameter(String.class);
		createQuery.select(fromClass).where(buider.equal(fromClass.get("pickupStore"), pickupStore));
		Query<OrderList> queryword = factory.getCurrentSession().createQuery(createQuery);
		queryword.setParameter(pickupStore, shopname);
		List<OrderList> list = queryword.getResultList();

//		for (OrderList loop : list) {
//			System.out.println("showAllOrderFromShop ="+loop.getBikeModel()+":"+loop.getPickupDate()+"=>"+loop.getDropoffDate());
//		}
		return list;

	}

	@Override
	public List<String> showAllBranch() {
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchDetail> createQuery = buider.createQuery(BranchDetail.class);
		Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
		createQuery.select(fromClass);
		List<BranchDetail> branchlist = factory.getCurrentSession().createQuery(createQuery).getResultList();
		List<String> branchnamelist = new ArrayList<>();
		for (BranchDetail loop : branchlist) {
			branchnamelist.add(loop.getBranchName());
		}
		return branchnamelist;
	}

	@Override
	public List<String> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parsePickupDate = sdf.parse(customerquery.getPickupDate());
		Date parseDropoffDate = sdf.parse(customerquery.getDropoffDate());
		List<String> motorlist = new ArrayList<String>();
		// 開始比對訂單......
		for (OrderList loop : orderbranch) {
			// 如果新單開始時間大於等於已存在訂單開始時間 ＆ 小於訂單結束時間 ＝跳過這次迴圈並且記錄重複的訂單車子
			if (parsePickupDate.getTime() >= loop.getPickupDate().getTime()
					&& parsePickupDate.getTime() < loop.getDropoffDate().getTime()) {
				motorlist.add(loop.getBikeModel());
				continue;
			}
			// 如果新單結束時間大於已存在訂單開始時間 ＆ 小於等於訂單結束時間 ＝跳過這次迴圈並且記錄重複的訂單車子
			if (parseDropoffDate.getTime() > loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() <= loop.getDropoffDate().getTime()) {
				motorlist.add(loop.getBikeModel());
				continue;
			}
			// 如果新單開始時間小於等於已存在訂單開始時間 ＆ 新單結束時間大於等於訂單結束時間 ＝記錄重複的訂單車子
			if (parsePickupDate.getTime() < loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() > loop.getDropoffDate().getTime()) {
				motorlist.add(loop.getBikeModel());
			}
		}
		for (String loop : motorlist) {
			System.out.println("重複車輛測試＝" + loop);
		}
		return motorlist;
	}

	@Override
	public List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist, String shopname) {
		// 取出店內庫存總數
		// 建立查詢物件
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		// 查詢結果的型態
		CriteriaQuery<EveryBikeInfo> createQuery = buider.createQuery(EveryBikeInfo.class);
		// 查詢目標
		Root<EveryBikeInfo> fromClass = createQuery.from(EveryBikeInfo.class);
		// 定義查詢型態
		ParameterExpression<String> checkshopname = buider.parameter(String.class);
		// select * from everbikeinfo.class where
		// everbikeinfo.class.BranchName.branchName = 傳入店名
		createQuery.select(fromClass).where(buider.equal(fromClass.get("branchName").get("branchName"), checkshopname));
		// 查詢物件
		Query<EveryBikeInfo> queryword = factory.getCurrentSession().createQuery(createQuery);
		// 定義參數
		queryword.setParameter(checkshopname, shopname);
		// 取出結果
		List<EveryBikeInfo> bikeDetaillist = queryword.getResultList();
		List<String> motorNameToString = new ArrayList<String>();
		for (EveryBikeInfo loop : bikeDetaillist) {
			System.out.println("測試=" + loop.getBikeDetail().getIdClassBikeDetail().getBikeModel());
			motorNameToString.add(loop.getBikeDetail().getIdClassBikeDetail().getBikeModel());
		}
		// 將庫存轉成數量
		Set<String> branchMotorUniqueSet = new HashSet<String>(motorNameToString);
		HashMap<String, Integer> branchMotorMap = new HashMap<String, Integer>();
		for (String loop : branchMotorUniqueSet) {
			System.out.println(loop + ": " + Collections.frequency(motorNameToString, loop));
			branchMotorMap.put(loop, Integer.valueOf(Collections.frequency(motorNameToString, loop)));
		}
		// 將訂單重複的資訊轉成數量
		Set<String> orderMotorUniqueSet = new HashSet<String>(compareOrderlist);
		HashMap<String, Integer> orderMotorMap = new HashMap<String, Integer>();
		for (String loop : orderMotorUniqueSet) {
			System.out.println("order=" + loop + ": " + Collections.frequency(compareOrderlist, loop));
			orderMotorMap.put(loop, Integer.valueOf(Collections.frequency(compareOrderlist, loop)));
		}
		// 店內所有車種庫存取出比對
		for (String loopkey : branchMotorMap.keySet()) {
			System.out.println(loopkey + "=branch=" + branchMotorMap.get(loopkey));
			// 訂單重複的車子數量取出比對
			for (String orderloopkey : orderMotorMap.keySet()) {
				System.out.println(orderloopkey + "=order=" + orderMotorMap.get(orderloopkey));
				// 如果店內車名等於訂單重複車名 就進去比較庫存
				if (loopkey.equals(orderloopkey)) {
					// 如果店內庫存小於等於訂單比對結果=>無車可以租把他從店內庫存ＭＡＰ移出去
					if (branchMotorMap.get(loopkey) <= orderMotorMap.get(orderloopkey)) {
						branchMotorMap.remove(loopkey);
					}
				}
			}
		}
		//創建新的查詢字句
		CriteriaQuery<BikeDetail> createBikeQuery = buider.createQuery(BikeDetail.class);
		    Root<BikeDetail> fromClassBike = createBikeQuery.from(BikeDetail.class);
		    //封裝主查詢條件
		    List<Predicate> predicatesList = new ArrayList<Predicate>();
		    //封裝子查詢條件
		    List<Predicate> secondPredicate = new ArrayList<Predicate>(); 
		for(String loopkey : branchMotorMap.keySet()) {
			Predicate motorpre = buider.equal(fromClassBike.get("idClassBikeDetail").get("bikeModel"),loopkey);		
			secondPredicate.add(motorpre);
		}
		 predicatesList.add(buider.or(secondPredicate.toArray(new Predicate[secondPredicate.size()])));
		 createBikeQuery.select(fromClassBike).where(predicatesList.toArray(new Predicate[predicatesList.size()]));
		  List<BikeDetail> finalBikeDetail = factory.getCurrentSession().createQuery(createBikeQuery).getResultList();
		  for( BikeDetail motor:finalBikeDetail) {
			  System.out.println(motor.getBikeBrand()+"=="+motor.getIdClassBikeDetail().getBikeModel());
		  }
		return finalBikeDetail;
	}

	@Override
	public List<BikeDetailToGson> forGsonConvert(List<BikeDetail> finalBikeDetail) {
		
		ArrayList<BikeDetailToGson> bikeDetailToGsonList = new ArrayList<BikeDetailToGson>();
		
		for(BikeDetail loop:finalBikeDetail) {
			bikeDetailToGsonList.add(new BikeDetailToGson(loop.getIdClassBikeDetail().getBikeModel(), loop.getIdClassBikeDetail().
					getModelYear(), loop.getBikeBrand(), loop.getEngineType(), loop.getBikeType(), loop.getPlateType(), 
					loop.getFuelTankCapacity(), loop.getSeatHeight(),loop.getDryWeight(), loop.getFuelConsumption(), loop.getTire(), 
					loop.getFuelType(), loop.getABS(), loop.getHourPrice(), loop.getOnSheftTime()))	;
		}
		return bikeDetailToGsonList;
	}

}
