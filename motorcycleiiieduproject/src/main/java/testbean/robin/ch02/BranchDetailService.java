package testbean.robin.ch02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectbean.BranchDetail;
import testbean.robin.BranchDetailIFaceDao;
import testbean.robin.BranchDetailIFaceService;

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
