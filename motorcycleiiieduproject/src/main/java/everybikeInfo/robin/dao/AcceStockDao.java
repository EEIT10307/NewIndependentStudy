package everybikeInfo.robin.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cleanbean.AcceStockBean;
import projectbean.AcceSerialNum;
import projectbean.AcceStock;
import projectbean.BranchDetail;
@Repository
public class AcceStockDao implements AcceStockIFaceDao {
	@Autowired
	SessionFactory Factory; 
	@Autowired
	AcceStockIFaceDao acceStockIFaceDao;
	@Override//新增配件庫存
	public int insertAcceStock(AcceStockBean acceStockBean) {
		Session session = Factory.getCurrentSession();
		int count=0;
		String AcceName = acceStockBean.getAcceName();//配件品名子
		String BranchName = acceStockBean.getBranchName();//分店
		Integer AcceNum = acceStockBean.getAcceNum();//數量
		String AcceType = acceStockBean.getAcceType();//種類
		Integer AcceePrice = acceStockBean.getAcceePrice();//配件價格
		
		BranchDetail pp = acceStockIFaceDao.selectoneBikeDetail(BranchName);//讀取分店一筆資料
		Integer BranchSerialNum = pp.getBranchSerialNum();//分店流水號
		String BranchSerialstr=String.valueOf(BranchSerialNum);//轉成文字
		
		AcceSerialNum QQ=acceStockIFaceDao.selectAcceSerialNum(AcceType);//配件流水號
		String AcceSerialNum=QQ.getAcceSerialNum();//配件的流水號
		BranchDetail Branch=new BranchDetail();
		Branch.setBranchName(BranchName);
		AcceSerialNum Acc=new AcceSerialNum();
		Acc.setAcceType(AcceType);
		AcceStock AcceStock=new AcceStock(BranchSerialstr+AcceSerialNum,AcceName,Branch,AcceNum,Acc,AcceePrice);
		session.save(AcceStock);
		count++;
		return count;
	}

	@Override//取得分店一筆資料
	public BranchDetail selectoneBikeDetail(String BranchName) {
		Session session = Factory.getCurrentSession();

	
		String hgl = "FROM BranchDetail WHERE BranchName=:BranchName";
		BranchDetail Qoo = (BranchDetail) session.createQuery(hgl).setParameter("BranchName", BranchName).getSingleResult();
		
		

		return Qoo;
	}

	@Override//取得配件種類一筆資料
	public AcceSerialNum selectAcceSerialNum(String AcceType) {
		Session session = Factory.getCurrentSession();

		
		String hgl = "FROM AcceSerialNum WHERE AcceType=:AcceType";
		AcceSerialNum Qoo = (AcceSerialNum) session.createQuery(hgl).setParameter("AcceType", AcceType).getSingleResult();
		
		return Qoo;
	}
	@Override//取得配件種類一筆資料
	public List<AcceSerialNum> allAcceSerialNum() {
		Session session = Factory.getCurrentSession();

		
		String hgl = "FROM AcceSerialNum ";
		 List<AcceSerialNum>Qoo = session.createQuery(hgl).getResultList();
		
		return Qoo;
	}
}
