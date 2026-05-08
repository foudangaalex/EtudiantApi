package com.fna.EtudiantApi.repositories;

import com.fna.EtudiantApi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepo extends JpaRepository<Student,Integer> {
}
