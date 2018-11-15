package branchdetail;

import java.text.ParseException;
import java.util.List;

import cleanbean.BranchDetailBean;
import projectbean.BranchDetail;

public interface BranchDetailIFaceService {
	
	List<BranchDetail> selectBranchDetail(String Phone);

	List<BranchDetail> getBranchDetail();

	BranchDetail getDetail(int branchSerialNum);

	int deleteDetail(String branchArea, String branchPhone);

	int updateDetail(BranchDetail bd);

	int saveBranchDetail(BranchDetailBean branchDetail) throws  Exception;
	
	List<String> showBranchDetail();
	List<BranchDetail> showAllBranchDetail();
	List<BranchDetailToGson> forGsonConvert(List<BranchDetail> finalBranchDetail);
}
