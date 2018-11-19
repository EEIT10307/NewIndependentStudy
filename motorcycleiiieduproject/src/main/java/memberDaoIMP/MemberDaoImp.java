package memberDaoIMP;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import cleanbean.MemberDetailCleanBean;
import memberIDAO.MemberIdao;
import projectbean.BranchDetail;
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
//	@Transactional
	public MemberDetail getMember(String  email) {
		

//			Session session = entityManagerFactory.unwrap(Session.class) ;
//			Transaction tx = null ; 
		System.out.println("進入getMember方法");
		String hql = "FROM MemberDetail m WHERE m.email = :email";
		Session session = factory.getCurrentSession();
		
			MemberDetail member = (MemberDetail) session.createQuery(hql)
		             .setParameter("email", email)
		             .getSingleResult();
	
//		try {
//					 tx = session.beginTransaction() ; 
//			    member = session.get(MemberDetail.class, email) ; 
			  
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
//	@Transactional
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
	public MemberDetail updateMember(String email, String password, String name, String phone, Date birthday, String gender,
			String address) {
		System.out.println("進入updateMember方法");
	
//		MemberDetail memberDetail = new MemberDetail(email,password,name,phone,birthday,gender,
//				address);
		
		String hql = "FROM MemberDetail m WHERE m.email = :email";
		Session session = factory.getCurrentSession();
		
			MemberDetail member = (MemberDetail) session.createQuery(hql)
		             .setParameter("email", email)
		             .getSingleResult();
		
			member.setPassword(password);
			member.setName(name);
			member.setPhone(phone);
			member.setBirthday(birthday);
			member.setGender(gender);
			member.setAddress(address);
//		String hql = "FROM MemberDetail m WHERE m.email = :email";
//		Session session = factory.getCurrentSession();
//		MemberDetail mem = (MemberDetail) session.createQuery(hql)
//				.setParameter("email", email)
//				.getSingleResult();
		try {
		
			
			session.update(member);
//		String password = mem.getPassword();
//		String name = mem.getName();
//		String phone = mem.getPhone();
//		Date birthday = mem.getBirthday();
//		String gender = mem.getGender();
//		String address = mem.getAddress();


//					String check = mb.getClass().getName(); 
//					System.out.println("check="+check);   check=bean.MemberBean
					//                                    mb=bean.MemberBean@5d635c4a
				} catch(NoResultException ex) {
					member = null;
				}
				return member;
	
	}
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberDetail物件，
    // 否則傳回 null。
	@Override
//	@Transactional
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
//	@Transactional
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

	@Override
	public MemberDetailCleanBean updateMember(MemberDetailCleanBean mdcb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDetail updateMemberPic(String email) {
		System.out.println("進入updateMemberPic方法");
		MemberDetail mb = null;
		String hql = "FROM MemberDetail m WHERE m.email = :email";
		Session session = factory.getCurrentSession() ; 
		
			mb = (MemberDetail) session.createQuery(hql)
			             .setParameter("email", email)
			             .getSingleResult();
			
			String profilePhoto ="Front"+email+".jpg";
			mb.setProfilePhoto(profilePhoto);
			
			session.update(mb);
			
		return mb;
	}

	@Override
	public boolean checkPhotoString(String email) {
		System.out.println("進入checkPhotoString方法");
		MemberDetail mb = null;
		boolean drop =false ; 
		String hql = "FROM MemberDetail m WHERE m.email = :email";
		Session session = factory.getCurrentSession() ; 
		
			mb = (MemberDetail) session.createQuery(hql)
			             .setParameter("email", email)
			             .getSingleResult();
			
			String profilePhoto = mb.getProfilePhoto();
			System.out.println("profilePhoto為"+profilePhoto);
			if(profilePhoto != null) {
				System.out.println("profilePhoto有資料");
				drop=true;
				
			}
				
				return drop;
			
	}

	@Override
	public List<MemberDetail> getMemberPhone(String email) {
		
		
		CriteriaBuilder buider = factory.getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<MemberDetail> createQuery = buider.createQuery(MemberDetail.class);
		Root<MemberDetail> fromClass = createQuery.from(MemberDetail.class);
		createQuery.select(fromClass).where(buider.equal(fromClass.get("email"), email));
		List<MemberDetail> branchlist = factory.getCurrentSession().createQuery(createQuery).getResultList();
	
		return branchlist;
		
		
		
	
		
	}
	
	
	

	}








	
	


