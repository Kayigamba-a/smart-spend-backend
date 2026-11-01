package auca.example.smartspend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.example.smartspend.model.Employee;
import auca.example.smartspend.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<>(
            employeeService.getAllEmployees(),
            HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return new ResponseEntity<>(
            "Employee saved successfully",
            HttpStatus.CREATED
        );
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Employee>> getEmployeesByLastName(
            @RequestParam String lastName) {

        List<Employee> employees = employeeService.getEmployeesByLastName(lastName);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {

        Employee employee = employeeService.getEmployeeById(id);

        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                "The employee with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable UUID id,
            @RequestBody Employee employee) {

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        if (updatedEmployee != null) {
            return new ResponseEntity<>(
                "Employee updated successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The employee with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable UUID id) {

        boolean deleted = employeeService.deleteEmployee(id);

        if (deleted) {
            return new ResponseEntity<>(
                "Employee deleted successfully",
                HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                "The employee with that id does not exist",
                HttpStatus.NOT_FOUND
            );
        }
    }
}