package com.drydock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.drydock.config.PageNavigationConfig;
import com.drydock.config.RestNavigationConfig;
import com.drydock.util.HandleTokenUtil;
import com.drydock.web.filter.AuthenticationFilter;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,DispatcherServletAutoConfiguration.class})
public class SpringBootWebApplication extends SpringBootServletInitializer {

	public SpringBootWebApplication() {
	    super();
	    setRegisterErrorPageFilter(false); // <- this one
	}
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}*/

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	@Bean
    public ServletRegistrationBean foo() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();   
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(PageNavigationConfig.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "*.action");
        servletRegistrationBean.setName("PageNavigationConfig");
        return servletRegistrationBean;
    }
    @Bean
    public ServletRegistrationBean bar() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(RestNavigationConfig.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/rest/*");
        servletRegistrationBean.setName("RestNavigationConfig");
        return servletRegistrationBean;
    }
}