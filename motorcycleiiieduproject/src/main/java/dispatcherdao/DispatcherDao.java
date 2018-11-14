package dispatcherdao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import cleanbean.DispacherHistoryBean;
import orderdao.OrderIFaceDAO;
import projectbean.BikeDetail;
import projectbean.EveryBikeInfo;
import projectbean.OrderList;

@Repository
public class DispatcherDao implements DispatcherIFaceDao {
	@Autowired
	SessionFactory factory;
	@Autowired
	OrderIFaceDAO orderIFaceDAO;

	@Override
	public List<OrderList> showAllOrderFromShop(List<String> allbranch ) {
		
		
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		List<Predicate> predicatesList = new ArrayList<Predicate>();
		for(String root:allbranch) {
			predicatesList.add(buider.equal(fromClass.get("pickupStore"), root));
		}
		createQuery.select(fromClass)
				.where(buider.and(buider.or(predicatesList.toArray(new Predicate[predicatesList.size()])),
						buider.or(buider.equal(fromClass.get("orderStatus"), "未來訂單"),
								buider.equal(fromClass.get("orderStatus"), "進行中訂單"),
								buider.equal(fromClass.get("orderStatus"), "未來調度"),
								buider.equal(fromClass.get("orderStatus"), "進行中調度"))));
		Query<OrderList> queryword = factory.getCurrentSession().createQuery(createQuery);
		List<OrderList> list = queryword.getResultList();
		return list;
	}

	@Override
	public List<OrderList> showAllOrderFromAnotherShop(List<String> shopname, String pickupdate) throws ParseException {
		
		
		
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<Predicate> predicatesList = new ArrayList<Predicate>();
		for( String root:shopname) {
			predicatesList.add(buider.equal(fromClass.get("dropoffStore"), root)) ; 
			
		}
		// 乙地甲環訂單篩選 1.環車地點為甲地 2.取車地點不等於還車地點 3.環車時間小於訂單開始時間 4.訂單為預定或者進行中
		createQuery.select(fromClass)
				.where(buider.and(buider.or(predicatesList.toArray(new Predicate[predicatesList.size()])),
						buider.notEqual(fromClass.get("pickupStore"), fromClass.get("dropoffStore")),
						buider.lessThanOrEqualTo(fromClass.<Date>get("dropoffDate"), sdf.parse(pickupdate)),
						buider.or(buider.equal(fromClass.get("orderStatus"), "未來訂單"),
								buider.equal(fromClass.get("orderStatus"), "進行中訂單"),
								buider.equal(fromClass.get("orderStatus"), "未來調度"),
								buider.equal(fromClass.get("orderStatus"), "進行中調度"))));
		Query<OrderList> queryword = factory.getCurrentSession().createQuery(createQuery);
		List<OrderList> list = queryword.getResultList();
		return list;
	}

