package memberservice;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cleanbean.MemberDetailCleanBean;
import memberIDAO.MemberIdao;
import projectbean.MemberDetail;


@Service
@Transactional
public class MemberService {
	


	@Autowired
	MemberIdao memberDaoImp ; 

	@Autowired
	 SessionFactory factory;
	
		public MemberService() {
		System.out.println("service run");
	}

	public MemberDetail checkEmailPassword(String email, String password) {
		return memberDaoImp.checkEmailPassword(email, password);
	};
	public MemberDetail checkEmail(String email) {
		return memberDaoImp.checkEmail(email);
	};
	

	public boolean isDup(String email) {
	//	MemberDaoImp dao = new MemberDaoImp();
		return memberDaoImp.isDup(email);
	}
	
//	@Transactional
	public int save(MemberDetail mb) {
	//	MemberDaoImp dao = new MemberDaoImp();
		 Session session = factory.getCurrentSession() ; 
		System.out.println("Servicesession = " + session);
	//	try {
		
		return memberDaoImp.save(mb);
	//	}catch (Exception e) {
	//		System.out.println("任何異常");
			
		
			
			
	//		return  0 ; 
	//	}
		
		
	}
	
	@Transactional
	public List<MemberDetail> getAllMembers() {
		
	//	MemberDaoImp dao = new MemberDaoImp();	
		System.out.println("getAllurn");
		return memberDaoImp.getAllMembers() ; 
	}
	
	public int delete(MemberDetail mb) {
	//	MemberDaoImp dao = new MemberDaoImp();
		return memberDaoImp.deleteMember(mb);
	}
	
	public MemberDetail getMember(String  email) {
	//	MemberDaoImp dao = new MemberDaoImp();
		return memberDaoImp.getMember(email) ; 
	}


	public MemberDetailCleanBean updateMember(MemberDetailCleanBean mdcb) {
		// TODO Auto-generated method stub
		return memberDaoImp.updateMember(mdcb) ; 
	}

	public MemberDetail updateMember(String email, String password, String name, String phone, Date birthday, String gender,
			String address) {
		return memberDaoImp.updateMember(email,password,name,phone,birthday,gender,
				address) ;
		
	}
	public MemberDetail updateMemberPic(String email){
		return memberDaoImp.updateMemberPic(email) ;
	}

	public boolean checkPhotoString(String email) {
		// TODO Auto-generated method stub
		return memberDaoImp.checkPhotoString(email);
	}
	
	public List<MemberDetail> getMemberPhone(String email) {
		// TODO Auto-generated method stub
		return memberDaoImp.getMemberPhone(email);
	}
	
	
}
