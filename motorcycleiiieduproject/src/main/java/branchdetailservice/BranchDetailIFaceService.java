package branchdetailservice;

import java.util.List;

import branchdetaildao.BranchDetailToGson;
import projectbean.BranchDetail;

public interface BranchDetailIFaceService {
	
	List<BranchDetail> selectBranchDetail(String Phone);

	List<BranchDetail> getBranchDetail();

	BranchDetail getDetail(int branchSerialNum);

	int deleteDetail(String branchArea, String branchPhone);

	int updateDetail(BranchDetail bd);

	int saveBranchDetail(int branchSerialNum, String branchName, String branchArea,
			String branchCounty, String branchAddress, String branchPhone, java.util.Date openingDay);
	
	List<BranchDetail> showBranchDetail(String branchName);
	List<BranchDetailToGson> forGsonConvert(List<BranchDetail> finalBranchDetail);
//	public BranchDetail insert(BranchDetail bd);
//
//	public List<BranchDetail> select();
//	
//	public BranchDetail select(int branchSerialNum);
//
//	public boolean delete(int branchSerialNum);
//
//	public BranchDetail update(int branchSerialNum, String branchName, String branchArea,
//			String branchCounty, String branchAddress, String branchPhone, java.util.Date openingDay);
}
