package auca.example.smartspend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auca.example.smartspend.model.SpendRequest;
import auca.example.smartspend.model.SpendStatus;
import auca.example.smartspend.repository.ReceiptRepository;
import auca.example.smartspend.repository.SpendRequestRepository;

@Service
public class SpendRequestService {

    private final SpendRequestRepository spendRequestRepository;
    private final ReceiptRepository receiptRepository;

    public SpendRequestService(
            SpendRequestRepository repository,
            ReceiptRepository receiptRepository) {
        this.spendRequestRepository = repository;
        this.receiptRepository = receiptRepository;
    }

    public List<SpendRequest> getAllSpendRequests() {
        return spendRequestRepository.findAll();
    }

    public String saveSpendRequest(SpendRequest spendRequest) {

        if (spendRequest.getRequestDate().isBefore(LocalDate.now())) {
            return "Spend request date cannot be in the past";
        }

        boolean exists = spendRequestRepository.existsByFinanceOfficerAndRequestDateAndStatusNot(
            spendRequest.getFinanceOfficer(),
            spendRequest.getRequestDate(),
            SpendStatus.REJECTED
        );

        if (exists) {
            return "Finance officer already reviews a request on that date";
        }

        spendRequest.setStatus(SpendStatus.SUBMITTED);
        spendRequestRepository.save(spendRequest);
        return "Spend request saved successfully";
    }

    public SpendRequest getSpendRequestById(UUID id) {
        return spendRequestRepository.findById(id).orElse(null);
    }

    public SpendRequest updateSpendRequest(UUID id, SpendRequest spendRequest) {

        SpendRequest existingSpendRequest = getSpendRequestById(id);

        if (existingSpendRequest != null) {

            existingSpendRequest.setRequestDate(spendRequest.getRequestDate());
            existingSpendRequest.setPurpose(spendRequest.getPurpose());
            existingSpendRequest.setRequestedAmount(spendRequest.getRequestedAmount());
            existingSpendRequest.setEmployee(spendRequest.getEmployee());
            existingSpendRequest.setFinanceOfficer(spendRequest.getFinanceOfficer());

            return spendRequestRepository.save(existingSpendRequest);
        }

        return null;
    }

    public String deleteSpendRequest(UUID id) {

        SpendRequest spendRequest = getSpendRequestById(id);

        if (spendRequest == null) {
            return "The spend request with that id does not exist";
        }

        if (receiptRepository.existsBySpendRequest(spendRequest)) {
            return "That spend request has receipts, delete them first";
        }

        spendRequestRepository.delete(spendRequest);
        return "Spend request deleted successfully";
    }

    public List<SpendRequest> getSpendRequestsByStatus(SpendStatus status) {

        return spendRequestRepository.findByStatusOrderByRequestDateAsc(status);
    }

    public List<SpendRequest> getSpendRequestsBetween(LocalDate start, LocalDate end) {

        return spendRequestRepository.findByRequestDateBetweenOrderByRequestDateAsc(start, end);
    }

    public String updateSpendRequestStatus(UUID id, SpendStatus status) {

        SpendRequest existingSpendRequest = getSpendRequestById(id);

        if (existingSpendRequest == null) {
            return "The spend request with that id does not exist";
        }

        SpendStatus current = existingSpendRequest.getStatus();
        boolean allowed = false;

        if (current == SpendStatus.SUBMITTED && (status == SpendStatus.REVIEWED || status == SpendStatus.REJECTED)) {
            allowed = true;
        }

        if (current == SpendStatus.REVIEWED && (status == SpendStatus.APPROVED || status == SpendStatus.REJECTED)) {
            allowed = true;
        }

        if (allowed == false) {
            return "Cannot change status from " + current + " to " + status;
        }

        existingSpendRequest.setStatus(status);
        spendRequestRepository.save(existingSpendRequest);
        return "Spend request status updated successfully";
    }

    public List<Object[]> getStatusStatistics() {

        return spendRequestRepository.countGroupedByStatus();
    }

    @Transactional
    public String cancelDay(UUID financeOfficerId, LocalDate date) {

        int changed = spendRequestRepository.cancelRequestsForAbsentOfficer(
            financeOfficerId,
            date,
            SpendStatus.APPROVED,
            SpendStatus.REJECTED
        );

        return changed + " spend requests cancelled";
    }
}