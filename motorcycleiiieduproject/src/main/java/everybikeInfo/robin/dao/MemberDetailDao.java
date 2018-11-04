package everybikeInfo.robin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.MemberDetail;

@Repository
public class MemberDetailDao implements MemberDetailIFaceDao{
	@Autowired
	SessionFactory Factory;

	@Override
	public List<MemberDetail> selectone(int memberSerialNum) {
		Session session = Factory.getCurrentSession();
		String hgl = "FROM MemberDetail WHERE memberSerialNum=:memberSerialNum";

		return session.createQuery(hgl).setParameter("memberSerialNum", memberSerialNum).getResultList();
	}


}
