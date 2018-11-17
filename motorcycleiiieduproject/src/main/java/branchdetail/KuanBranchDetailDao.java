package branchdetail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import branchdetail.BranchDetailIFaceDAO;
import cleanbean.BranchDetailBean;
import projectbean.BranchDetail;
import projectbean.BranchScenes;
import projectbean.EveryBikeMileage;

@Repository
public class KuanBranchDetailDao implements BranchDetailIFaceDAO{
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
	public int saveBranchDetail(BranchDetailBean branchDetail) throws Exception {
		Session session=factory.getCurrentSession();
		int updateCount = 0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("現在時間="+sdf.format(date));
		BranchDetail detail = new BranchDetail();
		detail.setBranchName(branchDetail.getBranchName());
		detail.setBranchArea(branchDetail.getBranchArea());
		detail.setBranchCounty(branchDetail.getBranchCounty());
		detail.setBranchAddress(branchDetail.getBranchAddress());
		detail.setBranchPhone(branchDetail.getBranchPhone());
		detail.setOpeningDay(sdf.parse(sdf.format(date)));
		
		System.out.println("到底");
		session.save(detail);
		
		BranchScenes savebranch = new BranchScenes();
		savebranch.setSpotName(branchDetail.getBranchName());

		updateCount = 1;

		return updateCount;
	}

	@Override
	public List<String> showBranchDetail() {
//		// 建立查詢物件
//		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
//		// 查詢結果的型態
//		CriteriaQuery<BranchDetail> createQuery = builder.createQuery(BranchDetail.class);
//		// 查詢目標
//		Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
//		// 定義查詢型態
//		ParameterExpression<String> branchArea = builder.parameter(String.class);
//		//
//		createQuery.select(fromClass).where(builder.equal(fromClass.get("branchArea"), branchArea));
//		// 查詢物件
//		Query<BranchDetail> queryword = factory.getCurrentSession().createQuery(createQuery);
//		// 定義參數
//		queryword.setParameter(branchArea, branchName);
//		// 取出結果
//		List<BranchDetail> list = queryword.getResultList();
//		
//		return list;
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchDetail> createQuery = buider.createQuery(BranchDetail.class);
		Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
		createQuery.select(fromClass);
		List<BranchDetail> branchlist = factory.getCurrentSession().createQuery(createQuery).getResultList();
		List<String> branchdetaillist = new ArrayList<String>();
		for (BranchDetail loop : branchlist) {
			branchdetaillist.add(loop.getBranchName());
			branchdetaillist.add(loop.getBranchArea());
			branchdetaillist.add(loop.getBranchPhone());
			branchdetaillist.add(loop.getBranchCounty());
			branchdetaillist.add(loop.getBranchAddress());
		}
		return branchdetaillist;
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

	@Override
	public List<BranchDetail> showAllBranchDetail() {
		CriteriaBuilder builder = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchDetail> createQuery = builder.createQuery(BranchDetail.class);
		Root<BranchDetail> fromClass = createQuery.from(BranchDetail.class);
		createQuery.orderBy(builder.asc(fromClass.get("branchSerialNum")));
		createQuery.select(fromClass);
//		.where(builder.greaterThanOrEqualTo(fromClass.get("currentMileage"), fromClass.get("maintenanceItem").get("requiredMileage")));
		Query<BranchDetail> queryword = factory.getCurrentSession().createQuery(createQuery);	
//		queryword.setParameter(branchName, shopName);
		List<BranchDetail> list = queryword.getResultList();
		return list;

	}
}
