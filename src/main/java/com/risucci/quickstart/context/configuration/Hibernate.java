package com.risucci.quickstart.context.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class Hibernate {

	private static final Log log = LogFactory.getLog(Hibernate.class);

	public enum Hbm2Ddl {
		NONE("none"), VALIDATE("validate"), UPDATE("update"), CREATE("create"), CREATE_AND_DROP("create-drop");

		private String value;

		private Hbm2Ddl(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	@Bean
	@Autowired
	public JpaVendorAdapter adapterFactory( //
			@Value("#{jpaVendorDatabase}") Database jpaVendorDatabase, //
			@Value("#{jpaVendorDialect}") String jpaVendorDialect, //
			@Value("#{generateDdl}") Boolean generateDdl, //
			@Value("#{showSql}") Boolean showSql) {

		log.info("Loading Hibernate as JPA vendor.");
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(jpaVendorDatabase);
		adapter.setDatabasePlatform(jpaVendorDialect);
		adapter.setGenerateDdl(generateDdl);
		adapter.setShowSql(showSql);
		return adapter;
	}

	@Bean(autowire = Autowire.BY_NAME)
	public String jpaVendorDialect() {
		return H2Dialect.class.getName();
	}

	@Bean(autowire = Autowire.BY_NAME)
	public Map<String, ?> jpaPropertiesMap() {
		Map<String, Object> bean = new HashMap<String, Object>();
		bean.put("hibernate.hbm2ddl.auto", Hbm2Ddl.UPDATE.getValue());
		bean.put("hibernate.format_sql", true);
		// Prevents the throwing of LazyInitializationException.
		bean.put("hibernate.enable_lazy_load_no_trans", true);
		return bean;
	}

}