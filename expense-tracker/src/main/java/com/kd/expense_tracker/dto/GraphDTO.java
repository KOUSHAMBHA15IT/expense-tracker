package com.kd.expense_tracker.dto;

import com.kd.expense_tracker.entity.Expense;
import com.kd.expense_tracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {
    private List<Expense> expenseList;
    private List<Income> incomeList;

}
