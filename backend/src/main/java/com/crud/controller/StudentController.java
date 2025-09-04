package com.crud.controller;

import com.crud.dtos.ApiResponseMessage;
import com.crud.dtos.StudentReq;
import com.crud.dtos.StudentRes;
import com.crud.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin("http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/create")
    public ResponseEntity<StudentRes> createStudent(@Valid @RequestBody StudentReq studentReq) {
        StudentRes studentReq2 = studentService.createStudent(studentReq);
        return new ResponseEntity<>(studentReq2, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Page<StudentRes>> getAllStudents(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "6", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder
    ) {
        return new ResponseEntity<>(studentService.findAllStudents(pageNumber,pageSize,sortBy,sortOrder), HttpStatus.OK);
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<StudentRes> updateStudent(@Valid @RequestBody StudentReq studentReq,@PathVariable("studentId") String studentId) {
        StudentRes studentRes = studentService.updateStudent(studentReq,studentId);
        return new ResponseEntity<>(studentRes, HttpStatus.OK);
    }

    @GetMapping("/id/{studentId}")
    public ResponseEntity<StudentRes> getStudentById(@PathVariable("studentId") String studentID) {
        StudentRes studentRes = studentService.getStudentById(studentID);
        logger.info(studentRes.toString());
        return new ResponseEntity<>(studentRes, HttpStatus.OK);
    }

    @GetMapping("/name/{studentName}")
    public ResponseEntity<StudentRes> getStudentByName(@PathVariable("studentName") String studentName) {
        StudentRes studentRes = studentService.getStudentByName(studentName);
        return new ResponseEntity<>(studentRes, HttpStatus.OK);
    }

    @GetMapping("/email/{studentEmail}")
    public ResponseEntity<StudentRes> getStudentByEmail(@PathVariable("studentEmail") String studentEmail) {
        StudentRes studentRes = studentService.getStudentByEmail(studentEmail);
        return new ResponseEntity<>(studentRes, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<ApiResponseMessage> deleteStudentById(@PathVariable("studentId") String studentId) {
        studentService.deleteStudent(studentId);
        ApiResponseMessage apiResponseMessage = new ApiResponseMessage();
        apiResponseMessage.setMessage("Delete student successfully");
        apiResponseMessage.setStatus(HttpStatus.OK);
        apiResponseMessage.setSuccess(true);
        return new ResponseEntity<>(apiResponseMessage,HttpStatus.OK);
    }

}
