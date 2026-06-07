package com.fna.EtudiantApi.service;

import com.fna.EtudiantApi.dtos.StudentDTO;

import java.util.List;

public interface StudentServiceI {
    StudentDTO saveStudent(StudentDTO student);

    StudentDTO findStudentByEmail(String email) ;

    List<StudentDTO> findAllStudents();

    void deleteStudentById(Long studentId);
}
