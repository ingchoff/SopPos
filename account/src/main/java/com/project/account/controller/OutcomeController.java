package com.project.account.controller;


import com.project.account.exception.ResourceNotFoundException;
import com.project.account.model.OutcomeModel;
import com.project.account.repository.OutcomeRepository;
import com.project.account.service.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {

    @Autowired
    OutcomeService outcomeService;

    @GetMapping("/get")
    public List<OutcomeModel> getAllOutcomeModel(){
        return outcomeService.getAll();
    }

    @PostMapping("/create")
    public OutcomeModel createOutcome(@Valid @RequestBody OutcomeModel outcomeModel){
        return outcomeService.createOutcome(outcomeModel);
    }

    @GetMapping("/get/{id}")
    public OutcomeModel getOneOutcome(@PathVariable(name = "id") Long outcomeId){
        return outcomeService.getOutcomeById(outcomeId);
    }

    @GetMapping("/get/alloutcome")
    public Double getAllmoney(){
        return outcomeService.getAllOutcome();
    }

    @GetMapping("/get/dateoutcome/{date}")
    public Double getDateOutcome(@PathVariable(name = "date")String date){
        return outcomeService.getDateOutcome(10, date);
    }

    @GetMapping("/get/monthoutcome/{month}")
    public Double getMonthOutcome(@PathVariable(name = "month")String month){
        return outcomeService.getDateOutcome(7, month);
    }

    @GetMapping("/get/yearoutcome/{year}")
    public Double getYearOutcome(@PathVariable(name = "year")String year){
        return outcomeService.getDateOutcome(4, year);
    }

    @GetMapping("/get/list/date/{date}")
    public List<OutcomeModel> getListDate(@PathVariable(name = "date")String date){
        return outcomeService.getListOutcome(10, date);
    }

    @GetMapping("/get/list/month/{month}")
    public List<OutcomeModel> getListMonth(@PathVariable(name = "month")String month){
        return outcomeService.getListOutcome(7, month);
    }

    @GetMapping("/get/list/year/{year}")
    public List<OutcomeModel> getListYear(@PathVariable(name = "year")String year){
        return outcomeService.getListOutcome(4, year);
    }

    @GetMapping("/get/mostoutcome/date/{date}")
    public OutcomeModel getMostDateIncome(@PathVariable(name = "date")String date){
        return outcomeService.getMostOutcome(10,date);
    }

    @GetMapping("/get/mostoutcome/month/{month}")
    public OutcomeModel getMostMonthIncome(@PathVariable(name = "month")String month){
        return outcomeService.getMostOutcome(7,month);
    }

    @GetMapping("/get/mostoutcome/year/{year}")
    public OutcomeModel getMostYearIncome(@PathVariable(name = "year")String year){
        return outcomeService.getMostOutcome(4,year);
    }

}

