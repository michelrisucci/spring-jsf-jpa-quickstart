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
				new Country("Canada", "CAN", 31147000L), //
				new Country("Portugal", "PRT", 9997600L), //
				new Country("United States", "USA", 315_000_000l), //
				new Country("Argentina", "ARG", 42_000_000l), //
				new Country("China", "CHN", 1_350_000_000l), //
				new Country("India", "IND", 1_250_000_000l), //
				new Country("Afghanistan", "AFG", 22720000L), //
				new Country("Netherlands", "NLD", 15864000L), //
				new Country("Algeria", "DZA", 31471000L), //
				new Country("Angola", "AGO", 12878000L), //
				new Country("United Arab Emirates", "ARE", 2441000L), //
				new Country("United Kingdom", "GBR", 59623400L), //
				new Country("Hong Kong", "HKG", 6782000L), //
				new Country("Ireland", "IRL", 3775100L), //
				new Country("Italy", "ITA", 57680000L), //
				new Country("Jamaica", "JAM", 2583000L), //
				new Country("Japan", "JPN", 126714000L), //
				new Country("South Korea", "KOR", 46844000L), //
				new Country("Cuba", "CUB", 11201000L), //
				new Country("Lebanon", "LBN", 3282000L), //
				new Country("Mexico", "MEX", 98881000L), //
				new Country("France", "FRA", 59225700L) //
		);

		countryRepository.save(countries);
	}

}