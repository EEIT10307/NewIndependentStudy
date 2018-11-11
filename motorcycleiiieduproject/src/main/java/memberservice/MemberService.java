package memberservice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public MemberDetail updateMember(MemberDetail mb) {
	//	MemberDaoImp dao = new MemberDaoImp();				
		return memberDaoImp.updateMember(mb) ; 
	}

	
}
