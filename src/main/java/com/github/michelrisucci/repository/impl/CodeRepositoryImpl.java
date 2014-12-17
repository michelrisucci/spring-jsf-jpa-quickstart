package com.github.michelrisucci.repository.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.michelrisucci.jsf.model.Code;
import com.github.michelrisucci.repository.CodeRepository;

/**
 * @author Michel Risucci
 */
@Named
public class CodeRepositoryImpl implements CodeRepository {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<Code> list() {
		String jpql = new StringBuilder() //
				.append("SELECT x ") //
				.append("FROM Code x ") //
				.toString();

		return em.createQuery(jpql, Code.class).getResultList();
	}

}