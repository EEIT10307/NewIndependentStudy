package branchsceneservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import projectbean.BranchDetail;
import projectbean.BranchScenes;

public interface BranchScenesIFaceService {
	List<BranchScenes> selectBranchScenes(String spotName);
	
	List<String> getBranchScenes();
	
	BranchScenes getScenes(int branchDetailSerialNum);
	
	int deleteScenes(String spotAddress, String spotPhoto);

	int updateScenes(BranchScenes bs);

	int saveBranchScenes() throws MalformedURLException, IOException;
	
	List<BranchScenes> showBranchScenes(BranchDetail branchName);
}
