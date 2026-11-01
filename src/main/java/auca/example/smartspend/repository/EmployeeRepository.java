package auca.example.smartspend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import auca.example.smartspend.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

}