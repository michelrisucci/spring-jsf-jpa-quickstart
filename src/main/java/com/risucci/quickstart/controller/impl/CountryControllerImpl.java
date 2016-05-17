package com.risucci.quickstart.controller.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.risucci.quickstart.controller.CountryController;
import com.risucci.quickstart.jsf.model.Country;
import com.risucci.quickstart.repository.CountryRepository;

/**
 * @author Michel Risucci
 */
@Named
public class CountryControllerImpl implements CountryController {

	@Inject
	private CountryRepository repository;

	@Override
	public List<Country> list() {
		return repository.list();
	}

	@Override
	public Country read(Long id) {
		return repository.read(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Country country) {
		repository.create(country);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Country update(Country country) {
		return repository.update(country);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Country country) {
		repository.delete(country);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		repository.delete(id);
	}

}