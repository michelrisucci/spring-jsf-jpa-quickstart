package com.risucci.quickstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.risucci.quickstart.jsf.model.Country;

/**
 * @author Michel Risucci
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
}