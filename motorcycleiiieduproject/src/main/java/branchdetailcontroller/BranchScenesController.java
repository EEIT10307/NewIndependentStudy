package branchdetailcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import branchdetailservice.BranchScenesIFaceService;
import projectbean.BranchDetail;
import projectbean.BranchScenes;

@Controller
public class BranchScenesController {

	@Autowired
	private BranchScenesIFaceService branchScenesIFaceService;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value = "/selectBranchScenes", method = RequestMethod.POST, produces = "text/html;; charset = UTF-8")
	public @ResponseBody String selectBranchScenes() throws IOException, ParseException {
		List<String> all = branchScenesIFaceService.getBranchScenes();
		gson.toJson(all);
		return gson.toJson(all);
	}
	
	@PostMapping(value = "/saveBranchScenes", produces = "text/html; charset = UTF-8")
	public @ResponseBody String saveBranchScenes(@RequestAttribute("reader") BufferedReader reader)throws IOException{
		BranchScenes[] test = gson.fromJson(reader, BranchScenes[].class);
		
		for(BranchScenes root : test) {
			System.out.println("流水號" + root.getBranchDetailSerialNum() + "分店" + root.getBranchName() + 
					"景點" + root.getSpotName() + "地址" + root.getSpotAddress() + "風景" + root.getSpotPhoto());
			int branchSerialNum = root.getBranchDetailSerialNum();
			BranchDetail branchName = root.getBranchName();
			String spotName = root.getSpotName();
			String spotAddress = root.getSpotAddress();
			String spotPhoto = root.getSpotPhoto();
			
			branchScenesIFaceService.saveBranchScenes(branchSerialNum, branchName, spotName, spotAddress, spotPhoto);
		}
		return "OK";
	}
}
