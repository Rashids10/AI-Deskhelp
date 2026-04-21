package com.rashid.ai_helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepoSitory extends JpaRepository<JpaRepository, Long> {


    Optional<Rol> findbyName(ERole, name);



    
}
