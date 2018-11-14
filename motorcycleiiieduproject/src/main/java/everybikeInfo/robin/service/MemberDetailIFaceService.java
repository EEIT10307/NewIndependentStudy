package everybikeInfo.robin.service;

import java.util.List;

import cleanbean.MemberDetailALLForJson;
import projectbean.MemberDetail;

public interface MemberDetailIFaceService {
	List<MemberDetail> isDup(int memberSerialNum);
	List<MemberDetail> selectMemberDetailAll();
	List<MemberDetailALLForJson> memberDetailALLForJson(List<MemberDetail> memberDetail);
	Integer selectOrderList(String phone);
}
