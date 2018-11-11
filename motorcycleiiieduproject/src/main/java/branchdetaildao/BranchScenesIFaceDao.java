package branchdetaildao;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.BranchDetail;
import projectbean.BranchScenes;

@Repository
public interface BranchScenesIFaceDao {
	
	List<BranchScenes> selectBranchScenes(String spotName);
	
	List<String> getBranchScenes();
	
	BranchScenes getScenes(int branchDetailSerialNum);
	
	int deleteScenes(String spotAddress, String spotPhoto);

	int updateScenes(BranchScenes bs);

	int saveBranchScenes(BranchScenes branchScenes);
	
	List<BranchScenes> showBranchScenes(BranchDetail branchName);
	List<BranchScenesToGson> forGsonConvert(List<BranchScenes> finalBranchScenes);
}
