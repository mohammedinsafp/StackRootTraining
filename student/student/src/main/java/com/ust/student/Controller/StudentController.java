package com.ust.student.Controller;

import com.ust.student.Model.Student;
import com.ust.student.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping("/Students")
    public List<Student> getAllCostomers() {
        List<Student> students= studentService.getAllCustomer();
        return students;
    }
}
