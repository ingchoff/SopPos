package com.project.account.controller;

import com.project.account.model.OutcomeModel;
import com.project.account.service.IncomeService;
import com.project.account.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    IncomeService incomeService;

    @Autowired
    OutcomeService outcomeService;

    @GetMapping("/get/profit/all")
    public Double getAllprofit(){
        return incomeService.getAllIncome() - outcomeService.getAllOutcome();
    }

    @GetMapping("/get/profit/date/{date}")
    public Double getDateprofit(@PathVariable(name = "date")String date){
        return incomeService.getDateIncome(10,date) - outcomeService.getDateOutcome(10,date);
    }

    @GetMapping("/get/profit/month/{month}")
    public Double getMonthprofit(@PathVariable(name = "month")String month){
        return incomeService.getDateIncome(7,month) - outcomeService.getDateOutcome(7,month);
    }

    @GetMapping("/get/profit/year/{year}")
    public Double getYearprofit(@PathVariable(name = "year")String year){
        return incomeService.getDateIncome(4,year) - outcomeService.getDateOutcome(4,year);
    }


}
