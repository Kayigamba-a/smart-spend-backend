
package auca.example.smartspend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.ExpenseType;
import auca.example.smartspend.repository.ExpenseTypeRepository;

@Service
public class ExpenseTypeService {

    private final ExpenseTypeRepository expenseTypeRepository;

    public ExpenseTypeService(ExpenseTypeRepository repository) {
        this.expenseTypeRepository = repository;
    }

    public List<ExpenseType> getAllExpenseTypes() {
        return expenseTypeRepository.findAll();
    }

    public ExpenseType saveExpenseType(ExpenseType expenseType) {
        return expenseTypeRepository.save(expenseType);
    }

    public ExpenseType getExpenseTypeById(UUID id) {
        return expenseTypeRepository.findById(id).orElse(null);
    }

    public ExpenseType updateExpenseType(UUID id, ExpenseType expenseType) {

        ExpenseType existingExpenseType = getExpenseTypeById(id);

        if (existingExpenseType != null) {

            existingExpenseType.setName(expenseType.getName());

            return expenseTypeRepository.save(existingExpenseType);
        }

        return null;
    }

    public boolean deleteExpenseType(UUID id) {

        ExpenseType expenseType = getExpenseTypeById(id);

        if (expenseType != null) {
            expenseTypeRepository.delete(expenseType);
            return true;
        }

        return false;
    }

    public List<ExpenseType> getUnusedExpenseTypes() {

        return expenseTypeRepository.findUnusedExpenseTypes();
    }
}
