package testbean.robin;

import java.util.List;

import org.springframework.stereotype.Repository;

import projectbean.BranchDetail;
@Repository
public interface BranchDetailIFaceDao {
	List<BranchDetail> getAllMembers();
}
