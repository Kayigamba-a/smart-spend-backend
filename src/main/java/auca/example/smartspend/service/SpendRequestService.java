
package auca.example.smartspend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.SpendRequest;
import auca.example.smartspend.repository.SpendRequestRepository;

@Service
public class SpendRequestService {

    private final SpendRequestRepository spendRequestRepository;

    public SpendRequestService(SpendRequestRepository repository) {
        this.spendRequestRepository = repository;
    }

    public List<SpendRequest> getAllSpendRequests() {
        return spendRequestRepository.findAll();
    }

    public SpendRequest saveSpendRequest(SpendRequest spendRequest) {
        return spendRequestRepository.save(spendRequest);
    }

    public SpendRequest getSpendRequestById(UUID id) {
        return spendRequestRepository.findById(id).orElse(null);
    }

    public SpendRequest updateSpendRequest(UUID id, SpendRequest spendRequest) {

        SpendRequest existingSpendRequest = getSpendRequestById(id);

        if (existingSpendRequest != null) {

            existingSpendRequest.setRequestDate(spendRequest.getRequestDate());
            existingSpendRequest.setStatus(spendRequest.getStatus());
            existingSpendRequest.setPurpose(spendRequest.getPurpose());
            existingSpendRequest.setRequestedAmount(spendRequest.getRequestedAmount());
            existingSpendRequest.setEmployee(spendRequest.getEmployee());
            existingSpendRequest.setFinanceOfficer(spendRequest.getFinanceOfficer());

            return spendRequestRepository.save(existingSpendRequest);
        }

        return null;
    }

    public boolean deleteSpendRequest(UUID id) {

        SpendRequest spendRequest = getSpendRequestById(id);

        if (spendRequest != null) {
            spendRequestRepository.delete(spendRequest);
            return true;
        }

        return false;
    }
}
