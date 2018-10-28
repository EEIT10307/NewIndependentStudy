package allconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"testcontroller","ordercontroller"})
public class SpringMVCConfig implements WebMvcConfigurer {
	
	
//註冊interceptor使用
	@Override
	public void addInterceptors(InterceptorRegistry registry) {   
//		    InterceptorRegistration myinter = registry.addInterceptor(new InterceptorUse()) ; 
//		    myinter.addPathPatterns("/CheckAllServlet","/ChangeServlet","/DeleteServlet" ,"/RegisterServlet") ; 		   
//		WebMvcConfigurer.super.addInterceptors(registry);
		
	}
	//靜態資源使用預設servlet
	@Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }

}
