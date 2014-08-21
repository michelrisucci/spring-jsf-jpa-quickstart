package com.github.michelrisucci.jsf.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.michelrisucci.jsf.model.Code;
import com.github.michelrisucci.jsf.util.FacesUtils;

@ManagedBean
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

	public void growlInfo() {
		FacesUtils.addI18nInfo("generic.operation.success");
	}

	public void growlError() {
		FacesUtils.addI18nError("generic.operation.fail");
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

}