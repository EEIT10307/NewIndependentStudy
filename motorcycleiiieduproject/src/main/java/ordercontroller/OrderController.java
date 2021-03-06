package ordercontroller;

import java.io.IOException;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import cleanbean.FinOrderBean;
import cleanbean.ManagerOrderCondition;
import cleanbean.OrderListToGson;
import cleanbean.ShowManagerChangeOrderStatus;
import orderservice.OrderIFaceService;
import projectbean.AcceStock;
import projectbean.BikeDetail;
import projectbean.OrderList;

@RestController
public class OrderController {

	@Autowired
	OrderIFaceService tesOrderIFaceService;

	@Autowired
	Gson gson;

	// 使用者選擇時間後傳入
	@PostMapping(value = "/showAllOrderFromShop", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showAllOrderFromShop(@RequestBody BasicOrderBean customerquery)
			throws IOException, ParseException {
		System.out.println("網頁傳入=" + customerquery);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
		if((sdf.parse(customerquery.getDropoffDate()).getTime() - 
				sdf.parse(customerquery.getPickupDate()).getTime()) <= 0 ) {
	
			return gson.toJson("error") ; 
		}else {
			
		try {
			// 此方法接收使用者入的店名 將該店所有訂單取出來
			List<OrderList> orderbranch = tesOrderIFaceService.showAllOrderFromShop(customerquery.getPickupStore());
			for(OrderList loop:orderbranch) {
				System.out.println("此存在訂單之時間"+loop.getOrderSerialNum());
			}
			// 此方法將甲地乙還的訂單選出來
			  List<OrderList> orderABbranch = tesOrderIFaceService.checkAllOrderFromABShop(orderbranch , customerquery.getPickupDate());
			  System.out.println("此方法將甲地乙還的訂單選出來"+orderABbranch.toString());
			  // 此方法將乙地甲環的訂單選出來
			  List<OrderList> orderFromAnotherbranch = tesOrderIFaceService.showAllOrderFromAnotherShop(customerquery.getPickupStore() , customerquery.getPickupDate()) ; 
			  System.out.println("此方法將乙地甲環的訂單選出來"+orderFromAnotherbranch.toString());
			  // 此方法比較所有已存在訂單並且回傳重複的訂單
			List<OrderList> compareOrderlist = tesOrderIFaceService.compareOrderlist(customerquery, orderbranch);
			List<String> comPareOrderListMotorModel = new ArrayList<String>();
			for(    OrderList loop :compareOrderlist) {
				comPareOrderListMotorModel.add(loop.getBikeModel());
			}
			// 此方法比對店內庫存並回傳可租車輛的資訊
			List<BikeDetail> finalBikeDetail = tesOrderIFaceService.returnMotorDetailAndShowView(comPareOrderListMotorModel,
					customerquery.getPickupStore() ,orderABbranch  , orderFromAnotherbranch  );
			
			
			// hibernate複合主鍵物件轉為普通java
			List<BikeDetailToGsonHaoUse> forGsonConvert = tesOrderIFaceService.forGsonConvert(finalBikeDetail);
			return gson.toJson(forGsonConvert);
			// gson.toJson(finalBikeDetail,BikeDetail.class)
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
		}
	}	

	// 網頁店名動態顯示
	@GetMapping(value = "/showAllBranch", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showAllBranch() throws IOException, ParseException {
		System.out.println("showAllBranch");
		try {
			String branchjson = gson.toJson(tesOrderIFaceService.showAllBranch());
			return branchjson;
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{\"fail\":fail}");
		}
	}
	
	
	
	

	// 取得折扣資訊
	@GetMapping(value = "/getDiscount", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String getBikrDetail() throws IOException, ParseException {
		System.out.println("getDiscount");
		try {
			return gson.toJson(tesOrderIFaceService.getDiscount());
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{\"fail\":fail}");
		}
	}

	// 取得配件庫存資訊
	@PostMapping(value = "/compareAcceStock", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String compareAcceStock(@RequestBody BasicOrderBean customerquery)
			throws IOException, ParseException {
		System.out.println("compareAcceStock");
		try {
			// 此方法接收使用者入的店名 將該店所有訂單取出來
			List<OrderList> orderbranch = tesOrderIFaceService.showAllOrderFromShop(customerquery.getPickupStore());
			// 此方法將甲地乙還的訂單選出來
			List<OrderList> orderABbranch = tesOrderIFaceService.checkAllOrderFromABShop(orderbranch , customerquery.getPickupDate());
			// 此方法將乙地甲環的訂單選出來
			List<OrderList> orderFromAnotherbranch = tesOrderIFaceService.showAllOrderFromAnotherShop(customerquery.getPickupStore() , customerquery.getPickupDate()) ; 
			System.out.println("===乙地甲環=="+orderFromAnotherbranch.toString());
			// 比對訂單並回傳可租庫存
			List<AcceStock> compareAcceStock = tesOrderIFaceService.compareAcceStock(customerquery ,orderABbranch , orderFromAnotherbranch);
			return gson.toJson(compareAcceStock);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{\"fail\":fail}");
		}
	}

	// 取得確認的訂單
	@PostMapping(value = "/getlastcheckorderlist", produces = "application/JSON; charset = UTF-8")
	public  ResponseEntity<String> getlastcheckorderlist(@RequestBody OrderListToGson customorder)
			throws IOException, ParseException {
		System.out.println("getlastcheckorderlist");
		//錯誤訊息庫
		    Map<String, String> errormessage = new HashMap<String,String>();
		// 最終比對車子訂單
		boolean motorFinallCheck = false;
		// 最終比對配件訂單
		boolean motorAcceFinallCheck = false;
		/// 訂單取出日期比對
		BasicOrderBean customerquery = new BasicOrderBean(customorder.getPickupDate(), customorder.getDropoffDate(),
				customorder.getPickupStore(), customorder.getDropoffStore());
		// 此方法接收使用者入的店名 將該店所有訂單取出來
		List<OrderList> orderbranch = tesOrderIFaceService.showAllOrderFromShop(customerquery.getPickupStore());
		System.out.println("取出所有訂單二次"+orderbranch.toString());
		// 此方法將甲地乙還的訂單選出來
		  List<OrderList> orderABbranch = tesOrderIFaceService.checkAllOrderFromABShop(orderbranch , customerquery.getPickupDate());
		  // 此方法將乙地甲環的訂單選出來
		  List<OrderList> orderFromAnotherbranch = tesOrderIFaceService.showAllOrderFromAnotherShop(customerquery.getPickupStore() , customerquery.getDropoffDate()) ;
		// 此方法比較所有已存在訂單並且回傳重複的車名
		  System.out.println("此方法比較所有已存在訂單並且回傳重複的車名進入之前" + orderbranch.toString());
		List<OrderList> compareOrderlist = tesOrderIFaceService.compareOrderlist(customerquery, orderbranch);
		List<String> comPareOrderListMotorModel = new ArrayList<String>();
		for(    OrderList loop :compareOrderlist) {
			comPareOrderListMotorModel.add(loop.getBikeModel());
			System.out.println("傳重複的車名"+loop.getBikeModel());
		}		
		
		// 此方法比對店內庫存並回傳可租車輛的資訊
		List<BikeDetail> finalBikeDetail = tesOrderIFaceService.returnMotorDetailAndShowView(comPareOrderListMotorModel,
				customerquery.getPickupStore() , orderABbranch , orderFromAnotherbranch);
		for(  BikeDetail root:finalBikeDetail) {
			System.out.println("二次判斷"+root.getIdClassBikeDetail().getBikeModel());
		}
		for (BikeDetail root : finalBikeDetail) {
			if (root.getIdClassBikeDetail().getBikeModel().equals(customorder.getBikeModel())) {
				motorFinallCheck = true;
			}
		}
//		//如果是二次調度
//		if(customorder.getPhone().equals("2222222222")) {
//			motorFinallCheck = true;
//		}
		
		
		System.out.println("hi" + motorFinallCheck);
		// 訂單取出配件資訊....
		String acceAmount = customorder.getAccessoriesAmount();
		System.out.println("======顧客下訂的配件======="+acceAmount);
		String[] acceListSplit = acceAmount.toString().replace("{", "").replace("}", "").replace("[", "").
				replace("]", "").replace("\"", "").trim().split(",");
		HashMap<String, Integer> acceMap = new HashMap<String, Integer>();
		System.out.println(acceListSplit.toString()+"==="+acceListSplit.length);
		if(acceListSplit.length >= 1 &&  !acceListSplit[0].isEmpty()) {
			for (String root : acceListSplit) {
				acceMap.put(root.split(":")[0].replaceAll("\"", ""),
						Integer.valueOf(root.split(":")[1].replaceAll("\"", "")));
			}
		}
		System.out.println("=======acceMap=="+acceMap.toString());
		Iterator<Entry<String, Integer>> acceMapIter = acceMap.entrySet().iterator();
		// 比對訂單庫存
		List<AcceStock> compareAcceStock = tesOrderIFaceService.compareAcceStock(customerquery , orderABbranch , orderFromAnotherbranch);
		int accecheck = 0 ; 
		while (acceMapIter.hasNext()) {
			Entry<String, Integer> mapIter = acceMapIter.next();
			System.out.println("+====="+compareAcceStock.toString());
			for (AcceStock root : compareAcceStock) {
				System.out.println("+==客戶訂單比對==="+mapIter.getKey()+"===="+mapIter.getValue());	
				System.out.println("+==比對庫存傳回的結果==="+root.getAcceName()+"======="+root.getAcceNum());	
				if (mapIter.getKey().equals(root.getAcceName())) {
					System.out.println("+==mapIter.getValue()=="+mapIter.getValue()+"====root.getAcceNum()="+root.getAcceNum());
					if (mapIter.getValue() <= root.getAcceNum()) {
						System.out.println("+==mapIter.getValue()=="+mapIter.getValue()+"====root.getAcceNum()="+root.getAcceNum());
						motorAcceFinallCheck = true;
					} else {
						accecheck++;
						errormessage.put(root.getAcceName(), "配件"+root.getAcceName()+"該時段已經沒有庫存囉");
					}
				}
			}
		}
		if(acceMap.size() == 0 ) {
			motorAcceFinallCheck = true;
		}
  if(accecheck>0) {
	  motorAcceFinallCheck = false ; 
  }
  if(!motorFinallCheck) {
	  errormessage.put("motorfull", customorder.getBikeModel()+"已經被人預定囉 , 請搜尋其他時段或者選擇其他車型");
  }
  
		System.out.println("acce result   ============="+motorAcceFinallCheck);
		try {
			//二次判斷是否有人中途攔截租走
			if(motorFinallCheck && motorAcceFinallCheck) {	
				tesOrderIFaceService.addOrderToDatabase(tesOrderIFaceService.convertToOrderList(customorder) ,customorder.getOrderTime().split(" ")[0] , 
						compareOrderlist, customerquery ,   orderABbranch  , orderFromAnotherbranch);
				    
		  
				
				System.out.println("成功");
				return  ResponseEntity.accepted().body(gson.toJson("ok"));
			}else {
				System.out.println("NG");
				return  ResponseEntity.badRequest().body(gson.toJson(errormessage));
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("NG2");
			return ResponseEntity.badRequest().body(gson.toJson("NG"));
		}
	
		
	}

	
	
	
	// 會員查詢訂單
		@PostMapping(value = "/showMemberAndNonMemberDetail", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String showMemberAndNonMemberDetail(@RequestBody String phone) throws IOException, ParseException {
			System.out.println("showMemberAndNonMemberDetail");
			System.out.println(phone.replace("\"", "").replace("{", "").replace("}", "").split(":")[1]);
				
			try {
				List<OrderList> customorder = tesOrderIFaceService.showMemberAndNonMemberDetail(phone.replace("\"", "").replace("{", "").replace("}", "").split(":")[1]);
				
	  List<OrderListToGson> gsonorderlist = tesOrderIFaceService.convertOrderListToGson(customorder) ; 
				
				
				return gson.toJson(gsonorderlist);
			} catch (Exception e) {
				e.printStackTrace();
				return new String("{\"fail\":fail}");
			}
		}
	
		// 管理員查詢訂單
		@PostMapping(value = "/showManagerSearchDetail", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String showManagerSearchDetail(@RequestBody ManagerOrderCondition managerOrderCondition) throws IOException, ParseException {
			try {
				   List<OrderList> condition = tesOrderIFaceService.showManagerSearchDetail(managerOrderCondition) ; 
				   List<OrderListToGson> gsonorderlist = tesOrderIFaceService.convertOrderListToGsonWithPlate(condition) ; 
				   
				   return gson.toJson(gsonorderlist);
			} catch (Exception e) {
				e.printStackTrace();
				return new String("{\"fail\":fail}");
			}
		}
		
		
		// 管理員接收訂單
		@PostMapping(value = "/showManagerChangeOrderStatus", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String showManagerChangeOrderStatus(@RequestBody ShowManagerChangeOrderStatus showManagerChangeOrderStatus) throws IOException, ParseException {
			try {
				  System.out.println(showManagerChangeOrderStatus.toString());
				  tesOrderIFaceService.showManagerChangeOrderStatus(showManagerChangeOrderStatus);
				   return  null;
			} catch (Exception e) {
				e.printStackTrace();
				return new String("{\"fail\":fail}");
			}
		}
		
		// 管理員完成訂單
		@PostMapping(value = "/showManagerFinishedOrder", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String showManagerFinishedOrder(@RequestBody FinOrderBean finOrderBean) throws IOException, ParseException {
			try {
				  System.out.println(finOrderBean.toString());
			  tesOrderIFaceService.showManagerFinishedOrder(finOrderBean);
				   return  null;
			} catch (Exception e) {
				e.printStackTrace();
				return new String("{\"fail\":fail}");
			}
		}
		
		
		// 管理員完成調度
		@PostMapping(value = "/showManagerFinishedDiapatcher", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String showManagerFinishedDiapatcher(@RequestBody ShowManagerChangeOrderStatus showManagerChangeOrderStatus) throws IOException, ParseException {
			try {
				  System.out.println(showManagerChangeOrderStatus.toString());
			  tesOrderIFaceService.showManagerFinishedDiapatcher(showManagerChangeOrderStatus);
				   return  null;
			} catch (Exception e) {
				e.printStackTrace();
				return new String("{\"fail\":fail}");
			}
		}
	
		
		// 會員訂單傳入特別方法
		@PostMapping(value = "/getlastcheckorderlistSpecialMethod", produces = "text/html; charset = UTF-8")
		public @ResponseBody String getlastcheckorderlistSpecialMethod( HttpServletRequest request ) throws IOException, ParseException {
			
				  System.out.println("FxxU");
				  
				     OrderListToGson customorder = gson.fromJson(request.getReader(), OrderListToGson.class);
				   //錯誤訊息庫
					    Map<String, String> errormessage = new HashMap<String,String>();
					// 最終比對車子訂單
					boolean motorFinallCheck = false;
					// 最終比對配件訂單
					boolean motorAcceFinallCheck = false;
					/// 訂單取出日期比對
					BasicOrderBean customerquery = new BasicOrderBean(customorder.getPickupDate(), customorder.getDropoffDate(),
							customorder.getPickupStore(), customorder.getDropoffStore());
					// 此方法接收使用者入的店名 將該店所有訂單取出來
					List<OrderList> orderbranch = tesOrderIFaceService.showAllOrderFromShop(customerquery.getPickupStore());
					System.out.println("取出所有訂單二次"+orderbranch.toString());
					// 此方法將甲地乙還的訂單選出來
					  List<OrderList> orderABbranch = tesOrderIFaceService.checkAllOrderFromABShop(orderbranch , customerquery.getPickupDate());
					  // 此方法將乙地甲環的訂單選出來
					  List<OrderList> orderFromAnotherbranch = tesOrderIFaceService.showAllOrderFromAnotherShop(customerquery.getPickupStore() , customerquery.getDropoffDate()) ;
					// 此方法比較所有已存在訂單並且回傳重複的車名
					  System.out.println("此方法比較所有已存在訂單並且回傳重複的車名進入之前" + orderbranch.toString());
					List<OrderList> compareOrderlist = tesOrderIFaceService.compareOrderlist(customerquery, orderbranch);
					List<String> comPareOrderListMotorModel = new ArrayList<String>();
					for(    OrderList loop :compareOrderlist) {
						comPareOrderListMotorModel.add(loop.getBikeModel());
						System.out.println("傳重複的車名"+loop.getBikeModel());
					}		
					
					// 此方法比對店內庫存並回傳可租車輛的資訊
					List<BikeDetail> finalBikeDetail = tesOrderIFaceService.returnMotorDetailAndShowView(comPareOrderListMotorModel,
							customerquery.getPickupStore() , orderABbranch , orderFromAnotherbranch);
					for(  BikeDetail root:finalBikeDetail) {
						System.out.println("二次判斷"+root.getIdClassBikeDetail().getBikeModel());
					}
					for (BikeDetail root : finalBikeDetail) {
						if (root.getIdClassBikeDetail().getBikeModel().equals(customorder.getBikeModel())) {
							motorFinallCheck = true;
						}
					}
					System.out.println("hi" + motorFinallCheck);
					// 訂單取出配件資訊....
					String acceAmount = customorder.getAccessoriesAmount();
					System.out.println("======顧客下訂的配件======="+acceAmount);
					String[] acceListSplit = acceAmount.toString().replace("{", "").replace("}", "").replace("[", "").
							replace("]", "").replace("\"", "").trim().split(",");
					HashMap<String, Integer> acceMap = new HashMap<String, Integer>();
					System.out.println(acceListSplit.toString()+"==="+acceListSplit.length);
					if(acceListSplit.length >= 1 &&  !acceListSplit[0].isEmpty()) {
						for (String root : acceListSplit) {
							acceMap.put(root.split(":")[0].replaceAll("\"", ""),
									Integer.valueOf(root.split(":")[1].replaceAll("\"", "")));
						}
					}
					System.out.println("=======acceMap=="+acceMap.toString());
					Iterator<Entry<String, Integer>> acceMapIter = acceMap.entrySet().iterator();
					// 比對訂單庫存
					List<AcceStock> compareAcceStock = tesOrderIFaceService.compareAcceStock(customerquery , orderABbranch , orderFromAnotherbranch);
					int accecheck = 0 ; 
					while (acceMapIter.hasNext()) {
						Entry<String, Integer> mapIter = acceMapIter.next();
						System.out.println("+====="+compareAcceStock.toString());
						for (AcceStock root : compareAcceStock) {
							System.out.println("+==客戶訂單比對==="+mapIter.getKey()+"===="+mapIter.getValue());	
							System.out.println("+==比對庫存傳回的結果==="+root.getAcceName()+"======="+root.getAcceNum());	
							if (mapIter.getKey().equals(root.getAcceName())) {
								System.out.println("+==mapIter.getValue()=="+mapIter.getValue()+"====root.getAcceNum()="+root.getAcceNum());
								if (mapIter.getValue() <= root.getAcceNum()) {
									System.out.println("+==mapIter.getValue()=="+mapIter.getValue()+"====root.getAcceNum()="+root.getAcceNum());
									motorAcceFinallCheck = true;
								} else {
									accecheck++;
									errormessage.put(root.getAcceName(), "配件"+root.getAcceName()+"該時段已經沒有庫存囉");
								}
							}
						}
					}
					if(acceMap.size() == 0 ) {
						motorAcceFinallCheck = true;
					}
			  if(accecheck>0) {
				  motorAcceFinallCheck = false ; 
			  }
			  if(!motorFinallCheck) {
				  errormessage.put("motorfull", customorder.getBikeModel()+"已經被人預定囉 , 請搜尋其他時段或者選擇其他車型");
			  }
			  
					System.out.println("acce result   ============="+motorAcceFinallCheck);
					try {
						//二次判斷是否有人中途攔截租走
						if(motorFinallCheck && motorAcceFinallCheck) {	
							tesOrderIFaceService.addOrderToDatabase(tesOrderIFaceService.convertToOrderList(customorder) ,customorder.getOrderTime().split(" ")[0] , 
									compareOrderlist, customerquery ,   orderABbranch  , orderFromAnotherbranch);
							    
					  
							
							System.out.println("感謝您的預定 , 系統將會自動發送評價表至您的信箱 ");
							return  "感謝您的預定 , 系統將會自動發送評價表至您的信箱 ";
						}else {
							System.out.println("NG");
							return  "NG";
						}
							
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("NG2");
						return "NG2";
					}
			
			}

}
