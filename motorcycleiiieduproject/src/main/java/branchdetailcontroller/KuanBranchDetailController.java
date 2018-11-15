package branchdetailcontroller;

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

import branchdetail.BranchDetailIFaceService;
import cleanbean.BranchDetailBean;
import projectbean.BranchDetail;
import projectbean.EveryBikeMileage;

@Controller
public class KuanBranchDetailController{
	
	@Autowired
	 BranchDetailIFaceService branchDetailIFaceService;
	@Autowired
	 Gson gson;
	
//	@RequestMapping(value = "/selectBranchDetail", method = RequestMethod.POST, produces = "text/html; charset = UTF-8")
//	public @ResponseBody String selectBranchDetail() throws IOException, ParseException {
//		List<BranchDetail> all = branchDetailIFaceService.getBranchDetail();
//		gson.toJson(all);
//		return gson.toJson(all);
//	}
	// 網頁店名動態顯示
		@GetMapping(value = "/showBranchDetail", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String showBranchDetail() throws IOException, ParseException {
			System.out.println("showBranchDetail");
			try {
				String branchjson = gson.toJson(branchDetailIFaceService.showBranchDetail());
				return branchjson;
			} catch (Exception e) {
				e.printStackTrace();
				return new String("{\"fail\":fail}");
			}
		}

		@GetMapping(value = "/showAllBranchDetail", produces = "application/JSON; charset = UTF-8")
		public @ResponseBody String showAllBranchDetail() {
			try {		
				List<BranchDetail> AllBranchList = branchDetailIFaceService.showAllBranchDetail();
				String forGsonConvert=gson.toJson(AllBranchList);
				return forGsonConvert;
			} catch (Exception e) {
				e.printStackTrace();
				return new String("{fail:fail}");
			}	
		}

	@PostMapping(value = "/saveBranchDetail", produces = "text/html;charset=utf-8")
	public @ResponseBody String saveBranchDetail(@RequestBody BranchDetailBean branchDetail)throws IOException{
		System.out.println("分店"+branchDetail);
		try {
			branchDetailIFaceService.saveBranchDetail(branchDetail);	
		} catch (Exception e) {
			e.printStackTrace();
			return new String("{\"fail\":fail}");
		}
		return gson.toJson(branchDetail);
	}
}
