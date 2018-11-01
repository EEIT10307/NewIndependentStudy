package testcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import testbean.TestService;

@Controller
public class TestController {

	@Autowired
	TestService testService;

	public TestController() {

		System.out.println("controller正常啟動");

	}

	@RequestMapping(value = "/testcontroller", produces = "text/html; charset = UTF-8")
	public @ResponseBody String testLink(ServletRequest req) throws IOException, ParseException {
		System.out.println("testLink startup");
		BufferedReader eader = req.getReader();
		String sss = eader.readLine();
		System.out.println(sss);
		try {
			testService.testLinkDAO();
		} catch (Exception e) {
			return "Fail";
		}
		return "OK";

	}

	@RequestMapping(value = "/testhiber", produces = "text/html; charset = UTF-8")
	public @ResponseBody String testHibernateBean() throws IOException, ParseException {

		try {
			testService.testHibernateBean();
		} catch (Exception e) {
			return "Fail";
		}
		return "OK";

	}
   //製作分店假資料
	@RequestMapping(value = "/makeFakeBranchDetail", produces = "text/html; charset = UTF-8")
	public @ResponseBody String makeFakeBranchDetail() throws IOException, ParseException {

		try {
			testService.makeFakeBranchDetail();
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "OK";

	}
	//製作摩托車與車牌假資料
	@RequestMapping(value = "/makeFakeBikedetail_EveryBikeInfor", produces = "text/html; charset = UTF-8")
	public @ResponseBody String makeFakeBikedetail_EveryBikeInfor() throws IOException, ParseException {

		try {
			testService.makeFakeBikedetail_EveryBikeInfor();
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "OK";

	}
	//製作訂單假資料
	@RequestMapping(value = "/makeFakeOrderlist", produces = "text/html; charset = UTF-8")
	public @ResponseBody String makeFakeOrderlist() throws IOException, ParseException {

		try {
			testService.makeFakeOrderlist();
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "OK";
	}


	//製作保養項目假資料
	@RequestMapping(value = "/makeFakeMaintenanceDetail", produces = "text/html; charset = UTF-8")
	public @ResponseBody String makeFakeMaintenanceDetail() throws IOException, ParseException {

		try {
			testService.makeFakeMaintenanceDetail();
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "OK";
	}
	
	
	

}
