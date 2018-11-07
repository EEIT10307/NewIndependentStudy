package everybikeInfo.robin.service;

import java.util.List;

import cleanbean.BikeDetailAndEveryBikeInfo;

import cleanbean.BikeDetailToGson;
import cleanbean.QaBean;
import cleanbean.QaBeanToJson;
import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.QAndA;

public interface BikeDetailIFaceService {
	boolean isDup(String id);

	List<BikeDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int save(BikeDetailAndEveryBikeInfo[] bikeDetailAndEveryBikeInfo);
	int updateBikeDetai(BikeDetailToGson bikeDetailToGson);
	int insertQA(QaBean qaBean);
	List<QAndA> selectQA();
	List<QaBeanToJson> QaBeanToJson(List<QAndA> QAndA);
}
