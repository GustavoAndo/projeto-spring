package com.projeto.teste.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SegurancaConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.
		authorizeRequests()
			.antMatchers("/cadastrarUsuarios").hasRole("ADMINISTRADOR")
			.antMatchers("/editarUsuarios/{id}").hasRole("ADMINISTRADOR")
			.antMatchers("/excluirUsuarios/{id}").hasRole("ADMINISTRADOR")
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
		.and()
		.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("gustavo")
				.password("123")
				.roles("ADMINISTRADOR")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
