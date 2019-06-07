package com.demo.config;

import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
 
@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class WebMvcConfig {
	private static DataSource datasource;
	@Value("${driverclassname}")
	private String driverClassName;
	@Value("${dburl}")
	private String dburl;
	@Value("${dbusername}")
	private String dbusername;
	@Value("${dbpassword}")
	private String dbpassword;
	@Value("${defaultlocale}")
	private String defaultlocale;
	 @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver()  {
        SessionLocaleResolver resolver= new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale(defaultlocale));
        return resolver;
    } 
     
    @Bean(name = "messageSource")
    public MessageSource getMessageResource()  {
        ResourceBundleMessageSource messageResource= new ResourceBundleMessageSource();
        messageResource.setBasename("resources/message_resource");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Bean
    public DataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dburl);
        dataSource.setUsername(dbusername);
        dataSource.setPassword(dbpassword);
//        dataSource.setDefaultAutoCommit(false);
        dataSource.setDefaultAutoCommit(false);
        datasource=dataSource; 
        return dataSource;
    }
    
    @Bean
    public DatabaseConfiguration databaseConfiguration()
    {
    	DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
    	databaseConfiguration.setDataSource(dataSource());
    	databaseConfiguration.setTable("application_configuration");
    	databaseConfiguration.setKeyColumn("property_key");
    	databaseConfiguration.setValueColumn("property_value");
    	
        return databaseConfiguration;
    }

}