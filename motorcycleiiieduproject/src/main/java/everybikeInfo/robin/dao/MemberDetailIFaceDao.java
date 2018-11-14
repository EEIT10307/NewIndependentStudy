package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.MemberDetailALLForJson;
import projectbean.MemberDetail;
import projectbean.OrderList;
@Repository
public interface MemberDetailIFaceDao {
	List<MemberDetail> selectone(int memberSerialNum);
	List<MemberDetail> selectMemberDetailAll();
	List<MemberDetailALLForJson> memberDetailALLForJson(List<MemberDetail> memberDetail);
	Integer selectOrderList(String phone);
}
