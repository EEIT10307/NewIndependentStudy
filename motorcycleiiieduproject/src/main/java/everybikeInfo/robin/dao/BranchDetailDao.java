package everybikeInfo.robin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BranchDetail;
@Repository
public class BranchDetailDao implements BranchDetailIFaceDao {
	@Autowired
	SessionFactory Factory;

	@Override
	public List<BranchDetail> getAllMembers() {
		Session session=Factory.getCurrentSession();
		String hql = "From BranchDetail";
		
		List<BranchDetail> allMembers = null;
		allMembers=session.createQuery(hql).getResultList();
		
		return allMembers;
	}

}
