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
import testbean.robin.BikeReviewIFaceService;
import testbean.robin.BranchDetailIFaceService;

@Controller
public class TestBikeReviewController {

	@Autowired
	BikeReviewIFaceService bikeReviewIFaceService;// 商品留言板
	@Autowired
	BranchDetailIFaceService branchDetailIFaceService;// 查詢分店

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
	
	@RequestMapping(value = "/insertBikeDetail", method = RequestMethod.POST) // 新增機車資料
	public @ResponseBody String insertBikeDetail(String licensePlate,String BranchName,String modelYear,String bikeBrand,String engineType,String bikeType,String plateType
			,Double fuelTankCapacity,Double seatHeight,Double dryWeight,Double fuelConsumption,String tire,String fuelType,Boolean ABS,String hourPrice) throws IOException {
		System.out.println("安安");
		System.out.println(licensePlate);
//		System.out.println(reader);
//		BikeDetail bike=gson.fromJson(reader, BikeDetail.class);
//		System.out.println(bike);
//		bikeDetailIFaceService.save(bike);
	
		
		
		return "";
	}

}
