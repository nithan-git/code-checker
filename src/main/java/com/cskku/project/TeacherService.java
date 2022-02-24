package com.cskku.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

	@Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Teacher saveTeacher(Teacher teacher) {
    	Teacher teacherEncode = encodePassword(teacher);
        return teacherRepository.save(teacherEncode);
    }

    private Teacher encodePassword(Teacher teacher) {
    	teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacher;
    }
}
