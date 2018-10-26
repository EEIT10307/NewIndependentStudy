package testbean;

import java.text.ParseException;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Autowired
	TestDAO testDAO ; 
	@Autowired
	 SessionFactory factory;
	
	public TestService() {
		System.out.println("TestService start up ");
	}
	
	
	@Transactional
	public  void testLinkDAO() throws ParseException {
		   @SuppressWarnings("unused")
		Session session = factory.getCurrentSession()  ; 
		System.out.println("testLinkDAO start up  ");
		testDAO.testFactory();
		
	}
	

	@Transactional
	public  void testHibernateBean() throws ParseException {
		   @SuppressWarnings("unused")
		Session session = factory.getCurrentSession()  ; 
		System.out.println("testLinkDAO start up  ");
		testDAO.testHibernateBean();
		
	}
	
	

}
