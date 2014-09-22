package com.github.michelrisucci.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.michelrisucci.controller.CodeController;
import com.github.michelrisucci.controller.impl.CodeControllerImpl;
import com.github.michelrisucci.jsf.model.Code;
import com.github.michelrisucci.jsf.util.FacesUtils;

/**
 * @author Michel Risucci
 */
@ManagedBean
@ViewScoped
public class CodeBean {

	public static final Log log = LogFactory.getLog(CodeBean.class);

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
		controller = FacesUtils.getBeanFromClass(CodeControllerImpl.class);
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

	public CodeController getController() {
		return controller;
	}

	public void setController(CodeController controller) {
		this.controller = controller;
	}

	public List<Code> getItems() {
		return items;
	}

	public void setItems(List<Code> items) {
		this.items = items;
	}

}