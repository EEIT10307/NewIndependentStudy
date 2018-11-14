package everybikeInfo.robin.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BikeReview;
import projectbean.IdClassBikeDetail;
import projectbean.MemberDetail;
import projectbean.OrderList;

//評價留言表 
@Repository
public class BikeReviewDao implements BikeReviewIFaceDao {
	@Autowired
	SessionFactory Factory;

	@Override
	public boolean isDup(String id) {
		Session session = Factory.getCurrentSession();
		boolean dup = false;
		String hgl = "FROM BikeReview WHERE id=:id";
		List<BikeReview> list = session.createQuery(hgl).setParameter("id", id).getResultList();
		if (list.size() > 0) {
			dup = true;
		}

		return dup;
	}

	@Override
	public int save(String orderSerialNum,Integer member,String reviewContent,Double satisfacation,Date reviewTime,String bikeModel) {
		Session session = Factory.getCurrentSession();
		OrderList orderSerialNum0=session.get(OrderList.class, orderSerialNum);
		MemberDetail email=session.get(MemberDetail.class, member);
		BikeReview bikeReview=new BikeReview(orderSerialNum0,email,reviewContent,satisfacation,reviewTime,bikeModel);
		int updateCount = 0;

		session.save(bikeReview);

		updateCount = 1;

		return updateCount;
	}

	@Override
	public List<BikeReview> getAllMembers() {
		Session session = Factory.getCurrentSession();

		List<BikeReview> allMembers = null;
		String hgl = "FROM BikeReview";

		allMembers = session.createQuery(hgl).getResultList();

		return allMembers;

	}

	@Override
	public BikeReview getMember(int pk) {
		Session session = Factory.getCurrentSession();
		BikeReview member = null;

		member = session.get(BikeReview.class, pk);

		return member;
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