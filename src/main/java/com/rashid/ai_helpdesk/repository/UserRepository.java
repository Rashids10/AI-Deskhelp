package com.rashid.ai_helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.rashid.ai_helpdesk.service.User;



@Repository


public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String username);



}


/*
 JpaRepository spart dir CRUD komplett


userRepository.save(user);        // speichern / updaten
userRepository.findById(1L);      // finden (Optional!)
userRepository.findAll();         // alle holen
userRepository.deleteById(1L);    // löschen
userRepository.existsById(1L);    // prüfen ob existiert




*/
