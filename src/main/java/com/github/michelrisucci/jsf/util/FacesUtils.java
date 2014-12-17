package com.github.michelrisucci.jsf.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.LocaleUtils;

public class FacesUtils {

	public static final String I18N_BUNDLE_NAME = "com.github.michelrisucci.jsf.i18n";
	public static final String I18N_BUNDLE_VAR = "i18n";

	private FacesUtils() {
	}

	/**
	 * Add FacesMessage to context.
	 * 
	 * @param severity
	 * @param title
	 * @param detail
	 */
	public static void addMessage(String clientId,
			FacesMessageSeverity severity, String title, String detail) {
		getFacesContext().addMessage(clientId,
				new FacesMessage(severity.getSeverity(), title, detail));
	}

	public static void addI18nMessage(String clientId,
			FacesMessageSeverity severity, String I18nTitle,
			Object[] titleArgs, String I18nDetail, Object[] detailArgs) {
		String title = formatI18nMessage(I18nTitle, titleArgs);
		String detail = formatI18nMessage(I18nDetail, detailArgs);
		addMessage(clientId, severity, title, detail);
	}

	public static void addMessage(String clientId,
			FacesMessageSeverity severity, String title) {
		addMessage(clientId, severity, title, null);
	}

	public static void addI18nMessage(String clientId,
			FacesMessageSeverity severity, String I18nTitle,
			Object... titleArgs) {
		String title = formatI18nMessage(I18nTitle, titleArgs);
		addMessage(clientId, severity, title);
	}

	/*
	 * INFO
	 */

	public static void addInfo(String title, String detail) {
		addMessage(null, FacesMessageSeverity.INFO, title, detail);
	}

	public static void addInfo(String title) {
		addMessage(null, FacesMessageSeverity.INFO, title);
	}

	public static void addI18nInfo(String I18ntitle, Object... titleArgs) {
		addI18nMessage(null, FacesMessageSeverity.INFO, I18ntitle, titleArgs);
	}

	/*
	 * WARN
	 */

	public static void addWarn(String title, String detail) {
		addMessage(null, FacesMessageSeverity.WARN, title, detail);
	}

	public static void addWarn(String title) {
		addMessage(null, FacesMessageSeverity.WARN, title);
	}

	public static void addI18nWarn(String I18ntitle, Object... titleArgs) {
		addI18nMessage(null, FacesMessageSeverity.WARN, I18ntitle, titleArgs);
	}

	/*
	 * ERROR
	 */

	public static void addError(String title, String detail) {
		addMessage(null, FacesMessageSeverity.ERROR, title, detail);
	}

	public static void addError(String title) {
		addMessage(null, FacesMessageSeverity.ERROR, title);
	}

	public static void addI18nError(String I18ntitle, Object... titleArgs) {
		addI18nMessage(null, FacesMessageSeverity.ERROR, I18ntitle, titleArgs);
	}

	/*
	 * FATAL ERROR
	 */

	public static void addFatalError(String title, String detail) {
		addMessage(null, FacesMessageSeverity.FATAL, title, detail);
	}

	public static void addFatalError(String title) {
		addMessage(null, FacesMessageSeverity.FATAL, title);
	}

	public static void addI18nFatalError(String I18ntitle, Object... titleArgs) {
		addI18nMessage(null, FacesMessageSeverity.FATAL, I18ntitle, titleArgs);
	}

	/**
	 * @return current instance of FacesContext
	 */
	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * @return current instance of {@link ExternalContext}
	 */
	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	/**
	 * @return current session of type {@link HttpSession}
	 */
	public static HttpSession getSession() {
		return HttpSession.class.cast(getExternalContext().getSession(true));
	}

	/**
	 * @return current instance of {@link HttpServletRequest}
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return HttpServletRequest.class.cast(getExternalContext().getRequest());
	}

	/**
	 * @return current instance of {@link ServletContext}
	 */
	public static ServletContext getServletContext() {
		return ServletContext.class.cast(getExternalContext().getContext());
	}

	/**
	 * @return user locale
	 */
	public static Locale getUserLocale() {
		return getFacesContext().getViewRoot().getLocale();
	}

	/**
	 * @param locale
	 */
	public static void updateUserLocale(String locale) {
		Locale resolvedLocale = LocaleUtils.toLocale(locale);
		setUserLocale(resolvedLocale);
	}

	/**
	 * @param locale
	 */
	public static void setUserLocale(Locale locale) {
		getFacesContext().getViewRoot().setLocale(locale);
	}

	/*
	 * RESOURCE BUNDLE
	 */

	public static String formatMessage(String message, Object... arguments) {
		return MessageFormat.format(message, arguments);
	}

	public static ResourceBundle getResourceBundle() {
		FacesContext context = getFacesContext();
		Application application = context.getApplication();
		return application.getResourceBundle(context, I18N_BUNDLE_VAR);
	}

	public static String formatI18nMessage(String I18nMessage,
			Object... arguments) {
		ResourceBundle bundle = getResourceBundle();
		return formatMessage(bundle.getString(I18nMessage), arguments);
	}

	/**
	 * Return the instance of the UIComponent for the desired fullComponentId
	 * 
	 * @param fullComponentId
	 * @return
	 */
	public static UIComponent getUIComponent(String fullComponentId) {
		return getFacesContext().getViewRoot().findComponent(fullComponentId);
	}

	/**
	 * Retrieve an instance of any context bean from {@link FacesContext}.
	 */
	public static <T> T getBeanFromClass(Class<T> beanClass) {
		FacesContext context = getFacesContext();
		Application application = context.getApplication();

		String beanName = resolveManagedBeanName(beanClass);
		if (beanName.equals("")) {
			beanName = toCamelCase(beanClass.getSimpleName());
		}

		return application.evaluateExpressionGet(context,
				"#{" + beanName + "}", beanClass);
	}

	private static String resolveManagedBeanName(Class<?> beanClass) {
		ManagedBean managedBean = beanClass.getAnnotation(ManagedBean.class);
		Named named = beanClass.getAnnotation(Named.class);

		if (managedBean != null) {
			return managedBean.name();
		} else if (named != null) {
			return named.value();
		} else {
			throw new IllegalArgumentException(beanClass.getName()
					+ " is not a bean.");
		}
	}

	public static String toCamelCase(String text) {
		String[] parts = text.split("_");

		StringBuilder builder = new StringBuilder();
		builder.append(parts[0].substring(0, 1).toLowerCase());
		builder.append(parts[0].substring(1));

		// Starting from char 1 (not ZERO).
		for (int i = 1; i < parts.length; i++) {
			builder.append(parts[i].substring(0, 1).toUpperCase());
			builder.append(parts[i].substring(1).toLowerCase());
		}

		return builder.toString();
	}

}
