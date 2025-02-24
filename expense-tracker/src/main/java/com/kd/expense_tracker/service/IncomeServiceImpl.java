package com.kd.expense_tracker.service;

import com.kd.expense_tracker.dto.IncomeDTO;
import com.kd.expense_tracker.entity.Income;
import com.kd.expense_tracker.repository.IncomeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService{

    private final IncomeRepository incomeRepository;

    public Income postIncome(IncomeDTO incomeDTO){
        return saveOrUpdateIncome(new Income(),incomeDTO);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO){
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());
        income.setAmount(incomeDTO.getAmount());

        return incomeRepository.save(income);

    }

    public Income updateIncome(Long id,IncomeDTO incomeDTO){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            return saveOrUpdateIncome(optionalIncome.get(),incomeDTO);
        }else{
            throw new EntityNotFoundException("Entity is not present with id"+ id);
        }
    }

    public List<IncomeDTO> getAllIncome(){
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate))
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public IncomeDTO getIncomeById(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDto();
        }else {
            throw new EntityNotFoundException("Entity is not present with id" + id);
        }
    }
    public void deleteIncome(Long id){
        Optional<Income> optionalIncome = incomeRepository.findById(id);
        if(optionalIncome.isPresent()){
            incomeRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Entity is not present with id" + id);
        }
    }

}
