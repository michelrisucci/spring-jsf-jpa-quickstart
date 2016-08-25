package com.risucci.quickstart.jsf.bean;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.risucci.quickstart.jsf.model.Country;
import com.risucci.quickstart.jsf.util.FacesUtils;
import com.risucci.quickstart.repository.CountryRepository;

/**
 * Making the JSF bean extend {@link SpringBeanAutowiringSupport} is the
 * simplest way to seamlessly integrate JSF Context with Spring, enabling
 * features like DI via @{@link Inject} or @{@link Autowired}. Spring does not
 * provide a powerful built-in JSF integration module.
 * 
 * @author Michel Risucci
 */
@ManagedBean
@ViewScoped
public class CountryBean extends SpringBeanAutowiringSupport {

	private static final ExampleMatcher MATCHER = ExampleMatcher.matching() //
			.withStringMatcher(CONTAINING) //
			.withIgnoreCase() //
			.withMatcher("name", startsWith()) //
			.withMatcher("acronym", startsWith()) //
			.withMatcher("population", exact());

	@Autowired
	private CountryRepository repository;

	// Entity Handlers
	private List<Country> items;
	private Country item;

	@PostConstruct
	private void postConstruct() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		Map<String, String> rp = ec.getRequestParameterMap();

		String id = rp.get("id");
		if (id != null) {
			System.out.println("ID = " + id);
			this.item = repository.findOne(Long.valueOf(id));
		} else {
			eraseFilter(null);
		}
	}

	/*
	 * Actions
	 */

	public void save(AjaxBehaviorEvent event) {
		repository.save(item);
		FacesUtils.redirect("/protected/manage/country-list.xhtml");
	}

	public void delete(AjaxBehaviorEvent event) {
		repository.delete(item.getId());
	}

	public void filter(AjaxBehaviorEvent event) {
		// Nullifying empty values
		if (StringUtils.isEmpty(item.getName())) {
			item.setName(null);
		}
		if (StringUtils.isEmpty(item.getAcronym())) {
			item.setAcronym(null);
		}

		this.items = repository.findAll(Example.of(item, MATCHER));
	}

	public void eraseFilter(AjaxBehaviorEvent event) {
		this.item = new Country();
		this.filter(event);
	}

	/*
	 * Getters and Setters
	 */

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