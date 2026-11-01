package auca.example.smartspend.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import auca.example.smartspend.model.*;

public interface CostCentreRepository extends JpaRepository<CostCentre, UUID>{

}