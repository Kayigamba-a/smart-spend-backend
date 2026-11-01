package auca.example.smartspend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import auca.example.smartspend.model.*;

public interface SpendRequestRepository extends JpaRepository<SpendRequest, UUID>{

    List<SpendRequest> findByStatusOrderByRequestDateAsc(SpendStatus status);

}