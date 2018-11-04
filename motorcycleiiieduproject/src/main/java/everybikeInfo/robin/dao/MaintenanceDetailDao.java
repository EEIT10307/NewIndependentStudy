package everybikeInfo.robin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.MaintenanceDetail;
@Repository
public class MaintenanceDetailDao implements MaintenanceDetailIFaceDao{
		@Autowired
		SessionFactory	Factory; 
	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaintenanceDetail> getAllMembers() {
		Session session=Factory.getCurrentSession();
		String hql = "From MaintenanceDetail";
		List<MaintenanceDetail> member=null;
		member=session.createQuery(hql).getResultList();
		return member;
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(BikeReview mb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(BikeDetail bikeDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

}
