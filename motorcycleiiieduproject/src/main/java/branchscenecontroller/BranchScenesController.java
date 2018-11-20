package branchscenecontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import branchsceneservice.BranchScenesIFaceService;
import cleanbean.BranchScenesForJson;
import projectbean.BranchScenes;

@Controller
public class BranchScenesController {

	@Autowired
	private BranchScenesIFaceService branchScenesIFaceService;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value = "/selectBranchScenes", method = RequestMethod.POST, produces = "text/html; charset = UTF-8")
	public @ResponseBody String selectBranchScenes() throws IOException, ParseException {
		List<BranchScenesForJson> all = branchScenesIFaceService.getBranchScenes();
		System.out.println(gson.toJson(all));
		return gson.toJson(all);
	}
	
	@RequestMapping(value = "/getAllBranchScenes", method = RequestMethod.POST, produces = "text/html; charset = UTF-8")
	public @ResponseBody String getBranchScenes() throws IOException, ParseException {
		List<BranchScenesForJson> all = branchScenesIFaceService.getBranchScenes();
		return gson.toJson(all);
	}
	
	@PostMapping(value = "/showBranchScenes", produces = "text/html; charset = UTF-8")
	public @ResponseBody String showBranchScenes(String spotArea) throws IOException, ParseException {
		System.out.println("嘉綺"+spotArea);
		try {
			List<BranchScenes> selectBranch = branchScenesIFaceService.showBranchScenes(spotArea);
			System.out.println("frfrr"+selectBranch.size());
			String forGsonConverter = gson.toJson(branchScenesIFaceService.showBranchScenesGson(selectBranch));
			System.out.println("forGsonConverter等於"+forGsonConverter);
			return forGsonConverter;
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{fail:fail}");
		}
	}
	
//	@PostMapping(value = "/showBranchScenes", produces = "application/JSON; charset = UTF-8")
//	public @ResponseBody String showBranchScenes(String spotArea ) {
//		System.out.println("網頁傳入="+maintenancequery);
//		try {
//			List<BranchScenes> selectLicensePlate = branchScenesIFaceService.showBranchScenes(spotArea);
//			List<BranchScenesForJson> forGsonConvert=testMaintenanceIFaceService.everyBikeMileageforGsonConvert(selectLicensePlate);
//			System.out.println("車牌"+maintenancequery.getLicensePlate()+"目前正在保養中的項目的JSON="+gson.toJson(forGsonConvert));
//			return gson.toJson(forGsonConvert);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new String("{fail:fail}");
//		}
//	}
//	@Scheduled(fixedDelay = 1296000)
	@GetMapping(value = "/saveOrUpdateBranchScenes", produces = "text/html; charset = UTF-8")
	public @ResponseBody String saveBranchScenes()throws IOException{
		branchScenesIFaceService.saveBranchScenes();
		return "OK";
	}
}
