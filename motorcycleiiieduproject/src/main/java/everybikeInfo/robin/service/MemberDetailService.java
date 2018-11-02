package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



}
