package branchsceneservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import cleanbean.BranchScenesForJson;
import projectbean.BranchDetail;
import projectbean.BranchScenes;

public interface BranchScenesIFaceService {
	List<BranchScenes> selectBranchScenes(String spotName);
	
	List<BranchScenesForJson> getBranchScenes();

	int saveBranchScenes() throws MalformedURLException, IOException;
	
	List<BranchScenes> showBranchScenes(String spotArea);
	
	List<BranchScenesForJson> showBranchScenesGson(List<BranchScenes> finalShowAreaSpot);
}
