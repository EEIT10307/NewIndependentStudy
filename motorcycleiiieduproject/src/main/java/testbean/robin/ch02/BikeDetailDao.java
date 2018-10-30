package testbean.robin.ch02;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BikeDetail;
import projectbean.BikeReview;
import projectbean.IdClassBikeDetail;
import testbean.robin.BikeDetailIFaceDAO;
@Repository
public class BikeDetailDao implements BikeDetailIFaceDAO{
	@Autowired
	SessionFactory Factory;

	@Override
	public boolean isDup(String id) {
		Session session = Factory.getCurrentSession();
		boolean dup = false;
		String hgl = "FROM BikeDetail WHERE id=:id";
		List<BikeDetail> list = session.createQuery(hgl).setParameter("id", id).getResultList();
		if (list.size() > 0) {
			dup = true;
		}

		return dup;
	}

	@Override
	public int save(BikeDetail bikeDetail) {
		Session session = Factory.getCurrentSession();
	
		int updateCount = 0;

		session.save(bikeDetail);

		updateCount = 1;

		return updateCount;
	}

	@Override
	public List<BikeDetail> getAllMembers() {
		Session session = Factory.getCurrentSession();

		List<BikeDetail> allMembers = null;
		String hgl = "FROM BikeDetail";

		allMembers = session.createQuery(hgl).getResultList();

		return allMembers;

	}

	

	@Override
	public int deleteMember(String bikeModel, String modelYear) {

		Session session = Factory.getCurrentSession();
		int updateCount = 0;
		// 複合主鍵(bikeModel型號,modelYear年分) 一次輸入兩個
		IdClassBikeDetail idClassBikeDetail = new IdClassBikeDetail(bikeModel, modelYear);
		session.delete(idClassBikeDetail);
		updateCount++;

		return updateCount;
	}

	@Override
	public int updateMember(BikeReview mb) {

		Session session = Factory.getCurrentSession();

		int updateCount = 0;

		session.saveOrUpdate(mb);

		updateCount++;

		return updateCount;
	}

}
