package com.digitalhotelmanagement.digitalhotelmanagement.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.digitalhotelmanagement.digitalhotelmanagement.service.AdminService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private AdminService userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public WebSecurity(AdminService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.userDetailsService=userDetailsService;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
//			.authorizeRequests().antMatchers(HttpMethod.POST, "digital-hotel-ws/admin").permitAll() 
//				.anyRequest().authenticated()
				;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

}
