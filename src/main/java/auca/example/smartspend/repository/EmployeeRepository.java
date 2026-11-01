package auca.example.smartspend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import auca.example.smartspend.model.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT s.employee FROM SpendRequest s WHERE s.financeOfficer.id = :financeOfficerId")
    List<Employee> findByFinanceOfficerId(@Param("financeOfficerId") UUID financeOfficerId);

}