package testcontroller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import testbean.robin.BikeDetailIFaceService;
import testbean.robin.BikeReviewIFaceService;
import testbean.robin.BranchDetailIFaceService;
import testbean.robin.EveryBikeInfoIFaceService;

@Controller
public class TestRobinController {
//羅冰負責的工作
	@Autowired
	BikeReviewIFaceService bikeReviewIFaceService;// 商品留言板
	@Autowired
	BranchDetailIFaceService branchDetailIFaceService;// 查詢分店
	@Autowired
	BikeDetailIFaceService bikeDetailIFaceService;
	@Autowired
	EveryBikeInfoIFaceService everyBikeInfoIFaceService;
	@Autowired
	Gson gson;

	@RequestMapping(value = "/insert", method = RequestMethod.POST) // 留言評價版
	public @ResponseBody String insertbikeReview(String orderSerialNum, int email, Double satisfacation,
			String bikeModel, String reviewContent) throws IOException, ParseException {
		System.out.println("收到了");
		System.out.println(orderSerialNum);// 訂單流水

		SimpleDateFormat fro = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		Date now = new Date();
		String aa = fro.format(now);

		bikeReviewIFaceService.save(orderSerialNum, email, reviewContent, satisfacation, now, bikeModel);// 評價留言板新增內容
		return "OK";
	}

	@RequestMapping(value = "/selectBranchDetail", method = RequestMethod.POST, produces = "text/html; charset = UTF-8") // 查詢分店
	public @ResponseBody String select() throws IOException {
		System.out.println("收到了1");
		List<BranchDetail> all = branchDetailIFaceService.getAllMembers();
		gson.toJson(all);
		System.out.println(gson.toJson(all));
		return gson.toJson(all);
	}

//羅冰的參考
//	"licensePlate" 車牌
//	"branchName"分店
//	"bikeModel" 型號
//	"modelYear" 年份
//	"bikeBrand" 廠牌
//	"engineType" CC數
//	"bikeType" 車種
//	"plateType"  紅白黃牌
//	"fuelTankCapacity" 油箱容量
//	"seatHeight" 座高
//	"dryWeight"  車子乾重:
//	"fuelConsumption"  油耗
//	"tire"  輪胎
//	"fuelType"  使用燃料
//	"aBS"  煞車ABS
//	"hourPrice" 每小時價格
	@RequestMapping(value = "/insertBikeDetail", method = RequestMethod.POST) // 新增機車資料
	public @ResponseBody String insertBikeDetail(String licensePlate, int branchName, String bikeModel,
			String modelYear, String bikeBrand, String engineType, String bikeType, String plateType,
			Double fuelTankCapacity, Double seatHeight, Double dryWeight, Double fuelConsumption, String tire,
			String fuelType, Boolean aBS, int hourPrice) throws IOException {
		System.out.println("安安");
		System.out.println(branchName);
		bikeDetailIFaceService.save(licensePlate, branchName, bikeModel, modelYear, bikeBrand, engineType, bikeType, plateType, fuelTankCapacity, seatHeight, dryWeight, fuelConsumption, tire, fuelType, aBS, hourPrice);
		return "";
	}
	@RequestMapping(value = "/selectEveryBikeInfo", method = RequestMethod.POST, produces = "text/html; charset = UTF-8") // 搜尋機車資料 取型號
	public @ResponseBody String selectEveryBikeInfo() throws IOException {
		System.out.println("安安");
		List<EveryBikeInfo> all = everyBikeInfoIFaceService.getAllMembers();
		EveryBikeInfo s1 = all.get(0);
		String s2 = s1.getBikeDetail().getIdClassBikeDetail().getBikeModel();
		System.out.println(s2);
//		gson.toJson(all);
//		System.out.println(gson.toJson(all));
		return "";
	}
	
}
