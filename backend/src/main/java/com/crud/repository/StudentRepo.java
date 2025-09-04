package com.crud.repository;

import com.crud.dtos.StudentRes;
import com.crud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,String> {
    Optional<Student> findStudentByName(String studentName);

    Optional<Student> findStudentByEmail(String email);
}
