package com.github.michelrisucci.controller;

import java.util.List;

import com.github.michelrisucci.jsf.model.Country;

/**
 * @author Michel Risucci
 */
public interface CountryController {

	/**
	 * @return mock list of codes
	 */
	List<Country> list();

	/**
	 * Reads a {@link Country}
	 * 
	 * @param id
	 * @return
	 */
	Country read(Long id);

	/**
	 * Creates a new {@link Country}
	 * 
	 * @param country
	 */
	void create(Country country);

	/**
	 * Updates a {@link Country} and returns the updated one
	 * 
	 * @param country
	 * @return
	 */
	Country update(Country country);

	/**
	 * Deletes a {@link Country}.
	 * 
	 * @param country
	 */
	void delete(Country country);

	/**
	 * Deletes a {@link Country}.
	 * 
	 * @param id
	 */
	void delete(Long id);

}