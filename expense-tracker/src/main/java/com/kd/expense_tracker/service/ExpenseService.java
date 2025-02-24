package com.kd.expense_tracker.service;

import com.kd.expense_tracker.dto.ExpenseDTO;
import com.kd.expense_tracker.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense postExpense(ExpenseDTO expenseDTO);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);

    public Expense updateExpense(Long id,ExpenseDTO expenseDTO);

    public void deleteExpense(Long id);
}
