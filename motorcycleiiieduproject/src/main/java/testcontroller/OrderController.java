package testcontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import projectbean.BikeDetail;
import projectbean.OrderList;
import testbean.BikeDetailToGson;
import testbean.OrderIFaceService;

@Controller
public class OrderController {

	@Autowired
	OrderIFaceService tesOrderIFaceService ;
	
	@Autowired
	Gson gson;
	
	
	//使用者選擇時間後傳入
	@PostMapping(value = "/showAllOrderFromShop", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showAllOrderFromShop(@RequestBody BasicOrderBean customerquery) throws IOException, ParseException {
		System.out.println("網頁傳入="+customerquery);
		try {
			//此方法接收使用者入的店名 將該店所有訂單取出來
			 List<OrderList> orderbranch = tesOrderIFaceService.showAllOrderFromShop(customerquery.getPickupStore());
			 //此方法比較所有已存在訂單並且回傳重複的車名
			 List<String> compareOrderlist = tesOrderIFaceService.compareOrderlist(customerquery, orderbranch);
			//此方法比對店內庫存並回傳可租車輛的資訊
			 List<BikeDetail> finalBikeDetail = tesOrderIFaceService.returnMotorDetailAndShowView(compareOrderlist, customerquery.getPickupStore());
			 //hibernate複合主鍵物件轉為普通java
			 List<BikeDetailToGson> forGsonConvert = tesOrderIFaceService.forGsonConvert(finalBikeDetail);
			 return gson.toJson(forGsonConvert);
			// gson.toJson(finalBikeDetail,BikeDetail.class)
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}

	}
	
	//網頁店名動態顯示
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
	
	
	
	
	
}
