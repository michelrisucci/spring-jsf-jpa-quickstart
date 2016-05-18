package com.risucci.quickstart.context.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Database {

	private static final String DATASOURCE = "classpath:datasource.properties";
	private static final Log log = LogFactory.getLog(Database.class);

	@Bean(name = "dsProperties")
	public Properties dsProperties(@Value(DATASOURCE) Resource ds) throws IOException {
		log.info("Loading DataSource configuration properties at " + DATASOURCE + ".");
		Properties dsProperties = new Properties();
		dsProperties.load(ds.getInputStream());
		return dsProperties;
	}

	@Bean
	@Primary
	@Autowired
	public DataSource dataSource(@Value("#{dsProperties}") Properties dsProperties) throws Exception {
		log.info("Loading DataSource.");
		return BasicDataSourceFactory.createDataSource(dsProperties);
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		log.info("Loading PersistenceExceptionTranslationPostProcessor.");
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		log.info("Loading PersistenceAnnotationBeanPostProcessor.");
		return new PersistenceAnnotationBeanPostProcessor();
	}

}