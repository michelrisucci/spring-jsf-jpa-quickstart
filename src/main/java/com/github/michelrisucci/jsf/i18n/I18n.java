package com.github.michelrisucci.jsf.i18n;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Named
@Scope(WebApplicationContext.SCOPE_SESSION)
public class I18n extends ResourceBundle {

	public static final Log log = LogFactory.getLog(I18n.class);

	public I18n() {
	}

	@PostConstruct
	private void postConstruct() {
		log.debug("I18N bundle instance created.");

		FacesContext context = FacesContext.getCurrentInstance();
		Locale userLocale = context.getViewRoot().getLocale();
		String bundleName = this.getClass().getName();

		ResourceBundle bundle = ResourceBundle
				.getBundle(bundleName, userLocale);

		setParent(bundle);
	}

	@Override
	protected Object handleGetObject(String key) {
		return parent.getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		return parent.getKeys();
	}

}