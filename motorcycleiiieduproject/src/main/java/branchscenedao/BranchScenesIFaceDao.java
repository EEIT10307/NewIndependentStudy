package branchscenedao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.BranchScenesForJson;
import projectbean.BranchDetail;
import projectbean.BranchScenes;

@Repository
public interface BranchScenesIFaceDao {
	
	List<BranchScenes> selectBranchScenes(String spotName);
	
	List<BranchScenesForJson> getBranchScenes();
	
	List<BranchScenes> getScenes();

	int saveBranchScenes() throws MalformedURLException, IOException;
	
	List<BranchScenes> showBranchScenes(String spotArea);
	
	List<BranchScenesForJson> showBranchScenesGson(List<BranchScenes> finalShowAreaSpot);
}
