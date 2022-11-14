package com.test.student.controllers;

import com.test.student.services.StudentService;
import com.test.student.models.SortingResponse;
import com.test.student.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/bubbleSort")
    SortingResponse getBubbleSortByGrade() {
        return studentService.getBubbleSort();
    }

    @PostMapping()
    List<Student> saveNewStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

}
