package com.project.account.account.repository;

import com.project.account.account.model.OutcomeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface OutcomeRepository extends JpaRepository<OutcomeModel, Long> {
//    List<OutcomeModel> findByDate(Date publicationDate);
}
