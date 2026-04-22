package com.rashid.ai_helpdesk.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rashid.ai_helpdesk.backend.entity.Erole;
import com.rashid.ai_helpdesk.backend.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(Erole adminName);
    Optional<RoleEntity> findByEmail(Erole adminEmail);
}
