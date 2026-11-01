package auca.example.smartspend.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.example.smartspend.model.SpendRequest;
import auca.example.smartspend.model.SpendStatus;
import auca.example.smartspend.service.SpendRequestService;

@RestController
@RequestMapping("/api/spend-requests")
public class SpendRequestController {

    private final SpendRequestService spendRequestService;

    public SpendRequestController(SpendRequestService service) {
        this.spendRequestService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SpendRequest>> getAllSpendRequests() {

        return new ResponseEntity<>(
            spendRequestService.getAllSpendRequests(),
            HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveSpendRequest(
            @RequestBody SpendRequest spendRequest) {

        spendRequestService.saveSpendRequest(spendRequest);

        return new ResponseEntity<>(
            "Spend request saved successfully",
            HttpStatus.CREATED
        );
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<SpendRequest>> getSpendRequestsByStatus(
            @RequestParam SpendStatus status) {

        List<SpendRequest> spendRequests =
            spendRequestService.getSpendRequestsByStatus(status);

        return new ResponseEntity<>(spendRequests, HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<SpendRequest>> getSpendRequestsBetween(
            @RequestParam String start,
            @RequestParam String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        List<SpendRequest> spendRequests =
            spendRequestService.getSpendRequestsBetween(startDate, endDate);

        return new ResponseEntity<>(spendRequests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpendRequestById(@PathVariable UUID id) {

        SpendRequest spendRequest = spendRequestService.getSpendRequestById(id);

        if (spendRequest != null) {
            return new ResponseEntity<>(spendRequest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                "The spend request with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSpendRequest(
            @PathVariable UUID id,
            @RequestBody SpendRequest spendRequest) {

        SpendRequest updatedSpendRequest =
            spendRequestService.updateSpendRequest(id, spendRequest);

        if (updatedSpendRequest != null) {
            return new ResponseEntity<>(
                "Spend request updated successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The spend request with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpendRequest(@PathVariable UUID id) {

        boolean deleted = spendRequestService.deleteSpendRequest(id);

        if (deleted) {
            return new ResponseEntity<>(
                "Spend request deleted successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The spend request with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }
}