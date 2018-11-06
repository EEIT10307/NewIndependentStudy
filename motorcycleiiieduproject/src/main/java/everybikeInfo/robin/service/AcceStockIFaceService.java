package everybikeInfo.robin.service;

import java.util.List;

import cleanbean.AcceStockBean;
import projectbean.AcceSerialNum;
import projectbean.BranchDetail;

public interface AcceStockIFaceService {
	int insertAcceStock(AcceStockBean acceStockBean);
	BranchDetail selectoneBikeDetail(String BranchName);
	AcceSerialNum selectAcceSerialNum(String AcceType);
	List<AcceSerialNum> allAcceSerialNum();
	BranchDetail selectBranchBranchSerialNum(int branchSerialNum);
	Long countAcceStockx();
}
