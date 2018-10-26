package allconfig;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.gson.Gson;



@Configuration
@ComponentScan(basePackages={"testbean"})
@EnableTransactionManagement
public class SpringConfig {

	//JNDI DataSource
	@Bean
	public DataSource dataSource(){			
		JndiObjectFactoryBean factory = new JndiObjectFactoryBean();
		factory.setJndiName("java:comp/env/spring/jndi");
	    factory.setProxyInterface(javax.sql.DataSource.class);
		try {
			factory.afterPropertiesSet();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return (DataSource) factory.getObject();		
	}
	
	//JDBC DataSource
//		@Bean
//	public DataSource jdbcDataSource() {
//		//不連網直接測試用
//		     DriverManagerDataSource jdbcd = new DriverManagerDataSource() ; 
//		     jdbcd.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		     jdbcd.setUrl("jdbc:sqlserver://172.20.10.14:1433;database=homeregister");
//		     jdbcd.setUsername("sa");
//		     jdbcd.setPassword("password");
//		     return jdbcd ; 
//		
//	}
	
	
	
	
	
	//Hibernate Factory
	@Bean
	  public SessionFactory sessionFactory() {
		    LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource()) ;  
		//     LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(jdbcDataSource()) ;
		     Properties props = new Properties();
		     props.setProperty("hibernate.hbm2ddl.auto", "update");
		     props.setProperty("hibernate.show_sql", "true");
		     props.setProperty("hibernate.format_sql", "true");
		     props.setProperty("hibernate.bytecode.use_reflection_optimizer", "false");
		 //    props.setProperty("hibernate.current_session_context_class", "thread");
		     builder.addProperties(props); 
		     //掃bean使用
//		     builder.addAnnotatedClasses(TestHibernateBean.class , AcceSerialNum.class , AcceStock.class , BikeDetail.class 
//		    		 , BikeReview.class , BranchDetail.class , BranchScenes.class , ChatRoom.class , Discount.class , EveryBikeInfo.class
//		    		 ,EveryBikeMileage.class , MaintenanceDetail.class);
		  
		     builder.scanPackages("projectbean");
		     
		     return builder.buildSessionFactory() ; 
	  
	  }
	   @Bean
	   public Gson gson() {		   
		    Gson gson = new Gson() ; 
		   return gson ; 
	   }
	   
	   
	   @Bean
	   public PlatformTransactionManager transactionManager() {		   
		   JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		   jpaTransactionManager.setDataSource(dataSource());	   
	//	   DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(jdbcDataSource()); 
		   
		   return jpaTransactionManager;
		   
		   
		   
	   }
	
	
}
