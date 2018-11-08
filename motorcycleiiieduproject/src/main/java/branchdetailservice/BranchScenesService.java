package branchdetailservice;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import branchdetaildao.BranchScenesDao;
import branchdetaildao.BranchScenesIFaceDao;
import branchdetaildao.BranchScenesToGson;
import projectbean.BranchDetail;
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
	public List<String> getBranchScenes() {
		// TODO Auto-generated method stub
		return branchScenesIFaceDao.getBranchScenes();
	}

	@Override
	public BranchScenes getScenes(int branchDetailSerialNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteScenes(String spotAddress, String spotPhoto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateScenes(BranchScenes bs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveBranchScenes(Integer branchDetailSerialNum, BranchDetail branchName, String spotName,
			String spotAddress, String spotPhoto) {
		
		BranchScenes branchScenes = new BranchScenes();
		
		return branchScenesIFaceDao.saveBranchScenes(branchScenes);
	}

	@Override
	public List<BranchScenes> showBranchScenes(BranchDetail branchName) {
		// TODO Auto-generated method stub
		return branchScenesIFaceDao.showBranchScenes(branchName);
	}

	@Override
	public List<BranchScenesToGson> forGsonConvert(List<BranchScenes> finalBranchScenes) {
		// TODO Auto-generated method stub
		return branchScenesIFaceDao.forGsonConvert(finalBranchScenes);
	}

}
