package auca.example.smartspend.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.Receipt;
import auca.example.smartspend.model.SpendRequest;
import auca.example.smartspend.model.SpendStatus;
import auca.example.smartspend.repository.ReceiptRepository;
import auca.example.smartspend.repository.SpendRequestRepository;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final SpendRequestRepository spendRequestRepository;

    public ReceiptService(
            ReceiptRepository receiptRepository,
            SpendRequestRepository spendRequestRepository) {
        this.receiptRepository = receiptRepository;
        this.spendRequestRepository = spendRequestRepository;
    }

    public String saveReceipt(Receipt receipt, UUID spendRequestId) {

        SpendRequest spendRequest =
            spendRequestRepository.findById(spendRequestId).orElse(null);

        if (spendRequest == null) {
            return "The spend request with that id does not exist";
        }

        if (spendRequest.getStatus() != SpendStatus.APPROVED) {
            return "Receipts are only for APPROVED spend requests";
        }

        receipt.setRecordedDate(LocalDate.now());
        receipt.setSpendRequest(spendRequest);
        receiptRepository.save(receipt);
        return "Receipt saved successfully";
    }
}