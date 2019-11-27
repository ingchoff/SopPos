package com.project.account.controller;



import com.project.account.exception.ResourceNotFoundException;
import com.project.account.model.IncomeModel;
import com.project.account.repository.IncomeRepository;
import com.project.account.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

    @GetMapping("/get")
    public List<IncomeModel> getAllIncome() {
        return incomeService.getAll();
    }

    @PostMapping("/create")
    public IncomeModel createIncome(@Valid @RequestBody IncomeModel incomeModel){
        return incomeService.createIncome(incomeModel);
    }

    @GetMapping("/get/{id}")
    public IncomeModel getOneIncome(@PathVariable(value = "id") Long incomeId) {
        return incomeService.getIncomeById(incomeId);
    }

    @GetMapping("/get/allincome")
    public Double getAllmoney(){
        return incomeService.getAllIncome();
    }
    @GetMapping("/get/dateincome/{date}")
    public Double getDateincome(@PathVariable(name = "date") String date) throws ParseException {

        return incomeService.getDateIncome(10, date);
    }

    @GetMapping("/get/monthincome/{month}")
    public Double getMonthincome(@PathVariable(name = "month")String month){
        return incomeService.getDateIncome(7, month);
    }

    @GetMapping("/get/yearincome/{year}")
    public Double getYearincome(@PathVariable(name = "year")String year){
        return incomeService.getDateIncome(4, year);
    }

    @GetMapping("/get/list/date/{date}")
    public List<IncomeModel> getListDate(@PathVariable(name = "date")String date){
        return incomeService.getListIncome(10,date);
    }

    @GetMapping("/get/list/month/{month}")
    public List<IncomeModel> getListMonth(@PathVariable(name = "month")String month){
        return incomeService.getListIncome(7,month);
    }

    @GetMapping("/get/list/year/{year}")
    public List<IncomeModel> getListYear(@PathVariable(name = "year")String year){
        return incomeService.getListIncome(4,year);
    }

    @GetMapping("/get/mostincome/date/{date}")
    public IncomeModel getMostDateIncome(@PathVariable(name = "date")String date){
        return incomeService.getMostIncome(10, date);
    }

    @GetMapping("/get/mostincome/month/{month}")
    public IncomeModel getMostMonthIncome(@PathVariable(name = "month")String month){
        return incomeService.getMostIncome(7, month);
    }

    @GetMapping("/get/mostincome/year/{year}")
    public IncomeModel getMostYearIncome(@PathVariable(name = "year")String year){
        return incomeService.getMostIncome(4, year);
    }


}
