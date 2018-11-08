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

import branchdetailservice.BranchDetailIFaceService;
import projectbean.BranchDetail;

//@SuppressWarnings("serial")
//@WebServlet(
//		urlPatterns={"/pages/product.branchdetailcontroller"}
//)
@Controller
public class BranchDetailController{
	
	@Autowired
	private BranchDetailIFaceService branchDetailIFaceService;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value = "/selectBranchDetail", method = RequestMethod.POST, produces = "text/html;; charset = UTF-8")
	public @ResponseBody String selectBranchDetail() throws IOException, ParseException {
		List<BranchDetail> all = branchDetailIFaceService.getBranchDetail();
		gson.toJson(all);
		return gson.toJson(all);
	}
	
	@PostMapping(value = "/saveBranchDetail", produces = "text/html; charset = UTF-8")
	public @ResponseBody String saveBranchDetail(@RequestAttribute("reader") BufferedReader reader)throws IOException{
		BranchDetail[] test = gson.fromJson(reader, BranchDetail[].class);
		
		for(BranchDetail root : test) {
			System.out.println("流水號" + root.getBranchSerialNum() + "分店" + root.getBranchName() + 
					"地區" + root.getBranchArea() + "縣市" + root.getBranchCounty() + "地址" + root.getBranchAddress() +
					"電話" +root.getBranchPhone() + "開店日" + root.getOpeningDay());
			int branchSerialNum = root.getBranchSerialNum();
			String branchName = root.getBranchName();
			String branchArea = root.getBranchArea();
			String branchCounty = root.getBranchCounty();
			String branchAddress = root.getBranchAddress();
			String branchPhone = root.getBranchPhone();
			Date openingDay = root.getOpeningDay();
			
			branchDetailIFaceService.saveBranchDetail(branchSerialNum, branchName,
					branchArea, branchCounty, branchAddress, branchPhone, openingDay);
		}
		return "OK";
	}
	
//	private BranchDetailService branchDetailService;
//	@Override
//	public void init() throws ServletException {
//		ServletContext application = this.getServletContext();
//		ApplicationContext context = (ApplicationContext)
//				application.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//		this.branchDetailService = (BranchDetailService)context.getBean("branchDetailService");
//	}
//	@Override
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
////嚙賭漱嚙賢��嚙踝蕭嚙�
//		String temp = request.getParameter("serialnum");
//		String name = request.getParameter("name");
//		String area = request.getParameter("area");
//		String county = request.getParameter("county");
//		String address = request.getParameter("address");
//		String phone = request.getParameter("phone");
//		String prodaction = request.getParameter("prodaction");
//
////�踝蕭��嚙賡��嚙踝蕭嚙�
//		Map<String, String> errors = new HashMap<>();
//		request.setAttribute("errorMsgs", errors);
//		
//		if("Insert".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction)) {
//			if(temp==null || temp.length()==0) {
//				errors.put("xxx1", "�ｇ蕭��閰剁蕭鈭伐蕭嚙踝蕭��銋拙�塚蕭��嚙�"+prodaction);
//			}
//		}
//		
////�改蕭嚙踝蕭��嚙踝蕭嚙�
//		int serialnum = 0;
//		if(temp != null || temp.length()!=0) {
//			try {
//				serialnum = Integer.parseInt(temp);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(errors!=null && !errors.isEmpty()) {
//			request.getRequestDispatcher(
//					"/pages/product.jsp").forward(request, response);
//			return;
//		}
//
////嚙賣�嚙賣�剁蕭嚙踝蕭
//		BranchDetail bd = new BranchDetail();
//		bd.setBranchSerialNum(serialnum);
//		bd.setBranchName(name);
//		bd.setBranchArea(area);
//		bd.setBranchCounty(county);
//		bd.setBranchAddress(address);
//		bd.setBranchPhone(phone);
//		
////嚙賢�嚙踝蕭Model嚙賜��嚙質�荔蕭嚙踝蕭��嚙踝蕭瞉�嚙賭凝iew
//		if("Select".equals(prodaction)) {
//			List<BranchDetail> result = branchDetailService.select(bd);
//			request.setAttribute("select", result);
//			request.getRequestDispatcher(
//					"/pages/display.jsp").forward(request, response);
//		} else if("Insert".equals(prodaction)) {
//			BranchDetail result = branchDetailService.insert(bd);
//			if(result==null) {
//				errors.put("action", "Insert fail");
//			} else {
//				request.setAttribute("insert", result);
//			}
//			request.getRequestDispatcher(
//					"/pages/product.jsp").forward(request, response);
//		} else if("Update".equals(prodaction)) {
//			BranchDetail result = branchDetailService.update(bd);
//			if(result==null) {
//				errors.put("action", "Update fail");
//			} else {
//				request.setAttribute("update", result);
//			}
//			request.getRequestDispatcher(
//					"/pages/product.jsp").forward(request, response);
//		} else if("Delete".equals(prodaction)) {
//			boolean result = branchDetailService.delete(bd);
//			if(!result) {
//				request.setAttribute("delete", 0);
//			} else {
//				request.setAttribute("delete", 1);
//			}
//			request.getRequestDispatcher(
//					"/pages/product.jsp").forward(request, response);
//		} else {
//			errors.put("action", "Unknown Action:"+prodaction);
//			request.getRequestDispatcher(
//					"/pages/product.jsp").forward(request, response);
//		}
//	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		this.doGet(req, resp);
//	}
}
