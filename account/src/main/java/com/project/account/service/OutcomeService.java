package com.project.account.service;

import com.project.account.exception.ResourceNotFoundException;
import com.project.account.model.OutcomeModel;
import com.project.account.repository.OutcomeRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutcomeService {
    private SimpleDateFormat formatDate;
    private OutcomeRepository outcomeRepository;

    public OutcomeService(OutcomeRepository repo){
        this.outcomeRepository = repo;
        this.formatDate = new SimpleDateFormat("yyyy-MM-dd");
    }

    public List<OutcomeModel> getAll(){
        return outcomeRepository.findAll();
    }

    public OutcomeModel createOutcome(OutcomeModel income){
        return outcomeRepository.save(income);
    }

    public OutcomeModel getOutcomeById(Long id){
        return outcomeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("IncomeModel","id",id));
    }

    public Double getAllOutcome(){
        List<OutcomeModel> income = getAll();
        Double sum = 0.0;
        for (OutcomeModel temp: income) {
            sum += temp.getPrice();
        }
        return sum;
    }

    public Double getDateOutcome(Integer str, String date){
        List<OutcomeModel> income = getAll();
        Double sum = 0.0;
        for (OutcomeModel temp: income) {
            if (checkDate(str, date, temp)){
                sum += temp.getPrice();
            }
        }
        return sum;
    }

    public List<OutcomeModel> getListOutcome(Integer str, String date){
        List<OutcomeModel> allincome = getAll();
        List<OutcomeModel> listincom = new ArrayList<OutcomeModel>();
        for (OutcomeModel temp: allincome) {
            if (checkDate(str, date, temp)){
                listincom.add(temp);
            }
        }
        return listincom;
    }
    public OutcomeModel getMostOutcome(Integer str, String date){
        List<OutcomeModel> allincome = getAll();
        OutcomeModel mostIncome = new OutcomeModel();
        Integer count = 0;
        for ( OutcomeModel temp: allincome) {
            if (checkDate(str, date, temp)){
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

    private Boolean checkDate(Integer str, String date, OutcomeModel temp){
        return formatDate.format(temp.getCreatedAt()).substring(0,str).equals(date);
    }
}
