package branchdetailservice;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import branchdetaildao.BranchDetailIFaceDAO;
import branchdetaildao.BranchDetailToGson;
import projectbean.BranchDetail;;

@Service
@Transactional
public class BranchDetailService implements BranchDetailIFaceService{
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
	public int saveBranchDetail(int branchSerialNum, String branchName, String branchArea,
			String branchCounty, String branchAddress, String branchPhone, java.util.Date openingDay) {
		
		BranchDetail branchDetail = new BranchDetail();
		
		return branchDetailIFaceDAO.saveBranchDetail(branchDetail);
	}

	@Override
	public List<BranchDetail> showBranchDetail(String branchName) {
		// TODO Auto-generated method stub
		return branchDetailIFaceDAO.showBranchDetail(branchName);
	}

	@Override
	public List<BranchDetailToGson> forGsonConvert(List<BranchDetail> finalBranchDetail) {
		// TODO Auto-generated method stub
		return branchDetailIFaceDAO.forGsonConvert(finalBranchDetail);
	}

	
//	public List<BranchDetail> select(BranchDetail bd) {
//		List<BranchDetail> result = null;
//		if(bd!=null && bd.getBranchSerialNum()!=0) {
//			BranchDetail temp = branchDetailIFaceDAO.select(bd.getBranchSerialNum());
//			if(temp!=null) {
//				result = new ArrayList<BranchDetail>();
//				result.add(temp);
//			}
//		} else {
//			result = branchDetailIFaceDAO.select(); 
//		}
//		return result;
//	}
//	public BranchDetail insert(BranchDetail bd) {
//		BranchDetail result = null;
//		if(bd!=null) {
//			result = branchDetailIFaceDAO.insert(bd);
//		}
//		return result;
//	}
//	public BranchDetail update(BranchDetail bd) {
//		BranchDetail result = null;
//		if(bd!=null) {
//			result = branchDetailIFaceDAO.update(bd.getBranchSerialNum(), bd.getBranchName(), bd.getBranchArea(),
//					bd.getBranchCounty(), bd.getBranchAddress(), bd.getBranchPhone(), bd.getOpeningDay());
//		}
//		return result;
//	}
//	public boolean delete(BranchDetail bd) {
//		boolean result = false;
//		if(bd!=null) {
//			result = branchDetailIFaceDAO.delete(bd.getBranchSerialNum());
//		}
//		return result;
//	}
}
