package everybikeInfocontroller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import cleanbean.AcceStockBean;
import cleanbean.BikeDetailAndEveryBikeInfo;
import cleanbean.BikeDetailToGson;
import cleanbean.EveryBikeInfoAddCarsBean;
import cleanbean.EveryBikeInfoBean;
import cleanbean.EveryBikeInfoToGson;
import cleanbean.QaBean;
import cleanbean.QaBeanToJson;
import everybikeInfo.robin.service.AcceStockIFaceService;
import everybikeInfo.robin.service.BikeDetailIFaceService;
import everybikeInfo.robin.service.BikeReviewIFaceService;
import everybikeInfo.robin.service.BranchDetailIFaceService;
import everybikeInfo.robin.service.EveryBikeInfoIFaceService;
import everybikeInfo.robin.service.EveryBikeMileageIFaceService;
import everybikeInfo.robin.service.TestEmailIFaceService;
import projectbean.AcceSerialNum;
import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;

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
	EveryBikeMileageIFaceService everyBikeMileageIFaceService;
	@Autowired
	AcceStockIFaceService acceStockIFaceService;
	@Autowired
	TestEmailIFaceService testEmailIFaceService;
	@Autowired
	Gson gson;

	@RequestMapping(value = "/insert", method = RequestMethod.POST) // 留言評價版
	public @ResponseBody String insertbikeReview(String orderSerialNum, int email, Double satisfacation,
			String bikeModel, String reviewContent) throws IOException, ParseException {
//		System.out.println("收到了");
		System.out.println(orderSerialNum);// 訂單流水

		SimpleDateFormat fro = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		Date now = new Date();

		bikeReviewIFaceService.save(orderSerialNum, email, reviewContent, satisfacation, now, bikeModel);// 評價留言板新增內容
		return "OK";
	}

	@RequestMapping(value = "/selectBranchDetail", method = RequestMethod.POST, produces = "text/html; charset = UTF-8") // 查詢分店
	public @ResponseBody String select() throws IOException {
//		System.out.println("收到了1");
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
	@RequestMapping(value = "/insertBikeDetail", method = RequestMethod.POST, produces = "text/html; charset = UTF-8") // 新增機車資料可多筆
	public @ResponseBody String insertBikeDetail(@RequestAttribute("reader") BufferedReader reader) throws IOException {
//		System.out.println("安安");

		Map<String, String> po = new HashMap<>();
		BikeDetailAndEveryBikeInfo[] bikeDetailAndEveryBikeInfo = gson.fromJson(reader,
				BikeDetailAndEveryBikeInfo[].class);

		for (BikeDetailAndEveryBikeInfo root : bikeDetailAndEveryBikeInfo) {
			if (everyBikeInfoIFaceService.getMemberOne(root.getLicensePlate())) {
				po.put("key", "此車牌重複:" + root.getLicensePlate());
				System.out.println("第一");
				return gson.toJson(po);
			} else if (everyBikeInfoIFaceService.checkbikeModelmodelYear(root.getBikeModel(), root.getModelYear())) {
				po.put("key", "重複:型號=" + root.getBikeModel() + "年份=" + root.getModelYear());

				System.out.println("第二");
				return gson.toJson(po);
			}

		}
		bikeDetailIFaceService.save(bikeDetailAndEveryBikeInfo);
	for (BikeDetailAndEveryBikeInfo root1 : bikeDetailAndEveryBikeInfo) {


		everyBikeInfoIFaceService.save(root1.getLicensePlate(), root1.getBranchName(), root1.getBikeModel(), root1.getModelYear());
		everyBikeMileageIFaceService.save(root1.getLicensePlate());
		}
		return "";
	}

	@RequestMapping(value = "/uploadmutipartf", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE) // 測試上傳照片(多張)
	public @ResponseBody String uploadmutiparxasdtf(@RequestParam("file") MultipartFile[] files) throws IOException {
		for (int i = 0; i < files.length; i++) {

			MultipartFile file = files[i];
			if (!file.getOriginalFilename().isEmpty()) {
				i++;
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(
						"C:\\Maven\\workspace-motro\\motorcycleiiieduproject.zip_expanded\\motorcycleiiieduproject\\src\\main\\webapp\\Image",
						"R3_1990_00" + i + ".jpg"))); // 上傳檔案位置為D:\

				outputStream.write(file.getBytes());
				outputStream.flush();
				outputStream.close();
				i--;
			} else {
				return "fail";
			}

		}
		return "";
	}

	@RequestMapping(value = "/selectEveryBikeInfoBikeModel", method = RequestMethod.POST, produces = "text/html; charset = UTF-8") // 搜尋機車資料
	// 取型號
	public @ResponseBody String selectEveryBikeInfo() throws IOException {

//		System.out.println("安安");
		List<EveryBikeInfo> all = everyBikeInfoIFaceService.getAllMembers();
		// 產生不重複的型號
		Set<String> list = new HashSet<String>();
		for (int x = 0; x < all.size(); x++) {// 如果list.add(all.get(x).getBikeDetail().getIdClassBikeDetail() 會多一筆
												// 所以要加.getBikeModel());
			list.add(all.get(x).getBikeDetail().getIdClassBikeDetail().getBikeModel());
		}
//		Iterator<IdClassBikeDetail> it = list.iterator();
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		return gson.toJson(list);
	}

	@RequestMapping(value = "/selectEveryBikeInfoModelYear", method = RequestMethod.POST, produces = "text/html; charset = UTF-8") // 搜尋機車資料
	// 取型號
	public @ResponseBody String selectEfveryBikeInfo(String Year) throws IOException {

		System.out.println("年份");
		System.out.println(Year);

		List<EveryBikeInfo> all = everyBikeInfoIFaceService.selectModelAll(Year);

		Set<String> list = new HashSet<String>();
		for (int x = 0; x < all.size(); x++) {// 如果list.add(all.get(x).getBikeDetail().getIdClassBikeDetail() 會多一筆
												// 所以要加.getBikeModel());
			list.add(all.get(x).getBikeDetail().getIdClassBikeDetail().getModelYear());
		}

		return gson.toJson(list);
	}

	@PostMapping(value = "/showAllshowAllEveryBikeInfo", produces = "text/html; charset = UTF-8") // 查詢機車所有年份
	public @ResponseBody String showAllshowAllEveryBikeInfo(@RequestBody EveryBikeInfoBean everyBikeInfoquery) {// @RequestBody
																												// EveryBikeInfoBean
																												// everyBikeInfoquery(使用這方法，網頁傳進來的值的名子要對應到
																												// EveryBikeInfoBean類別裡面的一樣)
		System.out.println("網頁傳入=" + everyBikeInfoquery);
		try {
			List<EveryBikeInfo> selectEveryBikeInfo = everyBikeInfoIFaceService
					.showAllEveryBikeInfo(everyBikeInfoquery.getEveryBikeInfoModel());

//		System.out.println("46行:"+selectMaintenancebranch.get(0).getBranchName().getBranchName());
			Set<String> list = new HashSet<String>();
			List<EveryBikeInfoToGson> forGsonConvert = everyBikeInfoIFaceService.forGsonConvert(selectEveryBikeInfo);
			for (int x = 0; x < forGsonConvert.size(); x++) {

				list.add(forGsonConvert.get(x).getModelYear());
			}
//			System.out.println(list.size());
//			System.out.println("List JSON=" + gson.toJson(list));
//			System.out.println("該保養車s的JSON=" + gson.toJson(forGsonConvert));

//		return gson.toJson(selectMaintenancebranch);
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
	}

	@PostMapping(value = "/insertAllLicensePlate", produces = "text/html; charset = UTF-8") // 只新增車牌資料 多筆
	public @ResponseBody String insertAllLicensePlate(@RequestAttribute("reader") BufferedReader reader)
			throws IOException {

		EveryBikeInfoAddCarsBean[] test = gson.fromJson(reader, EveryBikeInfoAddCarsBean[].class);
		for (EveryBikeInfoAddCarsBean root : test) {
			if (everyBikeInfoIFaceService.getMemberOne(root.getLicensePlate())) {
				return "此車牌重複:" + root.getLicensePlate();
			} else {
				System.out.println("車牌:" + root.getLicensePlate() + "分店:" + root.getBranchName() + "型號:"
						+ root.getBikeModel() + "年份:" + root.getModelYear());
				String licensePlate = root.getLicensePlate();
				int branchName = root.getBranchName();
				String bikeModel = root.getBikeModel();
				String modelYear = root.getModelYear();
				everyBikeInfoIFaceService.save(licensePlate, branchName, bikeModel, modelYear);
				everyBikeMileageIFaceService.save(licensePlate);
			}
		}
		return "OK";
	}

	@PostMapping(value = "/selectBikeDetial", produces = "text/html; charset = UTF-8") // 查詢機車詳細訂單
	public @ResponseBody String selectBikeDetial(String BikeModel, String ModelYear) throws IOException {
		BikeDetail select = everyBikeInfoIFaceService.selectbikeModelmodelYear(BikeModel, ModelYear);
		List<BikeDetailToGson> ta = everyBikeInfoIFaceService.forGsonConvertBikeDetail(select);
		return gson.toJson(ta);
	}

	@PostMapping(value = "/updateBikeDetial", produces = "text/html; charset = UTF-8") // Update 機車詳細資料
	public @ResponseBody String updateBikeDetial(@RequestBody BikeDetailToGson bikeDetail) throws IOException {
//		System.out.println("進來了:"+bikeDetail);
		bikeDetailIFaceService.updateBikeDetai(bikeDetail);
		return "";
	}

	@PostMapping(value = "/selectAcceSerialNum", produces = "text/html; charset = UTF-8") // 新增配件
	public @ResponseBody String insertAcceStock() throws IOException {
		List<AcceSerialNum> all = acceStockIFaceService.allAcceSerialNum();
		return gson.toJson(all);
	}

	@PostMapping(value = "/insertAcceStock", produces = "text/html; charset = UTF-8") // 新增配件
	public @ResponseBody String insertAcceStock(@RequestBody AcceStockBean acceStockBean) throws IOException {
		System.out.println("新增" + acceStockBean);
		acceStockIFaceService.insertAcceStock(acceStockBean);

		return "";
	}

	@PostMapping(value = "/testmail", produces = "text/html; charset = UTF-8") // emil
	public @ResponseBody String test_mail(String or, String em) throws IOException {
		System.out.println("訂單:" + or + "信箱:" + em);
		testEmailIFaceService.sendemail(or, em);
		return "";
	}

	@PostMapping(value = "/insertQA", produces = "text/html; charset = UTF-8") // 商品評價 新增
	public @ResponseBody String insertQA(@RequestBody QaBean qaBean) throws IOException {

		bikeDetailIFaceService.insertQA(qaBean);
		return "";
	}

	@PostMapping(value = "/selectQA", produces = "text/html; charset = UTF-8") // 商品評價 查詢
	public @ResponseBody String selectQA(String bikeModel,String modelYear) throws IOException {
		System.out.println("OK");
	
		 List<QaBeanToJson> QQ = bikeDetailIFaceService.QaBeanToJson(bikeDetailIFaceService.selectQA(bikeModel,modelYear));
		return gson.toJson(QQ);
	}
	@PostMapping(value = "/updateQA", produces = "text/html; charset = UTF-8") // 商品評價 查詢
	public @ResponseBody String updateQA(int qAndASerialNum,String ans,String ansquction) throws IOException {
		System.out.println(ans+"OK");
		bikeDetailIFaceService.updateQA(qAndASerialNum, ans, ansquction);
		return "回覆成功";
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------以下為測試

	@RequestMapping(value = "/uploadmutipart", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE) // 測試上傳照片(多張)
	public @ResponseBody String uploadmutipart(@RequestParam("file") MultipartFile[] files,
			@RequestParam("BikeModel") String BikeModel, @RequestParam("ModelYear") String ModelYear)
			throws IOException {
		System.out.println(BikeModel);
		System.out.println("FQ");
		for (int i = 0; i < files.length; i++) {
		
//			System.out.println(BikeModel);
//			System.out.println(ModelYear);
			MultipartFile file = files[i];
	
			if (!file.getOriginalFilename().isEmpty()) {
				i++;
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(
						"C:\\Maven\\repository\\motorcycleiiieduproject\\src\\main\\webapp\\Image",
						BikeModel + "_" + ModelYear + "_00" + i + ".jpg"))); // 上傳檔案位置為D:\

				outputStream.write(file.getBytes());
				outputStream.flush();
				outputStream.close();
				i--;
			} else {
				return "fail";
			}

		}

		return "success";

	}

	@PostMapping(value = "/selectBikeDetail", produces = "text/html; charset = UTF-8") // 查詢機車詳細訂單
	public @ResponseBody String insertAllLicensePlateD(String lice) throws IOException {
		System.out.println(lice);
		boolean as = everyBikeInfoIFaceService.getMemberOne("AAA-01");
		return "OK";
	}

	@PostMapping(value = "/test", produces = "text/html; charset = UTF-8") //
	public @ResponseBody String insertAllLicensePlate(String lice) throws IOException {
		System.out.println(lice);
//		everyBikeInfoIFaceService.checkbikeModelmodelYear("RR33", "22222");
		everyBikeMileageIFaceService.save("MFD-001");
		return "OK";
	}

}
