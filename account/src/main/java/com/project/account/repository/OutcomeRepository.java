package com.project.account.repository;


import com.project.account.model.OutcomeModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OutcomeRepository extends JpaRepository<OutcomeModel, Long> {
}
