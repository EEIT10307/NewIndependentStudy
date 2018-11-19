package everybikeInfo.robin.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.BikeReviewForJson;
import projectbean.BikeReview;
import projectbean.EveryBikeInfo;
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
	public int save(String orderSerialNum,Integer member,String reviewContent,Double satisfacation,Date reviewTime) {
		Session session1 = Factory.getCurrentSession();
		String hql="from OrderList where orderSerialNum=:orderSerialNum";
		OrderList ck=(OrderList) session1.createQuery(hql).setParameter("orderSerialNum", orderSerialNum).getSingleResult();
		 System.out.println(ck.getLicensePlate().getLicensePlate());//車牌
		 System.out.println(123);
		 Session session2 = Factory.getCurrentSession();
			String hql2="from EveryBikeInfo where licensePlate=:licensePlate";
			EveryBikeInfo ck2=(EveryBikeInfo) session2.createQuery(hql2).setParameter("licensePlate", ck.getLicensePlate().getLicensePlate()).getSingleResult();
		System.out.println("型號"+ck2.getBikeDetail().getIdClassBikeDetail().getBikeModel());//型號
		System.out.println("年分"+ck2.getBikeDetail().getIdClassBikeDetail().getModelYear());//年分
		
		Session session = Factory.getCurrentSession();
		OrderList orderSerialNum0=session.get(OrderList.class, orderSerialNum);
		MemberDetail email=session.get(MemberDetail.class, member);
		
		
		
		
		BikeReview bikeReview=new BikeReview(orderSerialNum0,email,reviewContent,satisfacation,reviewTime,ck2.getBikeDetail().getIdClassBikeDetail().getBikeModel(),ck2.getBikeDetail().getIdClassBikeDetail().getModelYear());
		int updateCount = 0;
//
		session.save(bikeReview);
//
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

	@Override
	public List<BikeReview> selectBikeReview(String bikeModel, String modelYear) {
		Session session = Factory.getCurrentSession();
		String hql="from BikeReview where bikeModel=:bikeModel and modelYear=:modelYear";
		List<BikeReview> xx=session.createQuery(hql).setParameter("bikeModel", bikeModel).setParameter("modelYear", modelYear).getResultList();
		
		return xx;
	}

	@Override
	public List<BikeReviewForJson> BikeReviewForJson(List<BikeReview> BikeReview) {
		 List<BikeReviewForJson> pp=new ArrayList<BikeReviewForJson>();
		 SimpleDateFormat cc=new SimpleDateFormat("yyyy/MM/dd");
		for(BikeReview op:BikeReview) {
			BikeReviewForJson bikeReviewForJson=new BikeReviewForJson();
			String as = cc.format(op.getReviewTime());
			bikeReviewForJson.setReviewContent(op.getReviewContent());//回復
			bikeReviewForJson.setReviewTime(as);//時間
			bikeReviewForJson.setSatisfacation(op.getSatisfacation());//分數
			pp.add(bikeReviewForJson);
		}
		
		
		return pp;
	}
	
}