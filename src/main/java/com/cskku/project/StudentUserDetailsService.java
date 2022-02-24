package com.cskku.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class StudentUserDetailsService implements UserDetailsService {
	
	@Autowired
	private StudentRepository studentRepository; 

	@Override
	public UserDetails loadUserByUsername(String student_id) throws UsernameNotFoundException {
		Student student = studentRepository.findById(student_id).orElse(null);
		if (student == null) {
            throw new UsernameNotFoundException("Could not find student user");
        }
		return new StudentUserDetails(student);
	}

}
