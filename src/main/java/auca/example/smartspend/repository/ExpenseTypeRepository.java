package auca.example.smartspend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import auca.example.smartspend.model.*;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, UUID>{

    @Query("SELECT e FROM ExpenseType e WHERE e.financeOfficers IS EMPTY")
    List<ExpenseType> findUnusedExpenseTypes();

}