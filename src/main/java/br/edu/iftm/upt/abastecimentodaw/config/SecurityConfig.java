package br.edu.iftm.upt.abastecimentodaw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// Qualquer um pode fazer requisições para essas URLs
				.antMatchers("/css/**", "/js/**", "/", "/index.html").permitAll()
				// Um usuário autenticado e com o papel ADMIN pode fazer requisições para essas URLs	
				.antMatchers("/abastecimentos/novo").hasRole("ADMIN")
				//.antMatchers("URL").hasAnyRole("ADMIN", "USUARIO")
				.and()
			// A autenticação usando formulário está habilitada
			.formLogin()
				// Uma página de login customizada
				.loginPage("/login");
				// Define a URL para o caso de falha no login
				//.failureUrl("/login-error");
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin").password("{noop}admin").roles("ADMIN")
				.and()
				.withUser("user").password("{noop}user").roles("USUARIO");
				//.withUser("outro").password("12345").roles("ADMIN", "USUARIO");
	}

}
