package orderdao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Arrays;
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
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import cleanbean.FinOrderBean;
import cleanbean.ManagerOrderCondition;
import cleanbean.OrderListToGson;
import cleanbean.ShowManagerChangeOrderStatus;
import projectbean.AcceStock;
import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.Discount;
import projectbean.EveryBikeInfo;
import projectbean.OrderList;

@Repository
public class OrderDAO implements OrderIFaceDAO {

	@Autowired
	SessionFactory factory;

	// 查詢該店未完成的訂單總數
	@Override
	public List<OrderList> showAllOrderFromShop(String shopname) {

		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		ParameterExpression<String> pickupStore = buider.parameter(String.class);
		createQuery.select(fromClass)
				.where(buider.and(buider.equal(fromClass.get("pickupStore"), pickupStore),
						buider.or(buider.equal(fromClass.get("orderStatus"), "未來訂單"),
								buider.equal(fromClass.get("orderStatus"), "進行中訂單"),
								buider.equal(fromClass.get("orderStatus"), "未來調度"),
								buider.equal(fromClass.get("orderStatus"), "進行中調度"))));

		Query<OrderList> queryword = factory.getCurrentSession().createQuery(createQuery);
		queryword.setParameter(pickupStore, shopname);
		List<OrderList> list = queryword.getResultList();

		return list;
	}

//製作分店選擇鈕
	@Override
	public List<String> showAllBranch() {
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchDetail> createQuery = buider.createQuery(BranchDetail.class);
		Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
		createQuery.select(fromClass);
		List<BranchDetail> branchlist = factory.getCurrentSession().createQuery(createQuery).getResultList();
		List<String> branchnamelist = new ArrayList<String>();
		for (BranchDetail loop : branchlist) {
			branchnamelist.add(loop.getBranchName());
		}
		return branchnamelist;
	}

//比對訂單與庫存
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
			if (parsePickupDate.getTime() >= loop.getPickupDate().getTime()
					&& parsePickupDate.getTime() < loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
				continue;
			}
			// 如果新單結束時間大於已存在訂單開始時間 ＆ 小於等於訂單結束時間 ＝跳過這次迴圈並且記錄重複的訂單車子
			if (parseDropoffDate.getTime() > loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() <= loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
				continue;
			}
			// 如果新單開始時間小於等於已存在訂單開始時間 ＆ 新單結束時間大於等於訂單結束時間 ＝記錄重複的訂單車子
			if (parsePickupDate.getTime() < loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() > loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
				continue;
			}
			// 如果新單開始時間等於已存在訂單開始時間 ＆ 新單結束時間等於訂單結束時間 ＝記錄重複的訂單車子
			if (parsePickupDate.getTime() == loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() == loop.getDropoffDate().getTime()) {
				motorlist.add(loop);
			}
		}
		for (OrderList lop : motorlist) {
			System.out.println("====重複車輛====" + lop.getBikeModel());
		}
		return motorlist;
	}

	@Override
	public List<BikeDetail> returnMotorDetailAndShowView(List<String> compareOrderlist, String shopname,
			List<OrderList> orderABbranch, List<OrderList> orderFromAnotherbranch) {
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
		createQuery.select(fromClass)
				.where(buider.and(buider.equal(fromClass.get("branchName").get("branchName"), checkshopname),
						buider.equal(fromClass.get("isReadyMaintenance"), false)));

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

		List<BikeDetailToGsonHaoUse> bikeDetailToGsonList = new ArrayList<BikeDetailToGsonHaoUse>();

		for (BikeDetail loop : finalBikeDetail) {

			bikeDetailToGsonList.add(new BikeDetailToGsonHaoUse(loop.getIdClassBikeDetail().getBikeModel(),
					loop.getIdClassBikeDetail().getModelYear(), loop.getBikeBrand(), loop.getEngineType(),
					loop.getBikeType(), loop.getPlateType(), loop.getFuelTankCapacity(), loop.getSeatHeight(),
					loop.getDryWeight(), loop.getFuelConsumption(), loop.getFrontTire(), loop.getFuelType(),
					loop.getABS(), loop.getHourPrice(), loop.getOnSheftTime(), loop.getFrontSuspension(),
					loop.getRearSuspension(), loop.getRearTire(), loop.getHorsePower(), loop.getTorque(),
					loop.getFrontBrake(), loop.getRearBrake(), loop.getDescription()));

		}

		return bikeDetailToGsonList;
	}

	@Override
	public List<Discount> getDiscount() {

		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Discount> createQuery = buider.createQuery(Discount.class);
		Root<Discount> fromClass = createQuery.from(Discount.class);
		Query<Discount> queryword = factory.getCurrentSession().createQuery(createQuery.select(fromClass));
		List<Discount> list = queryword.getResultList();

		return list;
	}

	@Override
	public List<AcceStock> compareAcceStock(BasicOrderBean customerquery, List<OrderList> orderABbranch,
			List<OrderList> orderFromAnotherbranch) throws ParseException {

		List<OrderList> orderbranch = showAllOrderFromShop(customerquery.getPickupStore());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date parsePickupDate = sdf.parse(customerquery.getPickupDate());
		Date parseDropoffDate = sdf.parse(customerquery.getDropoffDate());
		List<String> acceList = new ArrayList<String>();

		// 確認甲地乙還
		List<OrderList> acceUseOrderABbranch = new ArrayList<OrderList>(orderABbranch);
		List<OrderList> acceUseOrderFromAnotherbranch = new ArrayList<OrderList>(orderFromAnotherbranch);
		// 開始比對訂單......
		for (OrderList loop : orderbranch) {
			// 如果新單開始時間大於等於已存在訂單開始時間 ＆ 小於訂單結束時間 & 訂單配件內容不為空 ＝跳過這次迴圈並且記錄重複的訂單配件
			if (parsePickupDate.getTime() >= loop.getPickupDate().getTime()
					&& parsePickupDate.getTime() < loop.getDropoffDate().getTime()) {
				if ((!loop.getAccessoriesAmount().isEmpty()) && (loop.getAccessoriesAmount().length() != 0)) {
					acceList.add(loop.getAccessoriesAmount());
				}
				continue;
			}
			// 如果新單結束時間大於已存在訂單開始時間 ＆ 小於等於訂單結束時間 & 訂單配件內容不為空＝跳過這次迴圈並且記錄重複的訂單配件
			if (parseDropoffDate.getTime() > loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() <= loop.getDropoffDate().getTime()) {
				if ((!loop.getAccessoriesAmount().isEmpty()) && (loop.getAccessoriesAmount().length() != 0)) {
					acceList.add(loop.getAccessoriesAmount());
				}
				continue;
			}
			// 如果新單開始時間小於等於已存在訂單開始時間 ＆ 新單結束時間大於等於訂單結束時間 & 訂單配件內容不為空＝記錄重複的訂單配件
			if (parsePickupDate.getTime() < loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() > loop.getDropoffDate().getTime()) {
				if ((!loop.getAccessoriesAmount().isEmpty()) && (loop.getAccessoriesAmount().length() != 0)) {
					acceList.add(loop.getAccessoriesAmount());
					continue;
				}
			}
			// 如果新單開始時間等於已存在訂單開始時間 ＆ 新單結束時間等於訂單結束時間 & 訂單配件內容不為空＝記錄重複的訂單配件
			if (parsePickupDate.getTime() == loop.getPickupDate().getTime()
					&& parseDropoffDate.getTime() == loop.getDropoffDate().getTime()) {
				if ((!loop.getAccessoriesAmount().isEmpty()) && (loop.getAccessoriesAmount().length() != 0)) {
					acceList.add(loop.getAccessoriesAmount());
				}
			}

		}
		// 開始計算重複的配件數量.......
		System.out.println("重複配件" + acceList.toString());
//如果沒有重複 就直接回傳庫存量
		if (!acceList.isEmpty()) {
			String[] acceListSplit = acceList.toString().replace("{", "").replace("}", "").replace("[", "")
					.replace("]", "").replace("\"", "").trim().split(",");
			List<String> list = new ArrayList<String>();
			Set<String> acceName = new HashSet<String>();
			for (String root : acceListSplit) {
				acceName.add(root.trim().split(":")[0]);
				list.add(root);

				System.out.println("==============" + root);
				System.out.println("==============" + root.trim().split(":")[0]);
				// System.out.println("=============="+root.trim().split(":")[1]);
			}
			HashMap<String, Integer> acceMapName = new HashMap<String, Integer>();
			if (!list.get(0).isEmpty()) {
				for (String acceNameroot : acceName) {
					for (String acceListSplitroot : list) {
						if (acceNameroot.trim().equals(acceListSplitroot.trim().split(":")[0])) {
							if (acceMapName.get(acceNameroot) == null) {
								acceMapName.put(acceNameroot, Integer.valueOf(acceListSplitroot.trim().split(":")[1]));
							} else {
								acceMapName.put(acceNameroot, acceMapName.get(acceNameroot).intValue()
										+ Integer.valueOf(acceListSplitroot.trim().split(":")[1]).intValue());
							}
						}
					}
				}
			}
			System.out.println(acceMapName.toString());
			// 取出該店配件庫存的數量
			List<AcceStock> showAllAcceFromShop = showAllAcceFromShop(customerquery.getPickupStore());

			List<AcceStock> afterCompareAcc = new ArrayList<>();
			for (AcceStock loop : showAllAcceFromShop) {
				System.out.println(loop.getAcceName() + loop.getAcceNum() + loop.getAcceePrice());
				for (String loopmap : acceMapName.keySet()) {
					if (loop.getAcceName().equals(loopmap)) {
						if (loop.getAcceNum() > acceMapName.get(loopmap)) {
							AcceStock loopAfterCompare = new AcceStock(loop.getAcceStockSerialNum(), loop.getAcceName(),
									loop.getBranchName(), loop.getAcceNum() - acceMapName.get(loopmap),
									loop.getAcceType(), loop.getAcceePrice());
							afterCompareAcc.add(loopAfterCompare);
						} else {
							AcceStock loopAfterCompare = new AcceStock(loop.getAcceStockSerialNum(), loop.getAcceName(),
									loop.getBranchName(), 0, loop.getAcceType(), loop.getAcceePrice());
							afterCompareAcc.add(loopAfterCompare);
						}
					}
				}
			}

			// 把剩餘沒有重複的物件塞回去afterCompareAcc 準備傳回前端
			Iterator<AcceStock> showItr = showAllAcceFromShop.iterator();
			while (showItr.hasNext()) {
				AcceStock word = showItr.next();
				for (AcceStock boot : afterCompareAcc) {
					if (word.getAcceName().equals(boot.getAcceName())) {
						showItr.remove();
					}
				}
			}
			for (AcceStock loop : showAllAcceFromShop) {
				AcceStock loopAfterCompare = new AcceStock(loop.getAcceStockSerialNum(), loop.getAcceName(),
						loop.getBranchName(), loop.getAcceNum(), loop.getAcceType(), loop.getAcceePrice());
				afterCompareAcc.add(loopAfterCompare);
			}

			for (AcceStock test : afterCompareAcc) {
				System.out.println("after" + test.getAcceName() + "   " + test.getAcceNum());
			}

			// 利用甲地乙還 & 乙地甲環訂單調整傳回前端的數量
			return afterCompareAccAndABshopAndAnother(afterCompareAcc, acceUseOrderABbranch,
					acceUseOrderFromAnotherbranch);
		} else {
			List<AcceStock> afterCompareAcc = new ArrayList<>();

			for (AcceStock loop : showAllAcceFromShop(customerquery.getPickupStore())) {
				AcceStock loopAfterCompare = new AcceStock(loop.getAcceStockSerialNum(), loop.getAcceName(),
						loop.getBranchName(), loop.getAcceNum(), loop.getAcceType(), loop.getAcceePrice());
				afterCompareAcc.add(loopAfterCompare);
			}
			return afterCompareAccAndABshopAndAnother(afterCompareAcc, acceUseOrderABbranch,
					acceUseOrderFromAnotherbranch);
		}
	}

	@Override
	public List<AcceStock> showAllAcceFromShop(String shopname) {
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<AcceStock> createQuery = buider.createQuery(AcceStock.class);
		Root<AcceStock> fromClass = createQuery.from(AcceStock.class);
		ParameterExpression<String> pickupStore = buider.parameter(String.class);
		createQuery.select(fromClass).where(buider.equal(fromClass.get("branchName").get("branchName"), pickupStore));
		Query<AcceStock> queryword = factory.getCurrentSession().createQuery(createQuery);
		queryword.setParameter(pickupStore, shopname);
		List<AcceStock> list = queryword.getResultList();

		return list;
	}

