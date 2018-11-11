package branchdetaildao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.BranchDetail;
import projectbean.BranchScenes;

@Repository
public class BranchScenesDao implements BranchScenesIFaceDao{

	@Autowired
	SessionFactory factory;
	@Autowired
	String spotName;
	
	@Override
	public List<BranchScenes> selectBranchScenes(String spotName) {
		
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchScenes> createQuery = buider.createQuery(BranchScenes.class);
		Root<BranchScenes> fromClass = createQuery.from(BranchScenes.class);
		ParameterExpression<String> branchName = buider.parameter(String.class);
		createQuery.select(fromClass).where(buider.equal(fromClass.get("branchName"), branchName));
		Query<BranchScenes> queryword = factory.getCurrentSession().createQuery(createQuery);
		queryword.setParameter(branchName, spotName);
		List<BranchScenes> list = queryword.getResultList();

//		for (OrderList loop : list) {
//			System.out.println("showAllOrderFromShop ="+loop.getBikeModel()+":"+loop.getPickupDate()+"=>"+loop.getDropoffDate());
//		}
		return list;
	}

	@Override
	public List<String> getBranchScenes() {
		
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<BranchScenes> createQuery = buider.createQuery(BranchScenes.class);
		Root<BranchScenes> fromClass = createQuery.from(BranchScenes.class);
		createQuery.select(fromClass);
		List<BranchScenes> sceneslist = factory.getCurrentSession().createQuery(createQuery).getResultList();
		List<String> spotnamelist = new ArrayList<String>();
		for (BranchScenes loop : sceneslist) {
			spotnamelist.add(loop.getSpotName());
		}
		return spotnamelist;
	}

	@Override
	public BranchScenes getScenes(int branchDetailSerialNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteScenes(String spotAddress, String spotPhoto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateScenes(BranchScenes bs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveBranchScenes(BranchScenes branchScenes) {
		Session session=factory.getCurrentSession();
		int updateCount = 0;

		session.save(branchScenes);

		updateCount = 1;

		return updateCount;
	}

	@Override
	public List<BranchScenes> showBranchScenes(BranchDetail branchName) {
		return selectBranchScenes(spotName);
	}

	@Override
	public List<BranchScenesToGson> forGsonConvert(List<BranchScenes> finalBranchScenes) {
ArrayList<BranchScenesToGson> branchScenesToGsonList = new ArrayList<BranchScenesToGson>();
		
		for(BranchScenes loop:finalBranchScenes) {
			branchScenesToGsonList.add(new BranchScenesToGson(loop.getBranchDetailSerialNum(),
					loop.getBranchName(), loop.getSpotName(), loop.getSpotAddress(),
					loop.getSpotPhoto()));
		}
		return branchScenesToGsonList;
	}

}
