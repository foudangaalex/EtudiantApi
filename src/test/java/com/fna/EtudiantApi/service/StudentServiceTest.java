package com.fna.EtudiantApi.service;


import com.fna.EtudiantApi.dtos.StudentDTO;
import com.fna.EtudiantApi.entities.Student;
import com.fna.EtudiantApi.exceptions.EmailAlreadyExistException;
import com.fna.EtudiantApi.exceptions.EmailNotExistException;
import com.fna.EtudiantApi.exceptions.StudentNotFoundException;
import com.fna.EtudiantApi.mapper.StudentMapper;
import com.fna.EtudiantApi.repositories.StudentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepo repo;
    @Mock
    private StudentMapper mapper;
    @InjectMocks
    private StudentServiceImpl underTest;

    @Test
    void shouldThrowExceptionWhenEmailNotExist() {
        Student student = Student.builder().firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        when(repo.findByEmail(student.getEmail())).thenReturn(Optional.empty());
        assertThrows(EmailNotExistException.class, () -> {
         underTest.findStudentByEmail(student.getEmail());
        });
    }
    @Test
    void shouldThrowExceptionWhenEmailAlreadyExist() {
        StudentDTO studentDTO = StudentDTO.builder().firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        Student savedStudent = Student.builder()
                .id(1L)
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        Student student = Student.builder()
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        StudentDTO expected= StudentDTO.builder()
                .id(1L)
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        when(repo.findByEmail(student.getEmail())).thenReturn(Optional.of(savedStudent));
        when(mapper.toDto(student)).thenReturn(expected);
        assertThrows(EmailAlreadyExistException.class, () -> {
            StudentDTO result=underTest.saveStudent(studentDTO);
        });
    }
    @Test
    void shouldFindStudentByEmail() throws EmailNotExistException {
        Student student = Student.builder().firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        StudentDTO studentDTO = StudentDTO.builder().firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        when(repo.findByEmail(student.getEmail())).thenReturn(Optional.of(student));
        when(mapper.toDto(student)).thenReturn(studentDTO);
        StudentDTO byEmail=underTest.findStudentByEmail(student.getEmail());
        assertThat(byEmail).isNotNull();
        assertThat(byEmail).usingRecursiveComparison().ignoringFields("id").isEqualTo(studentDTO);
    }
    @Test
   void shouldSaveStudent() throws EmailAlreadyExistException {

        StudentDTO studentDTO = StudentDTO.builder()
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        Student student = Student.builder()
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        Student savedStudent = Student.builder()
                .id(1L)
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        StudentDTO expected = StudentDTO.builder()
                .id(1L)
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com").build();
        when(repo.findByEmail(student.getEmail())).thenReturn(Optional.empty());
        when(repo.save(student)).thenReturn(savedStudent);
        when(mapper.toDto(savedStudent)).thenReturn(expected);
        when(mapper.toEnty(expected)).thenReturn(savedStudent);
        StudentDTO saved = underTest.saveStudent(studentDTO);
        assertThat(saved).isInstanceOf(StudentDTO.class);
        assertThat(saved).usingRecursiveComparison().ignoringFields("id").isEqualTo(studentDTO);
    }
    @Test
    void shouldFindAllStudents(){
        List<Student> students=List.of(
                Student.builder().firstName("fouda nga").lastName("alex").email("foudangaalex@gmail.com").build(), Student.builder().firstName("enama nga").lastName("patrice").email("patriceenama@gmail.com").build()
        );
        List<StudentDTO> studentsDTO=List.of(
                StudentDTO.builder().firstName("fouda nga").lastName("alex").email("foudangaalex@gmail.com").build(), StudentDTO.builder().firstName("enama nga").lastName("patrice").email("patriceenama@gmail.com").build()
        );
        when(repo.findAll()).thenReturn(students);
        when(mapper.toDtoList(students)).thenReturn(studentsDTO);
        List<StudentDTO> listDTO=underTest.findAllStudents();
        assertThat(listDTO.size()).isEqualTo(2);
        assertThat(listDTO).usingRecursiveComparison().ignoringFields("id").isEqualTo(studentsDTO);
    }

    @Test
    void shouldDeleteStudent(){
        Long studentId=1L;
        Student student=Student.builder()
                .id(1L)
                .firstName("esamye")
                .lastName("joseph romeo")
                .email("jresamye@gmail.com")
                .build();
        when(repo.findById(studentId)).thenReturn(Optional.of(student));
        underTest.deleteStudentById(studentId);
        verify(repo).deleteById(studentId);
    }
    @Test
   void shouldNotDeleteStudent(){
        Long studentId=99l;
        when(repo.findById(studentId)).thenReturn(Optional.empty());
        assertThrows(StudentNotFoundException.class,()->{
            underTest.deleteStudentById(studentId);
        });
   }
   void shouldUpdateStudent(){
        Long studentId=1L;

   }
   }

