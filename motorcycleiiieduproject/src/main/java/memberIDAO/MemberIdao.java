package memberIDAO;

import java.util.Date;
import java.util.List;

import cleanbean.MemberDetailCleanBean;
import projectbean.MemberDetail;



public interface MemberIdao {
	
	boolean isDup(String email);

	int save(MemberDetail mb);

	List<MemberDetail> getAllMembers();

	MemberDetail getMember(String  email);

	int deleteMember(MemberDetail mem);

	public MemberDetail checkEmailPassword(String email, String password);
	
	public MemberDetail checkEmail(String email);


	MemberDetailCleanBean updateMember(MemberDetailCleanBean mdcb);

	MemberDetail updateMember(String email, String password, String name, String phone, Date date, String gender,
			String address);

	MemberDetail updateMemberPic(String email);

	boolean checkPhotoString(String email);

	


}
