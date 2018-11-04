package everybikeInfo.robin.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cleanbean.BikeDetailToGson;
import everybikeInfo.robin.dao.EveryBikeInfoIFaceDao;
import maintenance.EveryBikeInfoToGson;
import projectbean.BikeDetail;
import projectbean.BranchDetail;
import projectbean.EveryBikeInfo;
import projectbean.IdClassBikeDetail;

@Service
@Transactional
public class EveryBikeInfoService implements EveryBikeInfoIFaceService {
	@Autowired
	EveryBikeInfoIFaceDao everyBikeInfoIFaceDao;
	@Autowired
	EveryBikeMileageIFaceService everyBikeMileageIFaceService;
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
	public boolean getMemberOne(String LicensePlate) {
		
		
		return everyBikeInfoIFaceDao.getMemberOne(LicensePlate);
	} 

	@Override
	public BikeDetail selectbikeModelmodelYear(String bikeModel, String modelYear) {
		// TODO Auto-generated method stub
		return everyBikeInfoIFaceDao.selectbikeModelmodelYear(bikeModel, modelYear);
	}

	@Override
	public boolean checkbikeModelmodelYear(String bikeModel, String modelYear) {
	
		return everyBikeInfoIFaceDao.checkbikeModelmodelYear(bikeModel, modelYear);
	}

	@Override
	public int  save(String licensePlate, int branchName, String bikeModel, String modelYear) {
	
	int count=0;
	count++;
			Session session = factory.getCurrentSession();
			//同步新增 車牌和保養項目(EveryBikeMileage )
				BranchDetail branchDetail=session.get(BranchDetail.class, branchName);//分店 需要分店的流水號 加入機車 建構子
				EveryBikeInfo	everyBikeInfo = new EveryBikeInfo(licensePlate, 0.0, false,branchDetail);
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

	@Override
	public List<BikeDetailToGson> forGsonConvertBikeDetail(BikeDetail loop) {
		
		return everyBikeInfoIFaceDao.forGsonConvertBikeDetail(loop);

	}
	

}
