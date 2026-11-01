package auca.example.smartspend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.example.smartspend.model.ExpenseType;
import auca.example.smartspend.service.ExpenseTypeService;

@RestController
@RequestMapping("/api/expense-types")
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;

    public ExpenseTypeController(ExpenseTypeService service) {
        this.expenseTypeService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseType>> getAllExpenseTypes() {

        return new ResponseEntity<>(
            expenseTypeService.getAllExpenseTypes(),
            HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveExpenseType(@RequestBody ExpenseType expenseType) {

        expenseTypeService.saveExpenseType(expenseType);

        return new ResponseEntity<>(
            "Expense type saved successfully",
            HttpStatus.CREATED
        );
    }

    @GetMapping("/unused")
    public ResponseEntity<List<ExpenseType>> getUnusedExpenseTypes() {

        List<ExpenseType> expenseTypes = expenseTypeService.getUnusedExpenseTypes();

        return new ResponseEntity<>(expenseTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseTypeById(@PathVariable UUID id) {

        ExpenseType expenseType = expenseTypeService.getExpenseTypeById(id);

        if (expenseType != null) {
            return new ResponseEntity<>(expenseType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                "The expense type with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExpenseType(
            @PathVariable UUID id,
            @RequestBody ExpenseType expenseType) {

        ExpenseType updatedExpenseType =
            expenseTypeService.updateExpenseType(id, expenseType);

        if (updatedExpenseType != null) {
            return new ResponseEntity<>(
                "Expense type updated successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The expense type with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpenseType(@PathVariable UUID id) {

        boolean deleted = expenseTypeService.deleteExpenseType(id);

        if (deleted) {
            return new ResponseEntity<>(
                "Expense type deleted successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The expense type with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }
}
