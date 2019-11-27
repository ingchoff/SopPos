package com.project.authenservice.repository;

import com.project.authenservice.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);

    Optional<UserModel> findByFname(String fname);

    Optional<UserModel> findBySname(String sname);

    Optional<UserModel> findByFnameOrSname(String fname, String sname);

    List<UserModel> findByIdIn(List<Long> userIds);

    Boolean existsByUsername(String username);


}
