package com.mateus.entities;

import com.mateus.constants.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private Gender sex;

    private LocalDate birthdate;

    private String email;

    private String password;

    private boolean active;

    @OneToMany
    @JoinColumn(name = "CUSTOMER_ID")
    private List<Address> addresses =  new ArrayList<>();

    public void setAddresses(Address address){
        addresses.add(address);
    }

}
