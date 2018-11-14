package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import everybikeInfo.robin.dao.BranchDetailIFaceDao;
import projectbean.BranchDetail;

@Service
@Transactional
public class BranchDetailService implements BranchDetailIFaceService{
@Autowired
BranchDetailIFaceDao branchDetailIFaceDao;
	@Override
	public List<BranchDetail> getAllMembers() {
		
		return branchDetailIFaceDao.getAllMembers();
	}
}
