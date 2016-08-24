package com.risucci.quickstart.jsf.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public final class FacesUtils {

	public static final String I18N_BUNDLE_NAME = "com.github.michelrisucci.jsf.i18n";
	public static final String I18N_BUNDLE_VAR = "i18n";

	private FacesUtils() {
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ResourceBundle getResourceBundle() {
		FacesContext context = getFacesContext();
		Application application = context.getApplication();
		return application.getResourceBundle(context, I18N_BUNDLE_VAR);
	}

	public static String formatI18nMessage(String I18nMessage, Object... arguments) {
		ResourceBundle bundle = getResourceBundle();
		return MessageFormat.format(bundle.getString(I18nMessage), arguments);
	}

	public static void addGlobalMessage(FacesMessageSeverity severity, String title, String detail) {
		getFacesContext().addMessage(null, new FacesMessage(severity.getSeverity(), title, detail));
	}

	public static void addGlobalMessage(FacesMessageSeverity severity, String message) {
		addGlobalMessage(severity, message, null);
	}

	public static void addI18nGlobalMessage(FacesMessageSeverity severity, String i18nTitle, Object[] titleArgs, String i18nDetail, Object[] detailArgs) {
		String title = formatI18nMessage(i18nTitle, titleArgs);
		if (i18nDetail != null && !i18nDetail.isEmpty()) {
			String detail = formatI18nMessage(i18nDetail, detailArgs);
			addGlobalMessage(severity, title, detail);
		} else {
			addGlobalMessage(severity, title);
		}
	}

	public static void addI18nGlobalMessage(FacesMessageSeverity severity, String i18nMessage, Object... args) {
		addI18nGlobalMessage(severity, i18nMessage, args, null, null);
	}

}
