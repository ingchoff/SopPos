package com.project.authenservice.repository;

import com.project.authenservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    List<UserModel> findByIdIn(List<Long> userIds);

    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);

}
