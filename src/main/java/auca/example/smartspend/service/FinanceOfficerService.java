
package auca.example.smartspend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.FinanceOfficer;
import auca.example.smartspend.repository.FinanceOfficerRepository;

@Service
public class FinanceOfficerService {

    private final FinanceOfficerRepository financeOfficerRepository;

    public FinanceOfficerService(FinanceOfficerRepository repository) {
        this.financeOfficerRepository = repository;
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
}
