package com.risucci.quickstart.jsf.bean;

import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class LocaleManager {

	private static final String LOCALE_KEY = "localeManager.locale";

	private HttpSession session;

	@PostConstruct
	public void postConstruct() {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIViewRoot vr = fc.getViewRoot();
		ExternalContext ec = fc.getExternalContext();
		session = (HttpSession) ec.getSession(true);
		Map<String, String> rp = ec.getRequestParameterMap();
		String lang = rp.get("lang");

		Locale currentLocale = (Locale) session.getAttribute(LOCALE_KEY);
		if (currentLocale == null) {
			session.setAttribute(LOCALE_KEY, vr.getLocale());
		} else if (lang != null) {
			Locale locale = Locale.forLanguageTag(lang);
			session.setAttribute(LOCALE_KEY, locale);
		}
	}

	public Locale getLocale() {
		return (Locale) session.getAttribute(LOCALE_KEY);
	}

	public String getLanguage() {
		return getLocale().getLanguage();
	}

}