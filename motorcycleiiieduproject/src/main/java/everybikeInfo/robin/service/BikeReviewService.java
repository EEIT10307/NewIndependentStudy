package everybikeInfo.robin.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cleanbean.BikeReviewForJson;
import everybikeInfo.robin.dao.BikeReviewIFaceDao;
import projectbean.BikeReview;
@Service
@Transactional
public class BikeReviewService implements BikeReviewIFaceService {
	@Autowired
	BikeReviewIFaceDao BikeReviewIFaceDao;

	@Override
	public boolean isDup(String id) {

		return BikeReviewIFaceDao.isDup(id);
	}



	@Override
	public List<BikeReview> getAllMembers() {
		
		return BikeReviewIFaceDao.getAllMembers();
	}

	@Override
	public BikeReview getMember(int pk) {
		
		return BikeReviewIFaceDao.getMember(pk);
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		
		return BikeReviewIFaceDao.deleteMember(bikeModel, modelYear);
	}

	@Override
	public int updateMember(BikeReview mb) {
		
		return BikeReviewIFaceDao.updateMember(mb);
	}

	@Override
	public int save(String orderSerialNum, Integer member, String reviewContent, Double satisfacation, Date reviewTime) {
		// TODO Auto-generated method stub
		return BikeReviewIFaceDao.save(orderSerialNum, member, reviewContent, satisfacation, reviewTime);
	}



	@Override
	public List<BikeReview> selectBikeReview(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return BikeReviewIFaceDao.selectBikeReview(bikeModel, modelYear);
	}



	@Override
	public List<BikeReviewForJson> BikeReviewForJson(List<BikeReview> BikeReview) {
		// TODO Auto-generated method stub
		return BikeReviewIFaceDao.BikeReviewForJson(BikeReview);
	}






	

}
