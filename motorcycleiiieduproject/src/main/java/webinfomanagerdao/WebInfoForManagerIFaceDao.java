package webinfomanagerdao;

import java.util.List;

import projectbean.WebInformationForManager;
public interface WebInfoForManagerIFaceDao {
	List<WebInformationForManager> insertWebInfoForManager(List<WebInformationForManager> webInformationForManager);
	List<WebInformationForManager> updateWebInfoForManager(List<WebInformationForManager> webInformationForManager);
	List<WebInformationForManager> insertorupdateWebInfoForManager(List<WebInformationForManager> webInformationForManager);
	List<WebInformationForManager> getAllWebInfoManager();
}
