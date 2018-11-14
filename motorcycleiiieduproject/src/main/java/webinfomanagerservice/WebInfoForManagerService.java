package webinfomanagerservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectbean.WebInformationForManager;
import webinfomanagerdao.WebInfoForManagerIFaceDao;

@Service
@Transactional
public class WebInfoForManagerService implements WebInfoForManagerIFaceService {
	
	@Autowired
	WebInfoForManagerIFaceDao webInfoForManagerIFaceDao;

	@Override
	public List<WebInformationForManager> insertWebInfoForManager(List<WebInformationForManager> webInformationForManager) {
		
		return webInfoForManagerIFaceDao.insertWebInfoForManager(webInformationForManager);
	}
	
	@Override
	public List<WebInformationForManager> updateWebInfoForManager(List<WebInformationForManager> webInformationForManager) {
		
		return  webInfoForManagerIFaceDao.updateWebInfoForManager(webInformationForManager);
	}
	
	@Override
	public List<WebInformationForManager> insertorupdateWebInfoForManager(List<WebInformationForManager> webInformationForManager) {
		
		return  webInfoForManagerIFaceDao.insertorupdateWebInfoForManager(webInformationForManager);
	}
	
	@Override
	public List<WebInformationForManager> getAllWebInfoManager() {
		// TODO Auto-generated method stub
		return  webInfoForManagerIFaceDao.getAllWebInfoManager();
	}

}
