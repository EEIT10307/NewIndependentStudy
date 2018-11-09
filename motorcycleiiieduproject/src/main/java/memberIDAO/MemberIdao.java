package memberIDAO;

import java.util.List;

import projectbean.MemberDetail;



public interface MemberIdao {
	
	boolean isDup(String email);

	int save(MemberDetail mb);

	List<MemberDetail> getAllMembers();

	MemberDetail getMember(int pk);

	int deleteMember(MemberDetail mem);

	int updateMember(MemberDetail mb);
//

	public MemberDetail checkEmailPassword(String email, String password);
	
	public MemberDetail checkEmail(String email);


	


}
