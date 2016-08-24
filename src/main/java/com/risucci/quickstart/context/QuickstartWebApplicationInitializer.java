package com.risucci.quickstart.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.risucci.quickstart.context.adapter.WebApplicationConfigurerAdapter;
import com.risucci.quickstart.context.adapter.WebApplicationSecurityConfigurerAdapter;


@Configuration
@ComponentScan(basePackages = { "com.risucci.quickstart" })
@Order(1)
public class QuickstartWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { QuickstartWebApplicationInitializer.class };
	}

	/**
	 * {@link WebApplicationConfigurerAdapter} and
	 * {@link WebApplicationSecurityConfigurerAdapter} already being scanned.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/rest/*" };
	}

}