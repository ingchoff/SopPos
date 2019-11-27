package com.project.account.account.repository;

import com.project.account.account.model.IncomeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface IncomeRepository extends JpaRepository<IncomeModel, Long> {
}
