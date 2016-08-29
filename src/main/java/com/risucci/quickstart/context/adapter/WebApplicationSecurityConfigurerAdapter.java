package com.risucci.quickstart.context.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = false)
public class WebApplicationSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Security policy: login page.
		http.formLogin() //
				.loginPage("/public/login.xhtml") //
				.loginProcessingUrl("/public/login.xhtml") //
				.defaultSuccessUrl("/protected/index.xhtml") //
				.failureUrl("/public/login.xhtml?source=loginError") //
				.permitAll();

		// Security policy: public available paths
		http.authorizeRequests() //
				.antMatchers("/").permitAll() //
				.antMatchers("/index.html").permitAll() //
				.antMatchers("/public/**", "/resources/**", "/javax.faces.resource/**").permitAll();

		// Security policy: protecting all remaining paths.
		http.authorizeRequests() //
				.anyRequest().authenticated();

		// Logout handling.
		http.logout() //
				.logoutUrl("/logout") //
				.logoutSuccessUrl("/public/login.xhtml?source=logout") //
				.permitAll();

		/*
		 * Security policy: disabling CSRF protection. We must use it, but for
		 * now, I prefer to disable it to prevent more configuration overloads.
		 */
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //
				.withUser("admin") //
				.password("123456") //
				.roles("ADMIN");
	}

}