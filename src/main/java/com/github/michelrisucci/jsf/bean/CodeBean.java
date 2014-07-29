package com.github.michelrisucci.jsf.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.michelrisucci.jsf.model.Code;

@Named
@ViewScoped
public class CodeBean {

	public static final Log log = LogFactory.getLog(CodeBean.class);

	private Code code;

	public CodeBean() {
		log.info("Bean constructor called.");
	}

	@PostConstruct
	private void postConstruct() {
		log.info("Bean @PostConstruct called.");

		code = new Code();
		code.setValue(1234567890);
	}

	@PreDestroy
	private void preDestroy() {
		log.info("Bean @PreDestroy called.");
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

}