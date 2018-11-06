package maintenancecontroller;

import java.io.IOException;
import java.text.ParseException;
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
import maintenance.MaintenanceIFaceService;
import projectbean.EveryBikeInfo;
import projectbean.EveryBikeMileage;


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

//		System.out.println("46行:"+selectMaintenancebranch.get(0).getBranchName().getBranchName());
		List<EveryBikeInfoToGson> forGsonConvert=testMaintenanceIFaceService.everyBikeInfoforGsonConvert(selectMaintenancebranch);

		
		System.out.println(maintenancequery.getMaintenanceStore()+"店裡有的車的資料JSON="+gson.toJson(forGsonConvert));
//		return gson.toJson(selectMaintenancebranch);
		return gson.toJson(forGsonConvert);
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
	}
	
	@RequestMapping(value = "/insertNEWMaintenanceDetail", method = RequestMethod.POST,produces = "application/text; charset = UTF-8") // 保養項目新增
	public @ResponseBody String insertNEWMaintenanceDetail(String maintenanceItem, Double requiredMileage) throws IOException, ParseException {
		System.out.println("執行新增保養項目insertNEWMaintenanceDetail");
		System.out.println("保養項目:"+maintenanceItem+"需求里程:"+requiredMileage);// 訂單流水
		testMaintenanceIFaceService.insertNEWMaintenanceDetail(maintenanceItem, requiredMileage);
		return "成功新增保養項目!!!";
	}
	
	@PostMapping(value = "/showEveryBikeMileagebyStore", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String showEveryBikeMileagebyStore(@RequestBody MaintenanceBean maintenancequery) {
		System.out.println("網頁傳入="+maintenancequery);
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
	
	
	

}
