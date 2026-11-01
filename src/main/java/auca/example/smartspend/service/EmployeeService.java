package auca.example.smartspend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.example.smartspend.model.Employee;
import auca.example.smartspend.model.FinanceOfficer;
import auca.example.smartspend.repository.EmployeeRepository;
import auca.example.smartspend.repository.FinanceOfficerRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final FinanceOfficerRepository financeOfficerRepository;

    public EmployeeService(
            EmployeeRepository repository,
            FinanceOfficerRepository financeOfficerRepository) {
        this.employeeRepository = repository;
        this.financeOfficerRepository = financeOfficerRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(UUID id, Employee employee) {

        Employee existingEmployee = getEmployeeById(id);

        if (existingEmployee != null) {

            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setStaffNumber(employee.getStaffNumber());
            existingEmployee.setEmail(employee.getEmail());

            return employeeRepository.save(existingEmployee);
        }

        return null;
    }

    public boolean deleteEmployee(UUID id) {

        Employee employee = getEmployeeById(id);

        if (employee != null) {
            employeeRepository.delete(employee);
            return true;
        }

        return false;
    }

    public List<Employee> getEmployeesByLastName(String lastName) {

        return employeeRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public List<Employee> getEmployeesOfFinanceOfficer(UUID financeOfficerId) {

        FinanceOfficer financeOfficer =
            financeOfficerRepository.findById(financeOfficerId).orElse(null);

        if (financeOfficer == null) {
            return null;
        }

        return employeeRepository.findByFinanceOfficerId(financeOfficerId);
    }
}