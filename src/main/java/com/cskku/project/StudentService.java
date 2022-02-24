package com.cskku.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Student saveStudent(Student student) {
    	Student studentEncode = encodePassword(student);
        return studentRepository.save(studentEncode);
    }

    private Student encodePassword(Student student) {
    	student.setPassword(passwordEncoder.encode(student.getPassword()));
        return student;
    }
}
