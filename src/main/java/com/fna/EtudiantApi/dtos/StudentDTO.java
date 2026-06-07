package com.fna.EtudiantApi.dtos;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString @Builder
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
