package allconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import testcontroller.InterceptorUse;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"testcontroller","ordercontroller","maintenancecontroller","everybikeInfocontroller"})
public class SpringMVCConfig implements WebMvcConfigurer {
	
	
//註冊interceptor使用
	@Override
	public void addInterceptors(InterceptorRegistry registry) {   
		InterceptorRegistration myinter = registry.addInterceptor(new InterceptorUse()) ; 

	    myinter.addPathPatterns("/BikeReviewInsert","/insertAllLicensePlate","/insertBikeDetail") ; 		   
	WebMvcConfigurer.super.addInterceptors(registry);

		 
	}
	//靜態資源使用預設servlet
	@Override
	    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	        configurer.enable();
	    }
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
}
