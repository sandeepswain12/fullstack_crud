package com.crud.service.impl;


import com.crud.dtos.StudentReq;
import com.crud.dtos.StudentRes;
import com.crud.entity.Student;
import com.crud.exception.ResourceNotFoundException;
import com.crud.repository.StudentRepo;
import com.crud.service.StudentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public StudentRes createStudent(StudentReq studentReq) {
        String studentId = UUID.randomUUID().toString();
        Student student = new Student();
        student.setId(studentId);
        student.setName(studentReq.getName());
        student.setEmail(studentReq.getEmail());
        student.setAge(studentReq.getAge());
        student.setAddress(studentReq.getAddress());
        student.setMark(studentReq.getMark());
        student.setPassword(studentReq.getPassword());
        logger.info(student.toString());
        return modelMapper.map(studentRepo.save(student), StudentRes.class);
    }

    @Override
    public StudentRes updateStudent(StudentReq studentReq, String studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with id: " + studentId));
        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        student.setAddress(studentReq.getAddress());
        student.setMark(studentReq.getMark());
        student.setPassword(studentReq.getPassword());
        return modelMapper.map(studentRepo.save(student), StudentRes.class);
    }

    @Override
    public void deleteStudent(String studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with id: " + studentId));
        studentRepo.delete(student);
    }

    @Override
    public Page<StudentRes> findAllStudents(int pageNumber, int  pageSize, String sortBy, String sortOrder) {
        Sort sort = (sortOrder.equalsIgnoreCase("asc") ? (Sort.by(sortBy)).ascending(): Sort.by(sortBy).descending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Student> students = studentRepo.findAll(pageable);
        return students.map(student -> modelMapper.map(student, StudentRes.class));
    }

    @Override
    public StudentRes getStudentById(String studentId) {
        logger.info("getStudentById method invoked");
        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with id: " + studentId));
        logger.info("getStudentById method invoked");
        return modelMapper.map(student, StudentRes.class);
    }

    @Override
    public StudentRes getStudentByName(String studentName) {
        Student student = studentRepo.findStudentByName(studentName).orElseThrow(()-> new ResourceNotFoundException("Resource not found with name: " + studentName));
        return modelMapper.map(student, StudentRes.class);
    }

    @Override
    public StudentRes getStudentByEmail(String studentEmail) {
        Student student = studentRepo.findStudentByEmail(studentEmail).orElseThrow(()-> new ResourceNotFoundException("Resource not found with email: " + studentEmail));
        return modelMapper.map(student, StudentRes.class);
    }
}
