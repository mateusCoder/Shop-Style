package com.mateus.entities;

import com.mateus.constants.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active;

}
