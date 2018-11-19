package membercontroller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import cleanbean.MemberDetailCleanBean;
import maintenance.MaintenanceIFaceService;
import memberservice.MemberService;
import projectbean.MemberDetail;



/**
 * Servlet implementation class ChangeServlet
 */
@Controller
public class ControllerMVC  {	
 
	@Autowired
	MemberService ms ; 
	@Autowired
	Gson gson ; 
	@Autowired
	SessionFactory factory;
	@Autowired
	MaintenanceIFaceService maintenanceIFaceService;

	
	@Autowired
	ApplicationContext context;
	
	// 設定密碼欄位必須由大寫或小寫字母、數字與 !@#$%!^'" 等四組資料組合而成，且長度不能小於六個字元
	// 
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%!^'\"]).{6,})";
	private Pattern pattern = null;
	private Matcher matcher = null;
	
	@RequestMapping(value = "/AutoLoginCheck"  , method = RequestMethod.POST , produces = "text/html  ; charset=utf-8")
	private @ResponseBody  String AutoLoginCheck(@RequestAttribute("reader")  BufferedReader reader ,HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException  {
		System.out.println("進入AutoLoginCheck");
		MemberDetail mem = gson.fromJson(reader, MemberDetail.class);
		String email = mem.getEmail();
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		// 檢查傳過來的email資料是否有數值
		// 如果 email 沒數值，放一個錯誤訊息到 errorMsgMap 之內
		if (email == null || email.trim().length() == 0) {
			errorMsgMap.put("EmailEmptyError", "Cookies內沒Email紀錄");
		}
		// 如果 errorMsgMap 不是空的，表示有錯誤，停留在login頁面
				if (!errorMsgMap.isEmpty()) {
					
					System.out.println("Cookies內沒Email紀錄");
					return null;
				}
				//判斷是否Cookies內的email為會員
				MemberDetail mb = ms.checkEmail(email);
				
				//email確認為會員,傳回email資料
				if (mb != null) {
					System.out.println("Email為會員:"+mb.getEmail());
				
					return mb.getEmail();
				}else {
					//如果不是會員的email
					System.out.println("不是會員的email");
		
					return null;
				}
		
	};	
	
	@RequestMapping(value = "/LoginServlet"  , method = RequestMethod.POST , produces = "text/html  ; charset=utf-8")
	private @ResponseBody  String doMVCLogin(@RequestAttribute("reader")  BufferedReader reader ,HttpServletRequest request, HttpServletResponse response) throws JsonSyntaxException, JsonIOException, IOException  {	
		
		//利用Gson把序列轉成物件
		System.out.println("進入LoginServlet");
		MemberDetail mem = gson.fromJson(reader, MemberDetail.class);
		String email = mem.getEmail();
		String password = mem.getPassword();
//        String phone = mem.getPhone();
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		// 如果email或 password 欄位為空白，回傳字串""
//		if (email == null || email.trim().length() == 0||password == null || password.trim().length() == 0) {
//			return "nullpage";
//		}else {
			
		// 3. 檢查使用者輸入資料
		// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (email == null || email.trim().length() == 0) {
			errorMsgMap.put("EmailEmptyError", "信箱欄必須輸入");
		}
		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		// 如果 errorMsgMap 不是空的，表示有錯誤，交棒給login
		if (!errorMsgMap.isEmpty()) {
			return "nullpage";
		}
		
		
		
			MemberDetail mb = ms.checkEmailPassword(email, password);
			
			if (mb != null) {
				System.out.println("登入成功!!");
				System.out.println("登入帳戶:"+mb.getEmail());
				System.out.println("登入密碼:"+mb.getPassword());
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
//			model.addAttribute(name, attributeValue)
				
			 
				
				//設定Cookie
				Cookie cookieEmail = null;
				Cookie cookiePassword = null;
				Cookie cookiePhone = null;
				String cookiePath = "/";
				
				cookieEmail = new Cookie("email", email);
				cookieEmail.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				cookieEmail.setPath(cookiePath);
				cookiePassword = new Cookie("password", password);
				cookiePassword.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				cookiePassword.setPath(cookiePath);
				cookiePhone = new Cookie("memberphone", ms.getMemberPhone(email).get(0).getPhone());
				cookiePhone.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				cookiePhone.setPath(cookiePath);
				
				
				response.addCookie(cookieEmail);
				response.addCookie(cookiePassword);
				response.addCookie(cookiePhone);
				
				
				System.out.println("response"+response.getContentType());
				return mb.getEmail();
			} else {
				System.out.println("NG, 登入失敗");
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
//			errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
				return "";
			}
			
			
//		}
	
		
		
		
		
	}
	//舊寫法
	@RequestMapping(value="/CheckSingleServlet"  , method = RequestMethod.POST , produces = "application/json;charset=utf-8" )
	private @ResponseBody String checksingle(@RequestAttribute("reader")  BufferedReader reader) {	
		System.out.println("進入CheckSingleServlet");
		MemberDetail mem = gson.fromJson(reader, MemberDetail.class);
		String email = mem.getEmail();
		 MemberDetail mb = ms.getMember(email) ; 
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//			String birthday = sdf.format(mb.getBirthday());
//         
//        	System.out.println("生日=" + birthday);  
//        	json = {"memberSerialNum":2,"email":"as@gmail.com"
		    String json = gson.toJson(mb) ; 
		  
		     System.out.println("json = " + json);  
//		     factory.close();
		     return json ; 
		     
		    
		 //    ,headers = "Accept=*/*"
	}
	
