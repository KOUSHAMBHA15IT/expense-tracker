package com.kd.expense_tracker.service;

import com.kd.expense_tracker.dto.IncomeDTO;
import com.kd.expense_tracker.entity.Income;

import java.util.List;

public interface IncomeService {

    Income postIncome(IncomeDTO incomeDTO);
    public List<IncomeDTO> getAllIncome();
    public Income updateIncome(Long id,IncomeDTO incomeDTO);
    public IncomeDTO getIncomeById(Long id);
    public void deleteIncome(Long id);
}
