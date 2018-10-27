package testbean;

import java.io.IOException;
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
	
	
	@Transactional
	public  void makeFakeBranchDetail() throws ParseException, NumberFormatException, IOException {
		   @SuppressWarnings("unused")
		Session session = factory.getCurrentSession()  ; 
		System.out.println("makeFakeBranchDetail start up  ");
		testDAO.makeFakeBranchDetail();
		
	}
	@Transactional
	public  void makeFakeBikedetail_EveryBikeInfor() throws ParseException, NumberFormatException, IOException {
		   @SuppressWarnings("unused")
		Session session = factory.getCurrentSession()  ; 
		System.out.println("makeFakeBikedetail_EveryBikeInfor start up  ");
		testDAO.makeFakeBikedetail_EveryBikeInfor();
		
	}
	
	@Transactional
	public  void makeFakeOrderlist() throws ParseException, NumberFormatException, IOException {
		   @SuppressWarnings("unused")
		Session session = factory.getCurrentSession()  ; 
		System.out.println("makeFakeBikedetail_EveryBikeInfor start up  ");
		testDAO.makeFakeOrderlist();
		
	}
	

}
