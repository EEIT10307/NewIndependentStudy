package testbean.robin.ch02;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import testbean.robin.EveryBikeInfoIFaceDao;

@Repository
public class EveryBikeInfoDao implements EveryBikeInfoIFaceDao {
	@Autowired
	SessionFactory Factory;

	@Override
	public boolean isDup(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EveryBikeInfo> getAllMembers() {
		Session session=Factory.getCurrentSession();
		String hql = "From EveryBikeInfo";
		
		List<EveryBikeInfo> allMembers = null;
		allMembers=session.createQuery(hql).getResultList();
		
		return allMembers;
	}

	@Override
	public  EveryBikeInfo getMember(int pk) {
		
		return null ;
	}

	@Override
	public int deleteMember(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(EveryBikeInfo mb) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(EveryBikeInfo everyBikeInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
