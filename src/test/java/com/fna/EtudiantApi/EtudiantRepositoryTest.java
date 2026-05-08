package com.fna.EtudiantApi;

import com.fna.EtudiantApi.entities.Student;
import com.fna.EtudiantApi.repositories.EtudiantRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EtudiantRepositoryTest {
    @Mock
    private EtudiantRepo underTest;
    @Test
    void shouldSaveNewUser(){
        Student actual= Student.builder()
                .id(null)
                .firstName("fouda nga")
                .lastName("alex")
                .email("foudangaalex@gmail.com")
                .build();
        Student expected=underTest.save(actual);
        assertThat(actual).isEqualTo(expected);
    }
}
