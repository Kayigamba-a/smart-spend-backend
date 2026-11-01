package auca.example.smartspend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import auca.example.smartspend.model.*;

public interface SpendRequestRepository extends JpaRepository<SpendRequest, UUID>{

    List<SpendRequest> findByStatusOrderByRequestDateAsc(SpendStatus status);

    List<SpendRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    boolean existsByFinanceOfficerAndRequestDateAndStatusNot(FinanceOfficer financeOfficer, LocalDate requestDate, SpendStatus status);

    @Query("SELECT s.status, COUNT(s) FROM SpendRequest s GROUP BY s.status")
    List<Object[]> countGroupedByStatus();

}