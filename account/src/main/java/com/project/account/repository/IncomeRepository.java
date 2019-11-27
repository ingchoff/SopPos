package com.project.account.repository;


import com.project.account.model.IncomeModel;
import org.springframework.data.jpa.repository.JpaRepository;




public interface IncomeRepository extends JpaRepository<IncomeModel, Long> {
}