//訂單轉型
	@Override
	public OrderList convertToOrderList(OrderListToGson customorder) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		return new OrderList(customorder.getOrderSerialNum(), customorder.getPhone(), customorder.getBikeModel(),
				sdf.parse(customorder.getPickupDate()), sdf.parse(customorder.getDropoffDate()),
				customorder.getTotalDiscount(), customorder.getBikePrice(), customorder.getAccessoriesAmount(),
				customorder.getAccessoriesTotalPrice(), customorder.getOrderTotalPrice(),
				sdf.parse(customorder.getOrderTime()), customorder.getPickupStore(), customorder.getDropoffStore(),
				customorder.getDiscountName(), customorder.getOrderStatus(), customorder.isIs_member(),
				customorder.isPayOrNot());

	}

	// 新增訂單
	@Override
	public void addOrderToDatabase(OrderList convertOrder, String customorderOld, List<OrderList> orderbranch,
			BasicOrderBean customerquery, List<OrderList> orderABbranch, List<OrderList> orderFromAnotherbranch)
			throws ParseException {
		// 插入流水號...

		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Long> createQuery = buider.createQuery(Long.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 時間查詢條件
		createQuery.select(buider.count(fromClass)).where(buider.and(
				buider.lessThanOrEqualTo(fromClass.<Date>get("orderTime"), sdf.parse(customorderOld + " 23:59")),
				buider.greaterThanOrEqualTo(fromClass.<Date>get("orderTime"), sdf.parse(customorderOld + " 00:00"))));
		Long count = factory.getCurrentSession().createQuery(createQuery).getSingleResult();
		System.out.println("當日重訂單數量========" + count);
		String serialString = customorderOld.split("-")[0] + customorderOld.split("-")[1] + customorderOld.split("-")[2]
				+ String.format("%04d", count + 1);
		System.out.println("封裝後=======" + serialString);

		// 系統自動分派車牌..........
		// 查詢重複訂單內等於該車的車名
		Iterator<OrderList> orderbranchIter = orderbranch.iterator();
		for (OrderList roots : orderbranch) {
			System.out.println("篩選======" + roots.getBikeModel());
		}
		while (orderbranchIter.hasNext()) {
			OrderList ster = orderbranchIter.next();
			if (!ster.getBikeModel().equals(convertOrder.getBikeModel())) {
				orderbranchIter.remove();
			}
		}
		for (OrderList roots : orderbranch) {
			System.out.println("篩選後======" + roots.getBikeModel());
		}
		// 把甲地乙還訂單不屬於最後訂單的車型移除
		Iterator<OrderList> orderABbranchIter = orderABbranch.iterator();
		while (orderABbranchIter.hasNext()) {
			OrderList checkuse = orderABbranchIter.next();
			if (!checkuse.getBikeModel().equals(convertOrder.getBikeModel())) {
				orderABbranchIter.remove();
			}
		}
		// 把乙地甲環不屬於最後訂單的車型移除
		System.out.println(
				"把乙地甲環不屬於最後訂單的車型移除" + orderFromAnotherbranch.toString() + "===" + orderFromAnotherbranch.size());
		Iterator<OrderList> orderFromAnotherbranchIter = orderFromAnotherbranch.iterator();
		while (orderFromAnotherbranchIter.hasNext()) {
			OrderList check = orderFromAnotherbranchIter.next();
			if (!check.getBikeModel().equals(convertOrder.getBikeModel())) {
				System.out.println("ITER測試" + check.getBikeModel() + "========" + convertOrder.getBikeModel());
				orderFromAnotherbranchIter.remove();
			}
		}
		CriteriaQuery<EveryBikeInfo> createQueryEbike = buider.createQuery(EveryBikeInfo.class);
		Root<EveryBikeInfo> fromEbikeClass = createQueryEbike.from(EveryBikeInfo.class);

		// where Table bikemode = 訂單的model
		// 封裝主查詢條件
		List<Predicate> predicatesList = new ArrayList<Predicate>();
		predicatesList.add(buider.equal(fromEbikeClass.get("bikeDetail").get("idClassBikeDetail").get("bikeModel"),
				convertOrder.getBikeModel()));
		predicatesList
				.add(buider.equal(fromEbikeClass.get("branchName").get("branchName"), customerquery.getPickupStore()));
		// 店內車牌不能等於重複訂單的車牌
		for (OrderList roots : orderbranch) {
			predicatesList.add(
					buider.notEqual(fromEbikeClass.get("licensePlate"), roots.getLicensePlate().getLicensePlate()));
		}
		// 店內車牌不能等於甲地乙還車牌
		for (OrderList root : orderABbranch) {
			predicatesList
					.add(buider.notEqual(fromEbikeClass.get("licensePlate"), root.getLicensePlate().getLicensePlate()));
		}

		// 封裝乙地甲環查詢條件
		List<Predicate> predicatesFromAnotherList = new ArrayList<Predicate>();
		for (OrderList root : orderFromAnotherbranch) {
			predicatesFromAnotherList
					.add(buider.equal(fromEbikeClass.get("licensePlate"), root.getLicensePlate().getLicensePlate()));

		}

		createQueryEbike.orderBy(buider.asc(fromEbikeClass.get("totalMileage")));

		createQueryEbike.select(fromEbikeClass)
				.where(buider.or(buider.and(predicatesList.toArray(new Predicate[predicatesList.size()])),
						buider.or(predicatesFromAnotherList.toArray(new Predicate[predicatesFromAnotherList.size()]))));
		List<EveryBikeInfo> OrderlistPlate = factory.getCurrentSession().createQuery(createQueryEbike).setFirstResult(0)
				.setMaxResults(1).getResultList();
		for (EveryBikeInfo root : OrderlistPlate) {
			System.out.println("=====" + root.getLicensePlate() + "======" + root.getTotalMileage());
		}

		convertOrder.setOrderSerialNum(serialString);
		convertOrder.setLicensePlate(OrderlistPlate.get(0));

		factory.getCurrentSession().persist(convertOrder);

	}

	@Override
	public List<OrderList> checkAllOrderFromABShop(List<OrderList> orderbranch, String pickupdate)
			throws ParseException {
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
					|| (pickupdateTran.getTime() < check.getDropoffDate().getTime())) {
				orderbranchIter.remove();
			}

		}
		for (OrderList loop : listForAbShop) {
			System.out.println("=====甲地乙還" + loop.getOrderSerialNum());
		}
		return listForAbShop;
	}

	@Override
	public List<OrderList> showAllOrderFromAnotherShop(String shopname, String pickupdate) throws ParseException {
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		ParameterExpression<String> pickupstore = buider.parameter(String.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// 乙地甲環訂單篩選 1.環車地點為甲地 2.取車地點不等於還車地點 3.環車時間小於訂單開始時間 4.訂單為預定或者進行中
		createQuery.select(fromClass)
				.where(buider.and(buider.equal(fromClass.get("dropoffStore"), pickupstore),
						buider.notEqual(fromClass.get("pickupStore"), fromClass.get("dropoffStore")),
						buider.lessThanOrEqualTo(fromClass.<Date>get("dropoffDate"), sdf.parse(pickupdate)),
						buider.or(buider.equal(fromClass.get("orderStatus"), "未來訂單"),
								buider.equal(fromClass.get("orderStatus"), "進行中訂單"),
								buider.equal(fromClass.get("orderStatus"), "未來調度"),
								buider.equal(fromClass.get("orderStatus"), "進行中調度"))));

		Query<OrderList> queryword = factory.getCurrentSession().createQuery(createQuery);
		queryword.setParameter(pickupstore, shopname);
		List<OrderList> list = queryword.getResultList();

		return list;
	}

	@Override
	public List<AcceStock> afterCompareAccAndABshopAndAnother(List<AcceStock> afterCompareAcc,
			List<OrderList> acceUseOrderABbranch, List<OrderList> acceUseOrderFromAnotherbranch) {
		// 首先比對甲地乙還訂單
		for (AcceStock loopt : afterCompareAcc) {
			System.out.println("==庫存==" + loopt.getAcceName() + "==" + loopt.getAcceNum());
		}
		System.out.println("==AB==" + acceUseOrderABbranch.toString());
		System.out.println("==Athoer==" + acceUseOrderFromAnotherbranch.toString());
//	如果甲地乙還訂單有配件 那最終回傳庫存要扣掉
		for (OrderList abloop : acceUseOrderABbranch) {
			if (!abloop.getAccessoriesAmount().isEmpty()) {
				String[] aftersplit = abloop.getAccessoriesAmount().replace("{", "").replace("}", "").replace("[", "")
						.replace("]", "").replace("\"", "").trim().split(",");
				List<String> list = Arrays.asList(aftersplit);
				for (String listloop : list) {
					int count = 0;
					for (AcceStock loop : afterCompareAcc) {
						if (listloop.split(":")[0].equals(loop.getAcceName())) {
							if ((afterCompareAcc.get(count).getAcceNum()
									- Integer.parseInt(listloop.split(":")[1])) <= 0) {
								afterCompareAcc.get(count).setAcceNum(0);
							} else {
								afterCompareAcc.get(count).setAcceNum(afterCompareAcc.get(count).getAcceNum()
										- Integer.parseInt(listloop.split(":")[1]));
							}
						}
						count++;
					}
				}
			}
		}
		// 再來比對乙地甲還訂單(在甲地的東西直接加上去就好但是要移除掉給後面的人處理)
		Iterator<OrderList> acceUseOrderFromAnotherbranchiter = acceUseOrderFromAnotherbranch.iterator();

		while (acceUseOrderFromAnotherbranchiter.hasNext()) {
			OrderList abloop = acceUseOrderFromAnotherbranchiter.next();
			if (!abloop.getAccessoriesAmount().isEmpty()) {
				String[] aftersplit = abloop.getAccessoriesAmount().replace("{", "").replace("}", "").replace("[", "")
						.replace("]", "").replace("\"", "").trim().split(",");
				List<String> list = Arrays.asList(aftersplit);
				for (String listloop : list) {
					int count = 0;
					for (AcceStock loop : afterCompareAcc) {
						if (listloop.split(":")[0].equals(loop.getAcceName())) {
							afterCompareAcc.get(count).setAcceNum(
									afterCompareAcc.get(count).getAcceNum() + Integer.parseInt(listloop.split(":")[1]));
							acceUseOrderFromAnotherbranchiter.remove();
						}
						count++;
					}
				}
			} else {
				acceUseOrderFromAnotherbranchiter.remove();
			}
		}

		// 處理不在甲地的訂單（不在這邊的配件會沒有相關資訊 所以要抓出來）
		// List<String> checkAnothershopList = new ArrayList<String>();
		// HashSet<String> checkAnothershopSet = new HashSet<String>();
		if (acceUseOrderFromAnotherbranch.size() != 0) {

			HashMap<String, Integer> checkAnothershopMap = new HashMap<String, Integer>();
			// 先整理一下庫存ＫＶ

			for (OrderList loop : acceUseOrderFromAnotherbranch) {
				String[] aftersplit = loop.getAccessoriesAmount().replace("{", "").replace("}", "").replace("[", "")
						.replace("]", "").replace("\"", "").trim().split(",");
				List<String> innerlist = Arrays.asList(aftersplit);
				for (String loop2 : innerlist) {
					if (checkAnothershopMap.get(loop2.split(":")[0]) == null) {
						checkAnothershopMap.put(loop2.split(":")[0], Integer.valueOf(loop2.split(":")[1]));
					} else {
						checkAnothershopMap.put(loop2.split(":")[0],
								checkAnothershopMap.get(loop2.split(":")[0]) + Integer.valueOf(loop2.split(":")[1]));
					}
				}

			}
			Iterator<Entry<String, Integer>> checkmap = checkAnothershopMap.entrySet().iterator();

			CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
			CriteriaQuery<AcceStock> createQuery = buider.createQuery(AcceStock.class);
			Root<AcceStock> fromClass = createQuery.from(AcceStock.class);
			List<Predicate> predicatesList = new ArrayList<Predicate>();
			while (checkmap.hasNext()) {
				predicatesList.add(buider.equal(fromClass.get("acceName"), checkmap.next().getKey()));
			}
			createQuery.select(fromClass)
					.where(buider.and(
							buider.equal(fromClass.get("branchName").get("branchName"),
									acceUseOrderFromAnotherbranch.get(0).getPickupStore()),
							buider.or(predicatesList.toArray(new Predicate[predicatesList.size()]))));
			Query<AcceStock> queryword = factory.getCurrentSession().createQuery(createQuery);
			List<AcceStock> list = queryword.getResultList();
			Iterator<Entry<String, Integer>> checkmapagain = checkAnothershopMap.entrySet().iterator();

			while (checkmapagain.hasNext()) {
				Entry<String, Integer> checkmapagainEnt = checkmapagain.next();
				for (AcceStock loop : list) {
					if (checkmapagainEnt.getKey().equals(loop.getAcceName())) {
						AcceStock loopAfterCompare = new AcceStock(loop.getAcceStockSerialNum(), loop.getAcceName(),
								loop.getBranchName(), checkmapagainEnt.getValue(), loop.getAcceType(),
								loop.getAcceePrice());
						afterCompareAcc.add(loopAfterCompare);
					}
				}
			}
		}

		return afterCompareAcc;
	}

	@Override
	public List<OrderList> showMemberAndNonMemberDetail(String showMemberAndNonMemberphnoe) {

		System.out.println("showMemberAndNonMemberDetail" + showMemberAndNonMemberphnoe);

		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		createQuery.select(fromClass).where(buider.equal(fromClass.get("phone"), showMemberAndNonMemberphnoe));
		List<OrderList> branchlist = factory.getCurrentSession().createQuery(createQuery).getResultList();

		for (OrderList loop : branchlist) {

			System.out.println("loop" + loop.toString());

		}

		return branchlist;
	}

	@Override
	public List<OrderListToGson> convertOrderListToGson(List<OrderList> customorder) throws ParseException {

		List<OrderListToGson> orderListToGson = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (OrderList loop : customorder) {

			orderListToGson.add(new OrderListToGson(loop.getOrderSerialNum(), loop.getPhone(), loop.getBikeModel(),
					df.format(loop.getPickupDate()), df.format(loop.getDropoffDate()), loop.getTotalDiscount(),
					loop.getBikePrice(), loop.getAccessoriesAmount(), loop.getAccessoriesTotalPrice(),
					loop.getOrderTotalPrice(), df.format(loop.getOrderTime()), loop.getPickupStore(),
					loop.getDropoffStore(), loop.getDiscountName(), loop.getOrderStatus(), loop.isIs_member(),
					loop.isPayOrNot()));

		}

		return orderListToGson;
	}

	@Override
	public List<OrderList> showManagerSearchDetail(ManagerOrderCondition managerOrderCondition) {

		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<OrderList> createQuery = buider.createQuery(OrderList.class);
		Root<OrderList> fromClass = createQuery.from(OrderList.class);
		// 封裝主查詢條件
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		if (managerOrderCondition.getDropoffstore() != "") {
			predicatesList.add( buider.equal(fromClass.get("dropoffStore"), managerOrderCondition.getDropoffstore())); 
		}
		if (managerOrderCondition.getOrderstatus() != "") {
			predicatesList.add( buider.equal(fromClass.get("orderStatus"), managerOrderCondition.getOrderstatus()));
		}
		if (managerOrderCondition.getPickupstore() != "") {
			predicatesList.add( buider.equal(fromClass.get("pickupStore"), managerOrderCondition.getPickupstore()));
		}

		createQuery.select(fromClass).where(buider.and(predicatesList.toArray(new Predicate[predicatesList.size()])));
		List<OrderList> branchlist = factory.getCurrentSession().createQuery(createQuery).getResultList();

		
		
		
		return branchlist;
	}

	@Override
	public List<OrderListToGson> convertOrderListToGsonWithPlate(List<OrderList> customorder) throws ParseException {
		List<OrderListToGson> orderListToGson = new ArrayList<>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (OrderList loop : customorder) {

			orderListToGson.add(new OrderListToGson(loop.getOrderSerialNum(), loop.getPhone(), loop.getBikeModel(),loop.getLicensePlate().getLicensePlate() , 
					df.format(loop.getPickupDate()), df.format(loop.getDropoffDate()), loop.getTotalDiscount(),
					loop.getBikePrice(), loop.getAccessoriesAmount(), loop.getAccessoriesTotalPrice(),
					loop.getOrderTotalPrice(), df.format(loop.getOrderTime()), loop.getPickupStore(),
					loop.getDropoffStore(), loop.getDiscountName(), loop.getOrderStatus(), loop.isIs_member(),
					loop.isPayOrNot()));
			
	

		}

		return orderListToGson;
	}

	@Override
	public void showManagerChangeOrderStatus(ShowManagerChangeOrderStatus showManagerChangeOrderStatus) {
           OrderList oldorder = factory.getCurrentSession().get(OrderList.class, showManagerChangeOrderStatus.getOrdersernum()); 
           oldorder.setOrderStatus(showManagerChangeOrderStatus.getOrderstatus());
		factory.getCurrentSession().update(oldorder);	
		
		   
		
	}

	@Override
	public void showManagerFinishedOrder(FinOrderBean finOrderBean) {
		// TODO Auto-generated method stub
		  OrderList oldorder = factory.getCurrentSession().get(OrderList.class, finOrderBean.getOrdersernum()); 
		//如果訂單是甲店乙還
		  if( !oldorder.getPickupStore().equals(oldorder.getDropoffStore())) {
			   EveryBikeInfo changebike = factory.getCurrentSession().get(EveryBikeInfo.class, oldorder.getLicensePlate().getLicensePlate());
	
			   //取出分店物件
			     CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
				CriteriaQuery<BranchDetail> createQuery = buider.createQuery(BranchDetail.class);
				Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
				createQuery.select(fromClass).where(buider.equal(fromClass.get("branchName"), oldorder.getDropoffStore()));
				Query<BranchDetail> queryword = factory.getCurrentSession().createQuery(createQuery.select(fromClass));
				BranchDetail branch = queryword.getSingleResult();
			   changebike.setBranchName(branch);
			   
			   factory.getCurrentSession().update(changebike);
		  }
		  
		  
		  
		   
		   oldorder.setOrderStatus(finOrderBean.getOrderstatus());
		factory.getCurrentSession().update(oldorder);	
		
		
		
	}

	@Override
	public void showManagerFinishedDiapatcher(ShowManagerChangeOrderStatus showManagerChangeOrderStatus) {
		   OrderList oldorder = factory.getCurrentSession().get(OrderList.class, showManagerChangeOrderStatus.getOrdersernum()); 
      //如果訂單是甲店乙還
		  if( !oldorder.getPickupStore().equals(oldorder.getDropoffStore())) {
			   EveryBikeInfo changebike = factory.getCurrentSession().get(EveryBikeInfo.class, oldorder.getLicensePlate().getLicensePlate());
	
			   //取出分店物件
			     CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
				CriteriaQuery<BranchDetail> createQuery = buider.createQuery(BranchDetail.class);
				Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
				createQuery.select(fromClass).where(buider.equal(fromClass.get("branchName"), oldorder.getDropoffStore()));
				Query<BranchDetail> queryword = factory.getCurrentSession().createQuery(createQuery.select(fromClass));
				BranchDetail branch = queryword.getSingleResult();
			   changebike.setBranchName(branch);
			   
			   factory.getCurrentSession().update(changebike);
		  }
		   
		   oldorder.setOrderStatus(showManagerChangeOrderStatus.getOrderstatus());
		factory.getCurrentSession().update(oldorder);	
		
	}

}
