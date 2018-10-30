package testbean.robin.ch02;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projectbean.BikeDetail;
import projectbean.BikeReview;
import testbean.robin.BikeDetailIFaceDAO;
import testbean.robin.BikeDetailIFaceService;

@Service
@Transactional
public class BikeDetailService implements BikeDetailIFaceService{
@Autowired
BikeDetailIFaceDAO bikeDetailIFaceDAO;
	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.isDup(id);
	}

	@Override
	public List<BikeDetail> getAllMembers() {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.getAllMembers();
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.deleteMember(bikeModel, modelYear);
	}

	@Override
	public int updateMember(BikeReview mb) {
		// TODO Auto-generated method stub
		return bikeDetailIFaceDAO.updateMember(mb);
	}

	@Override
	public int save(BikeDetail bikeDetail) {
		Date now=new Date();
		bikeDetail.setOnSheftTime(now);
		return bikeDetailIFaceDAO.save(bikeDetail);
	}

}