	@Override
	public List<OrderList> checkAllOrderFromABShop(List<OrderList> orderbranch, String pickupdate)
			throws ParseException {
		// TODO Auto-generated method stub
		
		System.out.println("ABHSOP===================================");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		Date pickupdateTran = sdf.parse(pickupdate);    
		List<OrderList> listForAbShop = new ArrayList<OrderList>(orderbranch);
		System.out.println("listForAbShop" + listForAbShop.toString());
		Iterator<OrderList> orderbranchIter = listForAbShop.iterator();
		while (orderbranchIter.hasNext()) {
			OrderList check = orderbranchIter.next();
			// System.out.println("==取車地=="+check.getPickupStore()+"==環車地=="+check.getDropoffStore());
			// 甲地乙還訂單 由甲地所有訂單選出來 扣除 （取車地點等於還車地點） , 或者 取車時間小於 訂單還車時間 （代表這些可以給另一個判斷式篩選）
			if (check.getPickupStore().equals(check.getDropoffStore())
					|| ((pickupdateTran.getTime()-(60*60*1000)) < check.getDropoffDate().getTime())) {
				orderbranchIter.remove();
			}

		}
		for (OrderList loop : listForAbShop) {
			System.out.println("=====甲地乙還" + loop.getOrderSerialNum());
		}
		return listForAbShop;
	
}
	@Override
	public List<String> showAllBranch() {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public List<OrderList> compareOrderlist(BasicOrderBean customerquery, List<OrderList> orderbranch)
			throws ParseException {
		System.out.println("此方法比較所有已存在訂單並且回傳重複的車名進入之後" + orderbranch.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parsePickupDate = sdf.parse(customerquery.getPickupDate());
		Date parseDropoffDate = sdf.parse(customerquery.getDropoffDate());
		List<OrderList> motorlist = new ArrayList<OrderList>();
		// 開始比對訂單......
		for (OrderList loop : orderbranch) {
			System.out.println("二次判斷內部" + loop.getOrderSerialNum());
			// 如果新單開始時間大於等於已存在訂單開始時間 ＆ 小於訂單結束時間 ＝跳過這次迴圈並且記錄重複的訂單車子
			if ((parsePickupDate.getTime()-(60*60*1000)) >= loop.getPickupDate().getTime()
					&& (parsePickupDate.getTime()-(60*60*1000)) < loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
				continue;
			}
			// 如果新單結束時間大於已存在訂單開始時間 ＆ 小於等於訂單結束時間 ＝跳過這次迴圈並且記錄重複的訂單車子
			if ((parseDropoffDate.getTime()+(60*60*1000)) > loop.getPickupDate().getTime()
					&& (parseDropoffDate.getTime()+(60*60*1000)) <= loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
				continue;
			}
			// 如果新單開始時間小於等於已存在訂單開始時間 ＆ 新單結束時間大於等於訂單結束時間 ＝記錄重複的訂單車子
			if ((parsePickupDate.getTime()-(60*60*1000)) < loop.getPickupDate().getTime()
					&& (parseDropoffDate.getTime()+(60*60*1000)) > loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
				continue;
			}
			// 如果新單開始時間等於已存在訂單開始時間 ＆ 新單結束時間等於訂單結束時間 ＝記錄重複的訂單車子
			if ((parsePickupDate.getTime()-(60*60*1000)) == loop.getPickupDate().getTime()
					&& (parseDropoffDate.getTime()+(60*60*1000)) == loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
			}
		}
		for (OrderList lop : motorlist) {
			System.out.println("====重複車輛====" + lop.getBikeModel());
		}
		return motorlist;
	}

	@Override
	public List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist, List<String> shopname,
			List<OrderList> orderABbranch, List<OrderList> orderFromAnotherbranch) {
		// 取出店內庫存總數
				// 建立查詢物件
				CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
				// 查詢結果的型態
				CriteriaQuery<EveryBikeInfo> createQuery = buider.createQuery(EveryBikeInfo.class);
				// 查詢目標
				Root<EveryBikeInfo> fromClass = createQuery.from(EveryBikeInfo.class);
				List<Predicate> predicatesList11 = new ArrayList<Predicate>();
				for(String root:shopname) {
					predicatesList11.add(buider.equal(fromClass.get("branchName").get("branchName"), root));
				}
				// select * from everbikeinfo.class where
				// everbikeinfo.class.BranchName.branchName = 傳入店名
				createQuery.select(fromClass)
						.where(buider.and(buider.or(predicatesList11.toArray(new Predicate[predicatesList11.size()])),
								buider.equal(fromClass.get("isReadyMaintenance"), false)));

				// 查詢物件
				Query<EveryBikeInfo> queryword = factory.getCurrentSession().createQuery(createQuery);
		
				// 取出結果
				List<EveryBikeInfo> bikeDetaillist = queryword.getResultList();
				List<String> motorNameToString = new ArrayList<String>();
				for (EveryBikeInfo loop : bikeDetaillist) {
					System.out.println("測試=" + loop.getBikeDetail().getIdClassBikeDetail().getBikeModel()+"=="+loop.getLicensePlate());
					motorNameToString.add(loop.getBikeDetail().getIdClassBikeDetail().getBikeModel());
				}
				// 將庫存轉成數量
				Set<String> branchMotorUniqueSet = new HashSet<String>(motorNameToString);
				HashMap<String, Integer> branchMotorMap = new HashMap<String, Integer>();
				for (String loop : branchMotorUniqueSet) {
					System.out.println("店內庫存===" + loop + ": " + Collections.frequency(motorNameToString, loop));
					branchMotorMap.put(loop, Integer.valueOf(Collections.frequency(motorNameToString, loop)));
				}
				// 處理甲地乙還的訂單
				List<String> ABmotorName = new ArrayList<>();
				for (OrderList loop : orderABbranch) {
					ABmotorName.add(loop.getBikeModel());
				}
				Set<String> ABbranchMotorUniqueSet = new HashSet<String>(ABmotorName);
				// 店內的庫存如果有甲地乙還訂單 就扣掉數量（前提是顧客下單在甲地以還訂單之後
				Iterator<Entry<String, Integer>> branchMotorMapiter = branchMotorMap.entrySet().iterator();
				while (branchMotorMapiter.hasNext()) {
					Entry<String, Integer> branchMotorMapiterName = branchMotorMapiter.next();
					for (String innerloop : ABbranchMotorUniqueSet) {
						System.out.println(branchMotorMapiterName.getKey() + "甲地乙還判斷" + innerloop);
						// 如果計算完可承租車輛與甲地乙還訂單重複
						if (branchMotorMapiterName.getKey().equals(innerloop)) {
							System.out.println(branchMotorMapiterName.getValue() + "<===庫存 : 訂單===>"
									+ Collections.frequency(ABmotorName, innerloop));
							branchMotorMap.put(branchMotorMapiterName.getKey(),
									branchMotorMapiterName.getValue() - Collections.frequency(ABmotorName, innerloop));
							System.out.println(branchMotorMapiterName.getKey() + "==="
									+ (branchMotorMapiterName.getValue() - Collections.frequency(ABmotorName, innerloop)));
						}
					}
				}
				// 處理乙地甲還訂單
				// 把乙地甲環的訂單車名取出 用來計算
				List<String> fromAnothermotorName = new ArrayList<>();
				for (OrderList loop : orderFromAnotherbranch) {
					fromAnothermotorName.add(loop.getBikeModel());
				}
				// 因為甲地不一定有乙地的車 所以要從訂單下手
				Set<String> fromAnotherMotorUniqueSet = new HashSet<String>(fromAnothermotorName);
				branchMotorMapiter = branchMotorMap.entrySet().iterator();
				// 第一次比對庫存與乙地訂單有同樣名字的車 把數量加上去
				while (branchMotorMapiter.hasNext()) {
					Entry<String, Integer> branchMotorMapiterName = branchMotorMapiter.next();
					for (String loop : fromAnotherMotorUniqueSet) {
						if (branchMotorMapiterName.getKey().equals(loop)) {
							branchMotorMap.put(loop,
									(branchMotorMapiterName.getValue() + Collections.frequency(fromAnothermotorName, loop)));
						}
					}
				}
				// 最後把甲地沒有的車算到甲地的庫存內
				for (String loop : fromAnotherMotorUniqueSet) {
					if (branchMotorMap.get(loop) == null) {
						branchMotorMap.put(loop, Collections.frequency(fromAnothermotorName, loop));
					}
				}

				// 將訂單重複的資訊轉成數量
				Set<String> orderMotorUniqueSet = new HashSet<String>(compareOrderlist);
				HashMap<String, Integer> orderMotorMap = new HashMap<String, Integer>();
				for (String loop : orderMotorUniqueSet) {
					System.out.println("order=" + loop + ": " + Collections.frequency(compareOrderlist, loop));
					orderMotorMap.put(loop, Integer.valueOf(Collections.frequency(compareOrderlist, loop)));
				}
				// 店內所有車種庫存取出比對
				Iterator<Entry<String, Integer>> branchMotorItr = branchMotorMap.entrySet().iterator();
				while (branchMotorItr.hasNext()) {
					Entry<String, Integer> loopkey = branchMotorItr.next();
					System.out.println(loopkey.getKey() + "=branch=" + loopkey.getValue());
					// 訂單重複的車子數量取出比對
					for (String orderloopkey : orderMotorMap.keySet()) {
						System.out.println(orderloopkey + "=order=" + orderMotorMap.get(orderloopkey));
						// 如果店內車名等於訂單重複車名 就進去比較庫存
						if (loopkey.getKey().equals(orderloopkey)) {
							// 如果店內庫存小於等於訂單比對結果=>無車可以租把他從店內庫存ＭＡＰ移出去
							if (loopkey.getValue() <= orderMotorMap.get(orderloopkey)) {
								branchMotorItr.remove();
							}
						}
					}
				}
				// 店內所有車種經過甲地乙還判斷後庫存小於等於0 移除
				Iterator<Entry<String, Integer>> abbranchMotorItrcheck = branchMotorMap.entrySet().iterator();
				while (abbranchMotorItrcheck.hasNext()) {
					Entry<String, Integer> abbranchMotorItrcheckcheck = abbranchMotorItrcheck.next();
					if (abbranchMotorItrcheckcheck.getValue() <= 0) {
						abbranchMotorItrcheck.remove();
					}
				}

				// 創建新的查詢字句
				CriteriaQuery<BikeDetail> createBikeQuery = buider.createQuery(BikeDetail.class);
				Root<BikeDetail> fromClassBike = createBikeQuery.from(BikeDetail.class);
				// 封裝主查詢條件
				List<Predicate> predicatesList = new ArrayList<Predicate>();
				// 封裝子查詢條件
				List<Predicate> secondPredicate = new ArrayList<Predicate>();
				// 迴圈生成查詢A=B語句

				for (String loopkey : branchMotorMap.keySet()) {
					Predicate motorpre = buider.equal(fromClassBike.get("idClassBikeDetail").get("bikeModel"), loopkey);
					secondPredicate.add(motorpre);
				}
				// 將結果塞到or查詢語句
				predicatesList.add(buider.or(secondPredicate.toArray(new Predicate[secondPredicate.size()])));
				// 將結果塞到where語句 變成(select * from BikeDetail.class where [a=b or c=d or ......
				// 動態生成])
				createBikeQuery.select(fromClassBike).where(predicatesList.toArray(new Predicate[predicatesList.size()]));
				List<BikeDetail> finalBikeDetail = factory.getCurrentSession().createQuery(createBikeQuery).getResultList();
				for (BikeDetail motor : finalBikeDetail) {
					System.out.println(motor.getBikeBrand() + "==" + motor.getIdClassBikeDetail().getBikeModel());
				}
				return finalBikeDetail;
	}

	@Override
	public List<BikeDetailToGsonHaoUse> forGsonConvert(List<BikeDetail> finalBikeDetail) {
		
		return orderIFaceDAO.forGsonConvert(finalBikeDetail);
	}

	@Override
	public List<OrderList> checkDispatcherHistory(DispacherHistoryBean dispacherHistoryBean) {
		
		
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		// 封裝主查詢條件
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		if (dispacherHistoryBean.getDropoffstore() != "") {
			predicatesList.add( buider.equal(fromClass.get("dropoffStore"), dispacherHistoryBean.getDropoffstore())); 
		}
		if (dispacherHistoryBean.getOrderstatus() != "") {
			predicatesList.add( buider.equal(fromClass.get("orderStatus"), dispacherHistoryBean.getOrderstatus()));
		}
		if (dispacherHistoryBean.getPickupstore() != "") {
			predicatesList.add( buider.equal(fromClass.get("pickupStore"), dispacherHistoryBean.getPickupstore()));
		}

		createQuery.select(fromClass).where(buider.and(predicatesList.toArray(new Predicate[predicatesList.size()])));
		List<OrderList> branchlist = factory.getCurrentSession().createQuery(createQuery).getResultList();

		
		
		
		return branchlist;
		
	
	}

}
