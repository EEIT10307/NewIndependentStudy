package everybikeInfo.robin.service;

import java.util.List;

import projectbean.MemberDetail;

public interface MemberDetailIFaceService {
	List<MemberDetail> isDup(int memberSerialNum);
}
