package com.risucci.quickstart.repository;

import java.util.List;

import com.risucci.quickstart.jsf.model.Country;

/**
 * @author Michel Risucci
 */
public interface CountryRepository {

	/**
	 * Returns a list of {@link Country}s from database.
	 * 
	 * @return
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
	 * Deletes a {@link Country}
	 * 
	 * @param country
	 */
	void delete(Country country);

	/**
	 * Deletes a {@link Country}
	 * 
	 * @param country
	 */
	void delete(Long id);

}