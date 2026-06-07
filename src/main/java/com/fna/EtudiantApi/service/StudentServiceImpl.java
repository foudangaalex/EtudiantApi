package com.fna.EtudiantApi.service;

import com.fna.EtudiantApi.dtos.StudentDTO;
import com.fna.EtudiantApi.entities.Student;
import com.fna.EtudiantApi.exceptions.EmailAlreadyExistException;
import com.fna.EtudiantApi.exceptions.EmailNotExistException;
import com.fna.EtudiantApi.exceptions.StudentNotFoundException;
import com.fna.EtudiantApi.mapper.StudentMapper;
import com.fna.EtudiantApi.repositories.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentServiceI {
    private StudentRepo repo;
    private StudentMapper mapper;
    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        repo.findByEmail(studentDTO.getEmail())
                .ifPresent(s -> {throw new EmailAlreadyExistException("email already exist");});
        Student student = mapper.toEnty(studentDTO);

        Student save = repo.save(student);
        return mapper.toDto(save);
    }

    @Override
    public StudentDTO findStudentByEmail(String email) {
       return repo.findByEmail(email).map(mapper::toDto).orElseThrow(()-> {throw new EmailNotExistException("email not existe");});
    }

    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student> all = repo.findAll();
        return mapper.toDtoList(all);
    }

    @Override
    public void deleteStudentById(Long studentId) {
      repo.findById(studentId).orElseThrow(()->{throw new StudentNotFoundException("student not found");});
      repo.deleteById(studentId);
    }


}
