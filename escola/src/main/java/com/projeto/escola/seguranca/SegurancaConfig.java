package com.projeto.escola.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SegurancaConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	public void configAutenticacao(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
			.dataSource(dataSource)
			.usersByUsernameQuery("select username, password, ativo from usuario where username=?")
			.authoritiesByUsernameQuery("select username, nivel_acesso from usuario where username=?");
	}
	
	String[] staticResources = {
	        "/css/**",
	        "/img/**",
	        "/js/**",};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.
		authorizeRequests()
			.antMatchers(staticResources).permitAll()
//			.antMatchers("/notas", "/editarNotas/{id}", "/alunos").hasAnyRole("ADMINISTRADOR", "PROFESSOR")
//			.antMatchers("/usuarios", "/cadastrarUsuarios", "/editarUsuarios/{id}", "/excluirUsuarios/{id}", "/aprovarMensagens", "/editarAlunos/{id}", "/").hasRole("ADMINISTRADOR")
//			.antMatchers("/mensagem").hasRole("PROFESSOR")
//			.antMatchers("/minhasNotas", "/contato").hasRole("ALUNO")
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
}
