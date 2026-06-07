package com.fna.EtudiantApi.mapper;

import com.fna.EtudiantApi.dtos.StudentDTO;
import com.fna.EtudiantApi.entities.Student;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEnty(StudentDTO studentDTO);
    StudentDTO toDto(Student student);
    List<StudentDTO> toDtoList(List<Student> students);
}
