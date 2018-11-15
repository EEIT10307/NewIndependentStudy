package everybikeInfo.robin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.AcceStockBean;
import everybikeInfo.robin.service.AcceStockIFaceService;
import projectbean.AcceSerialNum;
import projectbean.AcceStock;
import projectbean.BranchDetail;

@Repository
public class AcceStockDao implements AcceStockIFaceDao {
	
	@Autowired
	SessionFactory Factory;
	@Autowired
	AcceStockIFaceService acceStockIFaceService;

	@Override // 新增配件庫存
	public int insertAcceStock(AcceStockBean acceStockBean) {
		Session session = Factory.getCurrentSession();
		int count = 0;
		String AcceName = acceStockBean.getAcceName();// 配件品名子
		String BranchName = acceStockBean.getBranchName();// 分店
		Integer AcceNum = acceStockBean.getAcceNum();// 數量
		String AcceType = acceStockBean.getAcceType();// 種類
		Integer AcceePrice = acceStockBean.getAcceePrice();// 配件價格
		BranchDetail as = acceStockIFaceService.selectoneBikeDetail(BranchName);//分店流水號
		Integer one = as.getBranchSerialNum();
		String two = String.valueOf(one);
		AcceSerialNum Acc = acceStockIFaceService.selectAcceSerialNum(AcceType);
		String Accn = Acc.getAcceSerialNum();
		Long tt = acceStockIFaceService.countAcceStockx();
		String QQ=String.valueOf(tt+1);
		BranchDetail branchDetail = session.get(BranchDetail.class, one);
		AcceSerialNum acceSerialNum = session.get(AcceSerialNum.class, Accn);
		
	
		AcceStock AcceStock = new AcceStock(QQ+"_"+two + Accn, AcceName, branchDetail, AcceNum,
				acceSerialNum, AcceePrice);
		session.save(AcceStock);
		count++;
		return count;
	}

	@Override // 取得分店一筆資料
	public BranchDetail selectoneBikeDetail(String BranchName) {
		Session session = Factory.getCurrentSession();

		String hgl = "FROM BranchDetail WHERE branchName=:branchName";
		BranchDetail Qoo = (BranchDetail) session.createQuery(hgl).setParameter("branchName", BranchName)
				.getSingleResult();

		return Qoo;
	}

	@Override // 取得配件種類一筆資料
	public AcceSerialNum selectAcceSerialNum(String AcceType) {
		Session session = Factory.getCurrentSession();

		String hgl = "FROM AcceSerialNum WHERE AcceType=:AcceType";
		AcceSerialNum Qoo = (AcceSerialNum) session.createQuery(hgl).setParameter("AcceType", AcceType)
				.getSingleResult();

		return Qoo;
	}

	@Override // 取得配件種類一筆資料
	public List<AcceSerialNum> allAcceSerialNum() {
		Session session = Factory.getCurrentSession();

		String hgl = "FROM AcceSerialNum ";
		List<AcceSerialNum> Qoo = session.createQuery(hgl).getResultList();

		return Qoo;
	}

	@Override
	public BranchDetail selectBranchBranchSerialNum(int branchSerialNum) {
		Session session = Factory.getCurrentSession();

		String hgl = "FROM BranchDetail WHERE branchSerialNum=:branchSerialNum";
		BranchDetail Qoo = (BranchDetail) session.createQuery(hgl).setParameter("branchSerialNum", branchSerialNum).getSingleResult();


		return Qoo;
	}

	@Override
	public Long countAcceStockx() {
		Session session = Factory.getCurrentSession();

		String hgl = "select count(*) from AcceStock";
		 Query Qoo = session.createQuery(hgl);
		 Long count = (Long)Qoo.uniqueResult();
		 
		return count;
	}


}
