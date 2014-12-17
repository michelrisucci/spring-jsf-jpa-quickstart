package com.github.michelrisucci.controller.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.michelrisucci.controller.CodeController;
import com.github.michelrisucci.jsf.model.Code;
import com.github.michelrisucci.repository.CodeRepository;

/**
 * @author Michel Risucci
 */
@Named
public class CodeControllerImpl implements CodeController {

	@Inject
	private CodeRepository repository;

	@Override
	public List<Code> list() {
		return repository.list();
	}

}