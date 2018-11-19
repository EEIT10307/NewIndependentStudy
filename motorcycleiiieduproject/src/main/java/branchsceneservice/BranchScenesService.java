package branchsceneservice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import branchscenedao.BranchScenesIFaceDao;
import cleanbean.BranchScenesForJson;
import projectbean.BranchScenes;

@Service
@Transactional
public class BranchScenesService implements BranchScenesIFaceService{
	@Autowired
	BranchScenesIFaceDao branchScenesIFaceDao;
	@Autowired
	SessionFactory factory;
	

	@Override
	public List<BranchScenes> selectBranchScenes(String spotName) {
		// TODO Auto-generated method stub
		return branchScenesIFaceDao.selectBranchScenes(spotName);
	}

	@Override
	public List<BranchScenesForJson> getBranchScenes() {
		return branchScenesIFaceDao.getBranchScenes();
	}

	@Override
	public int saveBranchScenes() throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		return branchScenesIFaceDao.saveBranchScenes();
	}

	@Override
	public List<BranchScenes> showBranchScenes(String spotArea) {
		System.out.println("showBranchScenes");
		return branchScenesIFaceDao.showBranchScenes(spotArea);
	}

	@Override
	public List<BranchScenesForJson> showBranchScenesGson(List<BranchScenes> finalShowAreaSpot) {
		System.out.println("showBranchScenesGson11111");
		return branchScenesIFaceDao.showBranchScenesGson(finalShowAreaSpot);
	}

}
