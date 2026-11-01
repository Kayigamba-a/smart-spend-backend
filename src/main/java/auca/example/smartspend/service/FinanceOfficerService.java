package auca.example.smartspend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.ExpenseType;
import auca.example.smartspend.model.FinanceOfficer;
import auca.example.smartspend.repository.ExpenseTypeRepository;
import auca.example.smartspend.repository.FinanceOfficerRepository;

@Service
public class FinanceOfficerService {

    private final FinanceOfficerRepository financeOfficerRepository;
    private final ExpenseTypeRepository expenseTypeRepository;

    public FinanceOfficerService(
            FinanceOfficerRepository repository,
            ExpenseTypeRepository expenseTypeRepository) {
        this.financeOfficerRepository = repository;
        this.expenseTypeRepository = expenseTypeRepository;
    }

    public List<FinanceOfficer> getAllFinanceOfficers() {
        return financeOfficerRepository.findAll();
    }

    public FinanceOfficer saveFinanceOfficer(FinanceOfficer financeOfficer) {
        return financeOfficerRepository.save(financeOfficer);
    }

    public FinanceOfficer getFinanceOfficerById(UUID id) {
        return financeOfficerRepository.findById(id).orElse(null);
    }

    public FinanceOfficer updateFinanceOfficer(UUID id, FinanceOfficer financeOfficer) {

        FinanceOfficer existingFinanceOfficer = getFinanceOfficerById(id);

        if (existingFinanceOfficer != null) {

            existingFinanceOfficer.setFirstName(financeOfficer.getFirstName());
            existingFinanceOfficer.setLastName(financeOfficer.getLastName());
            existingFinanceOfficer.setEmail(financeOfficer.getEmail());
            existingFinanceOfficer.setCostCentre(financeOfficer.getCostCentre());
            existingFinanceOfficer.setExpenseTypes(financeOfficer.getExpenseTypes());

            return financeOfficerRepository.save(existingFinanceOfficer);
        }

        return null;
    }

    public boolean deleteFinanceOfficer(UUID id) {

        FinanceOfficer financeOfficer = getFinanceOfficerById(id);

        if (financeOfficer != null) {
            financeOfficerRepository.delete(financeOfficer);
            return true;
        }

        return false;
    }

    public List<FinanceOfficer> getFinanceOfficersByExpenseType(String name) {

        return financeOfficerRepository.findByExpenseTypeName(name);
    }

    public String addExpenseType(UUID financeOfficerId, UUID expenseTypeId) {

        FinanceOfficer financeOfficer = getFinanceOfficerById(financeOfficerId);

        if (financeOfficer == null) {
            return "The finance officer with that id does not exist";
        }

        ExpenseType expenseType = expenseTypeRepository.findById(expenseTypeId).orElse(null);

        if (expenseType == null) {
            return "That expense type does not exist";
        }

        for (ExpenseType existingExpenseType : financeOfficer.getExpenseTypes()) {
            if (existingExpenseType.getId().equals(expenseTypeId)) {
                return "The finance officer already has that expense type";
            }
        }

        financeOfficer.getExpenseTypes().add(expenseType);
        financeOfficerRepository.save(financeOfficer);
        return "Expense type added to the finance officer";
    }
}