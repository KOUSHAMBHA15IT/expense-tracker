package com.kd.expense_tracker.service;


import com.kd.expense_tracker.dto.ExpenseDTO;
import com.kd.expense_tracker.entity.Expense;
import com.kd.expense_tracker.repository.ExpenseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDTO expenseDTO){
        return saveOrUpdateExpense(new Expense(),expenseDTO);
    }


    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO){
        expense.setTitle(expenseDTO.getTitle());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());

        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id,ExpenseDTO expenseDTO){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            return saveOrUpdateExpense(optionalExpense.get(),expenseDTO);
        }else{
            throw new EntityNotFoundException("Entity is not present with id"+ id);
        }
    }


    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll().
                stream().sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }
    public Expense getExpenseById(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            return optionalExpense.get();
        }else{
            throw new EntityNotFoundException("Expense not present with id:" + id);
        }
    }

    public void deleteExpense(Long id){
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent()){
            expenseRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException("Expense not present with id:" + id);
        }
    }




}
