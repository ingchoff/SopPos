package com.project.account.account.controller;


import com.project.account.account.model.IncomeModel;
import com.project.account.account.model.OutcomeModel;
import com.project.account.account.repository.IncomeRepository;
import com.project.account.account.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    IncomeRepository incomeRepository;
//
    @Autowired
    OutcomeRepository outcomeRepository;

    @GetMapping("/get/date/{date}")
    public Double getDateProfit(@PathVariable(name = "date")String date){
        List<IncomeModel> allincome = incomeRepository.findAll();
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        Double income = 0.0;
        Double outcome = 0.0;
        for (IncomeModel temp: allincome) {
            if (formatdate.format(temp.getCreatedAt()).equals(date)){
                income += temp.getPrice();
            }
        }
        for (OutcomeModel temp: alloutcome) {
            if(formatdate.format(temp.getCreatedAt()).equals(date)){
                outcome += temp.getPrice();
            }
        }
        return income-outcome;
    }

    @GetMapping("/get/month/{month}")
    public Double getMonthProfit(@PathVariable(name = "month")String month){
        List<IncomeModel> allincome = incomeRepository.findAll();
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        Double income = 0.0;
        Double outcome = 0.0;
        for (IncomeModel temp: allincome) {
            if (formatdate.format(temp.getCreatedAt()).substring(0,7).equals(month)){
                income += temp.getPrice();
            }
        }
        for (OutcomeModel temp: alloutcome) {
            if(formatdate.format(temp.getCreatedAt()).substring(0,7).equals(month)){
                outcome += temp.getPrice();
            }
        }
        return income-outcome;
    }

    @GetMapping("/get/year/{year}")
    public Double getYearProfit(@PathVariable(name = "year")String year){
        List<IncomeModel> allincome = incomeRepository.findAll();
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        Double income = 0.0;
        Double outcome = 0.0;
        for (IncomeModel temp: allincome) {
            if (formatdate.format(temp.getCreatedAt()).substring(0,4).equals(year)){
                income += temp.getPrice();
            }
        }
        for (OutcomeModel temp: alloutcome) {
            if(formatdate.format(temp.getCreatedAt()).substring(0,4).equals(year)){
                outcome += temp.getPrice();
            }
        }
        return income-outcome;
    }
}
