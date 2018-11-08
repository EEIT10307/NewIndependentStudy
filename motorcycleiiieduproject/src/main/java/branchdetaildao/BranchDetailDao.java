package branchdetaildao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BranchDetail;
import branchdetaildao.BranchDetailIFaceDAO;

@Repository
public class BranchDetailDao implements BranchDetailIFaceDAO{
	@Autowired
	SessionFactory factory;
	
	@Override
	public List<BranchDetail> selectBranchDetail(String branchDetail_branchSerialNum) {
		Session session=factory.getCurrentSession();
		String hgl = "FROM BranchDetail WHERE branchdetail0_.branchSerialNum=:branchSerialNum";
		@SuppressWarnings("unchecked")
		List<BranchDetail> list = session.createQuery(hgl).setParameter("branchDetail_branchSerialNum", branchDetail_branchSerialNum).getResultList();
		
		return list;
	}
	
	@Override
	public List<BranchDetail> getBranchDetail() {
		Session session=factory.getCurrentSession();
		String hql = "From BranchDetail";
		
		List<BranchDetail> branchDetail = null;
		branchDetail=session.createQuery(hql).getResultList();
		
		return branchDetail;
	}
	
	@Override
	public  BranchDetail getDetail(int branchSerialNum) {		
		return null ;
	}
	
	@Override
	public int deleteDetail(String branchArea, String branchPhone) {
		return 0;
	}
	
	@Override
	public int updateDetail(BranchDetail mb) {
		return 0; 
	}
	
	@Override
	public int saveBranchDetail(BranchDetail branchDetail) {
		Session session=factory.getCurrentSession();
		int updateCount = 0;

		session.save(branchDetail);

		updateCount = 1;

		return updateCount;
	}

	@Override
	public List<BranchDetail> showBranchDetail(String branchName) {
		// 建立查詢物件
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		// 查詢結果的型態
		CriteriaQuery<BranchDetail> createQuery = builder.createQuery(BranchDetail.class);
		// 查詢目標
		Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
		// 定義查詢型態
		ParameterExpression<String> branchArea = builder.parameter(String.class);
		//
		createQuery.select(fromClass).where(builder.equal(fromClass.get("branchArea"), branchArea));
		// 查詢物件
		Query<BranchDetail> queryword = factory.getCurrentSession().createQuery(createQuery);
		// 定義參數
		queryword.setParameter(branchArea, branchName);
		// 取出結果
		List<BranchDetail> list = queryword.getResultList();
		
		return list;
	}

	@Override
	public List<BranchDetailToGson> forGsonConvert(List<BranchDetail> finalBranchDetail) {
		ArrayList<BranchDetailToGson> branchDetailToGson = new ArrayList<BranchDetailToGson>();
		for(BranchDetail loop:finalBranchDetail) {
			branchDetailToGson.add(new BranchDetailToGson(loop.getBranchSerialNum(), loop.getBranchName(),
					loop.getBranchArea(), loop.getBranchCounty(), loop.getBranchAddress(), loop.getBranchPhone(),
					loop.getOpeningDay()));
		}
		return branchDetailToGson;
	}
	
	
	
//	public Session getSession() {
//		return this.factory.getCurrentSession();
//	}
//
//	@Override
//	public BranchDetail insert(BranchDetail bd) {
//		if(bd!=null) {
//			BranchDetail temp = this.getSession().get(BranchDetail.class, bd.getBranchSerialNum());
//			if(temp==null) {
//				((BranchDetailIFaceDAO) this.getSession()).insert(bd);
//				return bd;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public List<BranchDetail> select() {
//		return this.getSession().createQuery(
//				"from projectbean.BranchDetail", BranchDetail.class).setMaxResults(50).list();
//	}
//
//	@Override
//	public boolean delete(int branchSerialNum) {
//		BranchDetail temp = this.getSession().get(BranchDetail.class, branchSerialNum);
//		if(temp!=null) {
//			this.getSession().delete(temp);
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public BranchDetail update(int branchSerialNum, String branchName, String branchArea, String branchCounty,
//			String branchAddress, String branchPhone, Date openingDay) {
//		BranchDetail temp = this.getSession().get(BranchDetail.class, branchSerialNum);
//		if(temp!=null) {
//			temp.setBranchName(branchName);
//			temp.setBranchArea(branchArea);
//			temp.setBranchCounty(branchCounty);
//			temp.setBranchAddress(branchAddress);
//			temp.setBranchPhone(branchPhone);
//			temp.setOpeningDay(openingDay);
//		}
//		return temp;
//	}
//
//	@Override
//	public BranchDetail select(int branchSerialNum) {
//		return this.getSession().get(BranchDetail.class, branchSerialNum);
//	}
}
