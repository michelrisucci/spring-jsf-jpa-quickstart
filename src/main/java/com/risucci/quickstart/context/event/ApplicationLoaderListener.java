package com.risucci.quickstart.context.event;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.risucci.quickstart.jsf.model.Country;
import com.risucci.quickstart.repository.CountryRepository;

/**
 * Spring application context loader that checks if database has initial data.
 * If not, it fills database with some mock data.
 * 
 * @author Michel Risucci
 */
@Component
public class ApplicationLoaderListener implements ApplicationListener<ContextRefreshedEvent> {

	private CountryRepository countryRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext context = event.getApplicationContext();
		if (context.getParent() != null) {
			this.countryRepository = context.getBean(CountryRepository.class);
			long count = countryRepository.count();
			if (count == 0) {
				initializeDatabaseWithMockData();
			}
		}
	}

	private void initializeDatabaseWithMockData() {
		List<Country> countries = Arrays.asList( //
				new Country("Brazil", "BRA", 200_000_000l), //
				new Country("United States", "USA", 315_000_000l), //
				new Country("Argentina", "ARG", 42_000_000l), //
				new Country("China", "CHN", 1_350_000_000l), //
				new Country("India", "IND", 1_250_000_000l));

		countryRepository.save(countries);
	}

}