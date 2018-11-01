package testbean.robin.ch01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectbean.MemberDetail;
import testbean.robin.MemberDetailIFaceDao;
import testbean.robin.MemberDetailIFaceService;

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
