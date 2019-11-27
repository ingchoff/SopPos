package com.project.authenservice.repository;

import com.project.authenservice.model.RoleModel;
import com.project.authenservice.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(RoleName roleName);
}
