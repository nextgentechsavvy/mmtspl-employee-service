package com.mmtspl.employeeservice.configuration;	


import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mmtspl.employeeservice.model.MySQLBDUrls;
import com.mmtspl.employeeservice.service.MySQLBDUrlsService;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	
	// Local Database Properties
	@Value("${db.driver}")
	private String DRIVER;

	@Value("${db.password}")
	private String PASSWORD;

	@Value("${db.url}")
	private String URL;

	@Value("${db.username}")
	private String USERNAME;

	
	//Local Hibernate Properties
	@Value("${hibernate.dialect}")
	private String DIALECT;

	@Value("${hibernate.show_sql}")
	private String SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String PACKAGES_TO_SCAN;
	
	
	//zull API Gateway Server Properties
	@Value("${cloud.config.db.service.zullAPIGatewayHTTP}")
	private String zullAPIGatewayHTTP;
	
	@Value("${cloud.config.db.service.zullAPIGatewayIP}")
	private String zullAPIGatewayIP;

	@Value("${cloud.config.db.service.zullAPIGatewayPort}")
	private String zullAPIGatewayPort;

	@Value("${cloud.config.db.service.dbServiceApplicationName}")
	private String dbServiceApplicationName;

	
	//Cloud Config DB Service Url's	
	@Value("${cloud.config.db.service.baseUrl}")
	private String baseUrl;
	
	@Value("${cloud.config.db.service.port}")
	private String port;

	@Value("${cloud.config.db.service.mySQLBDUrlsServiceUri}")
	private String mySQLBDUrlsServiceUri;
	

	private MySQLBDUrls mySQLBDUrls;
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		//Calling using REST API
		//mySQLBDUrls = new MySQLBDUrlsService().getMySQLBDUrls(baseUrl, port, mySQLBDUrlsServiceUri);
		
		//Calling using Zull API Gateway Server
		//mySQLBDUrls = new MySQLBDUrlsService().getMySQLBDUrlsZullAPIGateway(zullAPIGatewayHTTP, zullAPIGatewayIP,
		//		zullAPIGatewayPort, dbServiceApplicationName, mySQLBDUrlsServiceUri);
		
		//if(mySQLBDUrls.getStatus() == false) {
			System.out.println("DB Url 111 : " + URL);
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USERNAME);
			dataSource.setPassword(PASSWORD);
		/*}else {
			System.out.println("DB Url : " + mySQLBDUrls.getUrl());
			dataSource.setDriverClassName(mySQLBDUrls.getDriver());
			dataSource.setUrl(mySQLBDUrls.getUrl());
			dataSource.setUsername(mySQLBDUrls.getUsername());
			dataSource.setPassword(mySQLBDUrls.getPassword());
		}*/
		System.out.println("DB Url : " + dataSource.getUrl());
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		Properties hibernateProperties = new Properties();
		
		
		//if(mySQLBDUrls.getStatus() == false) {
			sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
			hibernateProperties.put("hibernate.dialect", DIALECT);
			hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
			hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		/*}else {
			sessionFactory.setPackagesToScan(mySQLBDUrls.getEntitymanager_packagesToScan());
			hibernateProperties.put("hibernate.dialect", mySQLBDUrls.getHibernate_dialect());
			hibernateProperties.put("hibernate.show_sql", mySQLBDUrls.getHibernate_show_sql());
			hibernateProperties.put("hibernate.hbm2ddl.auto", mySQLBDUrls.getHibernate_hbm2ddl_auto());
		}*/
		
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}	
}