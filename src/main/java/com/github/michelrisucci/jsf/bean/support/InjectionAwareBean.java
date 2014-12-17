package com.github.michelrisucci.jsf.bean.support;

import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.github.michelrisucci.jsf.util.FacesUtils;

/**
 * Utility class that injects {@link Named} and {@link Autowired}
 * fields/methods, after bean instantiation.
 * 
 * @author Michel Risucci
 */
public abstract class InjectionAwareBean {

	protected final Log log = LogFactory.getLog(this.getClass());

	/**
	 * 
	 */
	public InjectionAwareBean() {
		log.info("Injecting Spring context beans on JSF beans.");
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				FacesUtils.getServletContext());
	}

}