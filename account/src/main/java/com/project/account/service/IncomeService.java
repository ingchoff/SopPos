package com.project.account.service;


import com.project.account.exception.ResourceNotFoundException;
import com.project.account.model.IncomeModel;
import com.project.account.repository.IncomeRepository;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeService {
    private SimpleDateFormat formatDate;
    private IncomeRepository incomeRepository;

    public IncomeService(IncomeRepository repo){
        this.incomeRepository = repo;
        this.formatDate = new SimpleDateFormat("yyyy-MM-dd");
    }

    public List<IncomeModel> getAll(){
        return incomeRepository.findAll();
    }

    public IncomeModel createIncome(IncomeModel income){
        return incomeRepository.save(income);
    }

    public IncomeModel getIncomeById(Long id){
        return incomeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("IncomeModel","id",id));
    }

    public Double getAllIncome(){
        List<IncomeModel> income = getAll();
        Double sum = 0.0;
        for (IncomeModel temp: income) {
            sum += temp.getPrice();
        }
        return sum;
    }

    public Double getDateIncome(Integer str, String date){
        List<IncomeModel> income = getAll();
        Double sum = 0.0;
        for (IncomeModel temp: income) {
            if (checkDate(str, date, temp)){
                sum += temp.getPrice();
            }
        }
        return sum;
    }

    public List<IncomeModel> getListIncome(Integer str, String date){
        List<IncomeModel> allincome = getAll();
        List<IncomeModel> listincom = new ArrayList<IncomeModel>();
        for (IncomeModel temp: allincome) {
            if (checkDate(str, date, temp)){
                listincom.add(temp);
            }
        }
        return listincom;
    }
    public IncomeModel getMostIncome(Integer str, String date){
        List<IncomeModel> allincome = getAll();
        IncomeModel mostIncome = new IncomeModel();
        Integer count = 0;
        for ( IncomeModel temp: allincome) {
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

    private Boolean checkDate(Integer str, String date, IncomeModel temp){
        return formatDate.format(temp.getCreatedAt()).substring(0,str).equals(date);
    }
}
