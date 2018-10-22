package testbean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {

	@Autowired
	 SessionFactory factory;
	
	
	
	public TestDAO() {
		System.out.println("test DAO startUP");
	}

	public void testFactory() throws ParseException {
		
		  Session session = factory.getCurrentSession();
		  
		  
		    TestHibernateBean testbea = new TestHibernateBean() ; 
		    testbea.setAge(11);
		    testbea.setName("阿P");
	
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		          
		        Date par = sdf.parse("2018-11-11 12:12:12") ; 
		    testbea.setDate(par);
		    session.save(testbea) ; 
		  
		  System.out.println(session.toString());
		
	}
	
	

	
	
}
