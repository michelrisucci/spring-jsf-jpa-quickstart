package com.github.michelrisucci.jsf;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Named
@ViewScoped
public class CodeBean {

	public static final Log log = LogFactory.getLog(CodeBean.class);

	private int code;

	public CodeBean() {
		log.info("Bean constructor called.");
	}

	@PostConstruct
	private void postConstruct() {
		log.info("Bean @PostConstruct called.");

		code = 999;
	}

	@PreDestroy
	private void preDestroy() {
		log.info("Bean @PreDestroy called.");
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}