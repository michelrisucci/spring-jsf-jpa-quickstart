package com.github.michelrisucci.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.github.michelrisucci.controller.CountryController;
import com.github.michelrisucci.jsf.model.Country;
import com.github.michelrisucci.jsf.util.FacesUtils;

/**
 * Making the JSF bean extend {@link SpringBeanAutowiringSupport} is the best
 * way to seamlessly integrate JSF Context with Spring Context, enabling
 * features like DI via @{@link Inject} and @{@link Autowire} . Spring does not
 * provide a powerful built-in JSF integration module.
 * 
 * @author Michel Risucci
 */
@ManagedBean
@ViewScoped
public class CountryBean extends SpringBeanAutowiringSupport {

	protected static final Log log = LogFactory.getLog(CountryBean.class);

	@Inject
	private CountryController controller;

	private String state;
	private List<Country> items;
	private Country item;

	/**
	 * 
	 */
	public CountryBean() {
		log.info("Bean constructor called.");
	}

	/**
	 * 
	 */
	@PostConstruct
	private void postConstruct() {
		log.info("Bean @PostConstruct called.");
		state = "READ";
		items = controller.list();
	}

	/**
	 * Clears entity items
	 */
	public void clearItems() {
		if (items != null) {
			items.clear();
		}
	}

	/**
	 * Clears entity item
	 */
	public void clearItem() {
		try {
			// Instantiating via reflection was used here for generic purposes
			item = Country.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			FacesUtils.addI18nError("generic.bean.unableToCleanViewData");
		}
	}

	/**
	 * @param event
	 */
	public void create() {
		controller.create(item);
		items = controller.list();
		item = null;
	}

	/**
	 * @param event
	 */
	public void update() {
		controller.update(item);
		items = controller.list();
		item = null;
	}

	public void delete() {
		controller.delete(item.getId());
		items = controller.list();
		item = null;
	}

	/**
	 * 
	 */
	@PreDestroy
	private void preDestroy() {
		log.info("Bean @PreDestroy called.");
	}

	/*
	 * Getters and Setters
	 */

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Country> getItems() {
		return items;
	}

	public void setItems(List<Country> items) {
		this.items = items;
	}

	public Country getItem() {
		return item;
	}

	public void setItem(Country item) {
		this.item = item;
	}

}