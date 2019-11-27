package com.project.account.account.controller;

import com.project.account.account.exception.ResourceNotFoundException;
import com.project.account.account.model.OutcomeModel;
import com.project.account.account.repository.OutcomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {
    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    OutcomeRepository outcomeRepository;

    @GetMapping("/get")
    public List<OutcomeModel> getAllOutcomeModle(){
        return outcomeRepository.findAll();
    }

    @PostMapping("/create")
    public OutcomeModel createOutcome(@Valid @RequestBody OutcomeModel outcomeModel){
        return outcomeRepository.save(outcomeModel);
    }

    @GetMapping("/get/{id}")
    public OutcomeModel getOneOutcome(@PathVariable(name = "id") Long outcomeId){
        return outcomeRepository.findById(outcomeId)
                .orElseThrow(() -> new ResourceNotFoundException("OutComeModel", "id", outcomeId));
    }

    @GetMapping("/get/alloutcome")
    public Double getAllmoney(){
        List<OutcomeModel> allIncome = outcomeRepository.findAll();
        Double sum = 0.0;
        for (OutcomeModel temp: allIncome) {
            sum += temp.getPrice();
        }
        return sum;
    }

    @GetMapping("/get/dateoutcome/{date}")
    public Double getDateOutcome(@PathVariable(name = "date")String date){
        Double sum = 0.0;
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        // cann't figure out how to use findBy
        for (OutcomeModel temp: alloutcome) {
            if (formatdate.format(temp.getCreatedAt()).equals(date))
            sum += temp.getPrice();
        }
        return sum;
    }

    @GetMapping("/get/monthoutcome/{month}")
    public Double getMonthOutcome(@PathVariable(name = "month")String month){
        Double sum = 0.0;
        String str = "";
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        // cann't figure out how to use findBy
        for (OutcomeModel temp: alloutcome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0,7);
            if (str.equals(month)){
                sum += temp.getPrice();
            }

        }
        return sum;
    }

    @GetMapping("/get/yearoutcome/{year}")
    public Double getYearOutcome(@PathVariable(name = "year")String year){
        Double sum = 0.0;
        String str = "";
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        // cann't figure out how to use findBy
        for (OutcomeModel temp: alloutcome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0,4);
            if (str.equals(year)){
                sum += temp.getPrice();
            }
        }
        return sum;
    }

    @GetMapping("/get/list/dateoutcome/{date}")
    public List<OutcomeModel> getListDate(@PathVariable(name = "date")String date){
        List<OutcomeModel> listoutcome = new ArrayList<OutcomeModel>();
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        for (OutcomeModel temp: alloutcome) {
            if (formatdate.format(temp.getCreatedAt()).equals(date)){
                listoutcome.add(temp);
            }

        }
        return listoutcome;
    }

    @GetMapping("/get/list/monthoutcome/{month}")
    public List<OutcomeModel> getListMonth(@PathVariable(name = "month")String month){
        List<OutcomeModel> listoutcome = new ArrayList<OutcomeModel>();
        String str = "";
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        for (OutcomeModel temp: alloutcome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0,7);
            if (str.equals(month)){
                listoutcome.add(temp);
            }
        }
        return listoutcome;
    }

    @GetMapping("/get/list/yearoutcome/{year}")
    public List<OutcomeModel> getListYear(@PathVariable(name = "year")String year){
        List<OutcomeModel> listoutcome = new ArrayList<OutcomeModel>();
        String str = "";
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        for (OutcomeModel temp: alloutcome) {
            str = formatdate.format(temp.getCreatedAt()).substring(0,4);
            if (str.equals(year)){
                listoutcome.add(temp);
            }
        }
        return listoutcome;
    }

    @GetMapping("/get/mostoutcome/date/{date}")
    public OutcomeModel getMostDateIncome(@PathVariable(name = "date")String date){
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        OutcomeModel mostOutcome = new OutcomeModel();
        Integer count = 0;
        for ( OutcomeModel temp: alloutcome) {
            if (formatdate.format(temp.getCreatedAt()).equals(date)){
                if (count != 0) {
                    if (temp.getPrice() > mostOutcome.getPrice()) {
                        mostOutcome = temp;
                    }
                }else {
                    mostOutcome = temp;
                }
                count += 1;
            }
        }
        return mostOutcome;
    }

    @GetMapping("/get/mostoutcome/month/{month}")
    public OutcomeModel getMostMonthIncome(@PathVariable(name = "month")String month){
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        OutcomeModel mostOutcome = new OutcomeModel();
        Integer count = 0;
        for ( OutcomeModel temp: alloutcome) {
            if (formatdate.format(temp.getCreatedAt()).substring(0,7).equals(month)){
                if (count != 0) {
                    if (temp.getPrice() > mostOutcome.getPrice()) {
                        mostOutcome = temp;
                    }
                }else {
                    mostOutcome = temp;
                }
                count += 1;
            }
        }
        return mostOutcome;
    }

    @GetMapping("/get/mostoutcome/year/{year}")
    public OutcomeModel getMostYearIncome(@PathVariable(name = "year")String year){
        List<OutcomeModel> alloutcome = outcomeRepository.findAll();
        OutcomeModel mostOutcome = new OutcomeModel();
        Integer count = 0;
        for ( OutcomeModel temp: alloutcome) {
            if (formatdate.format(temp.getCreatedAt()).substring(0,4).equals(year)){
                if (count != 0) {
                    if (temp.getPrice() > mostOutcome.getPrice()) {
                        mostOutcome = temp;
                    }
                }else {
                    mostOutcome = temp;
                }
                count += 1;
            }
        }
        return mostOutcome;
    }

}
