package everybikeInfo.robin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cleanbean.AcceStockBean;
import everybikeInfo.robin.dao.AcceStockIFaceDao;
import projectbean.AcceSerialNum;
import projectbean.BranchDetail;

@Service
@Transactional
public class AcceStockService implements AcceStockIFaceService {
	@Autowired
	AcceStockIFaceDao acceStockIFaceDao;

	@Override
	public int insertAcceStock(AcceStockBean acceStockBean) {

		return acceStockIFaceDao.insertAcceStock(acceStockBean);
	}

	@Override
	public BranchDetail selectoneBikeDetail(String BranchName) {

		return acceStockIFaceDao.selectoneBikeDetail(BranchName);
	}

	@Override
	public AcceSerialNum selectAcceSerialNum(String AcceType) {
		
		return acceStockIFaceDao.selectAcceSerialNum(AcceType);
	}

	@Override
	public List<AcceSerialNum> allAcceSerialNum() {
		// TODO Auto-generated method stub
		return acceStockIFaceDao.allAcceSerialNum();
	}


	@Override
	public BranchDetail selectBranchBranchSerialNum(int branchSerialNum) {
		// TODO Auto-generated method stub
		return acceStockIFaceDao.selectBranchBranchSerialNum(branchSerialNum);
	}

	@Override
	public Long countAcceStockx() {
		// TODO Auto-generated method stub
		return acceStockIFaceDao.countAcceStockx();
	}


}
