package com.risucci.quickstart.jsf.bean;

import static com.risucci.quickstart.jsf.util.FacesMessageSeverity.ERROR;
import static com.risucci.quickstart.jsf.util.FacesMessageSeverity.INFO;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	private int pageSize;
	private int pageIndex;
	private Page<Country> items;
	private Country item;

	@PostConstruct
	private void postConstruct() {
		pageSize = 10;
		pageIndex = 0;

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		Map<String, String> rp = ec.getRequestParameterMap();

		String id = rp.get("id");
		if (id != null) {
			this.item = repository.findOne(Long.valueOf(id));
		} else {
			eraseFilter();
		}
	}

	/*
	 * Actions
	 */

	public void save(AjaxBehaviorEvent event) {
		try {
			item = repository.save(item);

			FacesUtils.addI18nGlobalMessage(INFO, "operations.success.save", item.getName());
			FacesUtils.redirect("/protected/manage/country-list.xhtml");
		} catch (Exception e) {
			FacesUtils.addI18nGlobalMessage(ERROR, "operations.failWithDetails", e.getMessage());
		}
	}

	public void remove(AjaxBehaviorEvent event) {
		try {
			repository.delete(item.getId());

			FacesUtils.addI18nGlobalMessage(INFO, "operations.success.remove", item.getName());
			FacesUtils.redirect("/protected/manage/country-list.xhtml");
		} catch (Exception e) {
			FacesUtils.addI18nGlobalMessage(ERROR, "operations.failWithDetails", e.getMessage());
		}
	}

	public void filter() {
		// Nullifying empty values
		if (StringUtils.isEmpty(item.getName())) {
			item.setName(null);
		}
		if (StringUtils.isEmpty(item.getAcronym())) {
			item.setAcronym(null);
		}

		// Counting page with conditionals
		Example<Country> example = Example.of(item, MATCHER);
		long count = repository.count(example);

		// If first FETCH is an index out of count range, reset page index
		int firstFetchOnPage = pageIndex * pageSize;
		if (firstFetchOnPage > count) {
			pageIndex = 0;
		}

		// Filtering page with conditionals
		PageRequest pageRequest = new PageRequest(pageIndex, pageSize);
		items = repository.findAll(example, pageRequest);
	}

	public void eraseFilter() {
		this.item = new Country();
		this.filter();
	}

	/*
	 * Getters and Setters
	 */

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Page<Country> getItems() {
		return items;
	}

	public void setItems(Page<Country> items) {
		this.items = items;
	}

	public Country getItem() {
		return item;
	}

	public void setItem(Country item) {
		this.item = item;
	}

}