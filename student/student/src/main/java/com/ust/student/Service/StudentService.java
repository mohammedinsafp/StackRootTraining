package com.ust.student.Service;

import com.ust.student.Model.Student;
import com.ust.student.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<Student> getAllCustomer(){
        return studentRepository.findAll();
    }
}
