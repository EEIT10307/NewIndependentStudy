package testbean.robin.ch02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectbean.EveryBikeInfo;
import testbean.robin.EveryBikeInfoIFaceDao;
import testbean.robin.EveryBikeInfoIFaceService;

@Service
@Transactional
public class EveryBikeInfoService implements EveryBikeInfoIFaceService {
	@Autowired
	EveryBikeInfoIFaceDao everyBikeInfoIFaceDao;

	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EveryBikeInfo> getAllMembers() {
		// TODO Auto-generated method stub
		return everyBikeInfoIFaceDao.getAllMembers();
	}

	@Override
	public EveryBikeInfo getMember(int pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(EveryBikeInfo mb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(EveryBikeInfo everyBikeInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
