package auca.example.smartspend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.example.smartspend.model.FinanceOfficer;
import auca.example.smartspend.service.FinanceOfficerService;

@RestController
@RequestMapping("/api/finance-officers")
public class FinanceOfficerController {

    private final FinanceOfficerService financeOfficerService;

    public FinanceOfficerController(FinanceOfficerService service) {
        this.financeOfficerService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FinanceOfficer>> getAllFinanceOfficers() {

        return new ResponseEntity<>(
            financeOfficerService.getAllFinanceOfficers(),
            HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveFinanceOfficer(
            @RequestBody FinanceOfficer financeOfficer) {

        financeOfficerService.saveFinanceOfficer(financeOfficer);

        return new ResponseEntity<>(
            "Finance officer saved successfully",
            HttpStatus.CREATED
        );
    }

    @GetMapping("/by-expense-type")
    public ResponseEntity<List<FinanceOfficer>> getFinanceOfficersByExpenseType(
            @RequestParam String name) {

        List<FinanceOfficer> financeOfficers =
            financeOfficerService.getFinanceOfficersByExpenseType(name);

        return new ResponseEntity<>(financeOfficers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFinanceOfficerById(@PathVariable UUID id) {

        FinanceOfficer financeOfficer =
            financeOfficerService.getFinanceOfficerById(id);

        if (financeOfficer != null) {
            return new ResponseEntity<>(financeOfficer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                "The finance officer with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFinanceOfficer(
            @PathVariable UUID id,
            @RequestBody FinanceOfficer financeOfficer) {

        FinanceOfficer updatedFinanceOfficer =
            financeOfficerService.updateFinanceOfficer(id, financeOfficer);

        if (updatedFinanceOfficer != null) {
            return new ResponseEntity<>(
                "Finance officer updated successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The finance officer with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFinanceOfficer(@PathVariable UUID id) {

        boolean deleted = financeOfficerService.deleteFinanceOfficer(id);

        if (deleted) {
            return new ResponseEntity<>(
                "Finance officer deleted successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The finance officer with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }
}