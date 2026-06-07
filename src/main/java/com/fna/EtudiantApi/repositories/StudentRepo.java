package com.fna.EtudiantApi.repositories;

import com.fna.EtudiantApi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameContainingIgnoreCase(String keyword);
    Optional<Student> findByEmail(String email);
}

