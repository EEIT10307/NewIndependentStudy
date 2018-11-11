package branchdetailservice;

import java.util.List;

import branchdetaildao.BranchScenesToGson;
import projectbean.BranchDetail;
import projectbean.BranchScenes;

public interface BranchScenesIFaceService {
	List<BranchScenes> selectBranchScenes(String spotName);
	
	List<String> getBranchScenes();
	
	BranchScenes getScenes(int branchDetailSerialNum);
	
	int deleteScenes(String spotAddress, String spotPhoto);

	int updateScenes(BranchScenes bs);

	int saveBranchScenes(Integer branchDetailSerialNum, BranchDetail branchName, String spotName,
			String spotAddress, String spotPhoto);
	
	List<BranchScenes> showBranchScenes(BranchDetail branchName);
	List<BranchScenesToGson> forGsonConvert(List<BranchScenes> finalBranchScenes);
}
