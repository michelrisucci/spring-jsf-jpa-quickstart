package com.github.michelrisucci.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.github.michelrisucci.controller.CodeController;
import com.github.michelrisucci.jsf.bean.support.InjectionAwareBean;
import com.github.michelrisucci.jsf.model.Code;
import com.github.michelrisucci.jsf.util.FacesUtils;

/**
 * @author Michel Risucci
 */
@ManagedBean
@ViewScoped
public class CodeBean extends InjectionAwareBean {

	@Inject
	private CodeController controller;

	private List<Code> items;

	/**
	 * 
	 */
	public CodeBean() {
		log.info("Bean constructor called.");
	}

	/**
	 * 
	 */
	@PostConstruct
	private void postConstruct() {
		log.info("Bean @PostConstruct called.");
		items = controller.list();
	}

	/**
	 * 
	 */
	@PreDestroy
	private void preDestroy() {
		log.info("Bean @PreDestroy called.");
	}

	public void growlInfo() {
		FacesUtils.addI18nInfo("generic.operation.success");
	}

	public void growlError() {
		FacesUtils.addI18nError("generic.operation.fail");
	}

	public List<Code> getItems() {
		return items;
	}

	public void setItems(List<Code> items) {
		this.items = items;
	}

}