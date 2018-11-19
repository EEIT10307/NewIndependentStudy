package branchdetail;

import java.text.ParseException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cleanbean.BranchDetailBean;
import projectbean.BranchDetail;;

@Service
@Transactional
public class KuanBranchDetailService implements BranchDetailIFaceService{
	@Autowired
	BranchDetailIFaceDAO branchDetailIFaceDAO;
	@Autowired
	SessionFactory factory;

	@Override
	public List<BranchDetail> selectBranchDetail(String Phone) {
		// TODO Auto-generated method stub
		return branchDetailIFaceDAO.selectBranchDetail(Phone);
	}

	@Override
	public List<BranchDetail> getBranchDetail() {
		// TODO Auto-generated method stub
		return branchDetailIFaceDAO.getBranchDetail();
	}

	@Override
	public BranchDetail getDetail(int branchSerialNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteDetail(String branchArea, String branchPhone) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDetail(BranchDetail bd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveBranchDetail(BranchDetailBean branchDetail) throws Exception {

		return branchDetailIFaceDAO.saveBranchDetail(branchDetail);		
}

	@Override
	public List<String> showBranchDetail() {
		// TODO Auto-generated method stub
		return branchDetailIFaceDAO.showBranchDetail();
	}

	@Override
	public List<BranchDetailToGson> forGsonConvert(List<BranchDetail> finalBranchDetail) {
		// TODO Auto-generated method stub
		return branchDetailIFaceDAO.forGsonConvert(finalBranchDetail);
	}

	@Override
	public List<BranchDetail> showAllBranchDetail() {
		return branchDetailIFaceDAO.showAllBranchDetail();

	}
}
