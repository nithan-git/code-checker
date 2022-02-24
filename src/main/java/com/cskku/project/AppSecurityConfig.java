package com.cskku.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Configuration
	@Order(1)
	public static class StudentWebSecurityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		PasswordEncoder passwordEncoder;
		
		@Autowired
		StudentUserDetailsService studentUserDetailsService;		
		
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(studentUserDetailsService).passwordEncoder(passwordEncoder);
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/student/**")
				.authorizeRequests()
					.antMatchers("/student/register").permitAll()
					.antMatchers("/student/js/register.js").permitAll()
					.anyRequest().hasAuthority("STUDENT")
					.and()
				.formLogin()
					.loginPage("/student/login").permitAll()
					.loginProcessingUrl("/student/login")
					.failureUrl("/student/login?error=loginError")
					.defaultSuccessUrl("/student/")
				    .usernameParameter("student_id")
				    .passwordParameter("password")
					.and()
				.logout()
					.logoutUrl("/student/performLogout")
					.logoutSuccessUrl("/")
					.deleteCookies("JSESSIONID")
					.and()
				.exceptionHandling()
					.accessDeniedPage("/403")
					.and()
				.csrf()
					.disable();
		}

			
	}
	
	@Configuration
	@Order(2)
	public static class TeacherWebSecurityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		PasswordEncoder passwordEncoder;
		
		@Autowired
		TeacherUserDetailsService teacherUserDetailsService;
		
		@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(teacherUserDetailsService).passwordEncoder(passwordEncoder);
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/teacher/**")
				.authorizeRequests()
					.antMatchers("/teacher/register").permitAll()
					.antMatchers("/teacher/js/register.js").permitAll()
					.anyRequest().hasAuthority("TEACHER")
					.and()
				.formLogin()
					.loginPage("/teacher/login").permitAll()
					.loginProcessingUrl("/teacher/login")
					.failureUrl("/teacher/login?error=loginError")
					.defaultSuccessUrl("/teacher/")
				    .usernameParameter("teacher_id")
				    .passwordParameter("password")
					.and()
				.logout()
					.logoutUrl("/teacher/performLogout")
					.logoutSuccessUrl("/")
					.deleteCookies("JSESSIONID")
					.and()
				.exceptionHandling()
					.accessDeniedPage("/403")
					.and()
				.csrf()
					.disable();
		}

			
	}

}
