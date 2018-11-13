package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cleanbean.MemberDetailALLForJson;
import everybikeInfo.robin.dao.MemberDetailIFaceDao;
import projectbean.MemberDetail;

@Service
@Transactional
public class MemberDetailService implements MemberDetailIFaceService {

	@Autowired
	MemberDetailIFaceDao MemberDetailIFaceDao;
	
	@Override
	public List<MemberDetail> isDup(int memberSerialNum) {

		return MemberDetailIFaceDao.selectone(memberSerialNum);
	}

	@Override
	public List<MemberDetail> selectMemberDetailAll() {
		// TODO Auto-generated method stub
		return MemberDetailIFaceDao.selectMemberDetailAll();
	}

	@Override
	public List<MemberDetailALLForJson> memberDetailALLForJson(List<MemberDetail> memberDetail) {
		// TODO Auto-generated method stub
		return MemberDetailIFaceDao.memberDetailALLForJson(memberDetail);
	}

	@Override
	public Integer selectOrderList(String phone) {
		// TODO Auto-generated method stub
		return MemberDetailIFaceDao.selectOrderList(phone);
	}



}
