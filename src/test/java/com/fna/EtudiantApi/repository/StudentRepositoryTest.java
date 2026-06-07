package com.fna.EtudiantApi.repository;

import com.fna.EtudiantApi.entities.Student;
import com.fna.EtudiantApi.repositories.StudentRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
//@SpringBootTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepo underTest;
    private List<Student> students;

    @BeforeEach
    void fillData() {
        students = List.of(
                new Student(null, "ngane nounai ", "bobo", "bobongane@gmail.com"),
                new Student(null, "enama nga ", "patrice", "patriceenamanga@gmail.com")
        );
        underTest.saveAll(students);
    }



    @Test
    void shouldFindStudentByFirstName() {
        List<Student> list = underTest.findByFirstNameContainingIgnoreCase("a");
        assertThat(list.size()).isEqualTo(3);

    }

    @Test
    void shouldReturnEmptyOptionalWhenEmailNotExiste() {
        String email= "bobo@gmail.com";
        Optional<Student> expected = underTest.findByEmail(email);
        assertThat(expected).isEmpty();
    }

}
