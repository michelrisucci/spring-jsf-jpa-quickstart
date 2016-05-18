package com.risucci.quickstart.context.configuration;

import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.transaction.PlatformTransactionManager;

import com.risucci.quickstart.context.QuickstartWebApplicationInitializer;

@Configuration
public class Jpa {

	private static final Log log = LogFactory.getLog(Jpa.class);

	@Bean
	@Autowired
	public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean( //
			DataSource dataSource, //
			JpaVendorAdapter jpaVendorAdapter, //
			@Value("#{packagesToScan}") String[] packagesToScan, //
			@Value("#{sharedCacheMode}") SharedCacheMode sharedCacheMode, //
			@Value("#{jpaPropertiesMap}") Map<String, ?> jpaPropertiesMap) {

		log.info("Loading JPA EntityManagerFactory.");
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(jpaVendorAdapter);
		bean.setPackagesToScan(packagesToScan);
		bean.setSharedCacheMode(sharedCacheMode);
		bean.setJpaPropertyMap(jpaPropertiesMap);
		return bean;
	}

	/**
	 * {@link JpaTransactionManager} also supports direct DataSource access
	 * within a transaction (i.e. plain JDBC code working with the same
	 * DataSource). This allows for mixing services which access JPA and
	 * services which use plain JDBC (without being aware of JPA)! Application
	 * code needs to stick to the same simple Connection lookup pattern as with
	 * DataSourceTransactionManager (i.e.
	 * DataSourceUtils.getConnection(javax.sql.DataSource) or going through a
	 * TransactionAwareDataSourceProxy). Note that this requires a
	 * vendor-specific JpaDialect to be configured
	 * 
	 * @param dataSource
	 * @param emf
	 * @return
	 */
	@Bean
	@Autowired
	public PlatformTransactionManager platformTransactionManager(DataSource dataSource, EntityManagerFactory emf) {
		log.info("Loading JPA Transaction Management.");
		return new JpaTransactionManager(emf);
	}

	/*
	 * DEFINITIONS
	 */

	@Bean(autowire = Autowire.BY_NAME)
	public String[] packagesToScan() {
		return QuickstartWebApplicationInitializer.class.getAnnotation(ComponentScan.class).basePackages();
	}

	@Bean(autowire = Autowire.BY_NAME)
	public Database jpaVendorDatabase() {
		return Database.H2;
	}

	@Bean(autowire = Autowire.BY_NAME)
	public Boolean generateDdl() {
		return Boolean.TRUE;
	}

	@Bean(autowire = Autowire.BY_NAME)
	public Boolean showSql() {
		return Boolean.TRUE;
	}

	@Bean(autowire = Autowire.BY_NAME)
	public SharedCacheMode sharedCacheMode() {
		return SharedCacheMode.ENABLE_SELECTIVE;
	}

}