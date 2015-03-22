package com.github.michelrisucci.repository.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.michelrisucci.jsf.model.Country;
import com.github.michelrisucci.repository.CountryRepository;

/**
 * @author Michel Risucci
 */
@Named
public class CountryRepositoryImpl implements CountryRepository {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<Country> list() {
		StringBuilder jpql = new StringBuilder() //
				.append("SELECT x ") //
				.append("FROM " + Country.class.getName() + " x ") //
				.append("ORDER BY x.id ASC ");

		return em.createQuery(jpql.toString(), Country.class).getResultList();
	}

	@Override
	public Country read(Long id) {
		return em.find(Country.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void create(Country country) {
		em.persist(country);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Country update(Country country) {
		return em.merge(country);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(Country country) {
		em.remove(country);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(Long id) {
		Country country = em.getReference(Country.class, id);
		delete(country);
	}

}