	@RequestMapping(value="/CheckAllServlet"  , method = RequestMethod.POST , produces = "application/json;charset=utf-8" )
	private @ResponseBody String checkall() {	
		 List<MemberDetail> list = ms.getAllMembers() ; 	 		    
		    String json = gson.toJson(list) ; 
		     System.out.println("json = " + json); 
		     factory.close();
		     return json ; 
		     
		    
		 //    ,headers = "Accept=*/*"
	}
	//舊寫法
//	@RequestMapping(value = "/ChangeServlet"  , method = RequestMethod.POST)
//	private @ResponseBody String doMVCchage( @RequestAttribute("reader")  BufferedReader reader  ) throws JsonSyntaxException, JsonIOException, IOException  {     
//		System.out.println("進入ChangeServlet");
//		MemberDetail mem = gson.fromJson(reader, MemberDetail.class) ;
//				
		@RequestMapping(value = "/ChangeServlet", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
		private @ResponseBody String checksingle(@RequestBody MemberDetailCleanBean mdcb) throws ParseException {
			System.out.println("進入ChangeServlet");
			System.out.println("網頁傳入=" + mdcb);
			System.out.println(mdcb.toString());
//			MemberDetail mem = gson.fromJson(reader, MemberDetail.class);	
//			String email = mem.getEmail();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		                  Date birthday = sdf.parse(mdcb.getBirthday());
		              	System.out.println("生日=" + birthday);          
	 	String email = mdcb.getEmail();
	 	String password = mdcb.getPassword();	 	
		String name = mdcb.getName();
		String phone = mdcb.getPhone();		
		String gender = mdcb.getGender();
		String address = mdcb.getAddress();
		System.out.println("email:"+email+",password:"+password+"name:"+name+"phone:"+phone+"gender:"+gender+"address:"+address);
		
	 	 ms.updateMember(email, password, name, phone, birthday, gender, address);
//	     System.out.println(mem.getEmail());
//	     System.out.println(mem.getBirthday());
		
	 	     
	     return "check" ; 
	}
	
	@RequestMapping(value = "/DeleteServlet"  , method = RequestMethod.POST)
	private @ResponseBody String doMVCDelete(@RequestAttribute("reader")  BufferedReader reader ) throws JsonSyntaxException, JsonIOException, IOException  {     
		MemberDetail mem = gson.fromJson(reader, MemberDetail.class) ;  
	  ms.delete(mem) ;
	   return "" ; 
	}
	
	@RequestMapping(value = "/RegisterServlet"  , method = RequestMethod.POST , produces = "text/html  ; charset=utf-8")
	private @ResponseBody String doMVCRegister(@RequestAttribute("reader")  BufferedReader reader ) throws JsonSyntaxException, JsonIOException, IOException  {	
		
		
		//利用Gson把序列轉成物件
		System.out.println("進入RegisterServlet");
		MemberDetail mem = gson.fromJson(reader, MemberDetail.class) ;
		Date signinDate = new java.util.Date();
		  
//		  Blob test = new Blob(url);
		  mem.setSigninDate(signinDate); 
		  mem.setLastLoginDate(signinDate);
//		  mem.setProfilePhoto(test); 暫時將bean的 	@Column(nullable = false) 註解起來
		  System.out.println("最後登入日:"+mem.getLastLoginDate());
		  
		  //登出日目前和登入日相同，等待修改
		  System.out.println("最後登出日:"+mem.getSigninDate());
		  
		  //裝入使用者登入資料
		  String email = mem.getEmail();
		  String password = mem.getPassword();
		  String phone = mem.getPhone();
		 
		  
		//驗證資料
			Map<String, String> errors = new HashMap<String, String>();
//			model.addAttribute("errors", errors);
			
			if(email==null || email.length()==0) {
//				errors.put("username", "Please enter ID for login(MVC)");
				errors.put("EmailEmptyError",  "信箱欄必須輸入");
				 ;
			}
			if(password==null || password.length()==0) {
//				errors.put("password", "Please enter PWD for login(MVC)");
				errors.put("PasswordEmptyError", "密碼欄必須輸入");
				 
			}
			if(phone==null || phone.length()==0) {
//				errors.put("password", "Please enter PWD for login(MVC)");
				errors.put("PhoneEmptyError", "電話欄必須輸入");
				 
			}
			if(errors!=null && !errors.isEmpty()) {
//				model.getRequestDispatcher(
//						"/secure/login.jsp").forward(request, response);
				return "nullpage";
			}
//			return ""改寫成 return "login.success";
			
			//呼叫model
//					CustomerBean bean = customerService.login(username, password);
			
			// 如果都有輸入，但密碼規格有錯誤，將錯誤資訊放入errors
			if (errors.isEmpty()) {
				pattern = Pattern.compile(PASSWORD_PATTERN);
				matcher = pattern.matcher(password);
				if (!matcher.matches()) {
					errors.put("passwordError", "密碼至少含有一個大寫或小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
				System.out.println("密碼至少含有一個大寫或小寫字母、數字與!@#$%!^'\\\"等四組資料組合而成，且長度不能小於八個字元");
				}
			}
			
			// 如果errors有錯誤資料，則回傳errorlogintype格式錯誤頁面
			if (!errors.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			System.out.println("有欄位沒輸入");
				return "errorlogintype";
			}
			
			
			//檢查註冊資料核對資料庫是否重複
			//檢查信箱帳號是否已經存在
			 boolean drop = ms.isDup(email);
			
		 try {     
			 if(drop) {
				 System.out.println("Email已被使用，請使用其他email註冊!!");
				 return "duplicateEmail";
			 }else {
				 
		     ms.save(mem) ; 
			 }
		     
		 }catch (Exception e) {
			System.out.println("Register Eeception!!!");
			e.printStackTrace();
		}
		 System.out.println("Email="+mem.getEmail());
		 String registerdata = gson.toJson(mem);
		 System.out.println("registerdata="+registerdata);
//	   return mem.getEmail() ; 
		 return registerdata;
	}
	
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST , produces = MediaType.TEXT_PLAIN_VALUE)
	  public@ResponseBody String upload(@RequestParam("file") MultipartFile file,@RequestParam("email") String email ) throws IOException {
		
//		System.out.println("網頁傳入=" + mdcb);
//		System.out.println(mdcb.toString());
//		String email = mdcb.getEmail();
		System.out.println("進入upload");
	    System.out.println("傳入email:"+email);
		System.out.println(file.getOriginalFilename());
	    if (!file.getOriginalFilename().isEmpty()) {
	      BufferedOutputStream outputStream = new BufferedOutputStream(
	            new FileOutputStream(
	            		//===/Users/kuochiahao/git/repository/motorcycleiiieduproject/src/main/webapp/Images
	            		//===C:\\Maven\\eclipse-workspace\\motorcycleiiieduproject\\src\\main\\webapp\\Images
	                  new File("/Users/kuochiahao/git/repository/motorcycleiiieduproject/src/main/webapp/Images", "Front"+email+".jpg"))); // 上傳檔案位置為D:\
	      outputStream.write(file.getBytes());
	      outputStream.flush();
	      
	      
	      outputStream.close();
	    }else{
	      return "fail";
	    }
	    
	    return "success";
	}	
	
	
	@RequestMapping(value = "/ProfilePhotoServlet", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private @ResponseBody String savestring(@RequestAttribute("reader")  BufferedReader reader) throws ParseException {
		System.out.println("進入ProfilePhotoServlet");
		MemberDetail mem = gson.fromJson(reader, MemberDetail.class);
		String email = mem.getEmail();

		
		 MemberDetail mb = ms.updateMemberPic(email) ; 
		    String json = gson.toJson(mb) ; 			  
		     System.out.println("json = " + json); 
		 
 	     
     return json ; 
}
	
	@RequestMapping(value = "/PhotoStringCheckServlet", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	private @ResponseBody boolean photoStringCheck(@RequestAttribute("reader")  BufferedReader reader) throws ParseException {
		System.out.println("進入PhotoStringCheckServlet");
		MemberDetail mem = gson.fromJson(reader, MemberDetail.class);
		String email = mem.getEmail();
		boolean drop = ms.checkPhotoString(email) ;
	
	     return drop ; 
	}
	
	
	@RequestMapping(value = "/uploadmutipartMem",method = RequestMethod.POST , produces = MediaType.TEXT_PLAIN_VALUE)
	  public@ResponseBody String uploadmutipart(@RequestParam("file") MultipartFile[] files , @RequestParam("te") String te) throws IOException {
	for(int i = 0  ; i<files.length ; i ++) {
		System.out.println(te);
		MultipartFile file = files[i];		
	    if (!file.getOriginalFilename().isEmpty()) {
	      BufferedOutputStream outputStream = new BufferedOutputStream(
	            new FileOutputStream(
	            		//===/Users/kuochiahao/git/repository/motorcycleiiieduproject/src/main/webapp/Images
	            		//===C:\\Maven\\eclipse-workspace\\motorcycleiiieduproject\\src\\main\\webapp\\Images
	                  new File("/Users/kuochiahao/git/repository/motorcycleiiieduproject/src/main/webapp/Images", file.getOriginalFilename()))); // 上傳檔案位置為D:\
	      outputStream.write(file.getBytes());
	      outputStream.flush();      
	      outputStream.close();
	    }else{
	      return "fail";
	    }
	    
	}
	
	return "success";
	
	}
	@RequestMapping(value = "/selectEveryBikeInfo",method = RequestMethod.POST)
	  public@ResponseBody String selectEveryBikeInfo(String licensePlate) throws IOException {
	System.out.println("查詢:"+licensePlate);
	boolean zx = maintenanceIFaceService.selectEveryBikeInfo(licensePlate);
	return gson.toJson(zx);
	
	}
	
	
	
	
	
}
