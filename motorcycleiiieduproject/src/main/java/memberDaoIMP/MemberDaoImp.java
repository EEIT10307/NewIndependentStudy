package memberDaoIMP;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import memberIDAO.MemberIdao;
import projectbean.MemberDetail;



@Repository

public class MemberDaoImp implements MemberIdao {
	
	
	@Autowired
	 SessionFactory factory;
    
//	@Autowired
//	DetailIDAO  detailIDAO ; 
	
//	@Autowired
//	EntityManagerFactory entityManagerFactory;

	

	@SuppressWarnings("unchecked")
	@Override
//	@Transactional
	public boolean isDup(String email) {
		Session session = factory.getCurrentSession() ; 
	//	Session session = entityManagerFactory.unwrap(Session.class) ; 
	    boolean drop =false ; 
	    String  hgl = "FROM MemberDetail WHERE email = :email" ; 
//	    Transaction tx = null ; 
//	    try {
//	    	 tx = session.beginTransaction() ; 
	    	
			List<MemberDetail> list = session.createQuery(hgl).setParameter("email", email).getResultList() ; 
		    if(list.size()>0) {
		    	drop = true  ; 
		    }
//		    tx.commit();
//	    } catch(Exception ex) {
//	    	if(tx != null) {
//	    		tx.rollback();
//	    	}
//	    	ex.printStackTrace();
//	    }  finally {
//			session.close();
//		}
		    return drop;
	}

	@Override
	//@Transactional
	public int save(MemberDetail mb) {
		Session session = factory.getCurrentSession() ; 
		System.out.println("memberSession = " + session);
		
		if(mb != null)
		
//			Session session = entityManagerFactory.unwrap(Session.class) ;
//		Transaction tx = null ;  
		
//		
//		try {
//			 tx = session.beginTransaction() ; 
		      
			
//		       dmem.setName(mb.getName());
//		       dmem.setMail("testMemberDaoImp");   ignored a while
		   
			 session.save(mb) ; 
//			 detailIDAO.save(dmem) ;  
			 
	
			 
			 
//			 tx.commit();
//		}catch (Exception e) {
//			if(tx != null) {
//tx.rollback();
//			System.out.println("hi");
//			session.clear();
			
//			}
//			e.printStackTrace();
//		}finally {
//			session.close();
//		}
		
		
		return  0 ; 
		
	}

	@SuppressWarnings("unchecked")
	@Override
//	@Transactional
	public List<MemberDetail> getAllMembers() {
		Session session = factory.getCurrentSession() ; 
//			Session session = entityManagerFactory.unwrap(Session.class) ;
		   System.out.println(session.toString());
		     List<MemberDetail> list = new ArrayList<MemberDetail>() ; 
//		   Transaction tx = null ; 
//		   try {
//			  tx   =   session.beginTransaction() ; 
			list  =   session.createQuery("FROM MemberDetail").getResultList() ; 
//			tx.commit();			  
//			  
//		   }catch (Exception e) {
//			
//			   if(tx != null ) {
//				   tx.rollback();
//			   }
//				  			   			   
//		}finally {
//					session.close();
//		}
		   return list ; 		   
		
	}

	@Override
	@Transactional
	public MemberDetail getMember(String  email) {
		
		Session session = factory.getCurrentSession() ; 
//			Session session = entityManagerFactory.unwrap(Session.class) ;
//			Transaction tx = null ;  
			MemberDetail member = null ;   		          		
//		try {
//					 tx = session.beginTransaction() ; 
			    member = session.get(MemberDetail.class, email) ; 
			  
//			    		 tx.commit();
//		}catch (Exception e) {
//					if(tx != null) {
//					tx.rollback(); 
//				}
//			e.printStackTrace();
//		}finally {
//				session.close();
//		}
//		
		
		
		return member;
	}

	@Override
	@Transactional
	public int deleteMember(MemberDetail mem) {
		Session session = factory.getCurrentSession() ; 
//			Session session = entityManagerFactory.unwrap(Session.class) ;
//			Transaction tx = null ;  
//		
//		try {
//					 tx = session.beginTransaction() ; 
			 session.delete(mem); 
//			 		 tx.commit();
//		}catch (Exception e) {
//					if(tx != null) {
//						tx.rollback(); 
//				}
//			e.printStackTrace();
//		}finally {
//					session.close();
//		}
		
		
		
		return 0;
	}

	@Override
//	@Transactional
	public MemberDetail updateMember(MemberDetail mb) {
		
		String hql = "FROM MemberDetail m WHERE m.email = :email";
		Session session = factory.getCurrentSession();
		String email = mb.getEmail();
		String password = mb.getPassword();
		String name = mb.getName();
		String phone = mb.getPhone();
		Date birthday = mb.getBirthday();
		String gender = mb.getGender();
		String address = mb.getAddress();

				try {
			MemberDetail mem = (MemberDetail) session.createQuery(hql)
					             .setParameter("email", email)
					             .getSingleResult();
				
			mem.setPassword(password);
			mem.setName(name);
            mem.setPhone(phone);
            mem.setBirthday(birthday);
            mem.setGender(gender);
            mem.setAddress(address);
            
            session.update(mem);

//					String check = mb.getClass().getName(); 
//					System.out.println("check="+check);   check=bean.MemberBean
					//                                    mb=bean.MemberBean@5d635c4a
				} catch(NoResultException ex) {
					mb = null;
				}
				return mb;
	
	}
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberDetail物件，
    // 否則傳回 null。
	@Override
	@Transactional
	public MemberDetail checkEmailPassword(String email, String password) {
		MemberDetail mb = null;
		String hql = "FROM MemberDetail m WHERE m.email = :email and m.password = :password";
		Session session = factory.getCurrentSession() ; 
		try {
			mb = (MemberDetail) session.createQuery(hql)
			             .setParameter("email", email)
			             .setParameter("password",password)
			             .getSingleResult();
//			String check = mb.getClass().getName(); 
//			System.out.println("check="+check);   check=bean.MemberBean
			//                                    mb=bean.MemberBean@5d635c4a
		} catch(NoResultException ex) {
			mb = null;
		}
		return mb;
	}

	@Override
	@Transactional
	public MemberDetail checkEmail(String email) {
		MemberDetail mb = null;
		String hql = "FROM MemberDetail m WHERE m.email = :email";
		Session session = factory.getCurrentSession() ; 
		try {
			mb = (MemberDetail) session.createQuery(hql)
			             .setParameter("email", email)
			             .getSingleResult();
//			String check = mb.getClass().getName(); 
//			System.out.println("check="+check);   check=bean.MemberBean
			//                                    mb=bean.MemberBean@5d635c4a
		} catch(NoResultException ex) {
			mb = null;
		}
		return mb;
	}


	}








	
	


