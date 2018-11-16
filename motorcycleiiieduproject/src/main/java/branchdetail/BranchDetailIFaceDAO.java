package branchdetail;

import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.BranchDetailBean;
import projectbean.BranchDetail;

@Repository
public interface BranchDetailIFaceDAO {	

	List<BranchDetail> selectBranchDetail(String Phone);

	List<BranchDetail> getBranchDetail();

	BranchDetail getDetail(int branchSerialNum);

	int deleteDetail(String branchArea, String branchPhone);

	int updateDetail(BranchDetail bd);

	int saveBranchDetail(BranchDetailBean branchDetail) throws  Exception;
	
	List<BranchDetail> showBranchDetail(String branchName);
	List<BranchDetailToGson> forGsonConvert(List<BranchDetail> finalBranchDetail);
}
