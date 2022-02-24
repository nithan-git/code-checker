package com.cskku.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class TeacherUserDetailsService implements UserDetailsService {
	
	@Autowired
	private TeacherRepository teacherRepository; 

	@Override
	public UserDetails loadUserByUsername(String teacher_id) throws UsernameNotFoundException {
		Teacher teacher = teacherRepository.findById(teacher_id).orElse(null);
		if (teacher == null) {
            throw new UsernameNotFoundException("Could not find teacher user");
        }
		return new TeacherUserDetails(teacher);
	}

}
