package auca.example.smartspend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.example.smartspend.model.Receipt;
import auca.example.smartspend.service.ReceiptService;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService service) {
        this.receiptService = service;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveReceipt(
            @RequestParam UUID spendRequestId,
            @RequestBody Receipt receipt) {

        String message = receiptService.saveReceipt(receipt, spendRequestId);

        if (message.startsWith("The spend request")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        if (message.startsWith("Receipts are only")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{staffNumber}")
    public ResponseEntity<List<Receipt>> getReceiptsByEmployee(
            @PathVariable String staffNumber) {

        List<Receipt> receipts = receiptService.getReceiptsByEmployee(staffNumber);

        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }
}