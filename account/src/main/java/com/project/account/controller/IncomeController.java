package com.project.account.controller;



import com.project.account.exception.ResourceNotFoundException;
import com.project.account.model.IncomeModel;
import com.project.account.repository.IncomeRepository;
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
    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    IncomeRepository incomeRepository;

    @GetMapping("/get")
    public List<IncomeModel> getAllIncome() {
        return incomeRepository.findAll();
    }

    @PostMapping("/create")
    public IncomeModel createIncome(@Valid @RequestBody IncomeModel incomeModel){
        return incomeRepository.save(incomeModel);
    }

    @GetMapping("/get/{id}")
    public IncomeModel getOneIncome(@PathVariable(value = "id") Long incomeId) {
        return incomeRepository.findById(incomeId)
                .orElseThrow(() -> new ResourceNotFoundException("IncomeModel", "id", incomeId));
    }

    @GetMapping("/get/allincome")
    public Double getAllmoney(){
        List<IncomeModel> allIncome = incomeRepository.findAll();
        Double sum = 0.0;
        for (IncomeModel temp: allIncome) {
            sum += temp.getPrice();
        }
        return sum;
    }
    @GetMapping("/get/dateincome/{date}")
    public Double getDateincome(@PathVariable(name = "date") String date) throws ParseException {
        Double sum =0.0;
        List<IncomeModel> allincome = incomeRepository.findAll();
        // cann't figure out how to use findBy
        for (IncomeModel temp: allincome) {
            if (formatdate.format(temp.getCreatedAt()).equals(date)){
                sum += temp.getPrice();
            }
        }
        return sum;
    }

    @GetMapping("/get/monthincome/{month}")
    public Double getMonthincome(@PathVariable(name = "month")String month){
        Double sum = 0.0;
        String str = "";
        List<IncomeModel> allincome = incomeRepository.findAll();
        // cann't figure out how to use findBy
        for (IncomeModel temp : allincome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0,7);
            if(str.equals(month)){
                sum += temp.getPrice();
            }
        }
        return sum;
    }

    @GetMapping("/get/yearincome/{year}")
    public Double getYearincome(@PathVariable(name = "year")String year){
        Double sum = 0.0;
        String str = "";
        List<IncomeModel> allincome = incomeRepository.findAll();
        // cann't figure out how to use findBy
        for (IncomeModel temp : allincome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0, 4);
            if(str.equals(year)){
                sum += temp.getPrice();
            }
        }
        return sum;
    }

    @GetMapping("/get/list/date/{date}")
    public List<IncomeModel> getListDate(@PathVariable(name = "date")String date){
        List<IncomeModel> listoutcome = new ArrayList<IncomeModel>();
        List<IncomeModel> alloutcome = incomeRepository.findAll();
        for (IncomeModel temp: alloutcome) {
            if (formatdate.format(temp.getCreatedAt()).equals(date)){
                listoutcome.add(temp);
            }

        }
        return listoutcome;
    }

    @GetMapping("/get/list/month/{month}")
    public List<IncomeModel> getListMonth(@PathVariable(name = "month")String month){
        List<IncomeModel> listoutcome = new ArrayList<IncomeModel>();
        String str = "";
        List<IncomeModel> alloutcome = incomeRepository.findAll();
        for (IncomeModel temp: alloutcome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0,7);
            if (str.equals(month)){
                listoutcome.add(temp);
            }
        }
        return listoutcome;
    }

    @GetMapping("/get/list/year/{year}")
    public List<IncomeModel> getListYear(@PathVariable(name = "year")String year){
        List<IncomeModel> listoutcome = new ArrayList<IncomeModel>();
        String str = "";
        List<IncomeModel> alloutcome = incomeRepository.findAll();
        for (IncomeModel temp: alloutcome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0,4);
            if (str.equals(year)){
                listoutcome.add(temp);
            }
        }
        return listoutcome;
    }

    @GetMapping("/get/mostincome/date/{date}")
    public IncomeModel getMostDateIncome(@PathVariable(name = "date")String date){
        List<IncomeModel> allincome = incomeRepository.findAll();
        IncomeModel mostIncome = new IncomeModel();
        Integer count = 0;
        for ( IncomeModel temp: allincome) {
            if (formatdate.format(temp.getCreatedAt()).equals(date)){
                if (count != 0) {
                    if (temp.getPrice() > mostIncome.getPrice()) {
                        mostIncome = temp;
                    }
                }else {
                    mostIncome = temp;
                }
                count += 1;
            }
        }
        return mostIncome;
    }

    @GetMapping("/get/mostincome/month/{month}")
    public IncomeModel getMostMonthIncome(@PathVariable(name = "month")String month){
        List<IncomeModel> allincome = incomeRepository.findAll();
        IncomeModel mostIncome = new IncomeModel();
        Integer count = 0;
        for ( IncomeModel temp: allincome) {
            if (formatdate.format(temp.getCreatedAt()).substring(0,7).equals(month)){
                if (count != 0) {
                    if (temp.getPrice() > mostIncome.getPrice()) {
                        mostIncome = temp;
                    }
                }else {
                    mostIncome = temp;
                }
                count += 1;
            }
        }
        return mostIncome;
    }

    @GetMapping("/get/mostincome/year/{year}")
    public IncomeModel getMostYearIncome(@PathVariable(name = "year")String year){
        List<IncomeModel> allincome = incomeRepository.findAll();
        IncomeModel mostIncome = new IncomeModel();
        Integer count = 0;
        for ( IncomeModel temp: allincome) {
            if (formatdate.format(temp.getCreatedAt()).substring(0,4).equals(year)){
                if (count != 0) {
                    if (temp.getPrice() > mostIncome.getPrice()) {
                        mostIncome = temp;
                    }
                }else {
                    mostIncome = temp;
                }
                count += 1;
            }
        }
        return mostIncome;
    }


}
