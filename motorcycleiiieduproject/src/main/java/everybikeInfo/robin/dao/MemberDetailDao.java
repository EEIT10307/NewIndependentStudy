package everybikeInfo.robin.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.MemberDetailALLForJson;
import everybikeInfo.robin.service.MemberDetailIFaceService;
import projectbean.MemberDetail;
import projectbean.OrderList;

@Repository
public class MemberDetailDao implements MemberDetailIFaceDao{
	@Autowired
	SessionFactory Factory;
	@Autowired
	MemberDetailIFaceService memberDetailIFaceService;
	@Override
	public List<MemberDetail> selectone(int memberSerialNum) {
		Session session = Factory.getCurrentSession();
		String hgl = "FROM MemberDetail WHERE memberSerialNum=:memberSerialNum";

		return session.createQuery(hgl).setParameter("memberSerialNum", memberSerialNum).getResultList();
	}

	@Override
	public List<MemberDetail> selectMemberDetailAll() {
		Session session = Factory.getCurrentSession();
		String hgl = "FROM MemberDetail";
		return session.createQuery(hgl).getResultList();
		
	}

	@Override
	public List<MemberDetailALLForJson> memberDetailALLForJson(List<MemberDetail> memberDetail) {
		List<MemberDetailALLForJson> memberDetailALLForJson=new ArrayList<MemberDetailALLForJson>();
		SimpleDateFormat Format =new SimpleDateFormat("yyyy/MM/dd");
		for(MemberDetail op:memberDetail) {
			String birthday=Format.format(op.getBirthday());
			String signinDate=Format.format(op.getSigninDate());
			memberDetailALLForJson.add(new MemberDetailALLForJson(op.getEmail(),op.getPhone(),birthday,op.getGender(),op.getAddress(),signinDate,memberDetailIFaceService.selectOrderList(op.getPhone())));
		}
		return memberDetailALLForJson;
	}

	@Override
	public Integer selectOrderList(String phone) {
		Session session = Factory.getCurrentSession();
		String hgl = "select sum(orderTotalPrice)as a from OrderList where phone=:phone";
		return (Integer)session.createSQLQuery(hgl).setParameter("phone",phone).uniqueResult();
	}
	

}
