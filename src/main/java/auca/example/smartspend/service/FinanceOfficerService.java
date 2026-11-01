package auca.example.smartspend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.CostCentre;
import auca.example.smartspend.model.ExpenseType;
import auca.example.smartspend.model.FinanceOfficer;
import auca.example.smartspend.repository.CostCentreRepository;
import auca.example.smartspend.repository.ExpenseTypeRepository;
import auca.example.smartspend.repository.FinanceOfficerRepository;

@Service
public class FinanceOfficerService {

    private final FinanceOfficerRepository financeOfficerRepository;
    private final ExpenseTypeRepository expenseTypeRepository;
    private final CostCentreRepository costCentreRepository;

    public FinanceOfficerService(
            FinanceOfficerRepository repository,
            ExpenseTypeRepository expenseTypeRepository,
            CostCentreRepository costCentreRepository) {
        this.financeOfficerRepository = repository;
        this.expenseTypeRepository = expenseTypeRepository;
        this.costCentreRepository = costCentreRepository;
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

    public String changeCostCentre(UUID financeOfficerId, int centreNumber) {

        FinanceOfficer financeOfficer = getFinanceOfficerById(financeOfficerId);

        if (financeOfficer == null) {
            return "The finance officer with that id does not exist";
        }

        CostCentre costCentre = costCentreRepository.findByCentreNumber(centreNumber);

        if (costCentre == null) {
            return "The cost centre with that centreNumber does not exist";
        }

        if (financeOfficerRepository.existsByCostCentre(costCentre)) {

            CostCentre current = financeOfficer.getCostCentre();

            if (current == null || !current.getId().equals(costCentre.getId())) {
                return "That cost centre is taken by another finance officer";
            }
        }

        financeOfficer.setCostCentre(costCentre);
        financeOfficerRepository.save(financeOfficer);
        return "Cost centre changed successfully";
    }
}