package testbean.robin.ch02;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maintenance.EveryBikeInfoToGson;
import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.IdClassBikeDetail;
import testbean.robin.EveryBikeInfoIFaceDao;
import testbean.robin.EveryBikeInfoIFaceService;

@Service
@Transactional
public class EveryBikeInfoService implements EveryBikeInfoIFaceService {
	@Autowired
	EveryBikeInfoIFaceDao everyBikeInfoIFaceDao;
	@Autowired
	SessionFactory factory;
	@Override
	public List<EveryBikeInfo> selectModelAll(String Year) {
		// TODO Auto-generated method stub
		return everyBikeInfoIFaceDao.selectModelAll(Year);
	}

	@Override
	public List<EveryBikeInfo> getAllMembers() {
		// TODO Auto-generated method stub
		return everyBikeInfoIFaceDao.getAllMembers();
	}

	@Override
	public EveryBikeInfo getMember(int pk) {
		// TODO Auto-generated method stub
		return null;
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
	public int save(String licensePlate, int branchName, String bikeModel, String modelYear) {
		Session session = factory.getCurrentSession();
		BranchDetail branchDetail=session.get(BranchDetail.class, branchName);//分店 需要分店的流水號 加入機車 建構子
		EveryBikeInfo everyBikeInfo = new EveryBikeInfo(licensePlate, 0.0, false,branchDetail);// 機車個別資訊 新增
		IdClassBikeDetail idClassBikeDetail = new IdClassBikeDetail(bikeModel, modelYear);// 型號跟年份 複合主鍵
		BikeDetail bikeDetail=new BikeDetail();
		bikeDetail.setIdClassBikeDetail(idClassBikeDetail);
		everyBikeInfo.setBikeDetail(bikeDetail);
		return everyBikeInfoIFaceDao.save(everyBikeInfo);
	}

	@Override
	public List<EveryBikeInfo> showAllEveryBikeInfo(String shopName) {
		// TODO Auto-generated method stub
		return everyBikeInfoIFaceDao.showAllEveryBikeInfo(shopName);
	}
 
	@Override
	public List<EveryBikeInfoToGson> forGsonConvert(List<EveryBikeInfo> finalEveryBikeInfo) {
		// TODO Auto-generated method stub
		return everyBikeInfoIFaceDao.forGsonConvert(finalEveryBikeInfo);
	}
	

}
