package auca.example.smartspend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import auca.example.smartspend.model.*;

public interface CostCentreRepository extends JpaRepository<CostCentre, UUID>{

    @Query("SELECT c FROM CostCentre c WHERE NOT EXISTS (SELECT f FROM FinanceOfficer f WHERE f.costCentre = c) ORDER BY c.centreNumber")
    List<CostCentre> findFreeCostCentres();

    CostCentre findByCentreNumber(int centreNumber);

}