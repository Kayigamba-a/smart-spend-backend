package auca.example.smartspend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import auca.example.smartspend.model.*;

public interface SpendRequestRepository extends JpaRepository<SpendRequest, UUID>{

    List<SpendRequest> findByStatusOrderByRequestDateAsc(SpendStatus status);

    List<SpendRequest> findByRequestDateBetweenOrderByRequestDateAsc(LocalDate start, LocalDate end);

    boolean existsByFinanceOfficerAndRequestDateAndStatusNot(FinanceOfficer financeOfficer, LocalDate requestDate, SpendStatus status);

    @Query("SELECT s.status, COUNT(s) FROM SpendRequest s GROUP BY s.status")
    List<Object[]> countGroupedByStatus();

    @Modifying
    @Query("UPDATE SpendRequest s SET s.status = :rejected WHERE s.financeOfficer.id = :financeOfficerId AND s.requestDate = :date AND s.status <> :approved")
    int cancelRequestsForAbsentOfficer(@Param("financeOfficerId") UUID financeOfficerId, @Param("date") LocalDate date, @Param("approved") SpendStatus approved, @Param("rejected") SpendStatus rejected);

}