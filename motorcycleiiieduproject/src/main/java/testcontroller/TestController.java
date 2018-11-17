package testcontroller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import testfakebikedao.TestService;

@Controller
public class TestController {

	@Autowired
	TestService testService;

	public TestController() {

		System.out.println("controller正常啟動");

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
	//新增AcceSerialNum假資料
	@RequestMapping(value = "/insertFakeAcceSerialNum", produces = "text/html; charset = UTF-8")
	public @ResponseBody String insertFakeAcceSerialNum() throws IOException, ParseException {

		try {
			testService.insertFakeAcceSerialNum();
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "OK";
	}
	//新增MemberDetail假資料
	@RequestMapping(value = "/insertFakeMemberDetail", produces = "text/html; charset = UTF-8")
	public @ResponseBody String makeFakeMemberDetail() throws IOException, ParseException {
		
		try {
			testService.insertMemberDetail();
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
	//新增EveryBikeMileage假資料
			@RequestMapping(value = "/insertFakeEveryBikeMileage", produces = "text/html; charset = UTF-8")
			public @ResponseBody String makeFakeEveryBikeMileage() throws IOException, ParseException {


				try {
					testService.insertEveryBikeMileage();
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
	@RequestMapping(value = "/makeFakeBikeDescription", produces = "text/html; charset = UTF-8")
	public @ResponseBody String makeFakeBikeDescription() throws IOException, ParseException {

		try {
			testService.makeFakeBikeDescription();
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
		return "OK";
	}
	
	

}
