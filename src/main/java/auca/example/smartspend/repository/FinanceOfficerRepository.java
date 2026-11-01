package auca.example.smartspend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import auca.example.smartspend.model.*;

public interface FinanceOfficerRepository extends JpaRepository<FinanceOfficer, UUID>{

    @Query("SELECT f FROM FinanceOfficer f JOIN f.expenseTypes e WHERE LOWER(e.name) = LOWER(:name)")
    List<FinanceOfficer> findByExpenseTypeName(@Param("name") String name);

    boolean existsByCostCentre(CostCentre costCentre);

}