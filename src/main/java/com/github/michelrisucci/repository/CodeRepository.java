package com.github.michelrisucci.repository;

import java.util.List;

import com.github.michelrisucci.jsf.model.Code;

/**
 * @author Michel Risucci
 */
public interface CodeRepository {

	/**
	 * Returns a list of {@link Code}s from database.
	 * 
	 * @return
	 */
	List<Code> list();

}