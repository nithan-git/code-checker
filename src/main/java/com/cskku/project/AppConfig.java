package com.cskku.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan
public class AppConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public StudentUserDetailsService studentUserDetailsService() {
		return new StudentUserDetailsService();
	}
	
	@Bean
	public TeacherUserDetailsService teacherUserDetailsService() {
		return new TeacherUserDetailsService();
	}
	
	
	
}
