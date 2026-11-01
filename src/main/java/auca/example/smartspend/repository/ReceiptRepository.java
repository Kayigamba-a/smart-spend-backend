package auca.example.smartspend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import auca.example.smartspend.model.*;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID>{

    @Query("SELECT r FROM Receipt r WHERE r.spendRequest.employee.staffNumber = :staffNumber ORDER BY r.recordedDate DESC")
    List<Receipt> findByEmployeeStaffNumber(@Param("staffNumber") String staffNumber);

}