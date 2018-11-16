package branchscenecontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping(value = "/saveOrUpdateBranchScenes", produces = "text/html; charset = UTF-8")
	public @ResponseBody String saveBranchScenes()throws IOException{
		branchScenesIFaceService.saveBranchScenes();
		return "OK";
	}
}
