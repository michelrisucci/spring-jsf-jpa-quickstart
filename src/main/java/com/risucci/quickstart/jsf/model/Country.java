package com.risucci.quickstart.jsf.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Michel Risucci
 */
@Entity
@Table(name = "country")
public class Country {

	private Long id;
	private String name;
	private String acronym;
	private Long population;

	/**
	 * Default no-arg constructor
	 */
	public Country() {
	}

	/**
	 * @param name
	 * @param acronym
	 * @param population
	 */
	public Country(String name, String acronym, long population) {
		this.name = name;
		this.acronym = acronym;
		this.population = Long.valueOf(population);
	}

	/**
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	@Basic(optional = false)
	@Column(name = "name", nullable = false, length = 64)
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	@Basic(optional = true)
	@Column(name = "acronym", nullable = true, length = 4)
	public String getAcronym() {
		return acronym;
	}

	/**
	 * @param acronym
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	/**
	 * @return
	 */
	@Basic(optional = true)
	@Column(name = "population", nullable = true)
	public Long getPopulation() {
		return population;
	}

	/**
	 * @param population
	 */
	public void setPopulation(Long population) {
		this.population = population;
	}

}