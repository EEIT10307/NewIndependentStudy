package everybikeInfo.robin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cleanbean.AcceStockBean;
import projectbean.AcceSerialNum;
import projectbean.BranchDetail;
@Repository
public interface AcceStockIFaceDao {
	int insertAcceStock(AcceStockBean acceStockBean);
	BranchDetail selectoneBikeDetail(String BranchName);
	AcceSerialNum selectAcceSerialNum(String AcceType);
	List<AcceSerialNum> allAcceSerialNum();
	BranchDetail selectBranchBranchSerialNum(int branchSerialNum);
	Long   countAcceStockx();
}
