package com.example.test007.repository;

import com.example.test007.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    List<UserEntity> findEtudiantsByCin(long studentCIN);
    List<UserEntity> findEtudiantsByNomEtAndPrenomEt(String nom, String prenom);
    List<UserEntity> findEtudiantByEcole(String schoolName);
    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
