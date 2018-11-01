package testbean.robin;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.MemberDetail;
@Repository
public interface MemberDetailIFaceDao {
	List<MemberDetail> selectone(int memberSerialNum);
}
