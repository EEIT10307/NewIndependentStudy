package testcontroller;


import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cleanbean.BasicOrderBean;
import everybikeInfo.TestService;

@Controller
public class TestControllerCriteria {
	@Autowired
	TestService testService;
	@Autowired
	Gson gson;
	
	
	@RequestMapping(value = "/createCriteria", produces = "application/JSON; charset = UTF-8")
	public @ResponseBody String createCriteria(@RequestBody BasicOrderBean customerquery) throws IOException, ParseException {

		
		System.out.println(customerquery);
		
		 
		
		
		try {
	//		testService.createCriteria();
		} catch (Exception e) {
			return "Fail";
		}
		return  gson.toJson(customerquery);

	}

	

}
