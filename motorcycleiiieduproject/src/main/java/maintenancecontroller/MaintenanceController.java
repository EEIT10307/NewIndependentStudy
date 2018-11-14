package maintenancecontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List; 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cleanbean.EveryBikeInfoToGson;
import cleanbean.EveryBikeMileageToGson;
import cleanbean.MaintenanceBean;
import cleanbean.MaintenanceHistoryToGson;
import maintenance.MaintenanceIFaceService;
import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;
import projectbean.MaintenanceHistory;

 
@Controller
public class MaintenanceController {
	@Autowired
	MaintenanceIFaceService testMaintenanceIFaceService;
	@Autowired
	Gson gson;
	
	@GetMapping(value = "/showAllBikePlate", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showAllBikePlate()  throws IOException, ParseException {
		System.out.println("showAllBikePlate");
		try {
				String platejson=gson.toJson(testMaintenanceIFaceService.showAllBikePlate());
				return platejson;
		}catch(Exception e) {
			e.printStackTrace();
			return new String("{\"fail\":fail}");
		}
	}
	@PostMapping(value = "/showAllisReadyMaintenanceBike", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showAllisReadyMaintenanceBike(@RequestBody MaintenanceBean maintenancequery) {
		System.out.println("網頁傳入="+maintenancequery);
		try {
		List<EveryBikeInfo> selectMaintenancebranch = testMaintenanceIFaceService.showAllisReadyMaintenanceBike(maintenancequery.getMaintenanceStore());
		List<EveryBikeInfoToGson> forGsonConvert=testMaintenanceIFaceService.everyBikeInfoforGsonConvert(selectMaintenancebranch);
		System.out.println(maintenancequery.getMaintenanceStore()+"店裡有的車的資料JSON="+gson.toJson(forGsonConvert));
		return gson.toJson(forGsonConvert);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
	}
	
	@PostMapping(value = "/showBikeMaintenancingItem", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showBikeMaintenancingItem(@RequestBody MaintenanceBean maintenancequery) {
		System.out.println("網頁傳入="+maintenancequery);
		try {
			List<EveryBikeMileage> selectLicensePlate = testMaintenanceIFaceService.showBikeMaintenancingItem(maintenancequery.getLicensePlate());
			List<EveryBikeMileageToGson> forGsonConvert=testMaintenanceIFaceService.everyBikeMileageforGsonConvert(selectLicensePlate);
			System.out.println("車牌"+maintenancequery.getLicensePlate()+"目前正在保養中的項目的JSON="+gson.toJson(forGsonConvert));
			return gson.toJson(forGsonConvert);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
	}
	
	@RequestMapping(value = "/insertNEWMaintenanceDetail", method = RequestMethod.POST,produces = "application/JSON; charset = UTF-8") // 保養項目新增
	public @ResponseBody String insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage,Double requiredHourTodo) throws IOException, ParseException {
		System.out.println("執行新增保養項目insertNEWMaintenanceDetail");
		System.out.println("保養項目:"+maintenanceItem+"需求里程:"+requiredMileage+"所需工時:"+requiredHourTodo);// 訂單流水
		testMaintenanceIFaceService.insertNEWMaintenanceDetail(maintenanceItem,requiredMileage,requiredHourTodo);
		return "成功新增保養項目!!!";
	}
	
	@PostMapping(value = "/showEveryBikeMileagebyStore", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showEveryBikeMileagebyStore(@RequestBody MaintenanceBean maintenancequery) {
//		System.out.println("網頁傳入="+maintenancequery);
		try {
		List<EveryBikeMileage> selectMaintenancebranch = testMaintenanceIFaceService.showEveryBikeMileagebyStore(maintenancequery.getMaintenanceStore());
		
		List<EveryBikeMileageToGson> forGsonConvert=testMaintenanceIFaceService.everyBikeMileageforGsonConvert(selectMaintenancebranch);
		System.out.println(maintenancequery.getMaintenanceStore()+"店裡有的車的各項保養里程資料JSON="+gson.toJson(forGsonConvert));
		
		return gson.toJson(forGsonConvert);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
		
	}
	@GetMapping(value = "/showMessageIfMileageIsOver", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showMessageIfMileageIsOver() {
		try {		
			List<EveryBikeMileage> selectBikewhichMileageIsOver = testMaintenanceIFaceService.showMessageIfMileageIsOver();
			String forGsonConvert=gson.toJson(testMaintenanceIFaceService.everyBikeMileageforGsonConvert(selectBikewhichMileageIsOver));
			return forGsonConvert;
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}	
	}
	
	@PostMapping(value = "/showMessageIfMileageIsOverAfterComplete", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showMessageIfMileageIsOverAfterComplete(@RequestBody MaintenanceBean maintenancequery) {
		try {		
			List<EveryBikeMileage> showMessageIfMileageIsOverAfterComplete = testMaintenanceIFaceService.showMessageIfMileageIsOverAfterComplete(maintenancequery.getLicensePlate());
			String forGsonConvert=gson.toJson(testMaintenanceIFaceService.everyBikeMileageforGsonConvert(showMessageIfMileageIsOverAfterComplete));
			return forGsonConvert;
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}	
	}
	
	@RequestMapping(value = "/updateBikeMileage", method = RequestMethod.POST,produces = "application/JSON; charset = UTF-8") // 保養項目新增
	public @ResponseBody String updateBikeMileage(@RequestBody MaintenanceBean maintenancequery) throws IOException, ParseException {
		System.out.println("執行增加里程updateBikeMileage");
		System.out.println("車牌:"+maintenancequery.getLicensePlate()+"增加里程:"+maintenancequery.getIncreasedMileage()+"km");
//		String selectBikePlate =testMaintenanceIFaceService.updateBikeMileage(maintenancequery.getLicensePlate(), maintenancequery.getIncreasedMileage());
		testMaintenanceIFaceService.updateBikeMileage(maintenancequery.getLicensePlate(), maintenancequery.getIncreasedMileage());
		
		try {		
			List<EveryBikeMileage> selectBikewhichMileageIsOver = testMaintenanceIFaceService.showMessageIfMileageIsOver();
			String forGsonConvert=gson.toJson(testMaintenanceIFaceService.everyBikeMileageforGsonConvert(selectBikewhichMileageIsOver));
			return forGsonConvert;
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
		
//		return selectBikePlate;
	

//		return "成功新增保養項目!!!";
	}
	@RequestMapping(value = "/sendMaintenance", method = RequestMethod.POST,produces = "application/JSON; charset = UTF-8") // 送保養
	public @ResponseBody String sendMaintenance(@RequestBody MaintenanceBean maintenancequery) throws IOException, ParseException {	
		System.out.println("執行送保養sendMaintenance");
		System.out.println("車牌:"+maintenancequery.getLicensePlate());
		testMaintenanceIFaceService.sendMaintenance(maintenancequery.getLicensePlate()); 
		return gson.toJson("成功送保養!!!");
	}
	@RequestMapping(value = "/completeMaintenance", method = RequestMethod.POST,produces = "application/JSON; charset = UTF-8") // 送保養
	public @ResponseBody String completeMaintenance(@RequestBody MaintenanceBean maintenancequery) throws IOException, ParseException {	
		System.out.println("執行完成保養completeMaintenance");
		System.out.println("車牌:"+maintenancequery.getLicensePlate());
//		testMaintenanceIFaceService.sendMaintenance(maintenancequery.getLicensePlate()); 
		testMaintenanceIFaceService.completeMaintenance(maintenancequery.getLicensePlate());
		return gson.toJson(maintenancequery.getLicensePlate()+"完成保養!!!");

	}
	
	@GetMapping(value = "/showAllMaintenanceHistory", produces = "text/html; charset = UTF-8")
	public @ResponseBody String showAllMaintenanceHistory() {
		try {		
			List<MaintenanceHistory> showAllMaintenanceHistory =testMaintenanceIFaceService.showAllMaintenanceHistory();	
			List<MaintenanceHistoryToGson> forGsonConvert=testMaintenanceIFaceService.maintenanceHistoryforGsonConvert(showAllMaintenanceHistory);
			return gson.toJson(forGsonConvert);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}	
	}
	

	

}
