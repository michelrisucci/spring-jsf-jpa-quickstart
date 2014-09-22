package com.github.michelrisucci.jsf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Michel Risucci
 */
@Entity
public class Code {

	private Long id;
	private int value;

	/**
	 * 
	 */
	public Code() {
	}

	/**
	 * @param value
	 */
	public Code(int value) {
		this.value = value;
	}

	/**
	 * @param id
	 * @param value
	 */
	public Code(Long id, int value) {
		this.id = id;
		this.value = value;
	}

	/**
	 * @return
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
