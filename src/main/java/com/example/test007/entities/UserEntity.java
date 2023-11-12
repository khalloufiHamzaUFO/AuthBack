package com.example.test007.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="User")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name="nomEt")
    private String nomEt;
    @Column(name="prenomEt")
    private String prenomEt;
    @Column(name="cin")
    private long cin;
    @JsonIgnore
    @Column(name="password")
    private String password;
    @Column(name="role")
    private Roles role;
    @Column(name="ecole")
    private String ecole;
    @Column(name="dateNaissance")
    private LocalDate dateNaissance ;

//    @ManyToMany(mappedBy = "users" , cascade =  CascadeType.ALL)
//    private List<Reservation> reservations ;

}
