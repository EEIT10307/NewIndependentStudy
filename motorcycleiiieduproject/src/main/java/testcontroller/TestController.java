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
	TestService testService ; 
	
	
	public TestController() {
	
		System.out.println("controller正常啟動");
		
		
	}

@RequestMapping(value = "/testcontroller"  , produces = "text/html; charset = UTF-8")	
public @ResponseBody String  testLink(ServletRequest req) throws IOException, ParseException {
	System.out.println("testLink startup");
	  BufferedReader eader = req.getReader() ; 	    
	  String sss = eader.readLine() ; 
	System.out.println(sss);
	try {
	testService.testLinkDAO();
	}catch (Exception e) {
		return "Fail" ; 
	}
	return "OK" ;
	
}
	
	

}
