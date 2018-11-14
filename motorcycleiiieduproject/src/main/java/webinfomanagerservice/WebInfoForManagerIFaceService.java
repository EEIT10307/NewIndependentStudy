package webinfomanagerservice;

import java.util.List;

import projectbean.WebInformationForManager;

public interface WebInfoForManagerIFaceService {
	List<WebInformationForManager> insertWebInfoForManager(List<WebInformationForManager> webInformationForManager);
	List<WebInformationForManager> updateWebInfoForManager(List<WebInformationForManager> webInformationForManager);
	List<WebInformationForManager> insertorupdateWebInfoForManager(List<WebInformationForManager> webInformationForManager);
//	int insertWebSrc(WebInformationForManager webInformationForManager);
//	int updateWebSrc(WebInformationForManager webInformationForManager);
	List<WebInformationForManager> getAllWebInfoManager();
}
