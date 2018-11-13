package webinfomanagercontroller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import projectbean.WebInformationForManager;
import webinfomanagerservice.WebInfoForManagerIFaceService;

@RestController
public class WebInfoForManagerController {
	
	@Autowired
	WebInfoForManagerIFaceService webInfoForManagerIFaceService;
	
	@Autowired
	Gson gson;
	
	@PostMapping(value= "/insertWebContent", produces="application/JSON; charset = UTF-8")
	public @ResponseBody List<WebInformationForManager> insertWebContent(@RequestBody List<WebInformationForManager> webInformationForManager) throws IOException, ParseException{
		System.out.println("insert all <div>'s id and content");
		webInfoForManagerIFaceService.insertWebInfoForManager(webInformationForManager);
		return null;
	}
	
	@PostMapping(value= "/updateWebContent", produces="application/JSON; charset = UTF-8")
	public @ResponseBody List<WebInformationForManager> updateWebContent(@RequestBody List<WebInformationForManager> webInformationForManager) throws IOException, ParseException{
		System.out.println("update all <div>'s id and content");
		webInfoForManagerIFaceService.updateWebInfoForManager(webInformationForManager);
//		for( WebInformationForManager loop:webInformationForManager) {
//			System.out.println(loop.getWebElements()+"  "+loop.getWebContent());
//		}
//		System.out.println(webInformationForManager.getWebElements()+webInformationForManager.getWebContent());
//	    webInfoForManagerIFaceService.updateWebInfoForManager(webInformationForManager);
		return null;
	}
	
	@PostMapping(value= "/insertorupdateWebContent", produces="application/JSON; charset = UTF-8")
	public @ResponseBody List<WebInformationForManager> insertorupdateWebContent(@RequestBody List<WebInformationForManager> webInformationForManager) throws IOException, ParseException{
		System.out.println("update all <div>'s id and content");
		webInfoForManagerIFaceService.insertorupdateWebInfoForManager(webInformationForManager);
		return null;
	}
	
	@GetMapping(value= "/queryAllWebContent", produces="application/JSON; charset = UTF-8")
	public @ResponseBody String getAllWebInfoManager(){
		System.out.println("get all <div>'s id and content");
		webInfoForManagerIFaceService.getAllWebInfoManager();
		return gson.toJson(webInfoForManagerIFaceService.getAllWebInfoManager());
	}
}
