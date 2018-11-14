package dispatchercontroller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cleanbean.BasicOrderBean;
import cleanbean.BikeDetailToGsonHaoUse;
import dispatcherservice.DispatcherIFaceService;
import orderservice.OrderIFaceService;
import projectbean.BikeDetail;
import projectbean.OrderList;

@RestController
public class DispatcherController {

	@Autowired
	DispatcherIFaceService dispatcherIFaceService;
	@Autowired
	OrderIFaceService tesOrderIFaceService;
	@Autowired
	Gson gson;
	
	
	// 管理者選擇時間後傳入
		@PostMapping(value = "/managerDispatcher", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String managerDispatcher(@RequestBody BasicOrderBean customerquery)
				throws IOException, ParseException {
			System.out.println("網頁傳入=" + customerquery);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		customerquery.setDropoffDate(sdf.format(  (sdf.parse(customerquery.getDropoffDate()).getTime()+(60*60*1000))));
		customerquery.setPickupDate(sdf.format(  (sdf.parse(customerquery.getPickupDate()).getTime()-(60*60*1000))));
		System.out.println("更改後=" + customerquery);
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
			
		
		}}
		
		
		
		
		
		
	


