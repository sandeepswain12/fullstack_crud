package com.crud.service;

import com.crud.dtos.StudentReq;
import com.crud.dtos.StudentRes;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentRes createStudent(StudentReq studentReq);

    StudentRes updateStudent(StudentReq studentReq, String studentId);

    void deleteStudent(String studentId);

    Page<StudentRes> findAllStudents(int pageNumber, int  pageSize, String sortBy, String sortOrder);

    StudentRes getStudentById(String studentId);

    StudentRes getStudentByName(String studentName);

    StudentRes getStudentByEmail(String studentEmail);
}
