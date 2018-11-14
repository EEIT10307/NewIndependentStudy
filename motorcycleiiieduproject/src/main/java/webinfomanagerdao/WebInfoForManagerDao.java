package webinfomanagerdao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projectbean.WebInformationForManager;
import webinfomanagerservice.WebInfoForManagerIFaceService;

@Repository
public class WebInfoForManagerDao implements WebInfoForManagerIFaceDao {
	
	@Autowired
	SessionFactory Factory;
	
	@Autowired
	WebInfoForManagerIFaceService webInfoForManagerIFaceService;

	@Override
	public List<WebInformationForManager> insertWebInfoForManager(List<WebInformationForManager> webInformationForManager) {
		
		Session session = Factory.getCurrentSession();
		for( WebInformationForManager loop:webInformationForManager) {
			session.save(loop);
			System.out.println("insert");
		}
		return null;
	}

	@Override
	public List<WebInformationForManager> updateWebInfoForManager(List<WebInformationForManager> webInformationForManager) {
		Session session = Factory.getCurrentSession();
		for( WebInformationForManager loop:webInformationForManager) {
			session.update(loop);
			System.out.println("update");
		}
		return null;
	}
	
	@Override
	public List<WebInformationForManager> insertorupdateWebInfoForManager(List<WebInformationForManager> webInformationForManager) {
		Session session = Factory.getCurrentSession();
		for( WebInformationForManager loop:webInformationForManager) {
			session.saveOrUpdate(loop);
			System.out.println("saveOrUpdate");
		}
		return null;
	}

	@Override
	public List<WebInformationForManager> getAllWebInfoManager() {
		CriteriaBuilder builder = Factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<WebInformationForManager> createQuery = builder.createQuery(WebInformationForManager.class);
		Root<WebInformationForManager> fromClass = createQuery.from(WebInformationForManager.class);
		createQuery.select(fromClass);
		List<WebInformationForManager> domlist = Factory.getCurrentSession().createQuery(createQuery).getResultList();
	//	System.out.println(domlist.get(0).getWebContent());
		return domlist;
	}
}
