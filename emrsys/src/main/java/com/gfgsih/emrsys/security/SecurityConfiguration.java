package com.gfgsih.emrsys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	private DoctorAuthService docauthserv;
	
	@Autowired
	public SecurityConfiguration(DoctorAuthService docauthserv) {
		super();
		this.docauthserv=docauthserv;
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.requestMatchers("/login","/register","/css/**","/js/**","/images/**").permitAll()
			.requestMatchers("/dashboard","/","/patient/register","/dashboard/id").authenticated()
			.and()
			.formLogin(form -> form
					.loginPage("/login")
					.defaultSuccessUrl("/dashboard?success")
					.loginProcessingUrl("/login")
					.failureUrl("/login?error")
					.permitAll()
					)
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
					);
		return http.build();
	}
	
	public void configure(AuthenticationManagerBuilder authbuilder) throws Exception{
		authbuilder.userDetailsService(docauthserv).passwordEncoder(passwordEncoder()); 
	}
}
