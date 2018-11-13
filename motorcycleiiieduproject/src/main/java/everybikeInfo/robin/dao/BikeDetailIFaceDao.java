package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.BikeDetailToGson;
import cleanbean.QaBean;
import cleanbean.QaBeanToJson;
import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.QAndA;

@Repository
public interface BikeDetailIFaceDao {
	boolean isDup(String id);

	List<BikeDetail> getAllMembers();

	

	int deleteMember(String bikeModel, String modelYear);

	int updateMember(BikeReview mb);

	int merge(BikeDetail bikeDetail);

	int updateBikeDetai(BikeDetailToGson bikeDetailToGson);
	int insertQA(QaBean qaBean);
	List<QAndA> selectQA();
	List<QaBeanToJson> QaBeanToJson(List<QAndA> QAndA);
	int updateQA(int qAndASerialNum,String ans,String ansquction);
	List<QAndA> selectQAwhere(String BikeModel,String ModelYear);
}
